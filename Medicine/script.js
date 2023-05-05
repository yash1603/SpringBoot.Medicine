

//validation
function oncheckDate() {
  let mfgdate = document.getElementById('mfgDate').value;
  let expiryDate = document.getElementById('expiryDate').value;


  if (mfgdate != "" && expiryDate != "") {


    if (expiryDate < mfgdate) {
      return alert("expiryDate is gretear than mfgdate ")

    }
  }

}

//getallMedicine
function getallMedicine() {
  fetch('http://localhost:8080/medicine/filter/1')
    .then((data) => {
      return data.json();
    })
    .then((objectData) => {

      let tableData = "";
      objectData.map((values) => {
        tableData += `<tr>
          <td>${values.id}</td>
          <td>${values.drugBrandName}</td>
          <td>${values.drugGenericName}</td>
          <td>${values.form.form}</td>
          <td>${values.manufacturerCompany}</td>
          <td>${values.packaging}</td>
          <td>${values.mfgDate}</td>
          <td>${values.expiryDate}</td>
          <td>${values.mrp}</td> 
          <td>${values.quantity}</td>
          <td>${values.otherDetails}</td>
          <td>${values.isActive}</td>
         <td><button onclick="onEdit(this)" class="btn btn-secondary">Edit</button>
         <button onclick="onDelete(this)"  class="btn btn-danger">Delete</button>
         </tr>
          `;

      });
      document.getElementById("table_body").innerHTML = tableData;
    });

}
//getMedicineNotActive
function getMedicineNotActive() {

  fetch('http://localhost:8080/medicine/filter/false')
    .then((data) => {
      return data.json();
    })
    .then((objectData) => {

      let tableData = "";
      objectData.map((values) => {
        tableData += `<tr>
          <td>${values.id}</td>
          <td>${values.drugBrandName}</td>
          <td>${values.drugGenericName}</td>
          <td>${values.form.form}</td>
          <td>${values.manufacturerCompany}</td>
          <td>${values.packaging}</td>
          <td>${values.mfgDate}</td>
          <td>${values.expiryDate}</td>
          <td>${values.mrp}</td> 
          <td>${values.quantity}</td>
          <td>${values.otherDetails}</td>
          <td>${values.isActive}</td>
         <td><button onclick="onEdit(this)" class="btn btn-secondary">Edit</button>
         <button onclick="onDelete(this)"  class="btn btn-danger">Delete</button>
         </tr>
          `;

      });
      document.getElementById("table_body").innerHTML = tableData;
    });
}
//getMedicineActive
function getMedicineActive() {

  fetch('http://localhost:8080/medicine/filter/true')
    .then((data) => {
      return data.json();
    })
    .then((objectData) => {

      let tableData = "";
      objectData.map((values) => {
        tableData += `<tr>
            <td>${values.id}</td>
            <td>${values.drugBrandName}</td>
            <td>${values.drugGenericName}</td>
            <td>${values.form.form}</td>
            <td>${values.manufacturerCompany}</td>
            <td>${values.packaging}</td>
            <td>${values.mfgDate}</td>
            <td>${values.expiryDate}</td>
            <td>${values.mrp}</td> 
            <td>${values.quantity}</td>
            <td>${values.otherDetails}</td>
            <td>${values.isActive}</td>
           <td><button onclick="onEdit(this)" class="btn btn-secondary">Edit</button>
           <button onclick="onDelete(this)"  class="btn btn-danger">Delete</button>
           </tr>
            `;

      });
      document.getElementById("table_body").innerHTML = tableData;
    });

}
//for dropdown
function change() {
  var e = document.getElementById("getData");

  var value = e.options[e.selectedIndex].value;
  //console.log(value);
  if (value == 0) {
    getallMedicine();
  }
  else if (value == 1) {
    getMedicineActive();
  }
  else {
    getMedicineNotActive();
  }


}
//detele medicine
function onDelete(td) {

  if (confirm("are you sure??")) {
    row = td.parentElement.parentElement;
    // console.log(row);
    let id = row.cells[0].textContent;

    fetch('http://localhost:8080/medicine/deleteMedicine/' + id, {
      method: 'DELETE',
      headers: {
        'Content-Type': 'application/json'
      }


    })
      .then((data) => {
        change();
        return data.json();
      })

  }

}

//click on edit button 
function onEdit(td) {
  row = td.parentElement.parentElement;

  let id = row.cells[0].textContent;

  url = 'form.html?id=' + encodeURIComponent(id);

  document.location.href = url;

}
//fill data in form when edit is call
function onMedicineData() {

  const queryString = window.location.search;
  const urlParams = new URLSearchParams(queryString);


  let id = urlParams.get('id');
  fetch('http://localhost:8080/medicine/getByid?id=' + id)
    .then((data) => {
      return data.json();
    })
    .then((objectData) => {


      let drugBrandName = objectData.drugBrandName;
      let drugGenericName = objectData.drugGenericName;
      var form = objectData.form.id;
      let manufacturerCompany = objectData.manufacturerCompany;
      let packaging = objectData.packaging;
      let mfgDate = objectData.mfgDate;
      let expiryDate = objectData.expiryDate;
      let mrp = objectData.mrp;
      let otherDetails = objectData.otherDetails;
      let quantity = objectData.quantity;



        document.getElementById('drugBrandName').value = drugBrandName,
        document.getElementById('drugGenericName').value = drugGenericName,
        document.getElementById('form').value = form,
        
        document.getElementById('manufacturerCompany').value = manufacturerCompany,
        document.getElementById('packaging').value = packaging
        document.getElementById('mfgDate').value = mfgDate,
        document.getElementById('expiryDate').value = expiryDate,
        document.getElementById('mrp').value = mrp,
        document.getElementById('otherDetails').value = otherDetails,
        document.getElementById('quantity').value = quantity

    }
    );

}


// add medicine
function addData() {


  let drugBrandName = document.getElementById('drugBrandName').value;
  let drugGenericName = document.getElementById('drugGenericName').value;
  let quantity = document.getElementById('quantity').value;
  let otherDetails = document.getElementById('otherDetails').value;
  let mrp = document.getElementById('mrp').value;
  let expiryDate = document.getElementById('expiryDate').value;
  let mfgDate = document.getElementById('mfgDate').value;
  let manufacturerCompany = document.getElementById('manufacturerCompany').value;
  let packaging = document.getElementById('packaging').value;
  let form = document.getElementById('form').value;


  jsonObj = {


    "drugBrandName": drugBrandName,
    "drugGenericName": drugGenericName,
    "form": {
      "id": parseInt(form)

    },
    "manufacturerCompany": manufacturerCompany,
    "packaging": packaging,
    "mfgDate": mfgDate,
    "expiryDate": expiryDate,
    "mrp": parseInt(mrp),
    "otherDetails": otherDetails,
    "quantity": parseInt(quantity)

  }

  var check = true;
  if (compareDates() == true) {
    fetch("http://localhost:8080/medicine/addMedicine", {
      method: 'POST',
      headers: {

        'Content-Type': 'application/json'
      },
      body: JSON.stringify(jsonObj)
    })
      .then((response) => {
        if (response.status === 200 || response.status === 201) {
          alert("saved")
          window.location.href = "index.html"
          check = false;
        }
        if (response.status === 500) {
          alert("Internel Server Error")
          check = false;
        }
        return response.json()
      }).then((data) => {
        if (check == true) {
          throw data
        }
      }).catch((data) => {

        let error = "";
        for (const e in data) {
          error += data[e] + "\n"
        }
        alert(error)





      })

  }

}


// update medicine
function onDataUpdate() {

  const queryString = window.location.search;
  const urlParams = new URLSearchParams(queryString);


  let id = urlParams.get('id');
  console.log(id)
  let drugBrandName = document.getElementById('drugBrandName').value;
  let drugGenericName = document.getElementById('drugGenericName').value;
  let form = document.getElementById('form').value;
  let manufacturerCompany = document.getElementById('manufacturerCompany').value;
  let packaging = document.getElementById('packaging').value;
  let mfgDate = document.getElementById('mfgDate').value;
  let expiryDate = document.getElementById('expiryDate').value;
  let mrp = document.getElementById('mrp').value;
  let otherDetails = document.getElementById('otherDetails').value;
  let quantity = document.getElementById('quantity').value;


  let jsonObj = {

    "id": id,
    "drugBrandName": drugBrandName,
    "drugGenericName": drugGenericName,
    "form": {
      "id": parseInt(form)

    },
    "manufacturerCompany": manufacturerCompany,
    "packaging": packaging,
    "mfgDate": mfgDate,
    "expiryDate": expiryDate,
    "mrp": parseInt(mrp),
    "otherDetails": otherDetails,
    "quantity": parseInt(quantity)

  }
  console.log(jsonObj);
  if (compareDates() == true) {

    fetch('http://localhost:8080/medicine/editMedicine', {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(jsonObj)


    }).then((response) => {
      if (response.status === 200 || response.status === 201) {
        alert("saved")
        window.location.href = "index.html"
        check = false;
      }
      return response.json()
    }).then((data) => {
      if (check == true) {
        throw data
      }
    }).catch((data) => {
      let error = "";
      for (const e in data) {
        error += data[e] + "\n"
      }
      alert(error)
    })

  }

}

//show the button save or update
function handleAction() {

  const queryString = window.location.search;
  const urlParams = new URLSearchParams(queryString);

  let id = urlParams.get('id');
  fillFormDropdown()
  packagingDropdown();
  // console.log( id)
  if (id == null || id == "") {
    condition = true
    
    document.getElementById("saveBtn").style.display = "block";
    document.getElementById("updateBtn").style.display = "none";
    // console.log(condition)
  } else {
    condition = false
    onMedicineData();

    document.getElementById("saveBtn").style.display = "none";
    document.getElementById("updateBtn").style.display = "block";

    // console.log(condition)
  }


}
//validation
function compareDates() {

 

  // Get the current date
  var today = new Date();

  // Get the mfgDate input value
  var mfgDateInput = document.getElementById("mfgDate").value;
  var mfgDate = new Date(mfgDateInput);

  // Get the expiryDate input value
  var expiryDateInput = document.getElementById("expiryDate").value;
  var expiryDate = new Date(expiryDateInput);

  if (mfgDate == "Invalid Date" || expiryDate == "Invalid Date") {
    alert("must fill the dates")
    return false;
  }

  // Check if mfgDate is before today
  if (mfgDate > today) {
    alert("The manufacture date cannot be in the future.");
    return false;
  }

  // Check if expiryDate is after today
  if (expiryDate < today) {
    alert("The product has already expired.");
    return false;
  }

  // Check if expiryDate is after mfgDate
  if (expiryDate < mfgDate) {
    alert("The expiry date cannot be before the manufacture date.");
    return false;
  }

  // All checks passed, return true
  return true;



}




//dropdown for form 
function fillFormDropdown() {
  // Fetch data from server
  fetch('http://localhost:8080/medicine/allForm')
    .then((response) => { return response.json() })
    .then((data) => {

      // Get reference to the select element
      const dropdown = document.getElementById('form');
      // Loop through the data and create an option element for each item
      data.forEach((item) => {
        const option = document.createElement('option');
        option.value = item.id;
        option.text = item.form;
        // if (option.value == 3) {
        //   optionElement.selected = true;
        // }
        // Append option to select element
        dropdown.appendChild(option);

      });

    })
    .catch((error) => console.error(error));
}
// //get Packege dropdown 
function packagingDropdown() {
 
  // Fetch data from server
  fetch('http://localhost:8080/medicine/getPackege')
    .then((response) => { return response.json() })
    .then((data) => {

      // Get reference to the select element
      const dropdown = document.getElementById('packaging');
      // Loop through the data and create an option element for each item
      data.forEach((item) => {
        const option = document.createElement('option');
        option.value = item;
        option.text = item;
        // if (option.value == 3) {
        //   optionElement.selected = true;
        // }
        // Append option to select element
        dropdown.appendChild(option);

      });

    })
    .catch((error) => console.error(error));
}














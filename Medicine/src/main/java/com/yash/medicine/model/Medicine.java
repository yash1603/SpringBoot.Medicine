package com.yash.medicine.model;

import java.util.Date;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.yash.medicine.model.genrator.MyGenrator;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "medicine")
public class Medicine {

	@Id

	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "medicine_id")
	@GenericGenerator(name = "medicine_id", strategy = "com.yash.medicine.model.genrator.MyGenrator", parameters = {
			@Parameter(name = MyGenrator.INCREMENT_PARAM, value = "1"),
			@Parameter(name = MyGenrator.VALUE_PREFIX_PARAMETER, value = "DN"),
			@Parameter(name = MyGenrator.NUMBER_FORMAT_PARAMETER, value = "%05d") })
	@Column(name = "medicine_id")
	private String id;

	@NotBlank(message = "Drug brand name is required")
	@Column(name = "Drug_brand_name", nullable = false)
	private String drugBrandName;

	@Column(name = "drug_generic_name")
	private String drugGenericName;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "form_id", nullable = false)

	private DrugFormTable form;

	@NotBlank(message = "Manufacturer company is required")
	@Size(max = 255, message = "Manufacturer company must be less than or equal to 255 characters")
	@Column(name = "manufacturer_company", nullable = false)
	private String manufacturerCompany;

	@NotNull(message = "Packaging is required")
	@Enumerated(EnumType.STRING)
	@Column(name = "packaging", nullable = false)
	private Packaging packaging;

	@NotNull(message = "Manufacturing date is required")
	@PastOrPresent(message = "Manufacturing date must be in the past or present")
	@Column(name = "mfg_date", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date mfgDate;

	@NotNull(message = "Expiry date is required")
	@Future(message = "Expiry date must be in the future...")
	@Column(name = "expiry_date", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date expiryDate;

	@NotNull(message = "MRP is required")
	@PositiveOrZero(message = "MRP must be a positive or zero value")
	@Column(name = "mrp", nullable = false)
	private Double mrp;

	@Size(max = 3000, message = "Other details must be less than or equal to 3000 characters")
	@Column(name = "other_details", length = 3000)
	private String otherDetails;

	@NotNull(message = "Quantity is required")
	@Min(value = 0, message = "Quantity must be a positive value")
	@Column(name = "quantity", nullable = false)
	private Integer quantity;

	@Column(name = "is_active", nullable = false)
	private Boolean isActive;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDrugBrandName() {
		return drugBrandName;
	}

	public void setDrugBrandName(String drugBrandName) {
		this.drugBrandName = drugBrandName;
	}

	public String getDrugGenericName() {
		return drugGenericName;
	}

	public void setDrugGenericName(String drugGenericName) {
		this.drugGenericName = drugGenericName;
	}

	public DrugFormTable getForm() {
		return form;
	}

	public void setForm(DrugFormTable form) {
		this.form = form;
	}

	public String getManufacturerCompany() {
		return manufacturerCompany;
	}

	public void setManufacturerCompany(String manufacturerCompany) {
		this.manufacturerCompany = manufacturerCompany;
	}

	public Packaging getPackaging() {
		return packaging;
	}

	public void setPackaging(Packaging packaging) {
		this.packaging = packaging;
	}

	public Date getMfgDate() {
		return mfgDate;
	}

	public void setMfgDate(Date mfgDate) {
		this.mfgDate = mfgDate;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Double getMrp() {
		return mrp;
	}

	public void setMrp(Double mrp) {
		this.mrp = mrp;
	}

	public String getOtherDetails() {
		return otherDetails;
	}

	public void setOtherDetails(String otherDetails) {
		this.otherDetails = otherDetails;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Medicine(String id, @NotBlank(message = "Drug btand name is required") String drugBrandName,
			String drugGenericName, @NotBlank(message = "Drug form is required") DrugFormTable form,
			@NotBlank(message = "Manufacturer company is required") @Size(max = 255, message = "Manufacturer company must be less than or equal to 255 characters") String manufacturerCompany,
			@NotNull(message = "Packaging is required") Packaging packaging,
			@NotNull(message = "Manufacturing date is required") @PastOrPresent(message = "Manufacturing date must be in the past or present") Date mfgDate,
			@NotNull(message = "Expiry date is required") @Future(message = "Expiry date must be in the future") Date expiryDate,
			@NotNull(message = "MRP is required") @PositiveOrZero(message = "MRP must be a positive or zero value") Double mrp,
			@Size(max = 3000, message = "Other details must be less than or equal to 3000 characters") String otherDetails,
			@NotNull(message = "Quantity is required") @Min(value = 0, message = "Quantity must be a positive value") Integer quantity,
			Boolean isActive) {
		super();
		this.id = id;
		this.drugBrandName = drugBrandName;
		this.drugGenericName = drugGenericName;
		this.form = form;
		this.manufacturerCompany = manufacturerCompany;
		this.packaging = packaging;
		this.mfgDate = mfgDate;
		this.expiryDate = expiryDate;
		this.mrp = mrp;
		this.otherDetails = otherDetails;
		this.quantity = quantity;
		this.isActive = isActive;
	}

	public Medicine() {
		super();
	}

}

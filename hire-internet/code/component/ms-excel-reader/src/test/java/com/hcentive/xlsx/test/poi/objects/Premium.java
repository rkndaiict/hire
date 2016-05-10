/**
 * 
 */
package com.hcentive.xlsx.test.poi.objects;

import com.hcentive.xlsx.annotations.Formatted;
import com.hcentive.xlsx.annotations.WBColumnRef;
import com.hcentive.xlsx.annotations.Workbook;

/**
 * @author prashant
 *
 */
@Workbook(header="Region,Smoker,Distribution,Age,Medical Plan,RX Plan,Gender,Premium",sheetIndex=1,sheetName="Premium")
public class Premium {

	@WBColumnRef(value="B")
	private String smoker = null;
	
	@WBColumnRef(value="A")
	private String region = null;
	
	@WBColumnRef(value="C")
	private String distrinution = null;
	
	@WBColumnRef(value="D")
	private Integer age ;
	
	@WBColumnRef(value="E")
	private String medicalPlan = null;
	
	@WBColumnRef(value="F")
	private String rxPlan = null;
	
	@WBColumnRef(value="G")
	private String gender = null;
	
	@Formatted(value="$,#") @WBColumnRef(value="H")
	private Double premium ;

	public String getSmoker() {
		return smoker;
	}

	public void setSmoker(String smoker) {
		this.smoker = smoker;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getDistrinution() {
		return distrinution;
	}

	public void setDistrinution(String distrinution) {
		this.distrinution = distrinution;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getMedicalPlan() {
		return medicalPlan;
	}

	public void setMedicalPlan(String medicalPlan) {
		this.medicalPlan = medicalPlan;
	}

	public String getRxPlan() {
		return rxPlan;
	}

	public void setRxPlan(String rxPlan) {
		this.rxPlan = rxPlan;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Double getPremium() {
		return premium;
	}

	public void setPremium(Double premium) {
		this.premium = premium;
	}

	@Override
	public String toString() {
		return "Premium [age=" + age + ", medicalPlan=" + medicalPlan
				+ ", premium=" + premium + "]";
	}



}

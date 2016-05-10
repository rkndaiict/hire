/**
 * 
 */
package com.hcentive.xlsx.test.poi.objects;

import com.hcentive.xlsx.annotations.WBColumnRef;
import com.hcentive.xlsx.annotations.Workbook;

/**
 * @author prashant
 *
 */
@Workbook(header="Region,Smoker,Distribution,Age,Medical Plan,RX Plan,Gender,Rider,Premium",sheetIndex=2,sheetName="Rider")
public class Rider {

	@WBColumnRef(value="B")
	private String smoker = null;
	
	@WBColumnRef(value="A")
	private String region = null;
	
	@WBColumnRef(value="C")
	private String distribution = null;
	
	@WBColumnRef(value="D")
	private String age ;
	
	@WBColumnRef(value="E")
	private String medicalPlan = null;
	
	@WBColumnRef(value="F")
	private String rxPlan = null;
	
	@WBColumnRef(value="G")
	private String gender = null;
	
	@WBColumnRef(value="H")
	private String rider = null;
	
	@WBColumnRef(value="I")
	private String premium ;

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

	public String getDistribution() {
		return distribution;
	}

	public void setDistribution(String distrinution) {
		this.distribution = distrinution;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
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

	public String getPremium() {
		return premium;
	}

	public void setPremium(String premium) {
		this.premium = premium;
	}

	public String getRider() {
		return rider;
	}

	public void setRider(String rider) {
		this.rider = rider;
	}


	@Override
	public String toString() {
		return "Riders [Region=" + region + ", smoker=" + smoker
				+ ", distribution =" + distribution + ",age=" + age +",PremiumRate=" + premium + "]";
	}
	


}

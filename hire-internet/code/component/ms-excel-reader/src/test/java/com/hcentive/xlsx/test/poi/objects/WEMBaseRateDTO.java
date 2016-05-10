package com.hcentive.xlsx.test.poi.objects;

import com.hcentive.xlsx.annotations.WBColumnRef;
import com.hcentive.xlsx.annotations.Workbook;

@Workbook(header="Area,Tobacco,Age,Plan,Plan Code,Metal,Recommendation,Full Rate,Tobacco Rider,Tax 1,Tax 2,Tax 3,Fee 1,Fee 2,Fee 3,Rate less taxes/fees,Effective,Quote Expiration",sheetIndex=0,sheetName="Sheet1")
public class WEMBaseRateDTO {

	@WBColumnRef(value="A")
	private String ratingAreaCode;
	
	@WBColumnRef(value="B")
	private String tobacco;
	
	@WBColumnRef(value="C")
	private String age;
	
	@WBColumnRef(value="D")
	private String plan;
	
	@WBColumnRef(value="E")
	private String planCode;
	
	@WBColumnRef(value="F")
	private String metal;
	
	@WBColumnRef(value="G")
	private String recommendation;
	
	@WBColumnRef(value="H")
	private String fullRate;
	
	@WBColumnRef(value="I")
	private String tabaccoRider;
	
	@WBColumnRef(value="J")
	private String tax1;
	
	@WBColumnRef(value="K")
	private String tax2;
	
	@WBColumnRef(value="L")
	private String tax3;
	
	@WBColumnRef(value="M")
	private String fee1;
	
	@WBColumnRef(value="N")
	private String fee2;
	
	@WBColumnRef(value="O")
	private String fee3;
	
	@WBColumnRef(value="P")
	private String rateLessTaxesFee;
	
	@WBColumnRef(value="Q")
	private String effective;
	
	@WBColumnRef(value="R")
	private String quoteExpiration;
	
	public String getEffective() {
		return effective;
	}
	public void setEffective(String effective) {
		this.effective = effective;
	}
	public String getRatingAreaCode() {
		return ratingAreaCode;
	}
	public void setRatingAreaCode(String ratingAreaCode) {
		this.ratingAreaCode = ratingAreaCode;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getTobacco() {
		return tobacco;
	}
	public void setTobacco(String tobacco) {
		this.tobacco = tobacco;
	}
	public String getPlan() {
		return plan;
	}
	public void setPlan(String plan) {
		this.plan = plan;
	}
	public String getPlanCode() {
		return planCode;
	}
	public void setPlanCode(String planCode) {
		this.planCode = planCode;
	}
	public String getMetal() {
		return metal;
	}
	public void setMetal(String metal) {
		this.metal = metal;
	}
	public String getRecommendation() {
		return recommendation;
	}
	public void setRecommendation(String recommendation) {
		this.recommendation = recommendation;
	}
	public String getFullRate() {
		return fullRate;
	}
	public void setFullRate(String fullRate) {
		this.fullRate = fullRate;
	}
	public String getTabaccoRider() {
		return tabaccoRider;
	}
	public void setTabaccoRider(String tabaccoRider) {
		this.tabaccoRider = tabaccoRider;
	}
	public String getTax1() {
		return tax1;
	}
	public void setTax1(String tax1) {
		this.tax1 = tax1;
	}
	public String getTax2() {
		return tax2;
	}
	public void setTax2(String tax2) {
		this.tax2 = tax2;
	}
	public String getTax3() {
		return tax3;
	}
	public void setTax3(String tax3) {
		this.tax3 = tax3;
	}
	public String getFee1() {
		return fee1;
	}
	public void setFee1(String fee1) {
		this.fee1 = fee1;
	}
	public String getFee2() {
		return fee2;
	}
	public void setFee2(String fee2) {
		this.fee2 = fee2;
	}
	public String getFee3() {
		return fee3;
	}
	public void setFee3(String fee3) {
		this.fee3 = fee3;
	}
	public String getRateLessTaxesFee() {
		return rateLessTaxesFee;
	}
	public void setRateLessTaxesFee(String rateLessTaxesFee) {
		this.rateLessTaxesFee = rateLessTaxesFee;
	}
	public String getQuoteExpiration() {
		return quoteExpiration;
	}
	public void setQuoteExpiration(String quoteExpiration) {
		this.quoteExpiration = quoteExpiration;
	}

}
	
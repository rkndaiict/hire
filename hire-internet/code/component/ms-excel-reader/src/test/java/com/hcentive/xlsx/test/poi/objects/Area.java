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
@Workbook(header="Zip,Region,County,Description",sheetIndex=0,sheetName="Areas")
public class Area {

	@WBColumnRef(value="A")
	private String zip = null;
	
	@WBColumnRef(value="B")
	private String region = null;
	
	@WBColumnRef(value="C")
	private String county = null;
	
	@WBColumnRef(value="D")
	private String description = null;

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return "Areas [zip=" + zip + ", region=" + region
				+ ", county =" + county + ", description=" + description +"]";
	}
	
	

}

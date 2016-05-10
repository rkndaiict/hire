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
@Workbook(header="Name,ISBN,Price",sheetIndex=1,sheetName="Publisher Info")
public class PublisherInfo {

	@WBColumnRef(value="A")
	private String name;
	
	@WBColumnRef(value="B")
	private String regNo;
	
	@WBColumnRef(value="C")
	private String contactNo;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegNo() {
		return regNo;
	}

	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
}

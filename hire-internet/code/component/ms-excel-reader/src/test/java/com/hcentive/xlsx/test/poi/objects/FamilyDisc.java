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
@Workbook(header="Region,Product,Size,Type,Factor",sheetIndex=3,sheetName="Family Disc")
public class FamilyDisc {

	@WBColumnRef(value="A")
	private String region = null;
	
	@WBColumnRef(value="B")
	private String product = null;
	
	@WBColumnRef(value="C")
	private String size ;
	
	@WBColumnRef(value="D")
	private String type = null;
	
	@WBColumnRef(value="E")
	private String factor;
	

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFactor() {
		return factor;
	}

	public void setFactor(String factor) {
		this.factor = factor;
	}
	

	@Override
	public String toString() {
		return "Family discount [region=" + region + ", product=" + product
				+ ", size =" + size + ", type=" + type +",Factor=" + factor +"]";
	}
	

	
}

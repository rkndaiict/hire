package com.hcentive.pdf;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hcentive.pdf.domain.PDFCreationInfo;
import com.hcentive.pdf.service.PDFService;
import com.hcentive.utils.error.BusinessException;

/**
 * @author Shalini Gupta
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:META-INF/spring/testApplicationContext.xml" })
public class PDFUtilityTest {

	@Autowired
	PDFService pdfService;

	@Test
	public void testCreatePDF() throws BusinessException {

		// Give the location of testTemplate on your machine to run this testCase
		String samplePDFTemplateLocation = "C:\\hcentive\\code\\PHIX\\5-20-13\\config\\employee\\test\\testTemplate.pdf";

		Map<String, Object> values = new HashMap<String, Object>();
		values.put("firstName", "Shalini");
		values.put("lastName", "Gupta");

		PDFCreationInfo pdfCreationInfo = new PDFCreationInfo();
		pdfCreationInfo.setPdfTemplatesLocation(samplePDFTemplateLocation);
		pdfCreationInfo.setPdfValuesMap(values);

		byte[] outputPDF = null;

		try {
			outputPDF = pdfService.createPDFUsingTemplate(pdfCreationInfo);
			generatePDF(outputPDF);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void generatePDF(byte[] outputPDF) {

		File file = new File("testResult.pdf");
		FileOutputStream fop = null;
		try {

			fop = new FileOutputStream(file);
			// if file doesn't exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			fop.write(outputPDF);
			fop.flush();
			fop.close();

			System.out.println("Done");

		} catch (IOException e) {
			System.out.println("Error creating/reading the file from disk");
		} finally{
			IOUtils.closeQuietly(fop);
		}
	}

}

package com.hire.pdf.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcentive.common.io.BufferedContent;
import com.hcentive.utils.error.BusinessException;
import com.hcentive.utils.error.Error;
import com.hcentive.utils.error.ErrorCode;
import com.hire.pdf.service.HtmlToPdfPreProcessor;
import com.hire.pdf.service.HtmlToPdfService;
import com.hire.pdf.util.PdfUtils;
import com.lowagie.text.pdf.AcroFields;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;

@Service
public class HtmlToPdfServiceImpl implements HtmlToPdfService {

	private static final Logger logger = LoggerFactory.getLogger(HtmlToPdfServiceImpl.class);
	
	@Autowired
	private HtmlToPdfPreProcessor htmlToPdfPreProcessor;

	@Override
	public BufferedContent createPdfFromHtml(String htmlContent) throws BusinessException {
		// TODO Auto-generated method stub
		String cleanHtml=null;
		InputStream is=null;
		try {
			cleanHtml = htmlToPdfPreProcessor.getResultantCleanHtml(htmlContent);
			cleanHtml = htmlToPdfPreProcessor.getResultantHtml(cleanHtml);
			is = PdfUtils.createPdf(cleanHtml);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new BusinessException(new Error(ErrorCode.INTERNAL_ERROR, "Error occured while creating pdf from following html :" + cleanHtml));
		}
		
		return new BufferedContent(is);
	}

	@Override
	public  BufferedContent createPdfFromHtml(String htmlContent, Map<String,String> fillingObject) throws BusinessException{
		// TODO Auto-generated method stub
		String cleanHtml=null;
		InputStream is = null;
		try {
			cleanHtml = htmlToPdfPreProcessor.getResultantCleanHtml(htmlContent);
			cleanHtml = htmlToPdfPreProcessor.getResultantHtml(cleanHtml);
			cleanHtml =  htmlToPdfPreProcessor.fillTemplate(cleanHtml, fillingObject);
			is = PdfUtils.createPdf(cleanHtml);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new BusinessException(new Error(ErrorCode.INTERNAL_ERROR, "Error occured while creating pdf from following html :" + cleanHtml));
		}
		return  new BufferedContent(is);
	}

	@Override
	public BufferedContent renderAsText(String htmlContent, Map<String,String> fillingObject) throws BusinessException {
		// TODO Auto-generated method stub
		BufferedContent buffereContent = null;
		String cleanHtml=null;

		try {
			cleanHtml = htmlToPdfPreProcessor.getResultantCleanHtml(htmlContent);
			cleanHtml = htmlToPdfPreProcessor.getResultantHtml(cleanHtml);
			cleanHtml =  htmlToPdfPreProcessor.fillTemplate(cleanHtml, fillingObject);
		} catch (Exception e) {
			throw new BusinessException(new Error(ErrorCode.INTERNAL_ERROR, "Error occured while filling Template" + cleanHtml));
		}
		return new BufferedContent(cleanHtml);
		
	}

	@Override
	public BufferedContent fillPdfAcroField(InputStream is, Map<String, String> fillingObject)
			throws BusinessException {

		PdfStamper stamper = null;
		PdfReader pdfReader = null;
		File file = null;
		FileOutputStream fos = null;
		try {
			pdfReader = new PdfReader(is);
			file = File.createTempFile("filled-form", "pdf");
			fos = new FileOutputStream(file);
			stamper = new PdfStamper(pdfReader, fos);
		} catch (RuntimeException e) {
			throw new BusinessException(
					new Error(ErrorCode.INTERNAL_ERROR, "Error occured while creating pdf stamper"));
		} catch (Exception e) {
			throw new BusinessException(
					new Error(ErrorCode.INTERNAL_ERROR, "Error occured while creating pdf stamper"));
		}finally {
			IOUtils.closeQuietly(fos);
		}

		AcroFields form = stamper.getAcroFields();
		Set<String> fields = form.getFields().keySet();
		int numberOfContentFields = fillingObject.size();
		int count = 0;
		boolean setFlag = false;
		Set<String> fillingObjectKey = fillingObject.keySet();
		for (String fieldName : fillingObjectKey) {
			if (fillingObject.get(fieldName) != null) {
				try {
					setFlag = form.setField(fieldName, fillingObject.get(fieldName));
				} catch (Exception e) {
					throw new BusinessException(
							new Error(ErrorCode.INTERNAL_ERROR, "Error occured while setting acrofield"));
				}
			}
		}
		stamper.setFormFlattening(true);
		try {
			stamper.close();
		} catch (Exception e) {
			throw new BusinessException(new Error(ErrorCode.INTERNAL_ERROR, "Error occured while closing the stamper"));
		}

		BufferedContent buffContent = null;
		try {
			buffContent = new BufferedContent(new FileInputStream(file));
		} catch (Exception e) {
			throw new BusinessException(
					new Error(ErrorCode.INTERNAL_ERROR, "Error occured while creating BufferedContent"));
		}
		return buffContent;
	}
	
	/*@Override
	public InputStream createTemplateDocumentAsPdf(String templateContent) {
		// TODO Auto-generated method stub
		String cleanHtml = templatePreProcessor.getResultantCleanHtml(templateContent);
		cleanHtml = templatePreProcessor.getResultantHtml(cleanHtml);
		InputStream is=null;
		try {
			is = PdfUtils.createPdf(cleanHtml);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return is;
	}

	@Override
	public InputStream createTemplateDocumentAsText(String templateContent) {
		// TODO Auto-generated method stub
		String cleanHtml = templatePreProcessor.getResultantCleanHtml(templateContent);
		cleanHtml = templatePreProcessor.getResultantHtml(cleanHtml);
		InputStream is = null;
		try {
			is = PdfUtils.createTextFile(cleanHtml);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return is;
	}
	
	public <T> InputStream createLetterDocumentAsPdf(String templateContent, T object)
	{
		String cleanHtml = templatePreProcessor.getResultantCleanHtml(templateContent);
		cleanHtml = templatePreProcessor.getResultantHtml(cleanHtml);
		cleanHtml =  templatePreProcessor.fillTemplate(cleanHtml, object);
		InputStream is = null;
		try {
			is = PdfUtils.createPdf(cleanHtml);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return is;
	}
	
	public <T> InputStream createLetterDocumentAsText(String templateContent, T object)
	{
		String cleanHtml = templatePreProcessor.getResultantCleanHtml(templateContent);
		cleanHtml = templatePreProcessor.getResultantHtml(cleanHtml);
		cleanHtml =  templatePreProcessor.fillTemplate(cleanHtml, object);
		InputStream is = null;
		try {
			is = PdfUtils.createTextFile(cleanHtml);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return is;
	}
	*/
	

}

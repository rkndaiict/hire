package com.hire.pdf.service;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import com.hcentive.common.io.BufferedContent;
import com.hcentive.utils.error.BusinessException;

public interface HtmlToPdfService {
	
	/**
	 * Returns a generated pdf document from the provided html content.
	 * 
	 * @param htmlContent
	 *            - template from which pdf is generated.
	 * @return
	 */
	public BufferedContent createPdfFromHtml(String htmlContent) throws BusinessException;

	/**
	 * Returns a generated pdf document from the provided html content. This
	 * method also supports data place holders in the content. These place
	 * holders are filled from the values in the value object.
	 * 
	 * @param htmlContent
	 *            - template from which pdf is generated.
	 * @param object
	 *            - value object which is used for filling content place holders
	 *            in the template.
	 * @return
	 */
	public BufferedContent createPdfFromHtml(String htmlContent, Map<String,String> fillingObject)  throws BusinessException;

	/**
	 * Generates only the text content from the provided htmlContent & the
	 * values object. This method is useful in those scenarios where a text
	 * format of the to-be-generated pdf document is required, say to provide a
	 * preview to the user.
	 * 
	 * @param htmlContent
	 *            - template from which pdf is generated.
	 * @param object - value object which is used for filling content place holders
	 *            in the template.
	 * @return
	 */
	public BufferedContent renderAsText(String htmlContent, Map<String,String> fillingObject) throws BusinessException;
	
	public BufferedContent fillPdfAcroField(InputStream is, Map<String,String> fillingObject) throws BusinessException;

}

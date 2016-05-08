package com.hire.pdf.service.impl;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.swing.text.html.HTML.Attribute;
import javax.swing.text.html.HTML.Tag;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.htmlcleaner.ContentNode;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.omg.IOP.TAG_ALTERNATE_IIOP_ADDRESS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hire.pdf.constants.PdfConstant;
import com.hire.pdf.service.HtmlToPdfPreProcessor;
import com.hire.pdf.util.PdfUtils;


@Service
public class HtmlToPdfPreProcessorImpl implements HtmlToPdfPreProcessor {

	@Resource(name="pdfVelocityEngine")
	private VelocityEngine velocityEngine;

	public String getResultantHtml(String htmlContent) {
		// TODO Auto-generated method stub
		StringBuilder resultantHtml= new StringBuilder();
		resultantHtml.append(PdfConstant.HTML_HEADER);
		resultantHtml.append(htmlContent);
		resultantHtml.append(PdfConstant.HTML_FOOTER);
		return resultantHtml.toString();
	}

	public String getResultantCleanHtml(String htmlContent) throws Exception{
		// TODO Auto-generated method stub
		
		HtmlCleaner htmlCleaner = new HtmlCleaner();

		TagNode root = htmlCleaner.clean(htmlContent);
		TagNode headNode = root.findElementByName(Tag.HEAD.toString(), false);
		TagNode linkNode = new TagNode(Tag.STYLE.toString());
		linkNode.setAttribute(Attribute.TYPE.toString(), "text/css");
			InputStream is = getClass().getResourceAsStream("/flying-saucer/pdfStyle.css");
			String css = IOUtils.toString(is, "UTF-8");
		//String css = FileUtils.readFileToString(new File("C:/Users/Parveen.Chhikara/work/PdfFly/src/main/webapp/resources/css/pdfStyle.css"));
		ContentNode t = new ContentNode(css);
		linkNode.addChild(t);
		
		headNode.addChild(linkNode);
		
        String cleanHtml = htmlCleaner.getInnerHtml(root);
        cleanHtml =  StringEscapeUtils.unescapeHtml(cleanHtml);
		System.out.println(cleanHtml);
		return cleanHtml;
	}
	

	public  String fillTemplate(String htmlContent, Map<String,String> tokenValueMap) throws Exception{
		// TODO Auto-generated method stub
		//velocityEngine.init();
		//Velocity.init("/velocity/velocity.properties");


        VelocityContext context = new VelocityContext();
       // EmployeeToken e = new EmployeeToken();
       // e.setId(1L);
       // e.setName("parveen");
       // e.setDesignation("developer");
       /* Map<String,String> propertiesInfo = PdfUtils.getObjectPropertiesInfo(object);
        for (Map.Entry<String, String> entry : propertiesInfo.entrySet()) {
            //System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
            context.put(entry.getKey(), entry.getValue());
        }*/
       Set<String> tokenKeys = tokenValueMap.keySet();
       for(String key : tokenKeys)
       {
    	   context.put(key,tokenValueMap.get(key));
       }
        StringWriter w = new StringWriter();
        velocityEngine.evaluate( context, w, "mystring22", htmlContent );
		return w.toString();
	}

	

}

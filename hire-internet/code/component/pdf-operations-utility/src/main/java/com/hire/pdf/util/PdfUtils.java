package com.hire.pdf.util;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class PdfUtils {

	public static InputStream createPdf(String filledContent) throws Exception {
		// TODO Auto-generated method stub
		/*OutputStream os = null;
		String fileName = "C:\\trr\\Test49.pdf";
		File file = new File(fileName);
		File file = File.createTempFile("tempFile", "pdf");
		
			os = new FileOutputStream(file);
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory
					.newInstance();
			docBuilderFactory.setValidating(false);
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			docBuilder.setEntityResolver(FSEntityResolver.instance());
			// Document doc = docBuilder.parse (filledContent);
			Document doc = docBuilder.parse(new ByteArrayInputStream(
					filledContent.toString().getBytes()));

			ITextRenderer renderer = new ITextRenderer();
			SharedContext sharedContext = renderer.getSharedContext();
			sharedContext.setPrint(true);
			sharedContext.setInteractive(false);
			// just set the factory here
			ImageReplacedElementFactory imageFactoy = new ImageReplacedElementFactory();
			sharedContext.setReplacedElementFactory(imageFactoy);
			sharedContext.getTextRenderer().setSmoothingThreshold(0);
			renderer.setDocument(doc, null);

			// renderer.setDocument(xhtmlContent, url);
			renderer.layout();
			renderer.createPDF(os);
			os.close();
			InputStream is = new FileInputStream(file);
		

		return is;*/
		return null;
	}
	
	/*public static InputStream createTextFile(String content)  throws Exception
	{
		File file = new File("C:\\trr\\Test49.txt");
		 
		// if file doesnt exists, then create it
		if (!file.exists()) {
			file.createNewFile();
		}

		FileWriter fw = new FileWriter(file);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(content);
		bw.close();
		InputStream is = new FileInputStream(file);
		return is;
	}*/

	public static <T> Map<String, String> getObjectPropertiesInfo(T object) {
		Map<String, String> map = null;
		try {
			Method[] methods = object.getClass().getMethods();
			map = new HashMap<String, String>();
			for (Method m : methods) {
				if (m.getName().startsWith("get")) {
					String value = String.valueOf(m.invoke(object));
					map.put(m.getName().substring(3), value);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

}

package com.hire.pdf.util;

 
 
public class ImageReplacedElementFactory //implements ReplacedElementFactory 
{
	
 
   /* public ImageReplacedElementFactory() {
		super();
	}

	public ReplacedElement createReplacedElement(LayoutContext c, BlockBox box, UserAgentCallback uac, int cssWidth, int cssHeight) {
	Element e = box.getElement();
	if (e == null) {
	    return null;
	}
	String nodeName = e.getNodeName();
	if (nodeName.equals("img")) {
	    String attribute = e.getAttribute("src");	    
	    FSImage fsImage;
	    try {
		fsImage = buildImage(attribute, uac);
	    } catch (BadElementException e1) {
		fsImage = null;
	    } catch (IOException e1) {
		fsImage = null;
	    }
	    if (fsImage != null) {
		if (cssWidth != -1 || cssHeight != -1) {
		    fsImage.scale(cssWidth, cssHeight);
		}
		return new ITextImageElement(fsImage);
	    }
	}
 
	return null;
    }
 
    protected FSImage buildImage(String srcAttr, UserAgentCallback uac) throws IOException, BadElementException {
	FSImage fsImage;
	if (srcAttr.startsWith("data:image/")) {
	    String b64encoded = srcAttr.substring(srcAttr.indexOf("base64,") + "base64,".length(), srcAttr.length());
	    // BASE64Decoder decoder = new BASE64Decoder();
	    // byte[] decodedBytes = decoder.decodeBuffer(b64encoded);
	    // byte[] decodedBytes = B64Decoder.decode(b64encoded);
	    byte[] decodedBytes = Base64.decode(b64encoded);
 
	    fsImage = new ITextFSImage(Image.getInstance(decodedBytes));
	} else {
		  InputStream input = null;
		 input = new FileInputStream(srcAttr);
         final byte[] bytes = IOUtils.toByteArray(input);
         final Image image = Image.getInstance(bytes);
         fsImage = new ITextFSImage(image);
		
	      URL url = new URL(srcAttr);

          // read image direct from url
          BufferedImage image = ImageIO.read(url);

          // write image to outputstream
          ByteArrayOutputStream baos = new ByteArrayOutputStream();
          ImageIO.write(image, "jpg", baos);
          baos.flush();

          // get bytes
          byte[] imageBytes = baos.toByteArray();
          final Image imagett = Image.getInstance(imageBytes);
          fsImage = new ITextFSImage(imagett);
         
	   // fsImage = uac.getImageResource(srcAttr).getImage();
	}
	return fsImage;
    }
 
    public void remove(Element e) {
    }
 
    public void reset() {
    }
    */
}

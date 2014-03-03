package pl.spliner21.svg_parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;


public class SVGTree {
	Document doc = null;
	
	SVGHead svghead;
	
	SVGTree(String path)
			throws FileNotFoundException, IOException
	{
		openSVGFile(path);
	}
	
	void openSVGFile(String path) 
		throws FileNotFoundException, IOException
	{
		File fXmlFile = new File(path);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
	 
		if(doc.getDocumentElement().getNodeName().contains("svg"))
		{
			Element main = doc.getDocumentElement();
			svghead = new SVGHead(main);
		}
		
	}
	
	String getCode()
	{
		return svghead.getCode();
	}
	
}

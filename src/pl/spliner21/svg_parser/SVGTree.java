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

import pl.spliner21.svg_parser.objects.SVGHead;


/**
 * Main parser class. Constructs SVG DOM Tree.
 * @author spliner21
 * @version 1.0
 */
public class SVGTree {
	Document doc = null;
	
	SVGHead svghead;
	
	public SVGHead getSVGObject() {
		return svghead;
	}

	/**
	 * Constructor based on path to SVG file
	 * @param path - string containing path to SVG file
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public SVGTree(String path)
			throws FileNotFoundException, IOException
	{
		openSVGFile(path);
	}
	
	/**
	 * Method for opening SVG file and parsing it to DOM-objects structure
	 * @param path - string containing path to SVG file
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
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
	
	/**
	 * Method that generates SVG file's content from library's DOM structure
	 * @return String containing SVG file's content
	 */
	String getCode()
	{
		return svghead.getCode();
	}
	
	@Override
	public String toString()
	{
		return getCode();
	}
	
}

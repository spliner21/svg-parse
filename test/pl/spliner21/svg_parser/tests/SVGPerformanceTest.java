package pl.spliner21.svg_parser.tests;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.batik.dom.svg.SAXSVGDocumentFactory;
import org.apache.batik.swing.JSVGCanvas;
import org.apache.batik.util.XMLResourceDescriptor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.svg.SVGAElement;
import org.w3c.dom.svg.SVGDocument;
import org.w3c.dom.svg.SVGElement;
import org.w3c.dom.svg.SVGSVGElement;

import pl.spliner21.svg_parser.SVGTree;

public class SVGPerformanceTest {
	SVGTree testTree;
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws FileNotFoundException, IOException {
				
		long time = System.nanoTime();
		testTree = new SVGTree("svg/arctic_big.svg");
		
		System.out.println("Time elapsed to open using SVG-Parse: "+(System.nanoTime() - time)/1000000000.0f+"s.");
		
		assertTrue(testTree != null);
		
		time = System.nanoTime();
		testTree.getSVGObject().rotate(180.0f);
		
		System.out.println("Time elapsed to rotate 180 "+(System.nanoTime() - time)/1000000000.0f+"s.");
		
		/* PrintWriter writer = new PrintWriter("E:\\Archiwum\\Code projects\\Android\\svg-parse\\svg\\arctic_big_180d.svg", "UTF-8");
		writer.println(testTree.toString());
		
		writer.close(); */
		InputStream is =  this.getClass().getClassLoader().getResourceAsStream("svg/arctic_big.svg");
		time = System.nanoTime();

		String parser = XMLResourceDescriptor.getXMLParserClassName();
		SAXSVGDocumentFactory f = new SAXSVGDocumentFactory(parser);
		Document doc = f.createDocument("svg/arctic_big.svg",is);
		
		System.out.println("Time elapsed to open using Batik: "+(System.nanoTime() - time)/1000000000.0f+"s.");
		
		System.out.println("Batik does not support automatic operations like rotation, scale, etc. for all DOM elements at once");
		
	}

}

package pl.spliner21.svg_parser.tests;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import org.apache.batik.dom.svg.SAXSVGDocumentFactory;
import org.apache.batik.util.XMLResourceDescriptor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;

import com.kitfox.svg.SVGDiagram;
import com.kitfox.svg.SVGUniverse;

import pl.spliner21.svg_parser.SVGTree;

public class SVGPerformanceTest {
	SVGTree testTree;
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@SuppressWarnings("unused")
	@Test
	public void test() throws FileNotFoundException, IOException {

		String name = "poland_coa";
		System.out.println("--------------- SVG-PARSE ---------------");
		long time = System.nanoTime();
		testTree = new SVGTree("svg/"+name+".svg");
		
		System.out.println("Time elapsed to open using SVG-Parse: "+(System.nanoTime() - time)/1000000000.0f+"s.");
		
		assertTrue(testTree != null);
		
		/*time = System.nanoTime();
		testTree.getSVGObject().rotate(90.0f);
		
		System.out.println("Time elapsed to rotate 90 "+(System.nanoTime() - time)/1000000000.0f+"s.");
		
		PrintWriter writer = new PrintWriter("./svg/"+name+"_90d.svg", "UTF-8");
		writer.println(testTree.toString());
		
		writer.close();*/

		time = System.nanoTime();
		testTree.getSVGObject().scale(2.0f,1.5f,0,0);
		
		System.out.println("Time elapsed to scale 2.0f, 1.5f "+(System.nanoTime() - time)/1000000000.0f+"s.");
		
		PrintWriter writer = new PrintWriter("./svg/"+name+"_2_1.5.svg", "UTF-8");
		writer.println(testTree.toString());
		
		writer.close();
		
		System.out.println("----------------- BATIK -----------------");
		InputStream is =  this.getClass().getClassLoader().getResourceAsStream("svg/arctic_big.svg");
		time = System.nanoTime();

		String parser = XMLResourceDescriptor.getXMLParserClassName();
		SAXSVGDocumentFactory f = new SAXSVGDocumentFactory(parser);
		Document doc = f.createDocument("svg/arctic_big.svg",is);
		
		System.out.println("Time elapsed to open using Batik: "+(System.nanoTime() - time)/1000000000.0f+"s.");
		
		System.out.println("Batik does not support automatic operations like rotation, scale, etc. for all DOM elements at once");

		System.out.println("--------------- SALAMANDER --------------");

		time = System.nanoTime();
		SVGUniverse svgu = new SVGUniverse();
		svgu.loadSVG(new File("svg/arctic_big.svg").toURI().toURL());
		System.out.println("Time elapsed to load SVG using Salamander: "+(System.nanoTime() - time)/1000000000.0f+"s.");
		
		time = System.nanoTime();
		SVGDiagram svgdiagram = svgu.getDiagram(new File("svg/android.svg").toURI());

		
		System.out.println("Time elapsed to create diagram using Salamander: "+(System.nanoTime() - time)/1000000000.0f+"s.");

		System.out.println("Salamander does not support automatic operations like rotation, scale, etc. for all DOM elements at once");
	}

}

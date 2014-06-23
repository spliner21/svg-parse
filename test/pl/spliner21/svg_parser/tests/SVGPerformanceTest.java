package pl.spliner21.svg_parser.tests;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.imageio.ImageIO;

import org.apache.batik.dom.svg.SAXSVGDocumentFactory;
import org.apache.batik.dom.util.DOMUtilities;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.JPEGTranscoder;
import org.apache.batik.util.XMLResourceDescriptor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.w3c.dom.svg.SVGDocument;

import com.kitfox.svg.SVGDiagram;
import com.kitfox.svg.SVGElementException;
import com.kitfox.svg.SVGException;
import com.kitfox.svg.SVGUniverse;
import com.kitfox.svg.animation.AnimationElement;

import pl.spliner21.svg_parser.SVGTree;

@RunWith(ExtendedRunner.class) 
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
		
		System.out.println("Total Java Heap Size for this VM: "+Runtime.getRuntime().totalMemory());
		
		/* Result vectors - parsing */
		float resPSVGParser = 0.0f;
		float resPBatik = 0.0f;
		float resPSalamander = 0.0f;
		float resPSalamanderD = 0.0f;
		
		/* Result vectors - transform */
		float resTSVGParserB = 0.0f;
		float resTSVGParserS = 0.0f;
		float resTBatik = 0.0f;
		float resTSalamander = 0.0f;

		/* Batik temp variables */
		JPEGTranscoder jpgtranscoder = new JPEGTranscoder();
		jpgtranscoder.addTranscodingHint(JPEGTranscoder.KEY_QUALITY, 0.8f);
		
		String name = "";	// filename
		PrintWriter writer = null;
		
		for(int i=0; i<4; ++i)
		{
			// name = "simple_house"; // simple_house [S] / awesome_tiger [M] / poland_coa [L] / world [XL]
			
			switch(i)
			{
				case 0:
					name = "world";
					break;
				case 1:
					name = "poland_coa";
					break;
				case 2:
					name = "awesome_tiger";
					break;
				case 3:
					name = "simple_house";
					break;
				default:
					break;
			}
			
			System.out.println("PROCESSED FILE: "+name+".svg");
			
			/* 
			 * SVG Parser Parsing test 
			 */
			long time = System.nanoTime();
			testTree = new SVGTree("svg/"+name+".svg");
			System.out.println("SVG-Parse parsing time: "+(System.nanoTime() - time)/1000000000.0f+"s.");
			
			resPSVGParser = (System.nanoTime() - time)/1000000000.0f;
			assertTrue(testTree != null);
	
			/* 
			 * SVG Parser rotate test 
			 */
			time = System.nanoTime();
			testTree.getSVGObject().rotate(90.0f);
			System.out.println("Rotate 90 using SVG Parse: "+(System.nanoTime() - time)/1000000000.0f+"s.");

			// Writing result to file
			writer = new PrintWriter("./svg/"+name+"_90d.svg", "UTF-8");
			writer.println(testTree.toString());
			System.out.println("SVG-Parse rotated image saved.");
			writer.close();
			
			/* 
			 * SVG Parser rotated image + Batik JPG generation test 
			 */
	        // Create the transcoder input.
	        String svgURI = new File("./svg/"+name+"_90d.svg").toURI().toString();
	        TranscoderInput input = new TranscoderInput(svgURI);
	        // Create the transcoder output.
	        OutputStream ostream = new FileOutputStream("./jpg/"+name+"_90d.jpg");
	        TranscoderOutput output = new TranscoderOutput(ostream);
	
			time = System.nanoTime();
	        // Save the image.
	        try {
				jpgtranscoder.transcode(input, output);
			} catch (TranscoderException e1) {
				e1.printStackTrace();
			}
	        // Flush and close the stream.
	        ostream.flush();
	        ostream.close();
			System.out.println("Generate JPG using Batik from SVG-Parser transformed SVG: "+(System.nanoTime() - time)/1000000000.0f+"s.");
			resTSVGParserB = (System.nanoTime() - time)/1000000000.0f;
			
			/* 
			 * SVG Parser rotated image + Salamander JPG generation test 
			 */
			SVGUniverse svguSVGP = new SVGUniverse();
			SVGDiagram svgdSVGP = svguSVGP.getDiagram(new File("svg/"+name+"_90d.svg").toURI());
			BufferedImage biSVGP = new BufferedImage((int)svgdSVGP.getWidth(), (int)svgdSVGP.getHeight(),BufferedImage.TYPE_INT_RGB);
			
			time = System.nanoTime();
			try {
				svgdSVGP.render(biSVGP.createGraphics());
			} catch (SVGException eSVGP) {
				eSVGP.printStackTrace();
			}
	
			File outs1 = new File("./jpg/"+name+"_90d_s.jpg");
			ImageIO.write(biSVGP, "jpg", outs1);
	
			System.out.println("Generate JPG using Salamander from SVG-Parser transformed SVG: "+(System.nanoTime() - time)/1000000000.0f+"s.");
			resTSVGParserS = (System.nanoTime() - time)/1000000000.0f;

			/* 
			 * SVG Parser scale test 
			 */
			time = System.nanoTime();
			testTree.getSVGObject().scale(2.0f,1.5f,0,0);
			
			System.out.println("Time elapsed to scale 2.0f, 1.5f "+(System.nanoTime() - time)/1000000000.0f+"s.");
			writer = new PrintWriter("./svg/"+name+"_2_1.5.svg", "UTF-8");
			writer.println(testTree.toString());
			writer.close();
						
			/* 
			 * Batik parsing test 
			 */
			time = System.nanoTime();
	
			InputStream is =  this.getClass().getClassLoader().getResourceAsStream("svg/"+name+".svg");
			String parser = XMLResourceDescriptor.getXMLParserClassName();
			SAXSVGDocumentFactory f = new SAXSVGDocumentFactory(parser);
			
			SVGDocument doc = (SVGDocument) f.createDocument("svg/"+name+".svg",is);
			System.out.println("Batik parsing time: "+(System.nanoTime() - time)/1000000000.0f+"s.");
			resPBatik = (System.nanoTime() - time)/1000000000.0f;
				
			/* 
			 * Batik transformation test
			 */
			time = System.nanoTime();
			double w,h;
			w = doc.getRootElement().getWidth().getBaseVal().getValue()/2.0;
			h = doc.getRootElement().getHeight().getBaseVal().getValue()/2.0;
			
			doc.getRootElement().getElementById("main_group").setAttribute("transform", "rotate(90,"+w+","+h+")");
			System.out.println("Batik transform parameter change time: "+(System.nanoTime() - time)/1000000000.0f+"s.");
	
			writer = new PrintWriter("./svg/"+name+"_90d_batik.svg", "UTF-8");
			writer.println(DOMUtilities.getXML(doc.getRootElement()));
			
			writer.close();	
			
			// Create the transcoder input.
			String svgURIb = new File("./svg/"+name+"_90d_batik.svg").toURI().toString();
	        TranscoderInput inputb = new TranscoderInput(svgURIb);
	        // Create the transcoder output.
	        OutputStream ostreamb = new FileOutputStream("./jpg/"+name+"_90d_batik.jpg");
	        TranscoderOutput outputb = new TranscoderOutput(ostreamb);
	
			time = System.nanoTime();
	        // Save the image.
	        try {
				jpgtranscoder.transcode(inputb, outputb);
			} catch (TranscoderException e1) {
				e1.printStackTrace();
			}
	        // Flush and close the stream.
	        ostreamb.flush();
	        ostreamb.close();
			System.out.println("Generate JPG from SVG using Batik: "+(System.nanoTime() - time)/1000000000.0f+"s.");
			resTBatik = (System.nanoTime() - time)/1000000000.0f;
			
			doc = null;
			is = null;
			
			/* 
			 * Salamander parsing tests 
			 */
			time = System.nanoTime();
			SVGUniverse svgu = new SVGUniverse();
			svgu.loadSVG(new File("svg/"+name+".svg").toURI().toURL());
			System.out.println("Salamander parsing time: "+(System.nanoTime() - time)/1000000000.0f+"s.");
			resPSalamander = (System.nanoTime() - time)/1000000000.0f;
			
			time = System.nanoTime();
			SVGUniverse svguniverse = new SVGUniverse();
			SVGDiagram svgdiagram = svguniverse.getDiagram(new File("svg/"+name+".svg").toURI());
			System.out.println("Salamander Diagram parsing time: "+(System.nanoTime() - time)/1000000000.0f+"s.");
			resPSalamanderD = (System.nanoTime() - time)/1000000000.0f;

			w = svgdiagram.getWidth()/2.0;
			h = svgdiagram.getHeight()/2.0;
			time = System.nanoTime();
			try {
				svgdiagram.getRoot().getChild("main_group").addAttribute("transform", AnimationElement.AT_XML, "rotate(90,"+w+","+h+")");
				svgdiagram.updateTime(0.0);
			} catch (SVGElementException ex) {
				ex.printStackTrace();
			} catch (SVGException e1) {
				e1.printStackTrace();
			}
			System.out.println("Transform using Salamander: "+(System.nanoTime() - time)/1000000000.0f+"s.");
			
			// Render with salamander
			BufferedImage bi = new BufferedImage((int)svgdiagram.getWidth(), (int)svgdiagram.getHeight(),BufferedImage.TYPE_INT_RGB);
			time = System.nanoTime();
			try {
				svgdiagram.render(bi.createGraphics());
			} catch (SVGException e1) {
				e1.printStackTrace();
			}
			File outputfile = new File("./jpg/"+name+"_90d_salamander.jpg");
			ImageIO.write(bi, "jpg", outputfile);
			System.out.println("Generate JPG from SVG using Salamander "+(System.nanoTime() - time)/1000000000.0f+"s.");
			resTSalamander = (System.nanoTime() - time)/1000000000.0f;
		}

		/* writing results to CSV */
		
		Date date = new Date();
		DateFormat dformat = new SimpleDateFormat("YYYY-MM-dd_HH-mm", Locale.US);
		
		writer = new PrintWriter("./res/parsing_"+dformat.format(date)+".csv", "UTF-8");
		writer.println("Plik 'world'");
		writer.println("SVG Parser; Batik; Salamander; Salamander Diagram;");
		writer.println(resPSVGParser+"; "+resPBatik+"; "+resPSalamander+"; "+resPSalamanderD+";");
		
		writer.println("");
		writer.println("Plik 'poland_coa'");
		writer.println("SVG Parser; Batik; Salamander; Salamander Diagram;");
		writer.println(resPSVGParser+"; "+resPBatik+"; "+resPSalamander+"; "+resPSalamanderD+";");
	
		writer.println("");
		writer.println("Plik 'awesome_tiger'");
		writer.println("SVG Parser; Batik; Salamander; Salamander Diagram;");
		writer.println(resPSVGParser+"; "+resPBatik+"; "+resPSalamander+"; "+resPSalamanderD+";");
		
		writer.println("");
		writer.println("Plik 'simple_house'");
		writer.println("SVG Parser; Batik; Salamander; Salamander Diagram;");
		writer.println(resPSVGParser+"; "+resPBatik+"; "+resPSalamander+"; "+resPSalamanderD+";");
		
		writer.close();	
		
		writer = new PrintWriter("./res/transform_"+dformat.format(date)+".csv", "UTF-8");
		writer.println("Plik 'world'");
		writer.println("SVG Parser i Batik;SVG Parser i Salamander; Batik; Salamander;");
		writer.println(resTSVGParserB+";"+resTSVGParserS+"; "+resTBatik+"; "+resTSalamander+";");

		writer.println("");
		writer.println("Plik 'poland_coa'");
		writer.println("SVG Parser i Batik;SVG Parser i Salamander; Batik; Salamander;");
		writer.println(resTSVGParserB+";"+resTSVGParserS+"; "+resTBatik+"; "+resTSalamander+";");
		
		writer.println("");
		writer.println("Plik 'awesome_tiger'");
		writer.println("SVG Parser i Batik;SVG Parser i Salamander; Batik; Salamander;");
		writer.println(resTSVGParserB+";"+resTSVGParserS+"; "+resTBatik+"; "+resTSalamander+";");
		
		writer.println("");
		writer.println("Plik 'simple_house'");
		writer.println("SVG Parser i Batik;SVG Parser i Salamander; Batik; Salamander;");
		writer.println(resTSVGParserB+";"+resTSVGParserS+"; "+resTBatik+"; "+resTSalamander+";");
		
		writer.close();	
	}
	
}

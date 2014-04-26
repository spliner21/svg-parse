package pl.spliner21.svg_parser.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pl.spliner21.svg_parser.objects.SVGGroup;
import pl.spliner21.svg_parser.objects.SVGHead;
import pl.spliner21.svg_parser.objects.SVGPolygon;
import pl.spliner21.svg_parser.objects.SVGPolyline;
import pl.spliner21.svg_parser.objects.SVGRectangle;

/**
 * Test class that checks if construction tools works properly
 * @author spliner21
 *
 */
public class SVGHeadTest {

	SVGHead testHead;
	
	@Before
	public void setUp() throws Exception {
		testHead = new SVGHead("1.0","base","test-head-id",0,0,100,200);
		testHead.addObject(new SVGPolygon("polygon1","","1,1 10,20 50,35 45,60 10,5","#ff0000","#00FFFF",1.5f,"",1.0f,""));
		testHead.addObject(new SVGPolyline("polyline1","","1,1 10,20 50,35 45,60 10,5","#ff0000","#00FFFF",1.5f,"",1.0f,""));
		SVGGroup t1 = new SVGGroup();
		t1.setID("group1");
		testHead.addObject(t1);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void IDSearchTest() {
		System.out.println("------ ID Search Test --------");
		System.out.println("");
		
		((SVGGroup)testHead.getObjectByID("group1")).addObject(new SVGRectangle("rect1","",10.0f,20.0f,3.0f,3.0f,50.0f,20.0f,"#ffffff","#000000",1.0f,"",1.0f,""));

		SVGRectangle rect = ((SVGRectangle)testHead.getObjectByID("rect1"));
		assertNotNull(rect);
		
		System.out.println(rect.toString());
		System.out.println("");
		System.out.println(testHead.toString());
		System.out.println("");
	}

	@Test
	public void TypeSearchTest() {
		System.out.println("----- Type Search Test -------");
		System.out.println("");
		
		((SVGGroup)testHead.getObjectByType(SVGGroup.class)).addObject(new SVGRectangle("rect1","",10.0f,20.0f,3.0f,3.0f,50.0f,20.0f,"#ffffff","#000000",1.0f,"",1.0f,""));

		SVGRectangle rect = ((SVGRectangle)testHead.getObjectByType(SVGRectangle.class));
		assertNotNull(rect);
		
		System.out.println(rect.toString());
		System.out.println("");
		System.out.println(testHead.toString());
		System.out.println("");
	}

	@Test
	public void IDDeletionTest() {
		System.out.println("----- ID Deletion Test -------");
		System.out.println("");
		
		((SVGGroup)testHead.getObjectByType(SVGGroup.class)).addObject(new SVGRectangle("rect1","",10.0f,20.0f,3.0f,3.0f,50.0f,20.0f,"#ffffff","#000000",1.0f,"",1.0f,""));

		SVGRectangle rect = ((SVGRectangle)testHead.getObjectByID("rect1"));
		assertNotNull(rect);

		
		System.out.println(rect.toString());
		System.out.println("");
		
		testHead.deleteObjectByID(rect.getID());
		SVGRectangle rect1 = ((SVGRectangle)testHead.getObjectByID("rect1"));
		assertNull(rect1);
		
		System.out.println(testHead.toString());
		System.out.println("");
	}
}

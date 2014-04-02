package pl.spliner21.svg_parser.tests;

import static org.junit.Assert.*;

import java.util.Vector;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pl.spliner21.svg_parser.objects.SVGPoint;
import pl.spliner21.svg_parser.objects.SVGPolyline;

public class SVGPolylineTest {
	SVGPolyline testPolyline;
	
	
	@Before
	public void setUp() throws Exception {
		testPolyline = new SVGPolyline("test","","0,0 10,10 10,20 30,15 0,10","#000000","#FF0000",2.0f,"",1.0f,"");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testConstruct() {
		Vector<SVGPoint> pts = testPolyline.getPoints();
		assertEquals("0.0,0.0", pts.elementAt(0).getCode());
		assertEquals("10.0,10.0", pts.elementAt(1).getCode());
		assertEquals("10.0,20.0", pts.elementAt(2).getCode());
		assertEquals("30.0,15.0", pts.elementAt(3).getCode());
		assertEquals("0.0,10.0", pts.elementAt(4).getCode());
	}

	@Test
	public void testSimpleScalling() {
		testPolyline.scale(2.0f);

		Vector<SVGPoint> pts = testPolyline.getPoints();
		assertEquals("-15.0,-10.0", pts.elementAt(0).getCode());
		assertEquals("5.0,10.0", pts.elementAt(1).getCode());
		assertEquals("5.0,30.0", pts.elementAt(2).getCode());
		assertEquals("45.0,20.0", pts.elementAt(3).getCode());
		assertEquals("-15.0,10.0", pts.elementAt(4).getCode());

		testPolyline.scale(0.5f, 0.25f);
		
		pts = testPolyline.getPoints();
		assertEquals("0.0,5.0", pts.elementAt(0).getCode());
		assertEquals("10.0,10.0", pts.elementAt(1).getCode());
		assertEquals("10.0,15.0", pts.elementAt(2).getCode());
		assertEquals("30.0,12.5", pts.elementAt(3).getCode());
		assertEquals("0.0,10.0", pts.elementAt(4).getCode());
	}
	
	@Test
	public void testAdvancedScalling() {
		testPolyline.scale(2.0f,0.0f,0.0f);

		Vector<SVGPoint> pts = testPolyline.getPoints();
		assertEquals("0.0,0.0", pts.elementAt(0).getCode());
		assertEquals("20.0,20.0", pts.elementAt(1).getCode());
		assertEquals("20.0,40.0", pts.elementAt(2).getCode());
		assertEquals("60.0,30.0", pts.elementAt(3).getCode());
		assertEquals("0.0,20.0", pts.elementAt(4).getCode());

		
		testPolyline.scale(2.0f, 4.0f,10.0f,10.0f);

		pts = testPolyline.getPoints();
		assertEquals("-10.0,-30.0", pts.elementAt(0).getCode());
		assertEquals("30.0,50.0", pts.elementAt(1).getCode());
		assertEquals("30.0,130.0", pts.elementAt(2).getCode());
		assertEquals("110.0,90.0", pts.elementAt(3).getCode());
		assertEquals("-10.0,50.0", pts.elementAt(4).getCode());
	}

	@Test
	public void testSimpleRotation()
	{
		testPolyline.rotate(90.0f);

		Vector<SVGPoint> pts = testPolyline.getPoints();
		assertEquals("25.0,-5.0", pts.elementAt(0).getCode());
		assertEquals("15.0,5.0", pts.elementAt(1).getCode());
		assertEquals("5.0,5.0", pts.elementAt(2).getCode());
		assertEquals("10.0,25.0", pts.elementAt(3).getCode());
		assertEquals("15.0,-5.0", pts.elementAt(4).getCode());
	}
	
	@Test
	public void testAdvancedRotation()
	{
		testPolyline.rotate(90.0f,20.0f,20.0f);

		Vector<SVGPoint> pts = testPolyline.getPoints();
		assertEquals("40.0,0.0", pts.elementAt(0).getCode());
		assertEquals("30.0,10.0", pts.elementAt(1).getCode());
		assertEquals("20.0,10.0", pts.elementAt(2).getCode());
		assertEquals("25.0,30.0", pts.elementAt(3).getCode());
		assertEquals("30.0,0.0", pts.elementAt(4).getCode());
	}
	
	@Test
	public void testTranslation()
	{
		testPolyline.translate(10.0f, 20.0f);

		Vector<SVGPoint> pts = testPolyline.getPoints();
		assertEquals("10.0,20.0", pts.elementAt(0).getCode());
		assertEquals("20.0,30.0", pts.elementAt(1).getCode());
		assertEquals("20.0,40.0", pts.elementAt(2).getCode());
		assertEquals("40.0,35.0", pts.elementAt(3).getCode());
		assertEquals("10.0,30.0", pts.elementAt(4).getCode());
	}

}

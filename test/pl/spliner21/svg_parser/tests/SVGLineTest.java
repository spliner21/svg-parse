package pl.spliner21.svg_parser.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pl.spliner21.svg_parser.objects.SVGLine;


public class SVGLineTest {
	SVGLine testLine;
	
	@Before
	public void setUp() throws Exception {
		testLine = new SVGLine("test","",10.0f,10.0f,30.0f,30.0f,"#FF0000",1.0f,"",1.0f,"");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testConstruct() {
		assertEquals(10.0, testLine.getX1().doubleValue(),0.0);
		assertEquals(10.0, testLine.getY1().doubleValue(),0.0);
		assertEquals(30.0, testLine.getX2().doubleValue(),0.0);
		assertEquals(30.0, testLine.getY2().doubleValue(),0.0);
	}

	@Test
	public void testSimpleScalling() {
		testLine.scale(2.0f);

		assertEquals(0.0, testLine.getX1().doubleValue(),0.0);
		assertEquals(0.0, testLine.getY1().doubleValue(),0.0);
		assertEquals(40.0, testLine.getX2().doubleValue(),0.0);
		assertEquals(40.0, testLine.getY2().doubleValue(),0.0);

		testLine.scale(0.5f, 0.25f);

		assertEquals(10.0, testLine.getX1().doubleValue(),0.0);
		assertEquals(15.0, testLine.getY1().doubleValue(),0.0);
		assertEquals(30.0, testLine.getX2().doubleValue(),0.0);
		assertEquals(25.0, testLine.getY2().doubleValue(),0.0);
	}
	
	@Test
	public void testAdvancedScalling() {
		testLine.scale(2.0f,0.0f,0.0f);

		assertEquals(20.0, testLine.getX1().doubleValue(),0.0);
		assertEquals(20.0, testLine.getY1().doubleValue(),0.0);
		assertEquals(60.0, testLine.getX2().doubleValue(),0.0);
		assertEquals(60.0, testLine.getY2().doubleValue(),0.0);

		
		testLine.scale(2.0f, 4.0f,10.0f,10.0f);

		assertEquals(30.0, testLine.getX1().doubleValue(),0.0);
		assertEquals(50.0, testLine.getY1().doubleValue(),0.0);
		assertEquals(110.0, testLine.getX2().doubleValue(),0.0);
		assertEquals(210.0, testLine.getY2().doubleValue(),0.0);
	}

	@Test
	public void testSimpleRotation()
	{
		testLine.rotate(90.0f);

		assertEquals(30.0, testLine.getX1().doubleValue(),0.0);
		assertEquals(10.0, testLine.getY1().doubleValue(),0.0);
		assertEquals(10.0, testLine.getX2().doubleValue(),0.0);
		assertEquals(30.0, testLine.getY2().doubleValue(),0.0);
	}
	@Test
	public void testAdvancedRotation()
	{
		testLine.rotate(90.0f,25.0f,35.0f);

		assertEquals(50.0, testLine.getX1().doubleValue(),0.0);
		assertEquals(20.0, testLine.getY1().doubleValue(),0.0);
		assertEquals(30.0, testLine.getX2().doubleValue(),0.0);
		assertEquals(40.0, testLine.getY2().doubleValue(),0.0);
	}
	
	@Test
	public void testTranslation()
	{
		testLine.translate(10.0f, 20.0f);

		assertEquals(20.0, testLine.getX1().doubleValue(),0.0);
		assertEquals(30.0, testLine.getY1().doubleValue(),0.0);
		assertEquals(40.0, testLine.getX2().doubleValue(),0.0);
		assertEquals(50.0, testLine.getY2().doubleValue(),0.0);
	}
}

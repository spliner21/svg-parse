package pl.spliner21.svg_parser.tests;

import static org.junit.Assert.*;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pl.spliner21.svg_parser.objects.SVGRectangle;

public class SVGRectangleTest {
	SVGRectangle testRectangle;
	
	@Before
	public void setUp() throws Exception {
		testRectangle = new SVGRectangle("test", "", 10.0f, 10.0f, 5.0f, 5.0f, 20.0f, 15.0f, "#ffffff", "#ff00ff", 1.0f, "", 1.0f, "");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testConstruct() {
		assertEquals(10.0f, testRectangle.getX(),0.0);
		assertEquals(10.0f, testRectangle.getY(),0.0);
		assertEquals(5.0f, testRectangle.getRx(),0.0);
		assertEquals(5.0f, testRectangle.getRy(),0.0);
		assertEquals(20.0f, testRectangle.getWidth(),0.0);
		assertEquals(15.0f, testRectangle.getHeight(),0.0);
	}

	@Test
	public void testSimpleScalling() {
		testRectangle.scale(2.0f);

		assertEquals(0.0f, testRectangle.getX(),0.0);
		assertEquals(2.5f, testRectangle.getY(),0.0);
		assertEquals(10.0f, testRectangle.getRx(),0.0);
		assertEquals(10.0f, testRectangle.getRy(),0.0);
		assertEquals(40.0f, testRectangle.getWidth(),0.0);
		assertEquals(30.0f, testRectangle.getHeight(),0.0);

		testRectangle.scale(0.5f, 0.25f);

		assertEquals(10.0f, testRectangle.getX(),0.0);
		assertEquals(13.75f, testRectangle.getY(),0.0);
		assertEquals(5.0f, testRectangle.getRx(),0.0);
		assertEquals(2.5f, testRectangle.getRy(),0.0);
		assertEquals(20.0f, testRectangle.getWidth(),0.0);
		assertEquals(7.5f, testRectangle.getHeight(),0.0);
	}
	@Test
	public void testAdvancedScalling() {
		testRectangle.scale(2.0f,0.0f,0.0f);

		assertEquals(20.0f, testRectangle.getX(),0.0);
		assertEquals(20.0f, testRectangle.getY(),0.0);
		assertEquals(10.0f, testRectangle.getRx(),0.0);
		assertEquals(10.0f, testRectangle.getRy(),0.0);
		assertEquals(40.0f, testRectangle.getWidth(),0.0);
		assertEquals(30.0f, testRectangle.getHeight(),0.0);

		
		testRectangle.scale(2.0f, 4.0f,25.0f,35.0f);

		assertEquals(15.0f, testRectangle.getX(),0.0);
		assertEquals(-25.0f, testRectangle.getY(),0.0);
		assertEquals(20.0f, testRectangle.getRx(),0.0);
		assertEquals(40.0f, testRectangle.getRy(),0.0);
		assertEquals(80.0f, testRectangle.getWidth(),0.0);
		assertEquals(120.0f, testRectangle.getHeight(),0.0);
	}

	@Test
	public void testSimpleRotation()
	{
		testRectangle.rotate(90.0f);

		assertEquals("rotate(90.0) ", testRectangle.getTransform());
	}

	@Test
	public void testAdvancedRotation()
	{
		testRectangle.rotate(90.0f,20.0f,20.0f);

		assertEquals("rotate(90.0) ", testRectangle.getTransform());
		assertEquals(30.0f, testRectangle.getX(),0.0);
		assertEquals(10.0f, testRectangle.getY(),0.0);
	}

	@Test
	public void testTranslation()
	{
		testRectangle.translate(10.0f, 20.0f);

		assertEquals(20.0f, testRectangle.getX(),0.0);
		assertEquals(30.0f, testRectangle.getY(),0.0);
	}

}

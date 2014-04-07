package pl.spliner21.svg_parser.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pl.spliner21.svg_parser.objects.SVGEllipse;


public class SVGEllipseTest {
	SVGEllipse testEllipse;
	
	@Before
	public void setUp() throws Exception {
		testEllipse = new SVGEllipse("test", "", 10.0f, 10.0f, 20.0f, 10.0f, "#ffffff", "#0000FF", 1.0f, "", 1.0f, "");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testConstruct() {
		assertEquals(10.0, testEllipse.getCX(),0.0);
		assertEquals(10.0, testEllipse.getCY(),0.0);
		assertEquals(20.0, testEllipse.getXRadius(),0.0);
		assertEquals(10.0, testEllipse.getYRadius(),0.0);
	}

	@Test
	public void testSimpleScalling() {
		testEllipse.scale(2.0f);

		assertEquals(40.0, testEllipse.getXRadius(),0.0);
		assertEquals(20.0, testEllipse.getYRadius(),0.0);

		assertFalse(testEllipse.isCircle());
		
		testEllipse.scale(2.0f, 4.0f);

		assertEquals(80.0, testEllipse.getXRadius(),0.0);
		assertEquals(80.0, testEllipse.getYRadius(),0.0);
		
		assertTrue(testEllipse.isCircle());
	}
	
	@Test
	public void testAdvancedScalling() {
		testEllipse.scale(2.0f,0.0f,0.0f);

		assertEquals(40.0, testEllipse.getXRadius(),0.0);
		assertEquals(20.0, testEllipse.getYRadius(),0.0);
		assertEquals(20.0, testEllipse.getCX(),0.0);
		assertEquals(20.0, testEllipse.getCY(),0.0);

		assertFalse(testEllipse.isCircle());
		
		testEllipse.scale(2.0f, 4.0f,10.0f,10.0f);

		assertEquals(80.0, testEllipse.getXRadius(),0.0);
		assertEquals(80.0, testEllipse.getYRadius(),0.0);
		assertEquals(30.0, testEllipse.getCX(),0.0);
		assertEquals(50.0, testEllipse.getCY(),0.0);
		
		assertTrue(testEllipse.isCircle());
	}

	@Test
	public void testAdvancedRotation()
	{
		testEllipse.rotate(90.0f, 10.0f, 20.0f);

		assertEquals(20.0, testEllipse.getCX(),0.0);
		assertEquals(20.0, testEllipse.getCY(),0.0);

		assertEquals("rotate(90.0) ", testEllipse.getTransform());

		testEllipse.rotate(45.0f, 10.0f, 20.0f);

		assertEquals(17.0, testEllipse.getCX(),0.1);
		assertEquals(27.0, testEllipse.getCY(),0.1);

		assertEquals("rotate(135.0) ", testEllipse.getTransform());
	}
	
	@Test
	public void testTranslation()
	{
		testEllipse.translate(10.0f, 20.0f);

		assertEquals(20.0, testEllipse.getCX(),0.0);
		assertEquals(30.0, testEllipse.getCY(),0.0);
	}
}

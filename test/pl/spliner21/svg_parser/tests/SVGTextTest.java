package pl.spliner21.svg_parser.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pl.spliner21.svg_parser.objects.SVGText;

public class SVGTextTest {
	
	SVGText testText;	
	
	@Before
	public void setUp() throws Exception {
		testText = new SVGText("test", "", 5.0f, 10.0f, "Test text", "#000000", "0000FF", 1.0f, "", 1.0f, "");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testConstruct() {
		assertEquals(5.0f, testText.getX(),0.0);
		assertEquals(10.0f, testText.getY(),0.0);
		assertEquals("Test text", testText.getText());
	}

	@Test
	public void testSimpleScalling() {
		testText.scale(2.0f);

		
		assertEquals("scale(2.0 2.0) ", testText.getTransform());

		testText.scale(0.5f, 0.25f);

		assertEquals("scale(1.0 0.5) ", testText.getTransform());
	}
	@Test
	public void testAdvancedScalling() {
		testText.scale(2.0f,0.0f,0.0f);

		assertEquals(10.0f, testText.getX(),0.0);
		assertEquals(20.0f, testText.getY(),0.0);
		assertEquals("scale(2.0 2.0) ", testText.getTransform());

		
		testText.scale(2.0f, 4.0f,25.0f,35.0f);

		assertEquals(-5.0f, testText.getX(),0.0);
		assertEquals(-25.0f, testText.getY(),0.0);
		assertEquals("scale(4.0 8.0) ", testText.getTransform());
	}

	@Test
	public void testSimpleRotation()
	{
		testText.rotate(90.0f);

		assertEquals("rotate(90.0) ", testText.getTransform());
	}

	@Test
	public void testAdvancedRotation()
	{
		testText.rotate(90.0f,20.0f,20.0f);

		assertEquals("rotate(90.0) ", testText.getTransform());
		assertEquals(30.0f, testText.getX(),0.0);
		assertEquals(5.0f, testText.getY(),0.0);
	}

	@Test
	public void testTranslation()
	{
		testText.translate(10.0f, 20.0f);

		assertEquals(15.0f, testText.getX(),0.0);
		assertEquals(30.0f, testText.getY(),0.0);
	}
}

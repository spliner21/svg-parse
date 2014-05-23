package pl.spliner21.svg_parser.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pl.spliner21.svg_parser.objects.SVGPath;

public class SVGPathTest {
	
	SVGPath testPath;
	
	@Before
	public void setUp() throws Exception {
		testPath = new SVGPath("test","","M 0.0 0.0 L 10.0 10.0 l 5.0 10.0 Q 3.0 3.0 15.0 15.0 s 3.0 3.0 5.0 -4.0 A 3.0 3.0 45.0 0.0 0.0 0.0 0.0","#FFFFFF","#00FF00",2.0f,"",1.0f,"");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testConstruct() {
		assertEquals("M 0.0 0.0 L 10.0 10.0 l 5.0 10.0 Q 3.0 3.0 15.0 15.0 s 3.0 3.0 5.0 -4.0 A 3.0 3.0 45.0 0.0 0.0 0.0 0.0 ", testPath.getStringD());
	}

	@Test
	public void testSimpleScalling() {
		/* currently unnecesary - scaling using transform */
	}
	
	@Test
	public void testAdvancedScalling() {
		testPath.scale(2.0f,0.0f,0.0f);
		assertEquals("M 0.0 0.0 L 20.0 20.0 l 10.0 20.0 Q 6.0 6.0 30.0 30.0 s 6.0 6.0 10.0 -8.0 A 6.0 6.0 45.0 0.0 0.0 0.0 0.0 ", testPath.getStringD());
		
		testPath.scale(2.0f, 4.0f,10.0f,10.0f);
		assertEquals("M -10.0 -30.0 L 30.0 50.0 l 10.0 50.0 Q 2.0 -6.0 50.0 90.0 s 12.0 24.0 10.0 -62.0 A 12.0 24.0 45.0 0.0 0.0 -10.0 -30.0 ", testPath.getStringD());
	}

	@Test
	public void testSimpleRotation()
	{
		/* currently unnecesary - rotating using transform */
	}
	@Test
	public void testAdvancedRotation()
	{
		/* currently unnecesary - rotating using transform */
	}
	
	@Test
	public void testTranslation()
	{
		testPath.translate(10.0f, 20.0f);
		assertEquals("M 10.0 20.0 L 20.0 30.0 l 5.0 10.0 Q 3.0 3.0 25.0 35.0 s 3.0 3.0 5.0 -4.0 A 3.0 3.0 45.0 0.0 0.0 10.0 20.0 ", testPath.getStringD());
	}
}

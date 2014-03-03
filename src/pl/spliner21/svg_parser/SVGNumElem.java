package pl.spliner21.svg_parser;

/* class representing number in <path> tag's d argument in SVG file
 * @author: Tomasz Szo³tysek
 * @version: 1.0
 */
public class SVGNumElem extends SVGdElem {
	Float value;

	SVGNumElem(String s) {
		value = Float.parseFloat(s);
	}
	
	@Override
	String getCode()
	{
		return value.toString();
	}
}

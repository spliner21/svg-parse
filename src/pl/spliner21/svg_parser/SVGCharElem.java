package pl.spliner21.svg_parser;

/* class representing char in <path> tag's d argument in SVG file
 * @author: Tomasz Szo³tysek
 * @version: 1.0
 */
public class SVGCharElem extends SVGdElem {
	char type;

	SVGCharElem(String s) {
		type = s.charAt(0);
	}
	
	@Override
	String getCode()
	{
		return Character.toString(type);
	}
}

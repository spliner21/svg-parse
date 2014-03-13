package pl.spliner21.svg_parser.objects;

/** 
 * Class representing char in <path> tag's d argument in SVG file
 * @author spliner21
 * @version 1.0
 */
public class SVGCharElem extends SVGdElem {
	char type;

	/**
	 * Constructor by parameter
	 * @param s character in String
	 */
	SVGCharElem(String s) {
		type = s.charAt(0);
	}
	
	@Override
	String getCode()
	{
		return Character.toString(type);
	}
}

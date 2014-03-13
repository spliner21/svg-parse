package pl.spliner21.svg_parser.objects;

/** 
 * Class representing number in <path> tag's d argument in SVG file
 * @author spliner21
 * @version 1.0
 */
public class SVGNumElem extends SVGdElem {
	Float value;

	/**
	 * Constructor by parameter
	 * @param s number in String
	 */
	SVGNumElem(String s) {
		value = Float.parseFloat(s);
	}
	
	@Override
	String getCode()
	{
		return value.toString();
	}
}

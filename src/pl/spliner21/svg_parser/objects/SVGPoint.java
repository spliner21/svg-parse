package pl.spliner21.svg_parser.objects;

/** 
 * Class representing point in <polyline> and <polygon> tag's points argument in SVG file
 * @author spliner21
 * @version 1.0
 */
public class SVGPoint {
	float x;
	float y;

	/**
	 * Constructor by parameter
	 * @param s String with point ("<X>,<Y>")
	 */
	SVGPoint(String s)
	{
		String[] sp = s.split(",");
		x = Float.parseFloat(sp[0]);
		y = Float.parseFloat(sp[1]);
	}
	/**
	 * Constructor by parameters
	 * @param sx point's X coordinate
	 * @param sy point's Y coordinate
	 */
	SVGPoint(float sx, float sy)
	{
		x = sx;
		y = sy;
	}
	
	String getCode()
	{
		return x+","+y;
	}
}

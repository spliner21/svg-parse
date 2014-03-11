package pl.spliner21.svg_parser.objects;

/* class representing point in <polyline> and <polygon> tag's points argument in SVG file
 * @author: Tomasz Szo³tysek
 * @version: 1.0
 */
public class SVGPoint {
	float x;
	float y;

	SVGPoint(String s)
	{
		String[] sp = s.split(",");
		x = Float.parseFloat(sp[0]);
		y = Float.parseFloat(sp[1]);
	}
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

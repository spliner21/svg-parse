package pl.spliner21.svg_parser.objects;

/** 
 * Class representing point in <polyline> and <polygon> tag's points argument in SVG file
 * @author spliner21
 * @version 1.0
 */
public class SVGPoint {
	protected float x;
	protected float y;

	/**
	 * Constructor by parameter
	 * @param s String with point ("<X>,<Y>")
	 */
	public SVGPoint(String s)
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
	public SVGPoint(float sx, float sy)
	{
		x = sx;
		y = sy;
	}
	
	/**
	 * Scale by factor (assume center is in 0,0)
	 * @param factor scaling factor (1.0f does nothing => 100% scale)
	 */
	public void scale(float factor)
	{
		x *= factor;
		y *= factor;
	}


	/**
	 * Scale by factors (assume center is in 0,0)
	 * @param factorx scaling X factor (1.0f does nothing => 100% scale)
	 * @param factory scaling Y factor (1.0f does nothing => 100% scale)
	 */
	public void scale(float factorx, float factory)
	{
		x *= factorx;
		y *= factory;
	}
	
	/**
	 * Scale by factor with scale's center
	 * @param factor scaling factor (1.0f does nothing => 100% scale)
	 * @param cex scaling center X coordinate
	 * @param cey scaling center Y coordinate
	 */
	public void scale(float factor, float cex, float cey)
	{
		x = (x-cex)*factor+cex;
		y = (y-cey)*factor+cey;
	}
	/**
	 * Scale by factor with scale's center
	 * @param factorx scaling X factor (1.0f does nothing => 100% scale)
	 * @param factory scaling Y factor (1.0f does nothing => 100% scale)
	 * @param cex scaling center X coordinate
	 * @param cey scaling center Y coordinate
	 */
	public void scale(float factorx, float factory, float cex, float cey)
	{
		x = (x-cex)*factorx+cex;
		y = (y-cey)*factory+cey;
	}
	/**
	 * Rotate point by angle around point 0,0.
	 * @param angle rotation angle
	 */
	public void rotate(float angle)
	{
		float sinus = (float) Math.sin(Math.toRadians(angle));
		float cosinus = (float) Math.cos(Math.toRadians(angle));
		float ox = x, oy = y;
		
		x = ox * cosinus - oy * sinus;
		y = ox * sinus + oy * cosinus;
	}
	/**
	 * Rotate point by angle around point cx,cy.
	 * @param angle rotation angle
	 * @param cx rotation point's X coordinate
	 * @param cy rotation point's Y coordinate
	 */
	public void rotate(float angle, float cx, float cy)
	{
		float ox = x - cx, oy = y - cy;
		float sinus = (float) Math.sin(Math.toRadians(angle));
		float cosinus = (float) Math.cos(Math.toRadians(angle));

		x = ox * cosinus - oy * sinus;
		y = ox * sinus + oy * cosinus;
		
		x += cx;
		y += cy;
	}
	
	public void translate(float tx, float ty)
	{
		x += tx;
		y += ty;
	}
	
	public String getCode()
	{
		return x+","+y;
	}
}

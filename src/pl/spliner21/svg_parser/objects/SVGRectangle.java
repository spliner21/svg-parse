package pl.spliner21.svg_parser.objects;

import org.w3c.dom.Element;

/** 
 * Class representing <rect> tag in SVG file
 * @author spliner21
 * @version 1.0
 */
public class SVGRectangle extends SVGObject {
	private float x = -1.0f, y = -1.0f;
	private float rx = -1.0f, ry = -1.0f;
	private float width = -1.0f, height = -1.0f;

	/**
	 * Default constructor
	 */
	public SVGRectangle()
	{
		super();
		x = 0.0f;
		y = 0.0f;
		rx = 0.0f;
		ry = 0.0f;
		width = 1.0f;
		height = 1.0f;
	}

	/**
	 * Constructor by arguments
	 * @param id tag's ID argument
	 * @param style tag's style argument
	 * @param x rectangle's X coordinate
	 * @param y rectangle's Y coordinate
	 * @param rx rectangle's corner X radius
	 * @param ry rectangle's corner X radius
	 * @param width rectangle's width
	 * @param height rectangle's height
	 * @param fill rectangle's fill color
	 * @param stroke rectangle's stroke color
	 * @param stroke_width rectangle's stroke_width
	 * @param transform rectangle's transformation
	 * @param opacity rectangle's opacity (0.0f-1.0f)
	 * @param display tag's display argument
	 */
	public SVGRectangle(String id, String style, float x, float y, float rx, float ry, float width, float height, 
			String fill, String stroke, float stroke_width, String transform, float opacity, String display)
	{
		super(id,style,transform,opacity,display,fill,stroke,stroke_width);
		this.x = x;
		this.y = y;
		this.rx = ry;
		this.ry = ry;
		this.width = width;
		this.height = height;
	}


	/**
	 * Constructor by xml's DOM tag element
	 * @param e xml's DOM tag element
	 */
	SVGRectangle(Element e)
	{
		super(e);
		if(e.hasAttribute("x"))
			x = Float.parseFloat(e.getAttribute("x"));
		if(e.hasAttribute("y"))
			y = Float.parseFloat(e.getAttribute("y"));
		if(e.hasAttribute("rx"))
			rx = Float.parseFloat(e.getAttribute("rx"));
		if(e.hasAttribute("ry"))
			ry = Float.parseFloat(e.getAttribute("ry"));
		
		width = Float.parseFloat(e.getAttribute("width"));
		height = Float.parseFloat(e.getAttribute("height"));
	}


	/**
	 * Rectangle's X getter
	 * @return rectangle's X coordinate
	 */
	public float getX() {
		return x;
	}
	/**
	 * Rectangle's X setter
	 * @param x new rectangle's X coordinate
	 */
	public void setX(float x) {
		this.x = x;
	}
	/**
	 * Rectangle's Y getter
	 * @return rectangle's Y coordinate
	 */
	public float getY() {
		return y;
	}
	/**
	 * Rectangle's Y setter
	 * @param y new rectangle's Y coordinate
	 */
	public void setY(float y) {
		this.y = y;
	}
	/**
	 * Rectangle's RX getter
	 * @return rectangle's corner X radius
	 */
	public float getRx() {
		return rx;
	}
	/**
	 * Rectangle's RX setter
	 * @param rx new rectangle's corner X radius
	 */
	public void setRx(float rx) {
		this.rx = rx;
	}
	/**
	 * Rectangle's RY getter
	 * @return rectangle's corner Y radius
	 */
	public float getRy() {
		return ry;
	}
	/**
	 * Rectangle's RY setter
	 * @param ry new rectangle's corner Y radius
	 */
	public void setRy(float ry) {
		this.ry = ry;
	}
	/**
	 * Rectangle's width getter
	 * @return rectangle's width
	 */
	public float getWidth() {
		return width;
	}
	/**
	 * Rectangle's width setter
	 * @param width new rectangle's width
	 */
	public void setWidth(float width) {
		this.width = width;
	}
	/**
	 * Rectangle's height getter
	 * @return rectangle's height
	 */
	public float getHeight() {
		return height;
	}
	/**
	 * Rectangle's height setter
	 * @param height new rectangle's height
	 */
	public void setHeight(float height) {
		this.height = height;
	}

	@Override
	public void scale(float factor)
	{
		x -= width*(factor-1.0f)/2;
		y -= height*(factor-1.0f)/2;
		width *= factor;
		height *= factor;
		rx *= factor;
		ry *= factor;
	}

	@Override
	public void scale(float factorx, float factory)
	{
		x -= width*(factorx-1.0f)/2;
		y -= height*(factory-1.0f)/2;
		width *= factorx;
		height *= factory;
		rx *= factorx;
		ry *= factory;
	}

	@Override
	public void scale(float factor, float cex, float cey)
	{
		x = (x-cex)*factor+cex;
		y = (y-cey)*factor+cey;
		width *= factor;
		height *= factor;
		rx *= factor;
		ry *= factor;
	}

	@Override
	public void scale(float factorx, float factory, float cex, float cey)
	{
		x = (x-cex)*factorx+cex;
		y = (y-cey)*factory+cey;
		width *= factorx;
		height *= factory;
		rx *= factorx;
		ry *= factory;
	}

	@Override
	public void rotate(float angle)
	{
		transform.rotate(angle);
	}

	@Override
	public void rotate(float angle, float cex, float cey)
	{
		angle %= 360.0f;
		if(angle == 0.0f)
			return;
		if(angle % 90.0f != 0)
		{
			transform.rotate(angle, cex, cey);
			return;
		}
	
		float bx = x - cex, by = y - cey;

		float sinus = (float) Math.sin(Math.toRadians(angle));
		float cosinus = (float) Math.cos(Math.toRadians(angle));

		x = bx * cosinus - by * sinus;
		y = bx * sinus + by * cosinus;

		x += cex;
		y += cey;

		if(angle == 90.0f)
		{		
			float tmp = width;
			width = height;
			height = tmp;
			
			x -= width;
		}
		else if(angle == 180.0f)
		{		
			x -= width;
			y -= height;
		}
		else if(angle == 270.0f)
		{		
			float tmp = width;
			width = height;
			height = tmp;
			
			y -= height;
		}
		
	}
	
	@Override
	public void translate(float tx, float ty)
	{
		x += tx;
		y += ty;
	}
	
	@Override
	public String toString() {
		String output;
		output = "<rect";
		if(id != "")
			output += " id=\""+id+"\"";
		output += " width=\""+width+"\" height=\""+height+"\"";
		if(x >= 0.0f) 
			output += " x=\""+x+"\"";
		if(y >= 0.0f) 
			output += " y=\""+y+"\"";
		if(rx >= 0.0f) 
			output += " rx=\""+rx+"\"";
		if(ry >= 0.0f) 
			output += " ry=\""+ry+"\"";
		if(opacity >= 0.0f)
			output+= " opacity=\""+opacity+"\"";
		if(transform != null)
			output+= " transform=\""+transform+"\"";
		if(fill != "")
			output+= " fill=\""+fill+"\"";
		if(stroke != "")
			output+= " stroke=\""+stroke+"\"";
		if(stroke_width >= 0.0f)
			output+= " stroke-width=\""+stroke_width+"\"";
		if(style != "")
			output+= " style=\""+style+"\"";
		if(display != "")
			output+= " display=\""+display+"\"";
		output+= " />";
		
		return output;
	}
}

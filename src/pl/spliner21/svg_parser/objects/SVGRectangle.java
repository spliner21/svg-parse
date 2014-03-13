package pl.spliner21.svg_parser.objects;

import org.w3c.dom.Element;

/** 
 * Class representing <rect> tag in SVG file
 * @author spliner21
 * @version 1.0
 */
public class SVGRectangle extends SVGObject {
	Float x = -1.0f, y = -1.0f;
	Float rx = -1.0f, ry = -1.0f;
	int width = -1, height = -1;

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
		width = 1;
		height = 1;
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
	public SVGRectangle(String id, String style, float x, float y, float rx, float ry, int width, int height, 
			String fill, String stroke, Float stroke_width, String transform, Float opacity, String display)
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
		
		width = Integer.parseInt(e.getAttribute("width"));
		height = Integer.parseInt(e.getAttribute("height"));
	}


	/**
	 * Rectangle's X getter
	 * @return rectangle's X coordinate
	 */
	public Float getX() {
		return x;
	}

	/**
	 * Rectangle's X setter
	 * @param x new rectangle's X coordinate
	 */
	public void setX(Float x) {
		this.x = x;
	}

	/**
	 * Rectangle's Y getter
	 * @return rectangle's Y coordinate
	 */
	public Float getY() {
		return y;
	}

	/**
	 * Rectangle's Y setter
	 * @param y new rectangle's Y coordinate
	 */
	public void setY(Float y) {
		this.y = y;
	}

	/**
	 * Rectangle's RX getter
	 * @return rectangle's corner X radius
	 */
	public Float getRx() {
		return rx;
	}

	/**
	 * Rectangle's RX setter
	 * @param rx new rectangle's corner X radius
	 */
	public void setRx(Float rx) {
		this.rx = rx;
	}

	/**
	 * Rectangle's RY getter
	 * @return rectangle's corner Y radius
	 */
	public Float getRy() {
		return ry;
	}

	/**
	 * Rectangle's RY setter
	 * @param ry new rectangle's corner Y radius
	 */
	public void setRy(Float ry) {
		this.ry = ry;
	}

	/**
	 * Rectangle's width getter
	 * @return rectangle's width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Rectangle's width setter
	 * @param width new rectangle's width
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * Rectangle's height getter
	 * @return rectangle's height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Rectangle's height setter
	 * @param height new rectangle's height
	 */
	public void setHeight(int height) {
		this.height = height;
	}
	

	/*
	 * Method which returns generated tags code
	 * author: Tomasz Szo³tysek
	 */
	@Override
	public String getCode() {
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
		if(transform != "")
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

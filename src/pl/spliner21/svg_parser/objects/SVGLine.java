package pl.spliner21.svg_parser.objects;

import org.w3c.dom.Element;

/** 
 * Class representing <line> tag in SVG file
 * @author spliner21
 * @version 1.0
 */
public class SVGLine extends SVGObject {
	int x1,y1,x2,y2;

	/**
	 * Default constructor
	 */
	public SVGLine()
	{
		super();
		x1 = 0;
		y1 = 0;
		x2 = 1;
		y2 = 1;
		stroke = "#0000ff";
		stroke_width = 1.0f;
	}

	/**
	 * Constructor by parameters
	 * @param id tag's ID parameter
	 * @param style tag's style parameter
	 * @param x1 line's first point's x coordinate
	 * @param y1 line's first point's y coordinate
	 * @param x2 line's second point's x coordinate
	 * @param y2 line's second point's y coordinate
	 * @param stroke line's stroke color
	 * @param stroke_width line's stroke_width
	 * @param transform line's transformation
	 * @param opacity line's opacity (0.0f-1.0f)
	 * @param display tag's display parameter
	 */
	public SVGLine(String id, String style, int x1, int y1, int x2, int y2, 
			String stroke, Float stroke_width, String transform, Float opacity, String display)
	{
		super(id,style,transform,opacity,display,"",stroke,stroke_width);
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}

	/**
	 * Constructor by xml's DOM tag element
	 * @param e xml's DOM tag element
	 */
	SVGLine(Element e)
	{
		super(e);
		
		x1 = Integer.parseInt(e.getAttribute("x1"));
		y1 = Integer.parseInt(e.getAttribute("y1"));
		x2 = Integer.parseInt(e.getAttribute("x2"));
		y2 = Integer.parseInt(e.getAttribute("y2"));
	}

	/* Getters & Setters */
	/**
	 * Line's first point's X coordinate getter
	 * @return Line's first point's X coordinate
	 */
	public int getX1() {
		return x1;
	}

	/**
	 * Line's first point's X coordinate setter
	 * @param x1 Line's first point's X coordinate
	 */
	public void setX1(int x1) {
		this.x1 = x1;
	}
	/**
	 * Line's first point's Y coordinate getter
	 * @return Line's first point's Y coordinate
	 */
	public int getY1() {
		return y1;
	}

	/**
	 * Line's first point's Y coordinate setter
	 * @param y1 Line's first point's Y coordinate
	 */
	public void setY1(int y1) {
		this.y1 = y1;
	}

	/**
	 * Line's second point's X coordinate getter
	 * @return Line's second point's X coordinate
	 */
	public int getX2() {
		return x2;
	}

	/**
	 * Line's second point's X coordinate setter
	 * @param x2 Line's second point's X coordinate
	 */
	public void setX2(int x2) {
		this.x2 = x2;
	}
	/**
	 * Line's second point's Y coordinate getter
	 * @return Line's second point's Y coordinate
	 */
	public int getY2() {
		return y2;
	}

	/**
	 * Line's second point's Y coordinate setter
	 * @param y2 Line's second point's Y coordinate
	 */
	public void setY2(int y2) {
		this.y2 = y2;
	}
	
	/**
	 * Line's points coordinate setter
	 * @param x1 Line's first point's X coordinate
	 * @param y1 Line's first point's Y coordinate
	 * @param x2 Line's second point's X coordinate
	 * @param y2 Line's second point's Y coordinate
	 */
	public void setPoints(int x1, int y1, int x2, int y2)
	{
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
	
	@Override
	public String getCode() {
		String output;
		output = "<line";
		if(id != "")
			output += " id=\""+id+"\"";
		output += " x1=\""+x1+"\" y1=\""+y1+"\" x2=\""+x2+"\" y2=\""+y2+"\"";
		if(opacity >= 0.0f)
			output+= " opacity=\""+opacity+"\"";
		if(transform != "")
			output+= " transform=\""+transform+"\"";
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

package pl.spliner21.svg_parser.objects;

import org.w3c.dom.Element;

/** 
 * Class representing <line> tag in SVG file
 * @author spliner21
 * @version 1.0
 */
public class SVGLine extends SVGObject {
	Float x1,y1,x2,y2;

	/**
	 * Default constructor
	 */
	public SVGLine()
	{
		super();
		x1 = 0.0f;
		y1 = 0.0f;
		x2 = 1.0f;
		y2 = 1.0f;
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
	public SVGLine(String id, String style, Float x1, Float y1, Float x2, Float y2, 
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
		
		x1 = Float.parseFloat(e.getAttribute("x1"));
		y1 = Float.parseFloat(e.getAttribute("y1"));
		x2 = Float.parseFloat(e.getAttribute("x2"));
		y2 = Float.parseFloat(e.getAttribute("y2"));
	}

	/* Getters & Setters */
	/**
	 * Line's first point's X coordinate getter
	 * @return Line's first point's X coordinate
	 */
	public Float getX1() {
		return x1;
	}
	/**
	 * Line's first point's X coordinate setter
	 * @param x1 Line's first point's X coordinate
	 */
	public void setX1(Float x1) {
		this.x1 = x1;
	}
	/**
	 * Line's first point's Y coordinate getter
	 * @return Line's first point's Y coordinate
	 */
	public Float getY1() {
		return y1;
	}
	/**
	 * Line's first point's Y coordinate setter
	 * @param y1 Line's first point's Y coordinate
	 */
	public void setY1(Float y1) {
		this.y1 = y1;
	}
	/**
	 * Line's second point's X coordinate getter
	 * @return Line's second point's X coordinate
	 */
	public Float getX2() {
		return x2;
	}
	/**
	 * Line's second point's X coordinate setter
	 * @param x2 Line's second point's X coordinate
	 */
	public void setX2(Float x2) {
		this.x2 = x2;
	}
	/**
	 * Line's second point's Y coordinate getter
	 * @return Line's second point's Y coordinate
	 */
	public Float getY2() {
		return y2;
	}
	/**
	 * Line's second point's Y coordinate setter
	 * @param y2 Line's second point's Y coordinate
	 */
	public void setY2(Float y2) {
		this.y2 = y2;
	}
	
	/**
	 * Line's points coordinate setter
	 * @param x1 Line's first point's X coordinate
	 * @param y1 Line's first point's Y coordinate
	 * @param x2 Line's second point's X coordinate
	 * @param y2 Line's second point's Y coordinate
	 */
	public void setPoints(Float x1, Float y1, Float x2, Float y2)
	{
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}

	@Override
	public void scale(Float factor)
	{
		Float cex,cey;
		if(x2 > x1)
			cex = x2-x1;
		else cex = x1-x2;
		if(y2 > y1)
			cey = y2-y1;
		else cey = y1-y2;
		x1 = (x1-cex)*factor+cex;
		y1 = (y1-cey)*factor+cey;
		x2 = (x2-cex)*factor+cex;
		y2 = (y2-cey)*factor+cey;
	}

	@Override
	public void scale(Float factorx, Float factory)
	{
		Float cex,cey;
		if(x2 > x1)
			cex = x2-x1;
		else cex = x1-x2;
		if(y2 > y1)
			cey = y2-y1;
		else cey = y1-y2;
		x1 = (x1-cex)*factorx+cex;
		y1 = (y1-cey)*factory+cey;
		x2 = (x2-cex)*factorx+cex;
		y2 = (y2-cey)*factory+cey;
	}

	@Override
	public void scale(Float factor, Float cex, Float cey)
	{
		x1 = (x1-cex)*factor+cex;
		y1 = (y1-cey)*factor+cey;
		x2 = (x2-cex)*factor+cex;
		y2 = (y2-cey)*factor+cey;
	}

	@Override
	public void scale(Float factorx, Float factory, Float cex, Float cey)
	{
		x1 = (x1-cex)*factorx+cex;
		y1 = (y1-cey)*factory+cey;
		x2 = (x2-cex)*factorx+cex;
		y2 = (y2-cey)*factory+cey;
	}

	@Override
	public void rotate(Float angle)
	{
		Float cex,cey;
		if(x2 > x1)
			cex = x2-x1;
		else cex = x1-x2;
		if(y2 > y1)
			cey = y2-y1;
		else cey = y1-y2;
		
		x1 -= cex;
		y1 -= cey;
		x2 -= cex;
		y2 -= cey;

		Float sinus = (float) Math.sin(Math.toRadians(angle));
		Float cosinus = (float) Math.cos(Math.toRadians(angle));

		x1 = x1 * cosinus - y1 * sinus;
		y1 = x1 * sinus + y1 * cosinus;
		x2 = x2 * cosinus - y2 * sinus;
		y2 = x2 * sinus + y2 * cosinus;

		x1 += cex;
		y1 += cey;
		x2 += cex;
		y2 += cey;
	}

	@Override
	public void rotate(Float angle, Float cex, Float cey)
	{
		x1 -= cex;
		y1 -= cey;
		x2 -= cex;
		y2 -= cey;

		Float sinus = (float) Math.sin(Math.toRadians(angle));
		Float cosinus = (float) Math.cos(Math.toRadians(angle));

		x1 = x1 * cosinus - y1 * sinus;
		y1 = x1 * sinus + y1 * cosinus;
		x2 = x2 * cosinus - y2 * sinus;
		y2 = x2 * sinus + y2 * cosinus;

		x1 += cex;
		y1 += cey;
		x2 += cex;
		y2 += cey;
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
		if(transform != null)
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

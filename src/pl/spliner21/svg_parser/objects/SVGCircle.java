package pl.spliner21.svg_parser.objects;

import org.w3c.dom.Element;

/** 
 * class representing <circle> tag in SVG file
 * @author spliner21
 * @version 1.0
 */
public class SVGCircle extends SVGObject {
	Float cx=-1.0f,cy=-1.0f;
	Float r=-1.0f;

	
	/**
	 * Default constructor
	 */
	public SVGCircle()
	{
		super();
		cx = 1.0f;
		cy = 1.0f;
		r = 1.0f;
	}

	/**
	 * Constructor by arguments
	 * @param id tag's ID argument
	 * @param style tag's style argument
	 * @param cx circle's center x coordinate
	 * @param cy circle's center y coordinate
	 * @param r circle's radius
	 * @param fill circle's fill color
	 * @param stroke circle's stroke color
	 * @param stroke_width circle's stroke_width
	 * @param transform circle's transformation
	 * @param opacity circle's opacity (0.0f-1.0f)
	 * @param display tag's display argument
	 */
	public SVGCircle(String id, String style, float cx, float cy, float r, 
			String fill, String stroke, Float stroke_width, String transform, Float opacity, String display)
	{
		super(id,style,transform,opacity,display,fill,stroke,stroke_width);
		this.cx = cx;
		this.cy = cy;
		this.r = r;
	}
	
	/**
	 * Constructor by xml's DOM tag element
	 * @param e xml's DOM tag element
	 */
	public SVGCircle(Element e)
	{
		super(e);
		
		if(e.hasAttribute("cx"))
			cx = Float.parseFloat(e.getAttribute("cx"));
		if(e.hasAttribute("cy"))
			cy = Float.parseFloat(e.getAttribute("cy"));
		if(e.hasAttribute("r"))
			r = Float.parseFloat(e.getAttribute("r"));
	}
	
	/* Getters & Setters */
	
	/**
	 * CX's getter
	 * @return circle's center X coordinate
	 */
	public Float getCX()
	{
		return cx;
	}
	/**
	 * CY's getter
	 * @return circle's center Y coordinate
	 */
	public Float getCY()
	{
		return cy;
	}
	/**
	 * CX's setter
	 * @param cx circle's center X coordinate
	 */
	public void setCX(Float cx)
	{
		this.cx = cx;
	}
	/**
	 * CY's setter
	 * @param cy circle's center Y coordinate
	 */
	public void setCY(Float cy)
	{
		this.cy = cy;
	}
	/**
	 * Circle's center setter
	 * @param cx circle's center X coordinate	 
	 * @param cy circle's center Y coordinate
	 */
	public void setCenter(Float cx, Float cy)
	{
		this.cx = cx;
		this.cy = cy;
	}
	
	/**
	 * Circle's radius getter
	 * @return circle's radius
	 */
	public Float getRadius()
	{
		return r;
	}
	/**
	 * Circle's radius setter
	 * @param r circle's radius
	 */
	public void setRadius(Float r)
	{
		this.r = r;
	}

	@Override
	public String getCode() {
		String output;
		output = "<circle";
		if(id != "")
			output += " id=\""+id+"\"";
		if(cx >= 0.0f) 
			output += " cx=\""+cx+"\"";
		if(cy >= 0.0f) 
			output += " cy=\""+cy+"\"";
		if(r >= 0.0f) 
			output += " r=\""+r+"\"";
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

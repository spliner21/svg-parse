package pl.spliner21.svg_parser.objects;

import org.w3c.dom.Element;

/* class representing <circle> tag in SVG file
 * @author: Tomasz Szo³tysek
 * @version: 1.0
 */
public class SVGCircle extends SVGObject {
	Float cx=-1.0f,cy=-1.0f;
	Float r=-1.0f;

	
	public SVGCircle()
	{
		super();
		cx = 1.0f;
		cy = 1.0f;
		r = 1.0f;
	}

	public SVGCircle(String id, String style, float cx, float cy, float r, 
			String fill, String stroke, Float stroke_width, String transform, Float opacity, String display)
	{
		super(id,style,transform,opacity,display,fill,stroke,stroke_width);
		this.cx = cx;
		this.cy = cy;
		this.r = r;
	}
	
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
	
	public Float getCX()
	{
		return cx;
	}
	public Float getCY()
	{
		return cy;
	}
	public void setCX(Float cx)
	{
		this.cx = cx;
	}
	public void setCY(Float cy)
	{
		this.cy = cy;
	}
	public void setCenter(Float cx, Float cy)
	{
		this.cx = cx;
		this.cy = cy;
	}
	public Float getRadius()
	{
		return r;
	}
	public void setRadius(Float r)
	{
		this.r = r;
	}

	/*
	 * Method which returns generated tags code
	 * author: Tomasz Szo³tysek
	 */
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

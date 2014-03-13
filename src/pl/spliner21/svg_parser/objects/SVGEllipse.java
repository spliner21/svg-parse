package pl.spliner21.svg_parser.objects;

import org.w3c.dom.Element;

/** 
 * Class representing <ellipse> tag in SVG file
 * @author spliner21
 * @version 1.0
 */
public class SVGEllipse extends SVGObject {
	Float cx=-1.0f,cy=-1.0f;
	Float rx=-1.0f,ry=-1.0f;
	String fill="",stroke="";
	Float stroke_width = -1.0f;

	/**
	 * Default constructor
	 */
	public SVGEllipse()
	{
		super();
		cx = 1.0f;
		cy = 1.0f;
		rx = 1.0f;
		ry = 1.0f;
		stroke = "#0000ff";
		stroke_width = 1.0f;
	}

	/**
	 * Constructor by parameters
	 * @param id tag's ID parameter
	 * @param style tag's style parameter
	 * @param cx ellipse's center x coordinate
	 * @param cy ellipse's center y coordinate
	 * @param rx ellipse's X radius
	 * @param ry ellipse's Y radius
	 * @param fill ellipse's fill color
	 * @param stroke ellipse's stroke color
	 * @param stroke_width ellipse's stroke_width
	 * @param transform ellipse's transformation
	 * @param opacity ellipse's opacity (0.0f-1.0f)
	 * @param display tag's display parameter
	 */
	public SVGEllipse(String id, String style, float cx, float cy, float rx, float ry, 
			String fill, String stroke, Float stroke_width, String transform, Float opacity, String display)
	{
		super(id,style,transform,opacity,display,fill,stroke,stroke_width);
		this.cx = cx;
		this.cy = cy;
		this.rx = rx;
		this.ry = ry;
	}
	

	/**
	 * Constructor by xml's DOM tag element
	 * @param e xml's DOM tag element
	 */
	SVGEllipse(Element e)
	{
		super(e);

		if(e.hasAttribute("cx"))
			cx = Float.parseFloat(e.getAttribute("cx"));
		if(e.hasAttribute("cy"))
			cy = Float.parseFloat(e.getAttribute("cy"));
		if(e.hasAttribute("rx"))
			rx = Float.parseFloat(e.getAttribute("rx"));
		if(e.hasAttribute("ry"))
			ry = Float.parseFloat(e.getAttribute("ry"));
	}

/* Getters & Setters */
	
	/**
	 * CX's getter
	 * @return ellipse's center X coordinate
	 */
	public Float getCX()
	{
		return cx;
	}
	/**
	 * CY's getter
	 * @return ellipse's center Y coordinate
	 */
	public Float getCY()
	{
		return cy;
	}
	/**
	 * CX's setter
	 * @param cx ellipse's center X coordinate
	 */
	public void setCX(Float cx)
	{
		this.cx = cx;
	}
	/**
	 * CY's setter
	 * @param cy ellipse's center Y coordinate
	 */
	public void setCY(Float cy)
	{
		this.cy = cy;
	}
	/**
	 * Ellipse's center setter
	 * @param cx ellipse's center X coordinate	 
	 * @param cy ellipse's center Y coordinate
	 */
	public void setCenter(Float cx, Float cy)
	{
		this.cx = cx;
		this.cy = cy;
	}
	
	
	/**
	 * RX's getter
	 * @return ellipse's X radius
	 */
	public Float getXRadius()
	{
		return rx;
	}
	
	/**
	 * RX's setter
	 * @param r ellipse's X radius
	 */
	public void setXRadius(Float r)
	{
		rx = r;
	}
	/**
	 * RY's getter
	 * @return ellipse's Y radius
	 */
	public Float getYRadius()
	{
		return ry;
	}
	/**
	 * RY's setter
	 * @param r ellipse's Y radius
	 */
	public void setYRadius(Float r)
	{
		ry = r;
	}
	/**
	 * Radius setter (will change both rx's and ry's value to r)
	 * @param r ellipse's radius
	 */
	public void setRadius(Float r)
	{
		this.rx = r;
		this.ry = r;
	}
	/**
	 * Radius setter
	 * @param rx ellipse's X radius
	 * @param ry ellipse's Y radius
	 */
	public void setXYRadius(Float rx, Float ry)
	{
		this.rx = rx;
		this.ry = ry;
	}

	@Override
	public String getCode() {
		String output;
		output = "<ellipse";
		if(id != "")
			output += " id=\""+id+"\"";
		if(cx >= 0.0f) 
			output += " cx=\""+cx+"\"";
		if(cy >= 0.0f) 
			output += " cy=\""+cy+"\"";
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

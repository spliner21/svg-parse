package pl.spliner21.svg_parser.objects;

import org.w3c.dom.Element;

/* class representing <ellipse> tag in SVG file
 * @author: Tomasz Szo³tysek
 * @version: 1.0
 */
public class SVGEllipse extends SVGObject {
	Float cx=-1.0f,cy=-1.0f;
	Float rx=-1.0f,ry=-1.0f;
	String fill="",stroke="";
	Float stroke_width = -1.0f;
	
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

	public SVGEllipse(String id, String style, float cx, float cy, float rx, float ry, 
			String fill, String stroke, Float stroke_width, String transform, Float opacity, String display)
	{
		super(id,style,transform,opacity,display,fill,stroke,stroke_width);
		this.cx = cx;
		this.cy = cy;
		this.rx = rx;
		this.ry = ry;
	}
	
	
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
	public Float getXRadius()
	{
		return rx;
	}
	public void setXRadius(Float r)
	{
		rx = r;
	}
	public Float getYRadius()
	{
		return ry;
	}
	public void setYRadius(Float r)
	{
		ry = r;
	}
	public void setRadius(Float r)
	{
		this.rx = r;
		this.ry = r;
	}
	public void setXYRadius(Float rx, Float ry)
	{
		this.rx = rx;
		this.ry = ry;
	}

	/*
	 * Method which returns generated tags code
	 * author: Tomasz Szo³tysek
	 */
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

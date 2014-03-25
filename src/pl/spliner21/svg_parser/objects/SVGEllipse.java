package pl.spliner21.svg_parser.objects;

import org.w3c.dom.Element;

/** 
 * Class representing <circle> and <ellipse> tags in SVG file
 * @author spliner21
 * @version 1.0
 */
public class SVGEllipse extends SVGObject {
	Float cx=-1.0f,cy=-1.0f;
	Float rx=-1.0f,ry=-1.0f;

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
	 * @param cx object's center x coordinate
	 * @param cy object's center y coordinate
	 * @param rx object's X radius
	 * @param ry object's Y radius
	 * @param fill object's fill color
	 * @param stroke object's stroke color
	 * @param stroke_width object's stroke_width
	 * @param transform object's transformation
	 * @param opacity object's opacity (0.0f-1.0f)
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
		if(e.hasAttribute("r"))
		{
			rx = Float.parseFloat(e.getAttribute("r"));
			ry = Float.parseFloat(e.getAttribute("r"));
		}
		else if(e.hasAttribute("rx"))
		{
			rx = Float.parseFloat(e.getAttribute("rx"));
			if(e.hasAttribute("ry"))
				ry = Float.parseFloat(e.getAttribute("ry"));
		}
	}

/* Getters & Setters */
	
	/**
	 * CX's getter
	 * @return object's center X coordinate
	 */
	public Float getCX()
	{
		return cx;
	}
	/**
	 * CY's getter
	 * @return object's center Y coordinate
	 */
	public Float getCY()
	{
		return cy;
	}
	/**
	 * CX's setter
	 * @param cx object's center X coordinate
	 */
	public void setCX(Float cx)
	{
		this.cx = cx;
	}
	/**
	 * CY's setter
	 * @param cy object's center Y coordinate
	 */
	public void setCY(Float cy)
	{
		this.cy = cy;
	}
	/**
	 * Object's center setter
	 * @param cx object's center X coordinate	 
	 * @param cy object's center Y coordinate
	 */
	public void setCenter(Float cx, Float cy)
	{
		this.cx = cx;
		this.cy = cy;
	}
	
	
	/**
	 * RX's getter
	 * @return object's X radius
	 */
	public Float getXRadius()
	{
		return rx;
	}
	
	/**
	 * RX's setter
	 * @param r object's X radius
	 */
	public void setXRadius(Float r)
	{
		rx = r;
	}
	/**
	 * RY's getter
	 * @return object's Y radius
	 */
	public Float getYRadius()
	{
		return ry;
	}
	/**
	 * RY's setter
	 * @param r object's Y radius
	 */
	public void setYRadius(Float r)
	{
		ry = r;
	}
	/**
	 * Radius setter (will change both rx's and ry's value to r)
	 * @param r object's radius
	 */
	public void setRadius(Float r)
	{
		this.rx = r;
		this.ry = r;
	}
	/**
	 * Radius setter
	 * @param rx object's X radius
	 * @param ry object's Y radius
	 */
	public void setXYRadius(Float rx, Float ry)
	{
		this.rx = rx;
		this.ry = ry;
	}

	@Override
	public void scale(Float factor)
	{
		rx*=Math.abs(factor);
		ry*=Math.abs(factor);
	}

	@Override
	public void scale(Float factorx, Float factory)
	{
		rx*=Math.abs(factorx);
		ry*=Math.abs(factory);
	}

	@Override
	public void scale(Float factor, Float cex, Float cey)
	{
		rx *= Math.abs(factor);
		ry *= Math.abs(factor);
		cx = (cx-cex)*factor+cex;
		cy = (cy-cey)*factor+cey;
	}

	@Override
	public void scale(Float factorx, Float factory, Float cex, Float cey)
	{
		rx *= Math.abs(factorx);
		ry *= Math.abs(factory);
		cx = (cx-cex)*factorx+cex;
		cy = (cy-cey)*factory+cey;
	}

	@Override
	public void rotate(Float angle)
	{
		transform.rotate(angle);
	}

	@Override
	public void rotate(Float angle, Float cex, Float cey)
	{
		cx -= cex;
		cy -= cey;

		Float sinus = (float) Math.sin(Math.toRadians(angle));
		Float cosinus = (float) Math.cos(Math.toRadians(angle));
		
		cx = cx * cosinus - cy * sinus;
		cy = cx * sinus + cy * cosinus;
		
		cx += cex;
		cy += cey;
		
		transform.rotate(angle);
	}
	
	
	@Override
	public String getCode() {
		String output;
		if(!rx.equals(ry))
		{
			output = "<ellipse";
			if(id != "")
				output += " id=\""+id+"\"";
			if(rx >= 0.0f) 
				output += " rx=\""+rx+"\"";
			if(ry >= 0.0f) 
				output += " ry=\""+ry+"\"";
		}
		else 
		{
			output = "<circle";
			if(id != "")
				output += " id=\""+id+"\"";
			if(rx >= 0.0f) 
				output += " r=\""+rx+"\"";
		}
		if(cx >= 0.0f) 
			output += " cx=\""+cx+"\"";
		if(cy >= 0.0f) 
			output += " cy=\""+cy+"\"";
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

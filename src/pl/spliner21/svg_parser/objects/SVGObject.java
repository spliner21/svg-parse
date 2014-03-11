package pl.spliner21.svg_parser.objects;

import org.w3c.dom.Element;


/* abstract class representing tag in SVG file,
 * element of DOM tree
 * @author: Tomasz Szo³tysek
 * @version: 1.0
 */
public abstract class SVGObject {
	protected String id = "";
	String display = "";
	protected String style = "";
	String transform = "";
	Float opacity = -1.0f;
	String fill="",stroke="";
	Float stroke_width = -1.0f;
	
	protected SVGObject()
	{
		id = "object-e";
		display = "true";
		fill = "#ffffff";
		stroke = "#0000ff";
		stroke_width = 1.0f;
	}
	
	protected SVGObject(String id, String style, String transform, Float opacity, String display,
			String fill, String stroke, Float stroke_width)
	{
		this.id = id;
		this.style = style;
		this.transform = transform;
		this.opacity = opacity;
		this.display = display;
		this.fill = fill;
		this.stroke = stroke;
		this.stroke_width = stroke_width;
	}
	
	protected SVGObject(Element e) {
		id = e.getAttribute("id");
		if(e.hasAttribute("display"))
			display = e.getAttribute("display");
		if(e.hasAttribute("style"))
			style = e.getAttribute("style");
		if(e.hasAttribute("transform"))
			transform = e.getAttribute("transform");
		if(e.hasAttribute("opacity"))
			opacity = Float.parseFloat(e.getAttribute("opacity"));
		if(e.hasAttribute("fill"))
			fill = e.getAttribute("fill");
		if(e.hasAttribute("stroke"))
			stroke = e.getAttribute("stroke");
		if(e.hasAttribute("stroke-width"))
			stroke_width = Float.parseFloat(e.getAttribute("stroke-width"));
	}

	public String getID()
	{
		return id;
	}
	public void setID(String ID)
	{
		id = ID;
	}
	
	public String getDisplay()
	{
		return display;
	}
	public void setDisplay(String Display)
	{
		display = Display;
	}
	
	public String getStyle()
	{
		return style;
	}
	public void setStyle(String Style)
	{
		style = Style;
	}
	
	public String getTransform()
	{
		return transform;
	}
	public void setTransform(String Transform)
	{
		transform = Transform;
	}
	
	public Float getOpacity()
	{
		return opacity;
	}
	public void setOpacity(Float Opacity)
	{
		opacity = Opacity;
	}
	public String getFill()
	{
		return fill;
	}
	public void setFill(String fill)
	{
		this.fill = fill;
	}
	public String getStroke()
	{
		return stroke;
	}
	public void setStroke(String stroke)
	{
		this.stroke = stroke;
	}
	
	public Float getStrokeWidth()
	{
		return stroke_width;
	}
	public void setStrokeWidth(Float stroke_width)
	{
		this.stroke_width = stroke_width;
	}
	
	public abstract String getCode();
}

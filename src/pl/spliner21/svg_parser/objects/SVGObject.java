package pl.spliner21.svg_parser.objects;


import org.w3c.dom.Element;


/** 
 * Abstract class representing tag in SVG file,
 * element of DOM tree
 * @author spliner21
 * @version 1.0
 */
public abstract class SVGObject {
	protected String id = "";
	String display = "";
	protected String style = "";
	float opacity = -1.0f;
	String fill="",stroke="";
	float stroke_width = -1.0f;

	SVGTransform transform = null;
	/**
	 * Default constructor ( super() )
	 */
	protected SVGObject()
	{
		id = "object-e";
		display = "true";
		fill = "#ffffff";
		stroke = "#0000ff";
		stroke_width = 1.0f;
		
		transform = new SVGTransform("");
	}
	
	/**
	 * Constructor by parameters
	 * @param id tag's ID
	 * @param style tag's style
	 * @param transform tag's transform
	 * @param opacity tag's opacity
	 * @param display tag's display
	 * @param fill tag's fill color
	 * @param stroke tag's stroke color
	 * @param stroke_width tag's stroke width
	 */
	protected SVGObject(String id, String style, String transform, float opacity, String display,
			String fill, String stroke, float stroke_width)
	{
		this.id = id;
		this.style = style;
		
		this.transform = new SVGTransform(transform);
		
		this.opacity = opacity;
		this.display = display;
		this.fill = fill;
		this.stroke = stroke;
		this.stroke_width = stroke_width;
	}

	/**
	 * Constructor by xml's DOM tag element
	 * @param e xml's DOM tag element
	 */
	protected SVGObject(Element e) {
		id = e.getAttribute("id");
		if(e.hasAttribute("display"))
			display = e.getAttribute("display");
		if(e.hasAttribute("style"))
			style = e.getAttribute("style");
		if(e.hasAttribute("transform"))
			transform = new SVGTransform(e.getAttribute("transform"));
		if(e.hasAttribute("opacity"))
			opacity = Float.parseFloat(e.getAttribute("opacity"));
		if(e.hasAttribute("fill"))
			fill = e.getAttribute("fill");
		if(e.hasAttribute("stroke"))
			stroke = e.getAttribute("stroke");
		if(e.hasAttribute("stroke-width"))
			stroke_width = Float.parseFloat(e.getAttribute("stroke-width"));
		
		transform = new SVGTransform("");
	}

	
	
	/**
	 * ID's getter
	 * @return tag's ID
	 */
	public String getID()
	{
		return id;
	}
	/**
	 * ID's setter
	 * @param id tag's new ID
	 */
	public void setID(String id)
	{
		this.id = id;
	}
	
	/**
	 * Tag's display parameter getter
	 * @return tag's display parameter
	 */
	public String getDisplay()
	{
		return display;
	}
	/**
	 * Tag's display parameter setter
	 * @param display new tag's display status
	 */
	public void setDisplay(String display)
	{
		this.display = display;
	}
	
	/**
	 * Tag's style content getter
	 * @return tag's style parameter content
	 */
	public String getStyle()
	{
		return style;
	}
	/**
	 * Tag's style content setter
	 * @param style new tag's style parameter conent
	 */
	public void setStyle(String style)
	{
		this.style = style;
	}
	
	/**
	 * Tag's transform getter
	 * @return tag's transform
	 */
	public String getTransform()
	{
		return transform.getCode();
	}
	/**
	 * Tag's transform setter
	 * @param transform new tag's transform
	 */
	public void setTransform(String transform)
	{
		this.transform = new SVGTransform(transform);
	}
	
	/**
	 * Tag's opacity getter
	 * @return tag's opacity parameter
	 */
	public float getOpacity()
	{
		return opacity;
	}
	/**
	 * Tag's opacity setter
	 * @param opacity new tag's opacity parameter
	 */
	public void setOpacity(float opacity)
	{
		this.opacity = opacity;
	}
	/**
	 * Tag's fill getter
	 * @return tag's fill color
	 */
	public String getFill()
	{
		return fill;
	}
	/**
	 * Tag's fill setter
	 * @param fill tag's fill color
	 */
	public void setFill(String fill)
	{
		this.fill = fill;
	}
	/**
	 * Tag's stroke getter
	 * @return tag's stroke color
	 */
	public String getStroke()
	{
		return stroke;
	}
	/**
	 * Tag's stroke setter
	 * @param stroke tag's stroke color
	 */
	public void setStroke(String stroke)
	{
		this.stroke = stroke;
	}

	/**
	 * Tag's stroke width getter
	 * @return tag's stroke width
	 */
	public float getStrokeWidth()
	{
		return stroke_width;
	}
	/**
	 * Tag's stroke width setter
	 * @param stroke_width tag's stroke width
	 */
	public void setStrokeWidth(float stroke_width)
	{
		this.stroke_width = stroke_width;
	}

	/**
	 * Scale by factor
	 * @param factor scaling X factor (1.0f does nothing => 100% scale)
	 */
	public abstract void scale(float factor);
	
	/**
	 * Scale by factors
	 * @param factorx scaling X factor (1.0f does nothing => 100% scale)
	 * @param factory scaling Y factor (1.0f does nothing => 100% scale)
	 */
	public abstract void scale(float factorx, float factory);
	
	/**
	 * Scale by factor with scale's center
	 * @param factor scaling factor (1.0f does nothing => 100% scale)
	 * @param cex scaling center X coordinate
	 * @param cey scaling center Y coordinate
	 */
	public abstract void scale(float factor, float cex, float cey);
	
	/**
	 * Scale by factors with scale's center
	 * @param factorx scaling X factor (1.0f does nothing => 100% scale)
	 * @param factory scaling Y factor (1.0f does nothing => 100% scale)
	 * @param cex scaling center X coordinate
	 * @param cey scaling center Y coordinate
	 */
	public abstract void scale(float factorx, float factory, float cex, float cey);

	/**
	 * Rotate object by angle around point cx,cy.
	 * @param angle rotation angle
	 */
	public abstract void rotate(float angle);
	/**
	 * Rotate object by angle around point cx,cy.
	 * @param angle rotation angle
	 * @param cx rotation point's X coordinate
	 * @param cy rotation point's Y coordinate
	 */
	public abstract void rotate(float angle, float cex, float cey);
	
	/**
	 * Translate object by distance
	 * @param tx translation distance in X axis
	 * @param ty translation distance in Y axis
	 */
	public abstract void translate(float tx, float ty);
	
	/**
	 * Method that generates tag's SVG code part for this element.
	 * @return String, that contains generated piece of code for this tag and it's children (if any).
	 */
	public abstract String getCode();
}

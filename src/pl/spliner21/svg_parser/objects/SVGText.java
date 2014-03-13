package pl.spliner21.svg_parser.objects;

import org.w3c.dom.Element;

/** 
 * Class representing <text> tag in SVG file
 * @author spliner21
 * @version 1.0
 */
public class SVGText extends SVGObject {
	Float x = -1.0f,y = -1.0f;
	String text = "";

	/**
	 * Default constructor
	 */
	public SVGText()
	{
		super();
		x = 0.0f;
		y = 0.0f;
		text = "Text";
	}

	/**
	 * Constructor by arguments
	 * @param id tag's ID argument
	 * @param style tag's style argument
	 * @param x text's X coordinate
	 * @param y text's Y coordinate
	 * @param text text's content
	 * @param fill text's fill color
	 * @param stroke text's stroke color
	 * @param stroke_width text's stroke_width
	 * @param transform text's transformation
	 * @param opacity text's opacity (0.0f-1.0f)
	 * @param display tag's display argument
	 */
	public SVGText(String id, String style, float x, float y, String text, 
			String fill, String stroke, Float stroke_width, String transform, Float opacity, String display)
	{
		super(id,style,transform,opacity,display,fill,stroke,stroke_width);
		this.x = x;
		this.y = y;
		this.text = text;
	}

	/**
	 * Constructor by xml's DOM tag element
	 * @param e xml's DOM tag element
	 */
	SVGText(Element e)
	{
		super(e);

		if(e.hasAttribute("x"))
			x = Float.parseFloat(e.getAttribute("x"));
		if(e.hasAttribute("y"))
			y = Float.parseFloat(e.getAttribute("y"));
		text = e.getTextContent();
	}

	/**
	 * Text's X getter
	 * @return text's X coordinate
	 */
	public Float getX() {
		return x;
	}

	/**
	 * Text's X setter
	 * @param x new text's X coordinate
	 */
	public void setX(Float x) {
		this.x = x;
	}

	/**
	 * Text's Y getter
	 * @return text's Y coordinate
	 */
	public Float getY() {
		return y;
	}

	/**
	 * Text's Y setter
	 * @param y new text's Y coordinate
	 */
	public void setY(Float y) {
		this.y = y;
	}

	/**
	 * Text's content getter
	 * @return text's content
	 */
	public String getText() {
		return text;
	}

	/**
	 * Text's content setter
	 * @param text new text's content
	 */
	public void setText(String text) {
		this.text = text;
	}

	/*
	 * Method which returns generated tags code
	 * author: Tomasz Szo³tysek
	 */
	@Override
	public String getCode() {
		String output;
		output = "<text";
		if(id != "")
			output += " id=\""+id+"\"";
		if(x >= 0.0f) 
			output += " x=\""+x+"\"";
		if(y >= 0.0f) 
			output += " y=\""+y+"\"";
		if(fill != "")
			output+= " fill=\""+fill+"\"";
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
		output+= ">"+text+"</text>";
		
		return output;
	}
}

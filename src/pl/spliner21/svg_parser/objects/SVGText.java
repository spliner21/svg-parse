package pl.spliner21.svg_parser.objects;

import org.w3c.dom.Element;

/* class representing <text> tag in SVG file
 * @author: Tomasz Szo³tysek
 * @version: 1.0
 */
public class SVGText extends SVGObject {
	Float x = -1.0f,y = -1.0f;
	String text = "";

	public SVGText()
	{
		super();
		x = 0.0f;
		y = 0.0f;
		text = "Text";
	}

	public SVGText(String id, String style, float x, float y, String text, 
			String fill, String stroke, Float stroke_width, String transform, Float opacity, String display)
	{
		super(id,style,transform,opacity,display,fill,stroke,stroke_width);
		this.x = x;
		this.y = y;
		this.text = text;
	}
	
	SVGText(Element e)
	{
		super(e);

		if(e.hasAttribute("x"))
			x = Float.parseFloat(e.getAttribute("x"));
		if(e.hasAttribute("y"))
			y = Float.parseFloat(e.getAttribute("y"));
		text = e.getTextContent();
	}

	public Float getX() {
		return x;
	}

	public void setX(Float x) {
		this.x = x;
	}

	public Float getY() {
		return y;
	}

	public void setY(Float y) {
		this.y = y;
	}

	public String getText() {
		return text;
	}

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

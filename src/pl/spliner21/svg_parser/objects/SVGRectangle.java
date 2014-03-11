package pl.spliner21.svg_parser.objects;

import org.w3c.dom.Element;

/* class representing <rect> tag in SVG file
 * @author: Tomasz Szo³tysek
 * @version: 1.0
 */
public class SVGRectangle extends SVGObject {
	Float x = -1.0f, y = -1.0f;
	Float rx = -1.0f, ry = -1.0f;
	int width = -1, height = -1;

	public SVGRectangle()
	{
		super();
		x = 0.0f;
		y = 0.0f;
		rx = 0.0f;
		ry = 0.0f;
		width = 1;
		height = 1;
	}

	public SVGRectangle(String id, String style, float x, float y, float rx, float ry, int width, int height, 
			String fill, String stroke, Float stroke_width, String transform, Float opacity, String display)
	{
		super(id,style,transform,opacity,display,fill,stroke,stroke_width);
		this.x = x;
		this.y = y;
		this.rx = ry;
		this.ry = ry;
		this.width = width;
		this.height = height;
	}
	
	SVGRectangle(Element e)
	{
		super(e);
		if(e.hasAttribute("x"))
			x = Float.parseFloat(e.getAttribute("x"));
		if(e.hasAttribute("y"))
			y = Float.parseFloat(e.getAttribute("y"));
		if(e.hasAttribute("rx"))
			rx = Float.parseFloat(e.getAttribute("rx"));
		if(e.hasAttribute("ry"))
			ry = Float.parseFloat(e.getAttribute("ry"));
		
		width = Integer.parseInt(e.getAttribute("width"));
		height = Integer.parseInt(e.getAttribute("height"));
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

	public Float getRx() {
		return rx;
	}

	public void setRx(Float rx) {
		this.rx = rx;
	}

	public Float getRy() {
		return ry;
	}

	public void setRy(Float ry) {
		this.ry = ry;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	

	/*
	 * Method which returns generated tags code
	 * author: Tomasz Szo³tysek
	 */
	@Override
	public String getCode() {
		String output;
		output = "<rect";
		if(id != "")
			output += " id=\""+id+"\"";
		output += " width=\""+width+"\" height=\""+height+"\"";
		if(x >= 0.0f) 
			output += " x=\""+x+"\"";
		if(y >= 0.0f) 
			output += " y=\""+y+"\"";
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

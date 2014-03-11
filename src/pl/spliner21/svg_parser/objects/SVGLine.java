package pl.spliner21.svg_parser.objects;

import org.w3c.dom.Element;

/* class representing <line> tag in SVG file
 * @author: Tomasz Szo³tysek
 * @version: 1.0
 */
public class SVGLine extends SVGObject {
	int x1,y1,x2,y2;

	public SVGLine()
	{
		super();
		x1 = 0;
		y1 = 0;
		x2 = 1;
		y2 = 1;
		stroke = "#0000ff";
		stroke_width = 1.0f;
	}
	
	public SVGLine(String id, String style, int x1, int y1, int x2, int y2, 
			String stroke, Float stroke_width, String transform, Float opacity, String display)
	{
		super(id,style,transform,opacity,display,"",stroke,stroke_width);
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
	
	SVGLine(Element e)
	{
		super(e);
		
		x1 = Integer.parseInt(e.getAttribute("x1"));
		y1 = Integer.parseInt(e.getAttribute("y1"));
		x2 = Integer.parseInt(e.getAttribute("x2"));
		y2 = Integer.parseInt(e.getAttribute("y2"));
	}

	/* Getters & Setters */
	public int getX1() {
		return x1;
	}

	public void setX1(int x1) {
		this.x1 = x1;
	}
	public int getY1() {
		return y1;
	}

	public void setY1(int y1) {
		this.y1 = y1;
	}

	public int getX2() {
		return x2;
	}

	public void setX2(int x2) {
		this.x2 = x2;
	}
	public int getY2() {
		return y2;
	}

	public void setY2(int y2) {
		this.y2 = y2;
	}
	public void setPoints(int x1, int y1, int x2, int y2)
	{
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
	
	@Override
	public String getCode() {
		String output;
		output = "<line";
		if(id != "")
			output += " id=\""+id+"\"";
		output += " x1=\""+x1+"\" y1=\""+y1+"\" x2=\""+x2+"\" y2=\""+y2+"\"";
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
		output+= " />";
		
		return output;
	}
}

package pl.spliner21.svg_parser;

import org.w3c.dom.Element;

/* class representing <rect> tag in SVG file
 * @author: Tomasz Szo³tysek
 * @version: 1.0
 */
public class SVGRectangle extends SVGObject {
	Float x,y;
	Float rx,ry;
	int width,height;
	String fill,stroke;
	int stroke_width;
	
	SVGRectangle(Element e)
	{
		super(e);
		if(e.hasAttribute("x"))
			x = Float.parseFloat(e.getAttribute("x"));
		else x = 0.0f;
		if(e.hasAttribute("y"))
			y = Float.parseFloat(e.getAttribute("y"));
		else y = 0.0f;
		if(e.hasAttribute("rx"))
			rx = Float.parseFloat(e.getAttribute("rx"));
		else rx = 0.0f;
		if(e.hasAttribute("ry"))
			ry = Float.parseFloat(e.getAttribute("ry"));
		else ry = 0.0f;
		
		width = Integer.parseInt(e.getAttribute("width"));
		height = Integer.parseInt(e.getAttribute("height"));
		
		if(e.hasAttribute("fill"))
			fill = e.getAttribute("fill");
		else fill = "";
		if(e.hasAttribute("stroke"))
			stroke = e.getAttribute("stroke");
		else stroke = "";
		if(e.hasAttribute("stroke_width"))
			stroke_width = Integer.parseInt(e.getAttribute("stroke_width"));
		if(e.hasAttribute("style"))
			style = e.getAttribute("style");
	}

	@Override
	public String getCode() {
		String output;
		output = "<rect id=\""+id+"\" width=\""+width+"\" height=\""+height+"\"";
		if(x > 0.0f) 
			output += " x=\""+x+"\"";
		if(y > 0.0f) 
			output += " y=\""+y+"\"";
		if(rx > 0.0f) 
			output += " rx=\""+rx+"\"";
		if(ry > 0.0f) 
			output += " ry=\""+ry+"\"";
		if(fill != "")
			output+= " fill=\""+fill+"\"";
		if(stroke != "")
			output+= " stroke=\""+stroke+"\"";
		if(stroke_width > 0)
			output+= " fill=\""+stroke_width+"\"";
		if(style != "")
			output+= " style=\""+style+"\"";
		if(display != "")
			output+= " display=\""+display+"\"";
		output+= " />";
		
		return output;
	}
}

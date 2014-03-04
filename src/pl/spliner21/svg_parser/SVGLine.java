package pl.spliner21.svg_parser;

import org.w3c.dom.Element;

/* class representing <line> tag in SVG file
 * @author: Tomasz Szo³tysek
 * @version: 1.0
 */
public class SVGLine extends SVGObject {
	int x1,y1,x2,y2;
	String stroke = "";
	int stroke_width;
	
	SVGLine(Element e)
	{
		super(e);
		
		x1 = Integer.parseInt(e.getAttribute("x1"));
		y1 = Integer.parseInt(e.getAttribute("y1"));
		x2 = Integer.parseInt(e.getAttribute("x2"));
		y2 = Integer.parseInt(e.getAttribute("y2"));
		if(e.hasAttribute("stroke"))
			stroke = e.getAttribute("stroke");
		if(e.hasAttribute("stroke_width"))
			stroke_width = Integer.parseInt(e.getAttribute("stroke_width"));
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

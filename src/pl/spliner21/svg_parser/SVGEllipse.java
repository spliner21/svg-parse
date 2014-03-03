package pl.spliner21.svg_parser;

import org.w3c.dom.Element;

/* class representing <ellipse> tag in SVG file
 * @author: Tomasz Szo³tysek
 * @version: 1.0
 */
public class SVGEllipse extends SVGObject {
	Float cx,cy;
	Float rx,ry;
	String fill,stroke;
	int stroke_width;
	
	SVGEllipse(Element e)
	{
		super(e);

		if(e.hasAttribute("cx"))
			cx = Float.parseFloat(e.getAttribute("cx"));
		else cx = 0.0f;
		if(e.hasAttribute("cy"))
			cy = Float.parseFloat(e.getAttribute("cy"));
		else cy = 0.0f;
		if(e.hasAttribute("rx"))
			rx = Float.parseFloat(e.getAttribute("rx"));
		else rx = 0.0f;
		if(e.hasAttribute("ry"))
			ry = Float.parseFloat(e.getAttribute("ry"));
		else ry = 0.0f;
		
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
		output = "<ellipse id=\""+id+"\"";
		if(cx > 0.0f) 
			output += " x=\""+cx+"\"";
		if(cy > 0.0f) 
			output += " y=\""+cy+"\"";
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

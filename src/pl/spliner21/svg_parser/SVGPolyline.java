package pl.spliner21.svg_parser;
import java.util.Vector;

import org.w3c.dom.Element;

/* class representing <polyline> tag in SVG file
 * @author: Tomasz Szo³tysek
 * @version: 1.0
 */
public class SVGPolyline extends SVGObject {
	Vector<SVGPoint> points;
	String fill,stroke;
	int stroke_width;
	
	SVGPolyline(Element e)
	{
		super(e);
		
		String pts = e.getAttribute("points");
		
		String[] ptslist = pts.split(" ");
		for (String s : ptslist) 
			points.add(new SVGPoint(s));

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
		output = "<polyline id=\""+id+"\" points=\"";
		for(SVGPoint p: points)
			output += p.x+","+p.y+" ";	// w ostatnim nie powinno byæ spacji tylko "
		output+= "\"";
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

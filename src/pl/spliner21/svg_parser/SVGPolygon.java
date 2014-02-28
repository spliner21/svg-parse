package pl.spliner21.svg_parser;

import java.util.Vector;

import org.w3c.dom.Element;

public class SVGPolygon extends SVGObject {
	Vector<SVGPoint> points;
	String fill,stroke;
	int stroke_width;
	
	SVGPolygon(Element e)
	{
		super(e);
		
		String pts = e.getAttribute("points");
		
		String[] ptslist = pts.split(" ");
		for (String s : ptslist) 
			points.add(new SVGPoint(s));
			
		
		fill = e.getAttribute("fill");
		stroke = e.getAttribute("stroke");
		stroke_width = Integer.parseInt(e.getAttribute("stroke_width"));
		style = e.getAttribute("style");
	}
}

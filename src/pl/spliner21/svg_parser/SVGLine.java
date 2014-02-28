package pl.spliner21.svg_parser;

import org.w3c.dom.Element;

public class SVGLine extends SVGObject {
	int x1,y1,x2,y2;
	String stroke;
	int stroke_width;
	
	SVGLine(Element e)
	{
		super(e);
		
		x1 = Integer.parseInt(e.getAttribute("x1"));
		y1 = Integer.parseInt(e.getAttribute("y1"));
		x2 = Integer.parseInt(e.getAttribute("x2"));
		y2 = Integer.parseInt(e.getAttribute("y2"));
		stroke = e.getAttribute("stroke");
		stroke_width = Integer.parseInt(e.getAttribute("stroke_width"));
		style = e.getAttribute("style");
	}
}

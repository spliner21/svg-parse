package pl.spliner21.svg_parser;

import org.w3c.dom.Element;

public class SVGRectangle extends SVGObject {
	int x,y;
	int rx,ry;
	int width,height;
	String fill,stroke;
	int stroke_width;
	
	SVGRectangle(Element e)
	{
		super(e);
		
		x = Integer.parseInt(e.getAttribute("x"));
		y = Integer.parseInt(e.getAttribute("y"));
		rx = Integer.parseInt(e.getAttribute("rx"));
		ry = Integer.parseInt(e.getAttribute("ry"));
		width = Integer.parseInt(e.getAttribute("width"));
		height = Integer.parseInt(e.getAttribute("height"));
		fill = e.getAttribute("fill");
		stroke = e.getAttribute("stroke");
		stroke_width = Integer.parseInt(e.getAttribute("stroke_width"));
		style = e.getAttribute("style");
	}
}

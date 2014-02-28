package pl.spliner21.svg_parser;

import org.w3c.dom.Element;

public class SVGEllipse extends SVGObject {
	int cx,cy;
	int rx,ry;
	String fill,stroke;
	int stroke_width;
	
	SVGEllipse(Element e)
	{
		super(e);
		
		cx = Integer.parseInt(e.getAttribute("cx"));
		cy = Integer.parseInt(e.getAttribute("cy"));
		rx = Integer.parseInt(e.getAttribute("rx"));
		ry = Integer.parseInt(e.getAttribute("ry"));
		fill = e.getAttribute("fill");
		stroke = e.getAttribute("stroke");
		stroke_width = Integer.parseInt(e.getAttribute("stroke_width"));
		style = e.getAttribute("style");
	}

}

package pl.spliner21.svg_parser;

import org.w3c.dom.Element;

public class SVGCircle extends SVGObject {
	int cx,cy;
	int r;
	String fill,stroke;
	int stroke_width;

	SVGCircle(Element e)
	{
		super(e);

		cx = Integer.parseInt(e.getAttribute("cx"));
		cy = Integer.parseInt(e.getAttribute("cy"));
		r = Integer.parseInt(e.getAttribute("r"));
		fill = e.getAttribute("fill");
		stroke = e.getAttribute("stroke");
		stroke_width = Integer.parseInt(e.getAttribute("stroke_width"));
		style = e.getAttribute("style");
	}
}

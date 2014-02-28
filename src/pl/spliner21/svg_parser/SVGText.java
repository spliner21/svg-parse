package pl.spliner21.svg_parser;

import org.w3c.dom.Element;

public class SVGText extends SVGObject {
	int x,y;
	String text;
	String fill,stroke;
	int stroke_width;
	
	SVGText(Element e)
	{
		super(e);

		x = Integer.parseInt(e.getAttribute("x"));
		y = Integer.parseInt(e.getAttribute("y"));
		text = e.getTextContent();
		fill = e.getAttribute("fill");
		stroke = e.getAttribute("stroke");
		stroke_width = Integer.parseInt(e.getAttribute("stroke_width"));
		style = e.getAttribute("style");
	}
}

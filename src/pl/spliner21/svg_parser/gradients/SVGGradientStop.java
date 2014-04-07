package pl.spliner21.svg_parser.gradients;

import org.w3c.dom.Element;

/** 
 * Class representing <stop> tag in SVG's gradients
 * @author Tomasz Szo³tysek
 * @version 1.0
 */
public class SVGGradientStop {
	float offset = 0.0f;
	String style = "";
	
	/**
	 * Default constructor
	 */
	public SVGGradientStop() {
		offset = 0.0f;
		style = "stop-color: #000000";
	}

	/**
	 * Constructor by parameters
	 * @param offset Gradient's stop offset (0.0 - 1.0)
	 * @param style Gradient's stop style ("stop-color: #COLOR")
	 */
	public SVGGradientStop(float offset, String style) {
		this.offset = offset;
		this.style = style;
	}
	
	/**
	 * Constructor by XML's DOM element
	 * @param e XML's DOM <stop> element.
	 */
	SVGGradientStop(Element e) {
		if(e.hasAttribute("offset"))
			offset = Float.parseFloat(e.getAttribute("offset"));
		if(e.hasAttribute("style"))
			style = e.getAttribute("style");
	}

	/**
	 * Method that generates tag's SVG code part for this element.
	 * @return String, that contains generated piece of code for this tag.
	 */
	public String getCode() {
		String output;
		output = "<stop offset=\""+offset+"\" style=\""+style+"\" />";
		return output;
	}
	
	
}

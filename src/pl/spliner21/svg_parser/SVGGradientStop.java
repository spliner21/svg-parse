package pl.spliner21.svg_parser;

import org.w3c.dom.Element;

/* class representing <stop> tag in SVG's gradients
 * @author: Tomasz Szo³tysek
 * @version: 1.0
 */
public class SVGGradientStop extends SVGObject {
	Float offset = 0.0f;
	String style = "";
	
	SVGGradientStop(Element e) {
		super(e);
		if(e.hasAttribute("offset"))
			offset = Float.parseFloat(e.getAttribute("offset"));
		if(e.hasAttribute("style"))
			style = e.getAttribute("style");
	}
	@Override
	public String getCode() {
		String output;
		output = "<stop";
		if(id != "")
			output += " id=\""+id+"\"";
		output+= " offset=\""+offset+"\"";
		if(style != "")
			output+= " style=\""+style+"\"";
		output+= " />";
		
		return output;
	}
	
	
}

package pl.spliner21.svg_parser.gradients;

import org.w3c.dom.Element;

import pl.spliner21.svg_parser.objects.SVGObject;

/* class representing <stop> tag in SVG's gradients
 * @author: Tomasz Szo³tysek
 * @version: 1.0
 */
public class SVGGradientStop extends SVGObject {
	Float offset = 0.0f;
	
	public SVGGradientStop() {
		offset = 0.0f;
		style = "stop-color: #000000";
	}

	public SVGGradientStop(Float offset, String style) {
		this.offset = offset;
		this.style = style;
	}
	
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

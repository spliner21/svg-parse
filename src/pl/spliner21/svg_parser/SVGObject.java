package pl.spliner21.svg_parser;

import org.w3c.dom.Element;


/* abstract class representing tag in SVG file,
 * element of DOM tree
 * @author: Tomasz Szo³tysek
 * @version: 1.0
 */
public abstract class SVGObject {
	String id = "";
	String display = "";
	String style = "";
	String transform = "";
	Float opacity = -1.0f;
	
	SVGObject(Element e) {
		id = e.getAttribute("id");
		if(e.hasAttribute("display"))
			display = e.getAttribute("display");
		if(e.hasAttribute("style"))
			style = e.getAttribute("style");
		if(e.hasAttribute("transform"))
			transform = e.getAttribute("transform");
		if(e.hasAttribute("opacity"))
			opacity = Float.parseFloat(e.getAttribute("opacity"));
	}

	public abstract String getCode();
}

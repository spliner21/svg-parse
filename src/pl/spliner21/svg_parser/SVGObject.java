package pl.spliner21.svg_parser;

import org.w3c.dom.Element;


/* abstract class representing tag in SVG file,
 * element of DOM tree
 * @author: Tomasz Szo³tysek
 * @version: 1.0
 */
public abstract class SVGObject {
	String id = "";
	String display;
	String style;
	
	SVGObject(Element e) {
		id = e.getAttribute("id");
		if(e.hasAttribute("display"))
			display = e.getAttribute("display");
		else display = "";
		if(e.hasAttribute("style"))
			style = e.getAttribute("style");
		else style = "";
	}

	public abstract String getCode();
}

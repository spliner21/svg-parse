package pl.spliner21.svg_parser;

import org.w3c.dom.Element;


public class SVGObject extends SVGDOMElem {
	String id = "";
	Boolean display = true;
	String style;
	
	SVGObject(Element e) {
		id = e.getAttribute("id");
		if(e.getAttribute("display").equalsIgnoreCase("false"))
			display = false;
	}

	@Override
	public String getCode() {
		// TODO Auto-generated method stub
		return null;
	}
}

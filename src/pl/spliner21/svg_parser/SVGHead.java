package pl.spliner21.svg_parser;

import java.util.Vector;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class SVGHead extends SVGObject {
	// <svg xmlns="http://www.w3.org/2000/svg" version="1.1" width="250px" height="250px" x="10px" y="10px">
	String xmlns = "http://www.w3.org/2000/svg";
	String version;
	String width,height,x,y;
	
	Vector<SVGObject> children = null;
	
	SVGHead(Element e) {
		super(e);
		xmlns = e.getAttribute("xmlns");
		version = e.getAttribute("version");
		width = e.getAttribute("width");
		height = e.getAttribute("height");
		x = e.getAttribute("x");
		y = e.getAttribute("y");
		
		if(e.hasChildNodes())
		{
			NodeList childNodes = e.getChildNodes();
			for (int i = 0; i < childNodes.getLength(); i++)
			{
				Node tmp = childNodes.item(i);
				if(tmp.getNodeName() == "g [OPEN]")
					children.add(new SVGGroup((Element)tmp));
				else if(tmp.getNodeName() == "circle [OPEN]")
					children.add(new SVGCircle((Element)tmp));
				else if(tmp.getNodeName() == "ellipse [OPEN]")
					children.add(new SVGEllipse((Element)tmp));
				else if(tmp.getNodeName() == "line [OPEN]")
					children.add(new SVGLine((Element)tmp));
				else if(tmp.getNodeName() == "path [OPEN]")
					children.add(new SVGPath((Element)tmp));
				else if(tmp.getNodeName() == "polygon [OPEN]")
					children.add(new SVGPolygon((Element)tmp));
				else if(tmp.getNodeName() == "polyline [OPEN]")
					children.add(new SVGPolyline((Element)tmp));
				else if(tmp.getNodeName() == "rectangle [OPEN]")
					children.add(new SVGRectangle((Element)tmp));
				else if(tmp.getNodeName() == "text [OPEN]")
					children.add(new SVGText((Element)tmp));
			}
		}
	}

}

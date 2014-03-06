package pl.spliner21.svg_parser;

import java.util.Vector;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/* class representing <radialGradient> tag in SVG file
 * @author: Tomasz Szo�tysek
 * @version: 1.0
 */
public class SVGRadialGradient extends SVGObject {
	String gradientUnits = "";
	Float x1 = -1.0f, y1 = -1.0f, x2 = -1.0f, y2 = -1.0f;
	String gradientTransform = "";
	Vector<SVGGradientStop> stops = null;
	
	SVGRadialGradient(Element e) {
		super(e);

		if(e.hasAttribute("gradientUnits"))
			gradientUnits = e.getAttribute("gradientUnits");
		if(e.hasAttribute("gradientTransform"))
			gradientTransform = e.getAttribute("gradientTransform");
		if(e.hasAttribute("x1"))
			x1 = Float.parseFloat(e.getAttribute("x1"));
		if(e.hasAttribute("y1"))
			y1 = Float.parseFloat(e.getAttribute("y1"));
		if(e.hasAttribute("x2"))
			x2 = Float.parseFloat(e.getAttribute("x2"));
		if(e.hasAttribute("y2"))
			y2 = Float.parseFloat(e.getAttribute("y2"));
		if(e.hasAttribute("style"))
			style = e.getAttribute("style");
		if(e.hasChildNodes())
		{
			stops = new Vector<SVGGradientStop>();
			NodeList childNodes = e.getChildNodes();
			for (int i = 0; i < childNodes.getLength(); i++)
			{
				Node tmp = childNodes.item(i);
				if(tmp.getNodeName() == "stop")
					stops.add(new SVGGradientStop((Element)tmp));
			}
		}
	}
	
	@Override
	public String getCode() {
		String output;
		output = "<radialGradient";
		if(id != "")
			output += " id=\""+id+"\"";
		if(gradientUnits != "")
			output+= " gradientUnits=\""+gradientUnits+"\"";
		if(x1 >= 0.0f)
			output+= " x1=\""+x1+"\"";
		if(y1 >= 0.0f)
			output+= " y1=\""+y1+"\"";
		if(x2 >= 0.0f)
			output+= " x2=\""+x2+"\"";
		if(y2 >= 0.0f)
			output+= " y2=\""+y2+"\"";
		if(gradientTransform != "")
			output+= " gradientTransform=\""+gradientTransform+"\"";
		output += ">";
		for(SVGGradientStop stop : stops)
			output += "\n"+stop.getCode();
		
		
		output+= "\n</radialGradient>";
		
		return output;
	}

}
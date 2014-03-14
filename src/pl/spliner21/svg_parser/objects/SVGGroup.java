package pl.spliner21.svg_parser.objects;

import java.util.Vector;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import pl.spliner21.svg_parser.gradients.SVGLinearGradient;
import pl.spliner21.svg_parser.gradients.SVGRadialGradient;

/** 
 * Class representing <g> tag in SVG file
 * @author spliner21
 * @version 1.0
 */
public class SVGGroup extends SVGObject {
	
	Vector<SVGObject> children = null;

	/**
	 * Default constructor
	 */
	SVGGroup()
	{
		super();
	}
	
	/**
	 * Constructor by xml's DOM tag element
	 * @param e xml's DOM tag element
	 */
	SVGGroup(Element e)
	{
		super(e);
		
		if(e.hasChildNodes())
		{
			children = new Vector<SVGObject>();
			NodeList childNodes = e.getChildNodes();
			for (int i = 0; i < childNodes.getLength(); i++)
			{
				Node tmp = childNodes.item(i);
				//System.out.println(tmp.getNodeName());
				if(tmp.getNodeName() == "g")
					children.add(new SVGGroup((Element)tmp));
				else if(tmp.getNodeName() == "circle")
					children.add(new SVGCircle((Element)tmp));
				else if(tmp.getNodeName() == "ellipse")
					children.add(new SVGEllipse((Element)tmp));
				else if(tmp.getNodeName() == "line")
					children.add(new SVGLine((Element)tmp));
				else if(tmp.getNodeName() == "path")
					children.add(new SVGPath((Element)tmp));
				else if(tmp.getNodeName() == "polygon")
					children.add(new SVGPolygon((Element)tmp));
				else if(tmp.getNodeName() == "polyline")
					children.add(new SVGPolyline((Element)tmp));
				else if(tmp.getNodeName() == "rect")
					children.add(new SVGRectangle((Element)tmp));
				else if(tmp.getNodeName() == "text")
					children.add(new SVGText((Element)tmp));
				else if(tmp.getNodeName() == "linearGradient")
					children.add(new SVGLinearGradient((Element)tmp));
				else if(tmp.getNodeName() == "radialGradient")
					children.add(new SVGRadialGradient((Element)tmp));
			}
		}
	}

	
	@Override
	public String getCode() {
		String output;
		output = "<g";
		if(id != "")
			output += " id=\""+id+"\"";
		if(opacity >= 0.0f)
			output+= " opacity=\""+opacity+"\"";
		if(transform != "")
			output+= " transform=\""+transform+"\"";
		if(style != "")
			output+= " style=\""+style+"\"";
		if(display != "")
			output+= " display=\""+display+"\"";
		output+= ">";
		
		for(SVGObject e: children)
			output+= "\t\n"+e.getCode();
		output+= "\n</g>";
		
		return output;
	}

}

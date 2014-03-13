package pl.spliner21.svg_parser.objects;

import java.util.Vector;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import pl.spliner21.svg_parser.gradients.SVGLinearGradient;
import pl.spliner21.svg_parser.gradients.SVGRadialGradient;

/** 
 * class representing main (<svg>) tag in SVG file
 * @author Tomasz Szo³tysek
 * @version 1.0
 */
public class SVGHead extends SVGObject {
	String xmlns = "http://www.w3.org/2000/svg";
	String baseProfile = "";
	String version = "";
	String width="",height="",x="",y="";
	String viewBox="";
	String enable_background="";
	String xml_space="";
	
	
	Vector<SVGObject> children = null;
	
	/**
	 * Default constructor
	 */
	public SVGHead() 
	{
		super();
		version = "1.1";
		baseProfile = "basic";
		id = "";
		x = "0px";
		y = "0px";
		width = "500px";
		height = "400px";
		viewBox = "0 0 500 400";
		xml_space = "preserve";
	}
	
	/**
	 * Constructor by parameters
	 * @param version SVG version
	 * @param baseProfile SVG baseProfile
	 * @param id SVG tag's ID
	 * @param x SVG position's x coordinate
	 * @param y SVG position's Y coordinate
	 * @param width SVG image's width
	 * @param height SVG image's height
	 */
	public SVGHead(String version, String baseProfile, String id, int x, int y, int width, int height) 
	{
		super();
		this.version = version;
		this.baseProfile = baseProfile;
		this.id = id;
		this.x = x+"px";
		this.y = y+"px";
		this.width = width+"px";
		this.height = height+"px";
		this.viewBox = x+" "+y+" "+width+" "+height;
		xml_space = "preserve";
	}

	/**
	 * Constructor by xml's DOM tag element
	 * @param e xml's DOM tag element
	 */
	public SVGHead(Element e) {
		super(e);
		if(e.hasAttribute("xmlns"))
			xmlns = e.getAttribute("xmlns");
		if(e.hasAttribute("baseProfile"))
			baseProfile = e.getAttribute("baseProfile");
		if(e.hasAttribute("version"))
			version = e.getAttribute("version");
		if(e.hasAttribute("width"))
			width = e.getAttribute("width");
		if(e.hasAttribute("height"))
			height = e.getAttribute("height");
		if(e.hasAttribute("x"))
			x = e.getAttribute("x");
		if(e.hasAttribute("y"))
			y = e.getAttribute("y");
		if(e.hasAttribute("viewBox"))
			viewBox = e.getAttribute("viewBox");
		if(e.hasAttribute("enable-background"))
			enable_background = e.getAttribute("enable-background");
		if(e.hasAttribute("xml:space"))
			xml_space = e.getAttribute("xml:space");
		
		if(e.hasChildNodes())
		{
			children = new Vector<SVGObject>();
			NodeList childNodes = e.getChildNodes();
			for (int i = 0; i < childNodes.getLength(); i++)
			{
				Node tmp = childNodes.item(i);
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
		output = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
"<!-- Generator: SVGParser Java Library :: author: Tomasz Szoltysek (http://spliner.net/)  -->\n" +
"<!DOCTYPE svg PUBLIC \"-//W3C//DTD SVG 1.1//EN\" \"http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd\">\n";
		output += "<svg xmlns=\""+xmlns+"\"";
		if(version != "")
			output+= " version=\""+version+"\"";
		if(baseProfile != "")
			output+= " baseProfile=\""+baseProfile+"\"";
		output+= " width=\""+width+"\" height=\""+height+"\"";
		if(opacity >= 0.0f)
			output+= " opacity=\""+opacity+"\"";
		if(transform != "")
			output+= " transform=\""+transform+"\"";
		if(x != "")
			output+= " x=\""+x+"\"";
		if(y != "")
			output+= " y=\""+y+"\"";
		if(enable_background != "")
			output+= " enable-background=\""+enable_background+"\"";
		if(viewBox != "")
			output+= " viewBox=\""+viewBox+"\"";
		if(xml_space != "")
			output+= " xml:space=\""+xml_space+"\"";
		output+= ">";
		
		for(SVGObject e: children)
			output+= "\t\n"+e.getCode();
		
		output+= "\n</svg>";
		
		return output;
	}

}

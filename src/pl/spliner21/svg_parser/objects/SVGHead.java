package pl.spliner21.svg_parser.objects;

import java.util.Vector;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import pl.spliner21.svg_parser.gradients.SVGLinearGradient;
import pl.spliner21.svg_parser.gradients.SVGRadialGradient;

/** 
 * class representing main (<svg>) tag in SVG file
 * @author Tomasz Szo�tysek
 * @version 1.0
 */
public class SVGHead extends SVGObject {
	private String xmlns = "http://www.w3.org/2000/svg";
	private String baseProfile = "";
	private String version = "";
	private float width,height;
	private String x="",y="";
	private String viewBox="";
	private String enable_background="";
	private String xml_space="";
	
	
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
		width = 500;
		height = 400;
		viewBox = "0 0 500 400";
		xml_space = "preserve";
		children = new Vector<SVGObject>();
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
		this.width = width;
		this.height = height;
		this.viewBox = x+" "+y+" "+width+" "+height;
		xml_space = "preserve";
		children = new Vector<SVGObject>();
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
			width = Float.parseFloat(e.getAttribute("width").replace("px", ""));
		if(e.hasAttribute("height"))
			height = Float.parseFloat(e.getAttribute("height").replace("px", ""));
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
				// long time = System.nanoTime();
				Node tmp = childNodes.item(i);
				if(tmp.getNodeName() == "g")
					children.add(new SVGGroup((Element)tmp));
				else if(tmp.getNodeName() == "circle")
					children.add(new SVGEllipse((Element)tmp));
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
				// System.out.println("Time elapsed to add node "+tmp.getNodeName()+": "+(System.nanoTime() - time)/1000000000.0f+"s.");
			}
		}
	}


	@Override
	public void scale(float factor) {
		width *= factor;
		height *= factor;
		viewBox = "0 0 "+width+" "+height;
		for(SVGObject o: children)
			o.scale(factor);
		
	}

	@Override
	public void scale(float factorx, float factory) {
		width *= factorx;
		height *= factory;
		viewBox = "0 0 "+width+" "+height;
		for(SVGObject o: children)
			o.scale(factorx, factory);
	}

	@Override
	public void scale(float factor, float cex, float cey) {
		width *= factor;
		height *= factor;
		viewBox = "0 0 "+width+" "+height;
		for(SVGObject o: children)
			o.scale(factor, cex, cey);		
	}

	@Override
	public void scale(float factorx, float factory, float cex, float cey) {
		width *= factorx;
		height *= factory;
		viewBox = "0 0 "+width+" "+height;
		for(SVGObject o: children)
			o.scale(factorx, factory, cex, cey);
	}

	@Override
	public void rotate(float angle) {
		for(SVGObject o: children)
			o.rotate(angle,width/2,height/2);		
	}

	@Override
	public void rotate(float angle, float cex, float cey) {
		for(SVGObject o: children)
			o.rotate(angle, cex, cey);	
	}

	/**
	 * Search in a tree for an object with certain ID
	 * @param ID Required object's ID
	 * @return Object with certain ID or null if failed
	 */
	public SVGObject getObjectByID(String ID)
	{
		SVGObject result;
		for(SVGObject o: children)
		{
			if(o.getID().equals(ID))
				return o;
			else if(o.getClass() == SVGGroup.class)
			{
				result = ((SVGGroup)o).getObjectByID(ID);
				if(result != null)
					return result;
			}
		}
		return null;
	}


	/**
	 * Search in a tree for an object of certain type
	 * @param c class of object we are looking for
	 * @return Object of certain type or null if failed
	 */
	public SVGObject getObjectByType(Class<?> c)
	{
		SVGObject result;
		for(SVGObject o: children)
		{
			if(o.getClass().equals(c))
				return o;
			else if(o.getClass() == SVGGroup.class)
			{
				result = ((SVGGroup)o).getObjectByType(c);
				if(result != null)
					return result;
			}
		}
		return null;
	}

	@Override
	public void translate(float tx, float ty) {
		for(SVGObject o: children)
			o.translate(tx, ty);	
	}

	/**
	 * Search in a tree for an object with certain ID and delete it
	 * @param ID Required object's ID
	 * @return true if succeeded, else false
	 */
	public boolean deleteObjectByID(String ID)
	{
		boolean res = false;
		SVGObject o = null;
		for(int i = 0; i < children.size(); ++i)
		{
			o = children.elementAt(i);
			if(o.getID().equals(ID))
				children.remove(o);
			else if(o.getClass() == SVGGroup.class)
			{
				res = ((SVGGroup)o).deleteObjectByID(ID);
				if(res)
					return res;					
			}
		}
		return res;
	}
	
	/**
	 * Method for adding object to SVG DOM Tree
	 * @param o object to be added (only not null object are added
	 */
	public void addObject(SVGObject o) {
		if(o != null)
			children.add(o);
	}
	
	@Override
	public String toString() {
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
		if(transform != null)
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
			output+= "\t\n"+e.toString();
		
		output+= "\n</svg>";
		
		return output;
	}
}

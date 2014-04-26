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
	public SVGGroup()
	{
		super();
		children = new Vector<SVGObject>();
	}
	
	/**
	 * Constructor by xml's DOM tag element
	 * @param e xml's DOM tag element
	 */
	public SVGGroup(Element e)
	{
		super(e);
		
		if(e.hasChildNodes())
		{
			children = new Vector<SVGObject>();
			NodeList childNodes = e.getChildNodes();
			for (int i = 0; i < childNodes.getLength(); i++)
			{
				// long time = System.nanoTime();
				Node tmp = childNodes.item(i);
				//System.out.println(tmp.getNodeName());
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
				// System.out.println("Time elapsed to add node "+tmp.getNodeName()+" to g: "+(System.nanoTime() - time)/1000000000.0f+"s.");
			}
		}
	}
	
	@Override
	public void scale(float factor) {
		for(SVGObject o: children)
			o.scale(factor);
		
	}

	@Override
	public void scale(float factorx, float factory) {
		for(SVGObject o: children)
			o.scale(factorx, factory);
	}

	@Override
	public void scale(float factor, float cex, float cey) {
		for(SVGObject o: children)
			o.scale(factor, cex, cey);		
	}

	@Override
	public void scale(float factorx, float factory, float cex, float cey) {
		for(SVGObject o: children)
			o.scale(factorx, factory, cex, cey);
	}

	@Override
	public void rotate(float angle) {
		for(SVGObject o: children)
			o.rotate(angle);		
	}

	@Override
	public void rotate(float angle, float cex, float cey) {
		for(SVGObject o: children)
			o.rotate(angle, cex, cey);	
	}

	@Override
	public void translate(float tx, float ty) {
		for(SVGObject o: children)
			o.translate(tx, ty);	
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
	
	/**
	 * Method for adding object to SVG DOM Tree Group
	 * @param o object to be added (only not null object are added
	 */
	public void addObject(SVGObject o) {
		if(o != null)
			children.add(o);
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
	
	@Override
	public String getCode() {
		String output;
		output = "<g";
		if(id != "")
			output += " id=\""+id+"\"";
		if(opacity >= 0.0f)
			output+= " opacity=\""+opacity+"\"";
		if(transform != null)
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

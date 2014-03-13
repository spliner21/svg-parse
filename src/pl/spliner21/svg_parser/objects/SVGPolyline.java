package pl.spliner21.svg_parser.objects;
import java.util.Vector;

import org.w3c.dom.Element;

/** 
 * Class representing <polyline> tag in SVG file
 * @author spliner21
 * @version 1.0
 */
public class SVGPolyline extends SVGObject {
	Vector<SVGPoint> points;
	
	/**
	 * Default constructor
	 */
	public SVGPolyline()
	{
		super();
		points.add(new SVGPoint("0,0"));
		points.add(new SVGPoint("1,1"));
	}

	/**
	 * Constructor by arguments
	 * @param id tag's ID argument
	 * @param style tag's style argument
	 * @param pts String, that contains points in the way, the points-parameter accepts them
	 * @param fill polyline's fill color
	 * @param stroke polyline's stroke color
	 * @param stroke_width polyline's stroke_width
	 * @param transform polyline's transformation
	 * @param opacity polyline's opacity (0.0f-1.0f)
	 * @param display tag's display argument
	 */
	public SVGPolyline(String id, String style, String pts, 
			String fill, String stroke, Float stroke_width, String transform, Float opacity, String display)
	{
		super(id,style,transform,opacity,display,fill,stroke,stroke_width);

		String[] ptslist = pts.split(" ");
		for (String s : ptslist) 
			points.add(new SVGPoint(s));
	}


	/**
	 * Constructor by xml's DOM tag element
	 * @param e xml's DOM tag element
	 */
	SVGPolyline(Element e)
	{
		super(e);
		
		String pts = e.getAttribute("points");
		
		String[] ptslist = pts.split(" ");
		for (String s : ptslist) 
			points.add(new SVGPoint(s));
	}


	/**
	 * Point's getter
	 * @return vector of SVGPoint elements for this tag
	 */
	public Vector<SVGPoint> getPoints() {
		return points;
	}

	/**
	 * Point's setter
	 * @param points vector of SVGPoint elements for this tag
	 */
	public void setPoints(Vector<SVGPoint> points) {
		this.points = points;
	}


	/*
	 * Method which returns generated tags code
	 * author: Tomasz Szo³tysek
	 */
	@Override
	public String getCode() {
		String output;
		output = "<polyline";
		if(id != "")
			output += " id=\""+id+"\"";
		output += " points=\"";
		for(SVGPoint p: points)
			output += p.x+","+p.y+" ";	// w ostatnim nie powinno byæ spacji tylko "
		output+= "\"";
		if(opacity >= 0.0f)
			output+= " opacity=\""+opacity+"\"";
		if(transform != "")
			output+= " transform=\""+transform+"\"";
		if(fill != "")
			output+= " fill=\""+fill+"\"";
		if(stroke != "")
			output+= " stroke=\""+stroke+"\"";
		if(stroke_width >= 0.0f)
			output+= " stroke-width=\""+stroke_width+"\"";
		if(style != "")
			output+= " style=\""+style+"\"";
		if(display != "")
			output+= " display=\""+display+"\"";
		output+= " />";
		
		return output;
	}

}

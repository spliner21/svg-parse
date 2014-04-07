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
		points = new Vector<SVGPoint>();
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
			String fill, String stroke, float stroke_width, String transform, float opacity, String display)
	{
		super(id,style,transform,opacity,display,fill,stroke,stroke_width);

		pts= pts.replaceAll("\\s+", " ");
		pts = pts.trim();
		String[] ptslist = pts.split(" ");
		points = new Vector<SVGPoint>();
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
		
		pts= pts.replaceAll("\\s+", " ");
		pts = pts.trim();
		String[] ptslist = pts.split(" ");
		points = new Vector<SVGPoint>();
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


	
	/**
	 * Find polyline's center
	 * @return polyline's center coordinates
	 */
	public SVGPoint findCenter()
	{
		float cxmin = Float.MAX_VALUE,cxmax = 0;
		float cymin = Float.MAX_VALUE,cymax = 0;
		float cx,cy;
		
		for(SVGPoint p: points)
		{
			if(p.x < cxmin)
				cxmin = p.x;
			if(p.x > cxmax)
				cxmax = p.x;
			if(p.y < cymin)
				cymin = p.y;
			if(p.y > cymax)
				cymax = p.y;
		}
		
		cx = cxmin + (cxmax - cxmin) / 2;
		cy = cymin + (cymax - cymin) / 2;
		
		return new SVGPoint(cx,cy);
	}
	
	@Override
	public void scale(float factor)
	{
		SVGPoint c = findCenter();
		for(SVGPoint p: points)
			p.scale(factor, c.x, c.y);
	}


	@Override
	public void scale(float factorx, float factory)
	{
		SVGPoint c = findCenter();
		for(SVGPoint p: points)
			p.scale(factorx, factory, c.x, c.y);
	}

	@Override
	public void scale(float factor, float cex, float cey)
	{
		for(SVGPoint p: points)
			p.scale(factor, cex, cey);
	}
	
	@Override
	public void scale(float factorx, float factory, float cex, float cey)
	{
		for(SVGPoint p: points)
			p.scale(factorx, factory, cex, cey);
	}
	
	@Override
	public void rotate(float angle)
	{
		SVGPoint c = findCenter();
		for(SVGPoint p: points)
			p.rotate(angle, c.x, c.y);
	}
	
	@Override
	public void rotate(float angle, float cex, float cey)
	{
		for(SVGPoint p: points)
			p.rotate(angle, cex, cey);
	}

	@Override
	public void translate(float tx, float ty)
	{
		for(SVGPoint p: points)
			p.translate(tx, ty);
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
		if(transform != null)
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

package pl.spliner21.svg_parser.objects;
import java.util.Vector;

import org.w3c.dom.Element;

/* class representing <polyline> tag in SVG file
 * @author: Tomasz Szo³tysek
 * @version: 1.0
 */
public class SVGPolyline extends SVGObject {
	Vector<SVGPoint> points;
	

	public SVGPolyline()
	{
		super();
		points.add(new SVGPoint("0,0"));
		points.add(new SVGPoint("1,1"));
	}
	
	public SVGPolyline(String id, String style, String pts, 
			String fill, String stroke, Float stroke_width, String transform, Float opacity, String display)
	{
		super(id,style,transform,opacity,display,fill,stroke,stroke_width);

		String[] ptslist = pts.split(" ");
		for (String s : ptslist) 
			points.add(new SVGPoint(s));
	}

	SVGPolyline(Element e)
	{
		super(e);
		
		String pts = e.getAttribute("points");
		
		String[] ptslist = pts.split(" ");
		for (String s : ptslist) 
			points.add(new SVGPoint(s));
	}


	public Vector<SVGPoint> getPoints() {
		return points;
	}

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

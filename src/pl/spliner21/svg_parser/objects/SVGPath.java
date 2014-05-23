package pl.spliner21.svg_parser.objects;

import java.awt.geom.Point2D;
import java.util.Vector;

import org.w3c.dom.Element;

/** 
 * Class representing <path> tag in SVG file
 * @author spliner21
 * @version 1.0
 */
public class SVGPath extends SVGObject {
	
	private Vector<SVGPathData> d;

	/**
	 * Default constructor
	 */
	public SVGPath()
	{
		super();
		d = new Vector<SVGPathData>();
		Vector<Float> points = new Vector<Float>();
		points.add(0.0f);
		points.add(0.0f);
		d.add(new SVGPathData('M',points));
		d.add(new SVGPathData('z',null));
	}

	/**
	 * Constructor by arguments
	 * @param id tag's ID argument
	 * @param style tag's style argument
	 * @param pts String, that contains points in the way, the d-parameter accepts them
	 * @param fill path's fill color
	 * @param stroke path's stroke color
	 * @param stroke_width path's stroke_width
	 * @param transform path's transformation
	 * @param opacity path's opacity (0.0f-1.0f)
	 * @param display tag's display argument
	 */
	public SVGPath(String id, String style, String pts, 
			String fill, String stroke, float stroke_width, String transform, float opacity, String display)
	{
		super(id,style,transform,opacity,display,fill,stroke,stroke_width);

		pts = pts.replace(',',' ');
		pts= pts.replace("-", " -");
		for(int i = 0; i< pts.length(); i++)
		{
			if(Character.isLetter( pts.charAt(i))) 
			{
				pts = pts.subSequence(0, i) + " " + pts.charAt(i) + " " + pts.subSequence(i+1, pts.length());
				i += 2;
			}
		}
		pts= pts.replaceAll("\\s+", " ");
		pts = pts.trim();
		
		String[] ptslist = pts.split(" ");
		d = new Vector<SVGPathData>();
		for (int i = 0; i< ptslist.length; i++)
		{
			if(Character.isLetter(ptslist[i].charAt(0)))
			{
				char type = ptslist[i].charAt(0);
				Vector<Float> points = new Vector<Float>();
				while(++i < ptslist.length && !Character.isLetter(ptslist[i].charAt(0)))
				{
					points.add(Float.parseFloat(ptslist[i]));
				}
				--i;
				d.add(new SVGPathData(type,points));
			}
		}
	}
	
	/**
	 * Constructor by xml's DOM tag element
	 * @param e xml's DOM tag element
	 */
	SVGPath(Element e)
	{
		super(e);

		String pts = e.getAttribute("d");
		pts = pts.replace(',',' ');
		pts= pts.replace("-", " -");
		for(int i = 0; i< pts.length(); i++)
		{
			if(Character.isLetter( pts.charAt(i))) 
			{
				pts = pts.subSequence(0, i) + " " + pts.charAt(i) + " " + pts.subSequence(i+1, pts.length());
				//System.out.println(pts);
				i += 2;
			}
		}
		pts= pts.replaceAll("\\s+", " ");
		pts = pts.trim();
		// System.out.println(pts);
		
		String[] ptslist = pts.split(" ");
		d = new Vector<SVGPathData>();
		for (int i = 0; i< ptslist.length; i++)
		{
			if(Character.isLetter(ptslist[i].charAt(0)))
			{
				char type = ptslist[i].charAt(0);
				Vector<Float> points = new Vector<Float>();
				while(++i < ptslist.length && !Character.isLetter(ptslist[i].charAt(0)))
				{
					points.add(Float.parseFloat(ptslist[i]));
				}
				--i;
				d.add(new SVGPathData(type,points));
			}
		}
	}


	/**
	 * Path's data getter
	 * @return path's data
	 */
	public Vector<SVGPathData> getD() {
		return d;
	}

	/**
	 * Path's data setter
	 * @param d path's data
	 */
	public void setD(Vector<SVGPathData> d) {
		this.d = d;
	}

	/**
	 * Path's data getter
	 * @return path's data
	 */
	public String getStringD() {
		String output = "";
		for(SVGPathData p: d)
			output += p.toString()+ " ";
		return output;
	}

	/**
	 * Path's data setter
	 * @param pts path's data
	 */
	public void setStringD(String pts) {
		pts = pts.replace(',',' ');
		pts= pts.replace("-", " -");
		for(int i = 0; i< pts.length(); i++)
		{
			if(Character.isLetter( pts.charAt(i))) 
			{
				pts = pts.subSequence(0, i) + " " + pts.charAt(i) + " " + pts.subSequence(i+1, pts.length());
				i += 2;
			}
		}
		pts= pts.replaceAll("\\s+", " ");
		pts = pts.trim();
		
		String[] ptslist = pts.split(" ");
		d = new Vector<SVGPathData>();
		for (int i = 0; i< ptslist.length; i++)
		{
			if(Character.isLetter(ptslist[i].charAt(0)))
			{
				char type = ptslist[i].charAt(0);
				Vector<Float> points = new Vector<Float>();
				while(!Character.isLetter(ptslist[++i].charAt(0)))
				{
					points.add(Float.parseFloat(ptslist[i]));
				}
				d.add(new SVGPathData(type,points));
			}
		}
	}

	@Override
	public void scale(float factor)
	{
		transform.scale(factor);
	}

	@Override
	public void scale(float factorx, float factory)
	{
		transform.scale(factorx,factory);
	}

	@Override
	public void scale(float factor, float cex, float cey)
	{
		Point2D last = new Point2D.Float();
		last.setLocation(Float.MIN_VALUE, Float.MIN_VALUE);
		
		for(SVGPathData p: d)
		{
			last = p.scale(factor, cex, cey, (float)last.getX(), (float)last.getY());
		}
	}

	@Override
	public void scale(float factorx, float factory, float cex, float cey)
	{
		Point2D last = new Point2D.Float();
		last.setLocation(Float.MIN_VALUE, Float.MIN_VALUE);
		
		for(SVGPathData p: d)
		{
			last = p.scale(factorx, factory, cex, cey, (float)last.getX(), (float)last.getY());
		}
	}

	@Override
	public void rotate(float angle)
	{
		transform.rotate(angle);
	}

	@Override
	public void rotate(float angle, float cex, float cey)
	{
		Point2D last = new Point2D.Float();
		last.setLocation(Float.MIN_VALUE, Float.MIN_VALUE);
		float sinus = (float) Math.sin(Math.toRadians(angle));
		float cosinus = (float) Math.cos(Math.toRadians(angle));
		
		for(SVGPathData p: d)
		{
			last = p.rotate(angle, sinus, cosinus, cex, cey, (float)last.getX(), (float)last.getY());
		}
	}

	@Override
	public void translate(float tx, float ty)
	{
		for(SVGPathData p: d)
		{
			p.translate(tx,ty);
		}
	}
	
	/*
	 * Method which returns generated tags code
	 */
	@Override
	public String toString() {
		String output;
		output = "<path";
		if(id != "")
			output += " id=\""+id+"\"";
		output += " d=\"";
		for(SVGPathData p: d)
			output += p.toString()+ " ";
		output = output.trim();
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

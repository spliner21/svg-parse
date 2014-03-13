package pl.spliner21.svg_parser.objects;

import java.util.Vector;

import org.w3c.dom.Element;

/** 
 * Class representing <path> tag in SVG file
 * @author spliner21
 * @version 1.0
 */
public class SVGPath extends SVGObject {
	Vector<SVGdElem> d;

	/**
	 * Default constructor
	 */
	public SVGPath()
	{
		super();
		String pts = "M 0 0 z";

		String[] ptslist = pts.split(" ");
		d = new Vector<SVGdElem>();
		for (int i = 0; i < ptslist.length; i++)
		{
			if(Character.isLetter(ptslist[i].charAt(0)))
				d.add(new SVGCharElem(ptslist[i]));
			else 
			{
				d.add(new SVGNumElem(ptslist[i]));
			}
		}
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
			String fill, String stroke, Float stroke_width, String transform, Float opacity, String display)
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
		d = new Vector<SVGdElem>();
		for (int i = 0; i < ptslist.length; i++)
		{
			if(Character.isLetter(ptslist[i].charAt(0)))
				d.add(new SVGCharElem(ptslist[i]));
			else 
			{
				d.add(new SVGNumElem(ptslist[i]));
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
		//System.out.println(pts);
		
		String[] ptslist = pts.split(" ");
		d = new Vector<SVGdElem>();
		for (int i = 0; i < ptslist.length; i++)
		{
			if(Character.isLetter(ptslist[i].charAt(0)))
				d.add(new SVGCharElem(ptslist[i]));
			else 
			{
				d.add(new SVGNumElem(ptslist[i]));
			}
		}
	}


	/*
	 * Method which returns generated tags code
	 */
	@Override
	public String getCode() {
		String output;
		output = "<path";
		if(id != "")
			output += " id=\""+id+"\"";
		output += " d=\"";
		for(SVGdElem p: d)
			output += p.getCode()+ " ";
		output = output.trim();
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

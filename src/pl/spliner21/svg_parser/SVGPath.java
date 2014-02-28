package pl.spliner21.svg_parser;

import java.util.Vector;

import org.w3c.dom.Element;

public class SVGPath extends SVGObject {
	Vector<SVGPointEx> d;
	String fill,stroke;
	int stroke_width;
	boolean closed = false;
	
	SVGPath(Element e)
	{
		super(e);

		String pts = e.getAttribute("d");
		
		// TODO: Parser punktów do SVGPointEx
		String[] ptslist = pts.split(" ");
		for (int i = 0; i < ptslist.length; i+=3)
		{
			if(i+1 < ptslist.length)
			{
				char t = ptslist[i].charAt(0);
				String pt = ptslist[i+1]+","+ptslist[i+2];
				d.add(new SVGPointEx(pt,t));
			}
			else if(ptslist[i].equalsIgnoreCase("z"))
				closed = true;
		}
		
		fill = e.getAttribute("fill");
		stroke = e.getAttribute("stroke");
		stroke_width = Integer.parseInt(e.getAttribute("stroke_width"));
		style = e.getAttribute("style");
	}
}

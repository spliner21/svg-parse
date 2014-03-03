package pl.spliner21.svg_parser;

import org.w3c.dom.Element;

/* class representing <text> tag in SVG file
 * @author: Tomasz Szo³tysek
 * @version: 1.0
 */
public class SVGText extends SVGObject {
	Float x,y;
	String text;
	String fill,stroke;
	int stroke_width;
	
	SVGText(Element e)
	{
		super(e);

		try {
			if(e.hasAttribute("x"))
				x = Float.parseFloat(e.getAttribute("x"));
			else x = 0.0f;
			if(e.hasAttribute("y"))
				y = Float.parseFloat(e.getAttribute("y"));
			else y = 0.0f;
		} catch(NumberFormatException ex) {
			System.out.println(e.getAttribute("x")+" "+e.getAttribute("y"));
		}
		text = e.getTextContent();
		if(e.hasAttribute("fill"))
			fill = e.getAttribute("fill");
		else fill = "";
		if(e.hasAttribute("stroke"))
			stroke = e.getAttribute("stroke");
		else stroke = "";
		if(e.hasAttribute("stroke_width"))
			stroke_width = Integer.parseInt(e.getAttribute("stroke_width"));
		if(e.hasAttribute("style"))
			style = e.getAttribute("style");
	}

	@Override
	public String getCode() {
		String output;
		output = "<text id=\""+id+"\" x=\""+x+"\" y=\""+y+"\"";
		if(x > 0.0f) 
			output += " x=\""+x+"\"";
		if(y > 0.0f) 
			output += " y=\""+y+"\"";
		if(fill != "")
			output+= " fill=\""+fill+"\"";
		if(stroke != "")
			output+= " stroke=\""+stroke+"\"";
		if(stroke_width > 0)
			output+= " fill=\""+stroke_width+"\"";
		if(style != "")
			output+= " style=\""+style+"\"";
		if(display != "")
			output+= " display=\""+display+"\"";
		output+= ">"+text+"</text>";
		
		return output;
	}
}

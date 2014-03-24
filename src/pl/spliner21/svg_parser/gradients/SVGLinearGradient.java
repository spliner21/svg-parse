package pl.spliner21.svg_parser.gradients;

import java.util.Vector;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import pl.spliner21.svg_parser.objects.SVGObject;


/**
 * Class representing <linearGradient> tag in SVG file
 * @author spliner21
 * @version 1.0
 */
public class SVGLinearGradient extends SVGObject {
	String gradientUnits = "";
	Float x1 = -1.0f, y1 = -1.0f, x2 = -1.0f, y2 = -1.0f;
	String gradientTransform = "";
	Vector<SVGGradientStop> stops = null;

	/**
	 * Default constructor
	 */
	public SVGLinearGradient()
	{
		super();
		gradientUnits = "userSpaceOnUse";
		x1 = 0.0f;
		y1 = 0.0f;
		x2 = 1.0f;
		y2 = 1.0f;
		stops = new Vector<SVGGradientStop>();
		stops.add(new SVGGradientStop());
		stops.add(new SVGGradientStop(1.0f, "stop-color: #ffffff")); 
	}

	/**
	 * Constructor by parameters
	 * @param id tag's ID
	 * @param style tag's style parameter
	 * @param x1 Gradient's line X1 coordinate
	 * @param y1 Gradient's line Y1 coordinate
	 * @param x2 Gradient's line X2 coordinate
	 * @param y2 Gradient's line Y2 coordinate
	 * @param stop1_color Gradient's first stop color
	 * @param stop2_color Gradient's second stop color
	 * @param transform gradient's transformation
	 * @param opacity gradient's opacity
	 * @param display tag's display parameter
	 */
	public SVGLinearGradient(String id, String style, float x1, float y1, float x2, float y2, String stop1_color, String stop2_color, 
			String transform, Float opacity, String display)
	{
		super(id,style,transform,opacity,display,"","",-1.0f);
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		stops = new Vector<SVGGradientStop>();
		stops.add(new SVGGradientStop(0.0f, stop1_color)); 
		stops.add(new SVGGradientStop(1.0f, stop2_color)); 
	}
	
	
	/**
	 * Constructor by xml's DOM tag element
	 * @param e xml's DOM tag element
	 */
	public SVGLinearGradient(Element e) {
		super(e);

		if(e.hasAttribute("gradientUnits"))
			gradientUnits = e.getAttribute("gradientUnits");
		if(e.hasAttribute("gradientTransform"))
			gradientTransform = e.getAttribute("gradientTransform");
		if(e.hasAttribute("x1"))
			x1 = Float.parseFloat(e.getAttribute("x1"));
		if(e.hasAttribute("y1"))
			y1 = Float.parseFloat(e.getAttribute("y1"));
		if(e.hasAttribute("x2"))
			x2 = Float.parseFloat(e.getAttribute("x2"));
		if(e.hasAttribute("y2"))
			y2 = Float.parseFloat(e.getAttribute("y2"));
		if(e.hasChildNodes())
		{
			stops = new Vector<SVGGradientStop>();
			NodeList childNodes = e.getChildNodes();
			for (int i = 0; i < childNodes.getLength(); i++)
			{
				Node tmp = childNodes.item(i);
				if(tmp.getNodeName() == "stop")
					stops.add(new SVGGradientStop((Element)tmp));
			}
		}
	}
	
	@Override
	public String getCode() {
		String output;
		output = "<linearGradient";
		if(id != "")
			output += " id=\""+id+"\"";
		if(gradientUnits != "")
			output+= " gradientUnits=\""+gradientUnits+"\"";
		if(x1 >= 0.0f)
			output+= " x1=\""+x1+"\"";
		if(y1 >= 0.0f)
			output+= " y1=\""+y1+"\"";
		if(x2 >= 0.0f)
			output+= " x2=\""+x2+"\"";
		if(y2 >= 0.0f)
			output+= " y2=\""+y2+"\"";
		if(gradientTransform != "")
			output+= " gradientTransform=\""+gradientTransform+"\"";
		output += ">";
		for(SVGGradientStop stop : stops)
			output += "\n"+stop.getCode();
		
		
		output+= "\n</linearGradient>";
		
		return output;
	}

	@Override
	public void scale(Float factor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void scale(Float factorx, Float factory) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void scale(Float factor, Float cex, Float cey) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void scale(Float factorx, Float factory, Float cex, Float cey) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rotate(Float angle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rotate(Float angle, Float cex, Float cey) {
		// TODO Auto-generated method stub
		
	}

}

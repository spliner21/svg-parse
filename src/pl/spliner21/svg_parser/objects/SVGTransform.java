package pl.spliner21.svg_parser.objects;

import java.awt.geom.Point2D;
import java.util.Vector;

/**
 * Class that represents transform argument of SVG DOM element
 * @author spliner21
 * @version 1.0
 */
public class SVGTransform {
	Point2D transformTranslate = null;
	Point2D transformScale = null;
	Float transformRotate = 0.0f;
	Point2D transformRotateCenter = null;
	Float transformSkewX = 0.0f;
	Float transformSkewY = 0.0f;
	Vector<Float> transformMatrix = null;

	/**
	 * Constructor using content of a transform parameter
	 * @param transform content of transform parameter of a tag
	 */
	SVGTransform(String transform)
	{
		transform = transform.toLowerCase();
		transform = transform.replace(',',' ');
		transform = transform.replaceAll("\\s+", " ");
		transform = transform.trim();
		String[] transformList = transform.split(";");
		for(String s: transformList)
		{
			s = s.trim();
			if(s.startsWith("translate"))
			{
				s = s.replaceFirst("translate(", "");
				s = s.replace(')',' ');
				s = s.trim();
				String[] elements = s.split(" ");
				transformTranslate.setLocation(Double.parseDouble(elements[0]), Double.parseDouble(elements[1]));
			}
			else if(s.startsWith("scale"))
			{
				s = s.replaceFirst("scale(", "");
				s = s.replace(')',' ');
				s = s.trim();
				String[] elements = s.split(" ");
				transformScale.setLocation(Double.parseDouble(elements[0]), Double.parseDouble(elements[1]));
			}
			else if(s.startsWith("rotate"))
			{
				s = s.replaceFirst("rotate(", "");
				s = s.replace(')',' ');
				s = s.trim();
				String[] elements = s.split(" ");
				transformRotate = Float.parseFloat(elements[0]);
				if(elements.length > 1)
					transformRotateCenter.setLocation(Double.parseDouble(elements[1]), Double.parseDouble(elements[2]));
			}
			else if(s.startsWith("skewx"))
			{
				s = s.replaceFirst("skewx(", "");
				s = s.replace(')',' ');
				s = s.trim();
				transformSkewX = Float.parseFloat(s);
			}
			else if(s.startsWith("skewY=y"))
			{
				s = s.replaceFirst("skewy(", "");
				s = s.replace(')',' ');
				s = s.trim();
				transformSkewY = Float.parseFloat(s);
			}
			else if(s.startsWith("matrix"))
			{
				s = s.replaceFirst("matrix(", "");
				s = s.replace(')',' ');
				s = s.trim();
				String[] elements = s.split(" ");
				transformMatrix = new Vector<Float>();
				for(String e: elements)
					transformMatrix.add(Float.parseFloat(e));
			}
		}
	}
	
	/**
	 * Rotate around angle
	 * @param angle rotation angle (in degrees)
	 */
	public void rotate(Float angle)
	{
		transformRotate += angle;
	}
	/**
	 * Rotate around angle (Double version)
	 * @param angle rotation angle (in degrees)
	 */
	public void rotate(Double angle)
	{
		transformRotate += angle.floatValue();
	}
	
	/**
	 * Generates content of a transform parameter in SVG-acceptable convention
	 * @return transform parameter content
	 */
	public String getCode()
	{
		String output = "";

		if(transformTranslate != null)
			output += "translate("+transformTranslate.getX()+" "+transformTranslate.getY()+"); ";
		if(transformScale != null)
			output += "scale("+transformScale.getX()+" "+transformScale.getY()+"); ";
		if(transformRotate != 0.0f)
		{
			output += "rotate("+transformRotate;
			if(transformRotateCenter != null)
				output += " "+transformRotateCenter.getX()+" "+transformRotateCenter.getY();
			output += "); ";
		}
		if(transformSkewX != 0.0f)
			output += "skewX("+transformSkewX+"); ";
		if(transformSkewY != 0.0f)
			output += "skewY("+transformSkewY+"); ";
		if(transformMatrix != null)
		{
			output += "matrix(";
			for(Float f:transformMatrix)
				output += f+" ";
			output += "); ";
		}
		
		return output;
	}
	
	@Override
	public String toString()
	{
		return getCode();
	}
}

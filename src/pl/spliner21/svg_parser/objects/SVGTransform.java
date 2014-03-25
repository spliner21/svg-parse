package pl.spliner21.svg_parser.objects;

import java.awt.geom.Point2D;
import java.util.Vector;

/**
 * Class that represents transform argument of SVG DOM element
 * @author spliner21
 * @version 1.0
 */
public class SVGTransform {
	Point2D translate = null;
	Point2D scale = null;
	Float rotate = 0.0f;
	Point2D rotationCenter = null;
	Float skewX = 0.0f;
	Float skewY = 0.0f;
	Vector<Float> matrix = null;

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
		String[] transformList = transform.split("\\)");
		for(String s: transformList)
		{
			s = s.trim();
			if(s.startsWith("translate"))
			{
				s = s.replaceFirst("translate\\(", "");
				s = s.trim();
				String[] elements = s.split(" ");
				translate = new Point2D.Double(Double.parseDouble(elements[0]), Double.parseDouble(elements[1]));
			}
			else if(s.startsWith("scale"))
			{
				s = s.replaceFirst("scale\\(", "");
				s = s.trim();
				String[] elements = s.split(" ");
				scale = new Point2D.Double(Double.parseDouble(elements[0]), Double.parseDouble(elements[1]));
			}
			else if(s.startsWith("rotate"))
			{
				s = s.replaceFirst("rotate\\(", "");
				s = s.trim();
				String[] elements = s.split(" ");
				rotate = Float.parseFloat(elements[0]);
				if(elements.length > 1)
					rotationCenter = new Point2D.Double(Double.parseDouble(elements[1]), Double.parseDouble(elements[2]));
			}
			else if(s.startsWith("skewx"))
			{
				s = s.replaceFirst("skewx\\(", "");
				s = s.trim();
				skewX = Float.parseFloat(s);
			}
			else if(s.startsWith("skewy"))
			{
				s = s.replaceFirst("skewy\\(", "");
				s = s.trim();
				skewY = Float.parseFloat(s);
			}
			else if(s.startsWith("matrix"))
			{
				s = s.replaceFirst("matrix\\(", "");
				s = s.trim();
				String[] elements = s.split(" ");
				matrix = new Vector<Float>();
				for(String e: elements)
					matrix.add(Float.parseFloat(e));
			}
		}
	}

	/**
	 * Scale by factor
	 * @param factor scaling factor (1.0f does nothing => 100% scale)
	 */
	public void scale(Float factor)
	{
		scale.setLocation(scale.getX()+factor, scale.getY()+factor);
	}


	/**
	 * Scale by factors
	 * @param factorx scaling X factor (1.0f does nothing => 100% scale)
	 * @param factory scaling Y factor (1.0f does nothing => 100% scale)
	 */
	public void scale(Float factorx, Float factory)
	{
		scale.setLocation(scale.getX()+factorx, scale.getY()+factory);
	}
	
	/**
	 * Rotate by angle
	 * @param angle rotation angle (in degrees)
	 */
	public void rotate(Float angle)
	{
		rotate += angle;
	}
	/**
	 * Rotate by angle (Double version)
	 * @param angle rotation angle (in degrees)
	 */
	public void rotate(Double angle)
	{
		rotate += angle.floatValue();
	}
	
	/**
	 * Rotate by angle around (cex,cey)
	 * @param angle rotation angle (in degrees)
	 * @param cex points X coordinate
	 * @param cey points Y coordinate
	 */
	public void rotate(Float angle, Float cex, Float cey)
	{
		rotate += angle;
		rotationCenter = new Point2D.Float(cex, cey); // TODO: it changes old center - if used Twice may occur problem
	}
	/**
	 * Rotate by angle around (cex,cey) (Double version)
	 * @param angle rotation angle (in degrees)
	 * @param cex points X coordinate
	 * @param cey points Y coordinate
	 */
	public void rotate(Double angle, Float cex, Float cey)
	{
		rotate += angle.floatValue();
		rotationCenter = new Point2D.Float(cex, cey); // TODO: it changes old center - if used Twice may occur problem
	}
	
	/**
	 * Generates content of a transform parameter in SVG-acceptable convention
	 * @return transform parameter content
	 */
	public String getCode()
	{
		String output = "";

		if(translate != null && (translate.getX() != 0.0f || translate.getY() != 0.0f))
			output += "translate("+translate.getX()+" "+translate.getY()+") ";
		if(scale != null && (scale.getX() != 1.0f || scale.getY() != 1.0f))
			output += "scale("+scale.getX()+" "+scale.getY()+") ";
		if(rotate != 0.0f)
		{
			output += "rotate("+rotate;
			if(rotationCenter != null)
				output += " "+rotationCenter.getX()+" "+rotationCenter.getY();
			output += ") ";
		}
		if(skewX != 0.0f)
			output += "skewX("+skewX+") ";
		if(skewY != 0.0f)
			output += "skewY("+skewY+") ";
		if(matrix != null)
		{
			output += "matrix(";
			for(Float f:matrix)
				output += f+" ";
			output += ") ";
		}
		
		return output;
	}
	
	@Override
	public String toString()
	{
		return getCode();
	}
}

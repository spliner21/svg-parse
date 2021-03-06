package pl.spliner21.svg_parser.objects;

import java.awt.geom.Point2D;
import java.util.Vector;

/**
 * Class that represents transform argument of SVG DOM element
 * @author spliner21
 * @version 1.0
 */
public class SVGTransform {
	private Point2D translate = new Point2D.Float(0.0f,0.0f);
	private Point2D scale = new Point2D.Float(1.0f,1.0f);
	private float rotate = 0.0f;
	private Point2D rotationCenter = null;
	private float skewX = 0.0f;
	private float skewY = 0.0f;
	private Vector<Float> matrix = new Vector<Float>();

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
	public void scale(float factor)
	{
		scale.setLocation(scale.getX()*factor, scale.getY()*factor);
	}


	/**
	 * Scale by factors
	 * @param factorx scaling X factor (1.0f does nothing => 100% scale)
	 * @param factory scaling Y factor (1.0f does nothing => 100% scale)
	 */
	public void scale(float factorx, float factory)
	{
		scale.setLocation(scale.getX()*factorx, scale.getY()*factory);
	}
	
	/**
	 * Rotate by angle
	 * @param angle rotation angle (in degrees)
	 */
	public void rotate(float angle)
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
	public void rotate(float angle, float cex, float cey)
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
	public void rotate(Double angle, float cex, float cey)
	{
		rotate += angle.floatValue();
		rotationCenter = new Point2D.Float(cex, cey); // TODO: it changes old center - if used Twice may occur problem
	}

	@Override
	public String toString()
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
		if(matrix != null && matrix.size() != 0)
		{
			output += "matrix(";
			for(float f:matrix)
				output += f+" ";
			output += ") ";
		}
		
		return output;
	}
}

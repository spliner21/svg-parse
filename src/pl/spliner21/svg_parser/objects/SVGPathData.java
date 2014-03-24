package pl.spliner21.svg_parser.objects;

import java.awt.geom.Point2D;
import java.util.Vector;

/** 
 * abstract class representing <path>'s data (d parameter) element in SVG file
 * @author spliner21
 * @version 1.0
 */
public class SVGPathData {
	
	char type;
	Vector<Float> ptsList;
	
	/**
	 * Default constructor
	 */
	SVGPathData()
	{
		type = 'z';
		ptsList = new Vector<Float>();
	}
	
	/**
	 * Constructor by parameters
	 * @param type char with which the coordinates are followed
	 * @param ptsList list of coordinates assigned to current char
	 */
	SVGPathData(char type, Vector<Float> ptsList)
	{
		this.type = type;
		this.ptsList = ptsList;
	}
	
	/**
	 * Scale points by factor
	 * @param factor scaling factor
	 */
	public void scale(float factor)
	{
		for(Float p: ptsList)
		{
			p *= factor;
		}
	}

	/**
	 * Scale points by factors
	 * @param factorx scaling factor X
	 * @param factory scaling factor Y
	 */
	public void scale(float factorx, float factory)
	{
		switch(type)
		{
		case 'H':
		case 'h':
			for(Float p: ptsList)
			{
				p *= factorx;
			}
			break;
		case 'V':
		case 'v':
			for(Float p: ptsList)
			{
				p *= factory;
			}
			break;
		case 'z':
			break;
		default:
			for(int i=0; i < ptsList.size(); i+=2)
			{
				ptsList.set(i, ptsList.elementAt(i)*factorx);
				ptsList.set(i+1, ptsList.elementAt(i+1)*factory);
			}
			break;
		}
	}

	
	/**
	 * Scale by factor with scale's center
	 * @param factor scaling factor (1.0f does nothing => 100% scale)
	 * @param cex scaling center X coordinate
	 * @param cey scaling center Y coordinate
	 * @param lx last absolute point's X coordinate
	 * @param ly last absolute point's Y coordinate
	 */
	public Point2D scale(Float factor, Float cex, Float cey, Float lx, Float ly)
	{
		Point2D ret = new Point2D.Float();
		switch(type)
		{
		case 'H':
			ret.setLocation(ptsList.elementAt(ptsList.size()-1),ly);
			for(Float p: ptsList)
			{
				p = (p-cex)*factor+cex;
			}
			break;
		case 'h':
			ret.setLocation(ptsList.elementAt(ptsList.size()-1)+lx,ly);
			for(Float p: ptsList)
			{
				p = (lx+p-cex)*factor+cex-lx;
			}
			break;
		case 'V':
			ret.setLocation(lx,ptsList.elementAt(ptsList.size()-1));
			for(Float p: ptsList)
			{
				p = (p-cey)*factor+cey;
			}
			break;
		case 'v':
			ret.setLocation(lx,ptsList.elementAt(ptsList.size()-1)+ly);
			for(Float p: ptsList)
			{
				ret.setLocation(lx, p+ly);
				p = (ly+p-cey)*factor+cey-ly;
			}
			break;
		case 'M':
			ret.setLocation(ptsList.elementAt(ptsList.size()-2),ptsList.elementAt(ptsList.size()-1));
			for(int i=0; i < ptsList.size(); i+=2)
			{
				ptsList.set(i, (ptsList.elementAt(i)-cex)*factor + cex);
				ptsList.set(i+1, (ptsList.elementAt(i+1)-cey)*factor + cey);
			}
			break;
		case 'm':
			ret.setLocation(ptsList.elementAt(ptsList.size()-2)+lx,ptsList.elementAt(ptsList.size()-1)+ly);
			int x = 0;
			if(lx == 0.f && ly == 0.f)
			{
				lx = ptsList.elementAt(0);
				ly = ptsList.elementAt(1);
				ptsList.set(0, (ptsList.elementAt(0)-cex)*factor + cex);
				ptsList.set(1, (ptsList.elementAt(1)-cey)*factor + cey);
				x+=2;
			}
			for(int i=x; i < ptsList.size(); i+=2)
			{
				ptsList.set(i, (ptsList.elementAt(i)-cex+lx)*factor + cex-lx);
				ptsList.set(i+1, (ptsList.elementAt(i+1)-cey+ly)*factor + cey-ly);
			}
			break;
		default:
			break;
		}
		return ret;
	}
	/**
	 * Scale by factor with scale's center
	 * @param factorx scaling X factor (1.0f does nothing => 100% scale)
	 * @param factory scaling Y factor (1.0f does nothing => 100% scale)
	 * @param cex scaling center X coordinate
	 * @param cey scaling center Y coordinate
	 * @param lx last absolute point's X coordinate
	 * @param ly last absolute point's Y coordinate
	 */
	public Point2D scale(Float factorx, Float factory, Float cex, Float cey, Float lx, Float ly)
	{
		Point2D ret = new Point2D.Float();
		switch(type)
		{
		case 'H':
			ret.setLocation(ptsList.elementAt(ptsList.size()-1),ly);
			for(Float p: ptsList)
			{
				p = (p-cex)*factorx+cex;
			}
			break;
		case 'h':
			ret.setLocation(ptsList.elementAt(ptsList.size()-1)+lx,ly);
			for(Float p: ptsList)
			{
				p = (lx+p-cex)*factorx+cex-lx;
			}
			break;
		case 'V':
			ret.setLocation(lx,ptsList.elementAt(ptsList.size()-1));
			for(Float p: ptsList)
			{
				p = (p-cey)*factory+cey;
			}
			break;
		case 'v':
			ret.setLocation(lx,ptsList.elementAt(ptsList.size()-1)+ly);
			for(Float p: ptsList)
			{
				ret.setLocation(lx, p+ly);
				p = (ly+p-cey)*factory+cey-ly;
			}
			break;
		case 'M':
			ret.setLocation(ptsList.elementAt(ptsList.size()-2),ptsList.elementAt(ptsList.size()-1));
			for(int i=0; i < ptsList.size(); i+=2)
			{
				ptsList.set(i, (ptsList.elementAt(i)-cex)*factorx + cex);
				ptsList.set(i+1, (ptsList.elementAt(i+1)-cey)*factory + cey);
			}
			break;
		case 'm':
			ret.setLocation(ptsList.elementAt(ptsList.size()-2)+lx,ptsList.elementAt(ptsList.size()-1)+ly);
			int x = 0;
			if(lx == 0.f && ly == 0.f)
			{
				lx = ptsList.elementAt(0);
				ly = ptsList.elementAt(1);
				ptsList.set(0, (ptsList.elementAt(0)-cex)*factorx + cex);
				ptsList.set(1, (ptsList.elementAt(1)-cey)*factory + cey);
				x+=2;
			}
			for(int i=x; i < ptsList.size(); i+=2)
			{
				ptsList.set(i, (ptsList.elementAt(i)-cex+lx)*factorx + cex-lx);
				ptsList.set(i+1, (ptsList.elementAt(i+1)-cey+ly)*factory + cey-ly);
			}
			break;
		default:
			break;
		}
		return ret;
	}
	
	
	/**
	 * Method that generates <path> tag's d-parameter code fragment
	 * @return generated SVG file's code part for element
	 */
	String getCode(){
		String r = Character.toString(type);
		for(Float f: ptsList)
		{
			r += " "+f.toString();
		}
		return r;
	}
}

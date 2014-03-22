package pl.spliner21.svg_parser.objects;

import java.util.Vector;

/** 
 * abstract class representing element in <path> tag's d argument in SVG file
 * @author spliner21
 * @version 1.0
 */
public class SVGdElem {
	
	char type;
	Vector<Float> ptsList;
	
	/**
	 * Default constructor
	 */
	SVGdElem()
	{
		type = 'z';
		ptsList = new Vector<Float>();
	}
	
	/**
	 * Constructor by parameters
	 * @param type char with which the coordinates are followed
	 * @param ptsList list of coordinates assigned to current char
	 */
	SVGdElem(char type, Vector<Float> ptsList)
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
	 */
	public void scale(Float factor, Float cex, Float cey)
	{
		/* switch(type)
		{
		case 'H':
		case 'h':
			for(Float p: ptsList)
			{
				p = (p-cex)*factor+cex;
			}
			break;
		case 'V':
		case 'v':
			for(Float p: ptsList)
			{
				p = (p-cey)*factor+cey;
			}
			break;
		case 'z':
			break;
		default:
			for(int i=0; i < ptsList.size(); i+=2)
			{
				ptsList.set(i, (ptsList.elementAt(i)-cex)*factor + cex);
				ptsList.set(i+1, (ptsList.elementAt(i+1)-cey)*factor + cey);
			}
			break;
		} */ 
		// TODO; It won't work correctly this way (small letter elements are relatively defined)
	}
	/**
	 * Scale by factor with scale's center
	 * @param factorx scaling X factor (1.0f does nothing => 100% scale)
	 * @param factory scaling Y factor (1.0f does nothing => 100% scale)
	 * @param cex scaling center X coordinate
	 * @param cey scaling center Y coordinate
	 */
	public void scale(Float factorx, Float factory, Float cex, Float cey)
	{
		/*switch(type)
		{
		case 'H':
		case 'h':
			for(Float p: ptsList)
			{
				p = (p-cex)*factorx+cex;
			}
			break;
		case 'V':
		case 'v':
			for(Float p: ptsList)
			{
				p = (p-cey)*factory+cey;
			}
			break;
		case 'z':
			break;
		default:
			for(int i=0; i < ptsList.size(); i+=2)
			{
				ptsList.set(i, (ptsList.elementAt(i)-cex)*factorx + cex);
				ptsList.set(i+1, (ptsList.elementAt(i+1)-cey)*factory + cey);
			}
			break;
		}*/ 
		// TODO; It won't work correctly this way (small letter elements are relatively defined)
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

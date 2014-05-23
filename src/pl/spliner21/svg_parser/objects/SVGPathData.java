package pl.spliner21.svg_parser.objects;

import java.awt.geom.Point2D;
import java.util.Vector;

/** 
 * abstract class representing <path>'s data (d parameter) element in SVG file
 * @author spliner21
 * @version 1.0
 */
public class SVGPathData {
	
	private char type;
	private Vector<Float> ptsList;
	
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
	 * Scale by factor with scale's center
	 * @param factor scaling factor (1.0f does nothing => 100% scale)
	 * @param cex scaling center X coordinate
	 * @param cey scaling center Y coordinate
	 * @param lx last absolute point's X coordinate
	 * @param ly last absolute point's Y coordinate
	 */
	public Point2D scale(float factor, float cex, float cey, float lx, float ly)
	{
		Point2D ret = new Point2D.Float();
		float tx = lx,ty = ly;
		float lax, lay; // lx and ly after scaling.
		switch(type)
		{
		case 'A':
			ret.setLocation(ptsList.elementAt(ptsList.size()-2),ptsList.elementAt(ptsList.size()-1));
			for(int i=0; i < ptsList.size(); i+=7)
			{
				ptsList.set(i, ptsList.elementAt(i)*factor);
				ptsList.set(i+1, ptsList.elementAt(i+1)*factor);
				ptsList.set(i+5, (ptsList.elementAt(i+5)-cex)*factor + cex);
				ptsList.set(i+6, (ptsList.elementAt(i+6)-cey)*factor + cey);
			}
			break;
		case 'H':
			ret.setLocation(ptsList.elementAt(ptsList.size()-1),ly);
			for(int i=0; i < ptsList.size(); ++i)
			{
				ptsList.set(i, (ptsList.elementAt(i)-cex)*factor + cex);
			}
			break;
		case 'h':
			for(int i=0; i < ptsList.size(); ++i)
			{
				lax = tx * factor;
				tx += ptsList.elementAt(i);
				ptsList.set(i, (tx-cex)*factor + cex-lax);
			}
			ret.setLocation(tx,ly);
			break;
		case 'V':
			ret.setLocation(lx,ptsList.elementAt(ptsList.size()-1));
			for(int i=0; i < ptsList.size(); ++i)
			{
				ptsList.set(i, (ptsList.elementAt(i)-cey)*factor + cey);
			}
			break;
		case 'v':
			for(int i=0; i < ptsList.size(); ++i)
			{
				lay = ty * factor;
				ty += ptsList.elementAt(i);
				ptsList.set(i, (ty-cey)*factor + cey-lay);
			}
			ret.setLocation(lx,ty);
			break;
		case 'q':
		case 's':
			lax = lx * factor;
			lay = ly * factor;
			for(int i=0; i < ptsList.size(); i+=4)
			{
				tx = lx + ptsList.elementAt(i+2);
				ty = ly + ptsList.elementAt(i+3);
				ptsList.set(i, ptsList.elementAt(i)*factor);
				ptsList.set(i+1, ptsList.elementAt(i+1)*factor);
				ptsList.set(i+2, (tx-cex)*factor + cex-lax);
				ptsList.set(i+3, (ty-cey)*factor + cey-lay);
			}
			ret.setLocation(tx,ty);
			break;
		case 'c':
			lax = lx * factor;
			lay = ly * factor;
			for(int i=0; i < ptsList.size(); i+=6)
			{
				tx = lx + ptsList.elementAt(i+4);
				ty = ly + ptsList.elementAt(i+5);
				ptsList.set(i, ptsList.elementAt(i)*factor);
				ptsList.set(i+1, ptsList.elementAt(i+1)*factor);
				ptsList.set(i+2, ptsList.elementAt(i+2)*factor);
				ptsList.set(i+3, ptsList.elementAt(i+3)*factor);
				ptsList.set(i+4, (tx-cex)*factor + cex-lax);
				ptsList.set(i+5, (ty-cey)*factor + cey-lay);
			}
			ret.setLocation(tx,ty);
			break;
		case 'M':
		case 'L':
		case 'T':
		case 'C':
		case 'Q':
		case 'S':
			ret.setLocation(ptsList.elementAt(ptsList.size()-2),ptsList.elementAt(ptsList.size()-1));
			for(int i=0; i < ptsList.size(); i+=2)
			{
				ptsList.set(i, (ptsList.elementAt(i)-cex)*factor + cex);
				ptsList.set(i+1, (ptsList.elementAt(i+1)-cey)*factor + cey);
			}
			break;
		case 'm':
		case 'l':
			ret.setLocation(ptsList.elementAt(ptsList.size()-2)+lx,ptsList.elementAt(ptsList.size()-1)+ly);
			for(int i=0; i < ptsList.size(); i+=2)
			{
				lax = tx * factor;
				lay = ty * factor;
				tx += ptsList.elementAt(i);
				ty += ptsList.elementAt(i+1);
				ptsList.set(i, (tx-cex)*factor + cex-lax);
				ptsList.set(i+1, (ty-cey)*factor + cey-lay);
			}
			break;
		case 't':
			ret.setLocation(ptsList.elementAt(ptsList.size()-2)+lx,ptsList.elementAt(ptsList.size()-1)+ly);
			lax = lx * factor;
			lay = ly * factor;
			for(int i=0; i < ptsList.size(); i+=2)
			{
				tx = lx + ptsList.elementAt(i);
				ty = ly + ptsList.elementAt(i+1);
				ptsList.set(i, (tx-cex)*factor + cex-lax);
				ptsList.set(i+1, (ty-cey)*factor + cey-lay);
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
	public Point2D scale(float factorx, float factory, float cex, float cey, float lx, float ly)
	{
		Point2D ret = new Point2D.Float();
		float tx = lx,ty = ly;
		float lax, lay; // lx and ly after scaling.
		switch(type)
		{
		case 'A':
			ret.setLocation(ptsList.elementAt(ptsList.size()-2),ptsList.elementAt(ptsList.size()-1));
			for(int i=0; i < ptsList.size(); i+=7)
			{
				ptsList.set(i, ptsList.elementAt(i)*factorx);
				ptsList.set(i+1, ptsList.elementAt(i+1)*factory);
				ptsList.set(i+5, (ptsList.elementAt(i+5)-cex)*factorx + cex);
				ptsList.set(i+6, (ptsList.elementAt(i+6)-cey)*factory + cey);
			}
			break;
		case 'H':
			ret.setLocation(ptsList.elementAt(ptsList.size()-1),ly);
			for(int i=0; i < ptsList.size(); ++i)
			{
				ptsList.set(i, (ptsList.elementAt(i)-cex)*factorx + cex);
			}
			break;
		case 'h':
			for(int i=0; i < ptsList.size(); ++i)
			{
				lax = tx * factorx;
				tx += ptsList.elementAt(i);
				ptsList.set(i, (tx-cex)*factorx + cex-lax);
			}
			ret.setLocation(tx,ly);
			break;
		case 'V':
			ret.setLocation(lx,ptsList.elementAt(ptsList.size()-1));
			for(int i=0; i < ptsList.size(); ++i)
			{
				ptsList.set(i, (ptsList.elementAt(i)-cey)*factory + cey);
			}
			break;
		case 'v':
			for(int i=0; i < ptsList.size(); ++i)
			{
				lay = ty * factory;
				ty += ptsList.elementAt(i);
				ptsList.set(i, (ty-cey)*factory + cey-lay);
			}
			ret.setLocation(lx,ty);
			break;
		case 'q':
		case 's':
			lax = lx * factorx;
			lay = ly * factory;
			for(int i=0; i < ptsList.size(); i+=4)
			{
				tx = lx + ptsList.elementAt(i+2);
				ty = ly + ptsList.elementAt(i+3);
				ptsList.set(i, ptsList.elementAt(i)*factorx);
				ptsList.set(i+1, ptsList.elementAt(i+1)*factory);
				ptsList.set(i+2, (tx-cex)*factorx + cex-lax);
				ptsList.set(i+3, (ty-cey)*factory + cey-lay);
			}
			ret.setLocation(tx,ty);
			break;
		case 'c':
			lax = lx * factorx;
			lay = ly * factory;
			for(int i=0; i < ptsList.size(); i+=6)
			{
				tx = lx + ptsList.elementAt(i+4);
				ty = ly + ptsList.elementAt(i+5);
				ptsList.set(i, ptsList.elementAt(i)*factorx);
				ptsList.set(i+1, ptsList.elementAt(i+1)*factory);
				ptsList.set(i+2, ptsList.elementAt(i+2)*factorx);
				ptsList.set(i+3, ptsList.elementAt(i+3)*factory);
				ptsList.set(i+4, (tx-cex)*factorx + cex-lax);
				ptsList.set(i+5, (ty-cey)*factory + cey-lay);
			}
			ret.setLocation(tx,ty);
			break;
		case 'M':
		case 'L':
		case 'T':
		case 'C':
		case 'Q':
		case 'S':
			ret.setLocation(ptsList.elementAt(ptsList.size()-2),ptsList.elementAt(ptsList.size()-1));
			for(int i=0; i < ptsList.size(); i+=2)
			{
				ptsList.set(i, (ptsList.elementAt(i)-cex)*factorx + cex);
				ptsList.set(i+1, (ptsList.elementAt(i+1)-cey)*factory + cey);
			}
			break;
		case 'm':
		case 'l':
			ret.setLocation(ptsList.elementAt(ptsList.size()-2)+lx,ptsList.elementAt(ptsList.size()-1)+ly);
			for(int i=0; i < ptsList.size(); i+=2)
			{
				lax = tx * factorx;
				lay = ty * factory;
				tx += ptsList.elementAt(i);
				ty += ptsList.elementAt(i+1);
				ptsList.set(i, (tx-cex)*factorx + cex-lax);
				ptsList.set(i+1, (ty-cey)*factory + cey-lay);
			}
			break;
		case 't':
			ret.setLocation(ptsList.elementAt(ptsList.size()-2)+lx,ptsList.elementAt(ptsList.size()-1)+ly);
			lax = lx * factorx;
			lay = ly * factory;
			for(int i=0; i < ptsList.size(); i+=2)
			{
				tx = lx + ptsList.elementAt(i);
				ty = ly + ptsList.elementAt(i+1);
				ptsList.set(i, (tx-cex)*factorx + cex-lax);
				ptsList.set(i+1, (ty-cey)*factory + cey-lay);
			}
			break;
		default:
			break;
		}
		return ret;
	}
	
	/**
	 * Scale by factor with scale's center
	 * @param angle rotation angle
	 * @param sina sinus of rotation angle
	 * @param cosa cosinus of rotation angle
	 * @param cex scaling center X coordinate
	 * @param cey scaling center Y coordinate
	 * @param lx last absolute point's X coordinate
	 * @param ly last absolute point's Y coordinate
	 */
	public Point2D rotate(float angle, float sina, float cosa, float cex, float cey, float lx, float ly)
	{
		Point2D ret = new Point2D.Float();
		float tx = lx,ty = ly;
		float lax, lay; // lx and ly after rotation.
		float ox, oy;
		Vector<Float> tmp; 
		switch(type)
		{
		case 'A':
			ret.setLocation(ptsList.elementAt(ptsList.size()-2),ptsList.elementAt(ptsList.size()-1));
			for(int i=0; i < ptsList.size(); i+=7)
			{
				ptsList.set(i+2, ptsList.elementAt(i+2)+angle);
				ox = ptsList.elementAt(i+5) - cex;
				oy = ptsList.elementAt(i+6) - cey;
				ptsList.set(i+5, ox * cosa - oy * sina + cex);
				ptsList.set(i+6, ox * sina + oy * cosa + cey);
			}
			break;
		case 'H':
			ret.setLocation(ptsList.elementAt(ptsList.size()-1),ly);
			type = 'L';
			tmp = new Vector<Float>(ptsList); 
			ptsList.clear();
			for(int i=0; i < tmp.size(); ++i)
			{
				ox = tmp.elementAt(i) - cex;
				oy = ly - cey;
				ptsList.add(ox * cosa - oy * sina + cex);
				ptsList.add(ox * sina + oy * cosa + cey);
			}
			break;
		case 'h':
			type = 'l';
			tmp = new Vector<Float>(ptsList); 
			ptsList.clear();
			for(int i=0; i < tmp.size(); ++i)
			{
				ox = tx - cex;
				oy = ly - cey;
				lax = ox * cosa - oy * sina + cex;
				lay = ox * sina + oy * cosa + cey;
				
				tx += tmp.elementAt(i);
				ox = tx - cex;
				ptsList.add(ox * cosa - oy * sina + cex - lax);
				ptsList.add(ox * sina + oy * cosa + cey - lay);
			}
			ret.setLocation(tx,ly);
			break;
		case 'V':
			ret.setLocation(lx,ptsList.elementAt(ptsList.size()-1));
			type = 'L';
			tmp = new Vector<Float>(ptsList); 
			ptsList.clear();
			for(int i=0; i < tmp.size(); ++i)
			{
				ox = lx - cex;
				oy = tmp.elementAt(i) - cey;
				ptsList.add(ox * cosa - oy * sina + cex);
				ptsList.add(ox * sina + oy * cosa + cey);
			}
			break;
		case 'v':
			type = 'l';
			tmp = new Vector<Float>(ptsList); 
			ptsList.clear();
			for(int i=0; i < tmp.size(); ++i)
			{
				ox = lx - cex;
				oy = ty - cey;
				lax = ox * cosa - oy * sina + cex;
				lay = ox * sina + oy * cosa + cey;
				
				ty += tmp.elementAt(i);
				oy = ty - cey;
				ptsList.add(ox * cosa - oy * sina + cex - lax);
				ptsList.add(ox * sina + oy * cosa + cey - lay);
			}
			ret.setLocation(lx,ty);
			break;
		case 'q':
		case 's':
			for(int i=0; i < ptsList.size(); i+=4)
			{
				ox = tx - cex;
				oy = ty - cey;
				lax = ox * cosa - oy * sina + cex;
				lay = ox * sina + oy * cosa + cey;
				
				tx = lx + ptsList.elementAt(i+2);
				ty = ly + ptsList.elementAt(i+3);
				
				ox = ptsList.elementAt(i) + lx - cex;
				oy = ptsList.elementAt(i+1) + ly - cey;
				ptsList.set(i, ox * cosa - oy * sina + cex - lax);
				ptsList.set(i+1, ox * sina + oy * cosa + cey - lay);
				
				ox = ptsList.elementAt(i+2) + lx - cex;
				oy = ptsList.elementAt(i+3) + ly - cey;
				ptsList.set(i+2, ox * cosa - oy * sina + cex - lax);
				ptsList.set(i+3, ox * sina + oy * cosa + cey - lay);
			}
			ret.setLocation(tx,ty);
			break;
		case 'c':
			for(int i=0; i < ptsList.size(); i+=6)
			{
				ox = tx - cex;
				oy = ty - cey;
				lax = ox * cosa - oy * sina + cex;
				lay = ox * sina + oy * cosa + cey;
				
				tx = lx + ptsList.elementAt(i+4);
				ty = ly + ptsList.elementAt(i+5);
				
				ox = ptsList.elementAt(i) + lx - cex;
				oy = ptsList.elementAt(i+1) + ly - cey;
				ptsList.set(i, ox * cosa - oy * sina + cex - lax);
				ptsList.set(i+1, ox * sina + oy * cosa + cey - lay);
				
				ox = ptsList.elementAt(i+2) + lx - cex;
				oy = ptsList.elementAt(i+3) + ly - cey;
				ptsList.set(i+2, ox * cosa - oy * sina + cex - lax);
				ptsList.set(i+3, ox * sina + oy * cosa + cey - lay);
				
				ox = ptsList.elementAt(i+4) + lx - cex;
				oy = ptsList.elementAt(i+5) + ly - cey;
				ptsList.set(i+4, ox * cosa - oy * sina + cex - lax);
				ptsList.set(i+5, ox * sina + oy * cosa + cey - lay);
			}
			ret.setLocation(tx,ty);
			break;
		case 'M':
		case 'L':
		case 'T':
		case 'C':
		case 'Q':
		case 'S':
			ret.setLocation(ptsList.elementAt(ptsList.size()-2),ptsList.elementAt(ptsList.size()-1));
			for(int i=0; i < ptsList.size(); i+=2)
			{
				ox = ptsList.elementAt(i) - cex;
				oy = ptsList.elementAt(i+1) - cey;
				ptsList.set(i, ox * cosa - oy * sina + cex);
				ptsList.set(i+1, ox * sina + oy * cosa + cey);
			}
			break;
		case 'm':
		case 'l':
			ox = lx - cex;
			oy = ly - cey;
			ret.setLocation(ptsList.elementAt(ptsList.size()-2)+lx,ptsList.elementAt(ptsList.size()-1)+ly);
			for(int i=0; i < ptsList.size(); i+=2)
			{
				lax = ox * cosa - oy * sina + cex;
				lay = ox * sina + oy * cosa + cey;
				ox += ptsList.elementAt(i);
				oy += ptsList.elementAt(i+1);
				ptsList.set(i, ox * cosa - oy * sina + cex - lax);
				ptsList.set(i+1, ox * sina + oy * cosa + cey - lay);
			}
			break;
		case 't':
			ret.setLocation(ptsList.elementAt(ptsList.size()-2)+lx,ptsList.elementAt(ptsList.size()-1)+ly);
			ox = tx - cex;
			oy = ty - cey;
			for(int i=0; i < ptsList.size(); i+=2)
			{
				lax = ox * cosa - oy * sina + cex;
				lay = ox * sina + oy * cosa + cey;
				ox = ptsList.elementAt(i) + lx - cex;
				oy = ptsList.elementAt(i+1) + ly - cey;
				ptsList.set(i, ox * cosa - oy * sina + cex - lax);
				ptsList.set(i+1, ox * sina + oy * cosa + cey - lay);
			}
			break;
		default:
			break;
		}
		return ret;
	}
	

	/**
	 * Translate data element by distance in X and Y
	 * @param tx translation distance in X axis
	 * @param ty translation distance in y axis
	 */
	public void translate(float tx, float ty)
	{
		switch(type)
		{
		case 'A':
			for(int i=0; i < ptsList.size(); i+=7)
			{
				ptsList.set(i+5, ptsList.elementAt(i+5) + tx);
				ptsList.set(i+6, ptsList.elementAt(i+6) + ty);
			}
			break;
		case 'H':
			for(float p: ptsList)
			{
				p = p + tx;
			}
			break;
		case 'V':
			for(float p: ptsList)
			{
				p = p + ty;
			}
			break;
		case 'Q':
		case 'S':
			for(int i=0; i < ptsList.size(); i+=4)
			{
				ptsList.set(i+2, ptsList.elementAt(i+2) + tx);
				ptsList.set(i+3, ptsList.elementAt(i+3) + ty);
			}
			break;
		case 'C':
			for(int i=0; i < ptsList.size(); i+=6)
			{
				ptsList.set(i+4, ptsList.elementAt(i+4) + tx);
				ptsList.set(i+5, ptsList.elementAt(i+5) + ty);
			}
			break;
		case 'M':
		case 'L':
		case 'T':
			for(int i=0; i < ptsList.size(); i+=2)
			{
				ptsList.set(i, ptsList.elementAt(i) + tx);
				ptsList.set(i+1, ptsList.elementAt(i+1) + ty);
			}
			break;
		default:
			break;
		}
	}
	
	
	@Override
	public String toString(){
		String r = Character.toString(type);
		for(float f: ptsList)
		{
			r += " "+f;
		}
		return r;
	}
}

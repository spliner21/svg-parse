package pl.spliner21.svg_parser;

public class SVGPoint {
	int x;
	int y;
	
	SVGPoint(String s)
	{
		String[] sp = s.split(",");
		x = Integer.parseInt(sp[0]);
		y = Integer.parseInt(sp[1]);
	}
}

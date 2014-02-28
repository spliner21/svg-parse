package pl.spliner21.svg_parser;

public class SVGPointEx extends SVGPoint {
	SVGPointEx(String s) {
		super(s);
		// TODO Auto-generated constructor stub
	}

	SVGPointEx(String s, char t) {
		super(s);
		type = t;
	}
	
	char type;
}

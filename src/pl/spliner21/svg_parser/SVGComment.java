package pl.spliner21.svg_parser;

public class SVGComment extends SVGDOMElem {
	String content;
	
	SVGComment(String line)
	{
		content = line; // TODO: May need to be validated
	}
	
	@Override
	public String getCode() {
		// TODO Auto-generated method stub
		return "<!-- " + content + "-->";
	}

}

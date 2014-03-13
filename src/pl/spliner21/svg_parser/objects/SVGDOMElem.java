package pl.spliner21.svg_parser.objects;

/**
 * Abstract class representing SVG's DOM Element
 * Whole tree's structure bases on this class
 * @author spliner21
 * @version 1.0
 */
public abstract class SVGDOMElem {
	
	/**
	 * Method that returns generated piece of SVG's file content
	 * @return SVG's XML code part for this element (and it's children, if any)
	 */
	public abstract String getCode();
	
}

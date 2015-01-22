/**
 * AlignKit - Word alignment toolkit
 * 
 */
package org.alignkit;

/**
 * @author Jeremy Gwinnup
 *
 */
public class Link {

	int source;
	int target;
	double strength;
	boolean enabled;
	boolean modified;
	
	//A3-friendly constructor
	public Link(int source, int target) {
		this.source = source;
		this.target = target;
		strength = 1.0;
		enabled = true;
		modified = false;
	}
}

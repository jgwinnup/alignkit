/**
 * 
 */
package org.alignkit;

import java.util.ArrayList;
import java.util.List;

/**
 * Base alignment class
 * 
 * @author Jeremy Gwinnup
 *
 */
public class Alignment {

	Sentence source;
	Sentence target;
	List<Link> links;
	private int id;
	double score;
	boolean modified;
	
	/**
	 * Empty alignment constructor
	 */
	public Alignment() {
		source = new Sentence();
		target = new Sentence();
		links = new ArrayList<Link>();
		setId(0);
		score = 0.0;
	}

	public void setId(int id) {
		this.id = id;
		
	}

	public void setScore(double score) {
		this.score = score;
		
	}

	public void setModified(boolean modified) {
		this.modified = modified;
	}

	public int getId() {
		return id;
	}
	
	public String getSourceWordsAsString() {
		return source.getWordsAsString();
	}
	
	public String getTargetWordsAsString() {
		return target.getWordsAsString();
	}
	
	public List<String> getSourceWords() {
		return source.getWords();
	}
	
	public List<String> getTargetWords() {
		return target.getWords();
	}
	
	public boolean linkExists(int s, int t) {
		
		boolean ret = false;
		
		//iterate over links 
		for(Link l : links){
			if((l.source == s) && (l.target == t)) {
				ret = true;
				break;
			}
		}
		
		return ret;
	}
	
}

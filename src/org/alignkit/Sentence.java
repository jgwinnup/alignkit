/**
 * 
 */
package org.alignkit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Sentence container
 *
 * @author Jeremy Gwinnup
 *
 */
public class Sentence {
	
	List<String> words;
	public Sentence(String in){
		words = Arrays.asList(in.split("\\s+"));
	}

	public Sentence() {
		words = new ArrayList<String>();
	}

	public int size() {
		return words.size();
	}
	
	/**
	 * Return words as a space-joined string
	 * @return
	 */
	public String getWordsAsString() {
		StringBuffer buf = new StringBuffer();
		boolean first = true;
		for(String w: words){
			if(first) {
				first = false;
			}
			else {
				buf.append(" ");
			}
			buf.append(w);
		}
		return buf.toString();
	}

	public List<String> getWords() {
		return words;
	}
	
}

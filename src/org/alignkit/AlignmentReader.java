/**
 * 
 */
package org.alignkit;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * Common alignment reader interface
 * 
 * @author Jeremy Gwinnup
 *
 */
public abstract class AlignmentReader extends BufferedReader {
	
	public AlignmentReader(String filename) throws UnsupportedEncodingException, FileNotFoundException {
		//new BufferedReader(new InputStreamReader(new FileInputStream(fname), "UTF8"));
		super(new InputStreamReader(new FileInputStream(filename), "UTF8"));
	}
	
	abstract public Alignment readAlignment() throws IOException;

}

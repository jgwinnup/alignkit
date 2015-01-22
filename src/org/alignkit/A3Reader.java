/**
 * 
 */
package org.alignkit;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Jeremy Gwinnup
 *
 */
public class A3Reader extends AlignmentReader {

	//Regex for A3 links on source line 
	private static Pattern linkPattern = Pattern.compile("\\S+\\s+\\(\\{[0-9\\s+]+\\}\\)");

	public A3Reader(String filename) throws UnsupportedEncodingException,
	FileNotFoundException {
		super(filename);
	}

	/**
	 * Reads a single A3 Format alignment
	 *  (non-Javadoc)
	 * @throws IOException 
	 * @see org.alignkit.AlignmentReader#readAlignment()
	 */
	@Override
	public Alignment readAlignment() throws IOException {

		if(this.ready()) {

			String commentLine = this.readLine();
			String targetLine = this.readLine();
			String sourceLine = this.readLine();

			if(commentLine == null) throw new IOException("null comment line");
			if(targetLine == null) throw new IOException("null target line");
			if(sourceLine == null) throw new IOException("null source line");

			return parseAlignment(commentLine, targetLine, sourceLine);
		}
		else {
			return null;
		}
	}

	/**
	 * Parse an A3 Format alignment from
	 * @param commentLine
	 * @param targetLine
	 * @param sourceLine
	 * @return
	 */
	private Alignment parseAlignment(String commentLine, String targetLine,
			String sourceLine) {

		Alignment ret = new Alignment();

		String[] commentChunks = commentLine.split("\\s+");

		//get sentence id
		//cur.setId(Integer.parseInt(commentChunks[3]));
		if(commentChunks.length >=4) {
			ret.setId(Integer.parseInt(commentChunks[3].substring(1, commentChunks[3].length() - 1)));
		}
		else {
			ret.setId(-1);
		}

		//get prob if available
		if(commentChunks.length >= 14) {
			ret.setScore(Double.parseDouble(commentChunks[13]));
			//System.out.println("Score:" + cur.getScore());
		}

		//get modified marker
		if((commentChunks.length >= 15) && commentChunks[14].matches("modified")){
			ret.setModified(true);
			System.err.println("Modified A3Entry");
		}

		//set target words
		ret.target.words.addAll(Arrays.asList(targetLine.split("\\s+")));

		//set source and links
		if(sourceLine != null) {

			Matcher linkMatch = linkPattern.matcher(sourceLine);

			while(linkMatch.find()) {

				String sub = sourceLine.substring(linkMatch.start(), linkMatch.end());

				//now chunk up the link token
				String[] linkChunks = sub.split("\\s+");
				int sourceIndex = ret.source.size();

				ret.source.words.add(linkChunks[0]);

				for(int i=2; i < linkChunks.length - 1; i++) {
					//System.out.println(" Link " + sourceIndex + ":" + linkChunks[i]);
					ret.links.add(new Link(sourceIndex, Integer.valueOf(linkChunks[i]) - 1));

					//cur.addLink(new A3Link("foo", linkChunks[0]));
				}
			}
		}

		return ret;
	}

}

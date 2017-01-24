package model.data;

import java.io.IOException;
/**
 * 
 */
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import com.thoughtworks.xstream.XStream;

import commons.Level;

public class MyXMLLevelLoader implements ILevelLoader {
	//Data members
	private Reader reader;
	private XStream xstream;
	
	//Constructor
	public MyXMLLevelLoader() {
		this.reader = null;
		this.xstream = new XStream();
	}
	
	//The method loads level from xml file
	@Override
	public Level loadLevel(InputStream is) {
		try {
			this.reader = new InputStreamReader(is);
			Level newLevel = (Level)this.xstream.fromXML(this.reader);
			reader.close();
			return newLevel;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
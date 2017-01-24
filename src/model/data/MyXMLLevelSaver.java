package model.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.thoughtworks.xstream.XStream;

import commons.Level;


public class MyXMLLevelSaver implements ILevelSaver{
	//Data member
	private XStream xstream;
	
	//Constructor
	public MyXMLLevelSaver() {
		this.xstream = new XStream();
	}
	
	//The method saves level from text file
	@Override
	public void saveLevel(Level level, String fileName) throws IOException {
		
		OutputStream outputStream;
		try {
			outputStream = new FileOutputStream(new File(fileName));
			this.xstream.toXML(level, outputStream);
			outputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
}

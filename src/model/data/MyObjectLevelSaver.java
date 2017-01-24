package model.data;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import commons.Level;

public class MyObjectLevelSaver implements ILevelSaver{

	//The method saves level to a binary level
	@Override
	public void saveLevel(Level level, String fileName) throws IOException {
		FileOutputStream outFile;
		outFile = new FileOutputStream(fileName);
		ObjectOutputStream objOutput = new ObjectOutputStream(outFile);
		objOutput.writeObject(level);
		objOutput.close();
		outFile.close();
	}
}
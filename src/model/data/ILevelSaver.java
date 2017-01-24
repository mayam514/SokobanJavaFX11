package model.data;

import java.io.IOException;

import commons.Level;

public interface ILevelSaver {
	/**
	 * The method saves a level
	 * @param level the level that we want to save
	 * @param fileName the path we want to save the level in
	 * @throws IOException
	 */
	public void saveLevel(Level level, String fileName) throws IOException;
}

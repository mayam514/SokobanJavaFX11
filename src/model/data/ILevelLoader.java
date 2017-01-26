package model.data;

import java.io.InputStream;

import commons.Level;

public interface ILevelLoader{
	/**
	 * The method load a level
	 * @param is the path of the file that we need to load
	 * @return the new level that we loaded
	 */
	public Level loadLevel(InputStream is);
}



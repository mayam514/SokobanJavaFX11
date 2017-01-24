package model;

import commons.Level;
import model.data.Character;

public interface IModel {
	/**
	 * The method checks which way the user wants the character
	 * to move to and than activates the move method in MySokobanPolicy
	 */
	public void moveCharacter(String direction, Character character);
	
	/**
	 * The method returns the current level
	 * @return the current level
	 */
	public Level getLevel();
	
	/**
	 * The method sets a new level
	 * @param level the level we want to set in the model
	 */
	public void setLevel(Level level);
}

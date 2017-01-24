package model.policy;

import commons.Level;
import model.data.Character;
import model.data.Item;

public abstract class MoveItem {
	//Data members
	private MySokobanPolicy _policy;
	
	//Constructor
	public MoveItem(MySokobanPolicy policy) {
		this._policy = policy;
	}
	
	//Get and set methods
	public MySokobanPolicy get_policy() {
		return _policy;
	}
	
	//Abstract methods
	/**
	 * The method moves the character
	 * @param level the level that we want to move the character in
	 * @param character the character that we want to move
	 * @param moveToItem the item that is next to the character
	 * @param nextItem the item that is second next to the character
	 * @return if we moved the character 
	 */
	public abstract boolean moveCharacter(Level level, Character character, Item moveToItem, Item nextItem);
	
	/**
	 * The method moves the character right
	 * @param level the level that we want to move the character in
	 * @param _character the character that we want to move
	 * @return if we moved the character 
	 */
	public abstract Level moveChararcterRight(Level level, Character character);
	
	/**
	 * The method moves the character left
	 * @param level the level that we want to move the character in
	 * @param character the character that we want to move
	 * @return if we moved the character 
	 */
	public abstract Level moveChararcterLeft(Level level, Character character);
	
	/**
	 * The method moves the character up
	 * @param level the level that we want to move the character in
	 * @param character the character that we want to move
	 * @return if we moved the character 
	 */
	public abstract Level moveChararcterUp(Level level, Character character);
	
	/**
	 * The method moves the character down
	 * @param level the level that we want to move the character in
	 * @param character the character that we want to move
	 * @return if we moved the character 
	 */
	public abstract Level moveChararcterDown(Level level, Character character);
	
}

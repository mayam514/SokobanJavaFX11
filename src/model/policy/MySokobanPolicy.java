package model.policy;

import model.data.Item;
import commons.Level;
import model.data.Character;


public abstract class MySokobanPolicy {

	/**
	 * The method checks if the character can move by the policy
	 * @param moveToItem the item that is next to the character
	 * @param nextItem the item that is second next to the character
	 * @return if the character can move
	 */
	public abstract boolean canCharacterMove(Item moveToItem, Item nextItem);
	
	/**
	 * The method checks if the character can move right
	 * @param character the character that we want to move
	 * @param level the level we want to move the character in
	 * @return if the character can move right
	 */
	public abstract boolean canChararcterMoveRight(Character character, Level level);
	
	/**
	 * The method checks if the character can move left
	 * @param character the character that we want to move
	 * @param level the level we want to move the character in
	 * @return if the character can move left
	 */
	public abstract boolean canChararcterMoveLeft(Character character, Level level);
	
	/**
	 * The method checks if the character can move up
	 * @param character the character that we want to move
	 * @param level the level we want to move the character in
	 * @return if the character can move up
	 */
	public abstract boolean canChararcterMoveUp(Character character, Level level);
	
	/**
	 * The method checks if the character can move down
	 * @param character the character that we want to move
	 * @param level the level we want to move the character in
	 * @return if the character can move down
	 */
	public abstract boolean canChararcterMoveDown(Character character, Level level);
		
	/**
	 * The method checks if we won the game
	 * @param level the level that we play
	 * @return if we won the game
	 */
	public abstract boolean isWinner(Level level);
}

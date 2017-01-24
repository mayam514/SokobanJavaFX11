package model.policy;

import model.data.Item;
import model.data.Position;
import commons.Level;
import model.data.Character;

public class Policy1 extends MySokobanPolicy{
	
	//Constructor
	public Policy1() {

	}
	
	@Override
	public boolean canCharacterMove(Item moveToItem, Item nextItem){
		if((moveToItem.isFreeSpace())||(moveToItem.isMoveable() && nextItem.isFreeSpace())){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean canChararcterMoveLeft(Character character, Level level) {
		Position p=new Position(character.get_position());
		Item i = level.get_items().get(p.getP_y()).get(p.getP_x()-1);//The item on the left to the character
		Item i1 = level.get_items().get(p.getP_y()).get(p.getP_x()-2);//The position on the left of i
		return this.canCharacterMove(i, i1);
	}

	@Override
	public boolean canChararcterMoveRight(Character character, Level level) {
		Position p=new Position(character.get_position());
		Item i = level.get_items().get(p.getP_y()).get(p.getP_x()+1);//The item on the right to the character
		Item i1 = level.get_items().get(p.getP_y()).get(p.getP_x()+2);//The position on the right of i
		return this.canCharacterMove(i, i1);
	}

	@Override
	public boolean canChararcterMoveUp(Character character, Level level) {
		Position p=new Position(character.get_position());
		Item i = level.get_items().get(p.getP_y()-1).get(p.getP_x());//The item up to the character
		Item i1 = level.get_items().get(p.getP_y()-2).get(p.getP_x());//The position up of i
		return this.canCharacterMove(i, i1);
	}

	@Override
	public boolean canChararcterMoveDown(Character character, Level level) {
		Position p=new Position(character.get_position());
		Item i = level.get_items().get(p.getP_y()+1).get(p.getP_x());//The item down to the character
		Item i1 = level.get_items().get(p.getP_y()+2).get(p.getP_x());//The position down of i
		return this.canCharacterMove(i, i1);
	}
	
	@Override
	public boolean isWinner(Level level) {
		if(level.get_numOfBoxesOnFinalPositions()==level.get_numOfFinalPositions()){
			System.out.println("You Won!!! :)");
			return true;
		}
		return false;
	}


	
}

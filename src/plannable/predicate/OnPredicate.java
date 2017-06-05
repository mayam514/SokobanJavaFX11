package plannable.predicate;

import model.data.Item;
import model.data.Position;

public class OnPredicate extends Predicate{
	//Data members
	private Position pos;
	private Item item;
	
	//Constructor
	public OnPredicate(String name, Object[] args) {
		super(name, args);
		this.item = (Item)args[0];
		this.pos = (Position)args[1];
	}

	@Override
	public boolean isSatisfied() {
		if(item.get_position() == pos){
			return true;
		}
		return false;
	}
}

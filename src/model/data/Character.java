package model.data;

public class Character extends Item{

	private static final long serialVersionUID = 1L;

	//Constructors
	public Character() {
		super(false,false);
	}
	
	public Character(Position p){
		super(false,false);
		this.set_position(p);
	}

	
}

package model.data;

public class Box extends Item{

	private static final long serialVersionUID = 1L;

	//Constructors
	public Box() {
		super(false,true);
	}
	
	public Box(Position p){
		super(false,true);
		this.set_position(p);
	}
}

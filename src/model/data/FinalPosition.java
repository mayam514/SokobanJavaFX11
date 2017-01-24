package model.data;

public class FinalPosition extends Item{

	private static final long serialVersionUID = 1L;

	//Constructors
	public FinalPosition() {
		super(true,false);
		this.set_isItemOnFinalPosition(true);
	}
	
	public FinalPosition(Position p){
		super(true,false);
		this.set_position(p);
		this.set_isItemOnFinalPosition(true);
	}
}

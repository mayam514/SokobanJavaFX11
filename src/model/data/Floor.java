package model.data;

public class Floor extends Item{

	private static final long serialVersionUID = 1L;

	//Constructors
	public Floor() {
		super(true,false);
	}
	
	public Floor(Position p){
		super(true,false);
		this.set_position(p);
	}
}

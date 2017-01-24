package model.data;

public class Wall extends Item{
	private static final long serialVersionUID = 1L;

	//Constructors
	public Wall() {
		super(false,false);
	}
	
	public Wall(Position p){
		super(false,false);
		this.set_position(p);
	}
}

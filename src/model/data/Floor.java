package model.data;

import javafx.scene.canvas.GraphicsContext;

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

	@Override
	public void draw(GraphicsContext gc, Position p, double cellWidth, double cellHeight) {
		
	}
}

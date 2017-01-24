package model.data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class FinalPosition extends Item{

	private static final long serialVersionUID = 1L;
	private Image _image;

	//Constructors
	public FinalPosition() {
		super(true,false);
		this.set_isItemOnFinalPosition(true);
		try {
			this._image = new Image(new FileInputStream("resources/finalPosition.jpg"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public FinalPosition(Position p){
		super(true,false);
		this.set_position(p);
		this.set_isItemOnFinalPosition(true);
		try {
			this._image = new Image(new FileInputStream("resources/finalPosition.jpg"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void draw(GraphicsContext gc, Position p, double cellWidth, double cellHeight) {
		gc.drawImage(this._image, p.getP_x() * cellWidth, p.getP_y() * cellHeight, 0.95 * cellWidth, 0.95 * cellHeight);
	}
}

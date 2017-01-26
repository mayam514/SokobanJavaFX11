package model.data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Character extends Item{
	//Data members
	private static final long serialVersionUID = 1L;
	private Image _image;

	//Constructors
	public Character() {
		super(false,false);
		try {
			this._image = new Image(new FileInputStream("resources/character.jpg"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Character(Position p){
		super(false,false);
		this.set_position(p);
		try {
			this._image = new Image(new FileInputStream("resources/character.jpg"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void draw(GraphicsContext gc, Position p, double cellWidth, double cellHeight) {
		gc.drawImage(this._image, p.getP_x() * cellWidth, p.getP_y() * cellHeight, 0.95 * cellWidth, 0.95 * cellHeight);
	}

	
}

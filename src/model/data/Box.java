package model.data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Box extends Item{
	//Data members
	private static final long serialVersionUID = 1L;
	private Image _image;

	//Constructors
	public Box() {
		super(false,true);
		try {
			this._image = new Image(new FileInputStream("resources/box.jpg"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Box(Position p){
		super(false,true);
		this.set_position(p);
		try {
			this._image = new Image(new FileInputStream("resources/box.jpg"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void draw(GraphicsContext gc, Position p, double cellWidth, double cellHeight) {
		gc.drawImage(this._image, p.getP_x() * cellWidth, p.getP_y() * cellHeight, 0.95 * cellWidth, 0.95 * cellHeight);
	}

	
}

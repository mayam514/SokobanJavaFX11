package model.data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Floor extends Item{

	private static final long serialVersionUID = 1L;
	private Image _image;

	//Constructors
	public Floor() {
		super(true,false);
		try {
			this._image = new Image(new FileInputStream("resources/floor.jpg"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Floor(Position p){
		super(true,false);
		this.set_position(p);
		try {
			this._image = new Image(new FileInputStream("resources/floor.jpg"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void draw(GraphicsContext gc, Position p, double cellWidth, double cellHeight) {
		gc.drawImage(this._image, p.getP_x() * cellWidth, p.getP_y() * cellHeight, 0.95 * cellWidth, 0.95 * cellHeight);
	}
}

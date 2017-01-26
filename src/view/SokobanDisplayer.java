package view;

import java.util.ArrayList;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import model.data.Item;
import model.data.Position;

public class SokobanDisplayer extends Canvas{
	//Data members
	ArrayList<ArrayList<Item>> _sokobanData;
	int cCol, cRow;//character place
	
	//Constructor
	public SokobanDisplayer() {
		cCol = 0;
		cRow = 0;
	}
	
	//Get and set methods
	public ArrayList<ArrayList<Item>> get_sokobanData() {
		return _sokobanData;
	}
	
	public void setCharacterPosition(int row, int col){
		cRow = row;
		cCol = col;
		redraw();//Something changed, so we want to display the game again
	}
	
	public int getcCol() {
		return cCol;
	}

	public int getcRow() {
		return cRow;
	}

	public void setSokobanData(ArrayList<ArrayList<Item>> _sokobanData) {
		this._sokobanData = _sokobanData;
		redraw();//Something changed, so we want to display the game again
	}
	
	/**
	 * The method draws the canvas
	 */
	public void redraw(){//I will want to activate it everytime that something changed
		if(_sokobanData != null){
			//Get the canvas dimentions
			double W = getWidth();
			double H = getHeight();
			int max = 0;
			for(int i=0; i<_sokobanData.size();i++){//Get the maximum width of the level
				if(_sokobanData.get(i).size() > max){
					max = _sokobanData.get(i).size();
				}
			}
			double w = W / max;
			double h = H / _sokobanData.size();
			
			GraphicsContext gc = getGraphicsContext2D();
						
			gc.clearRect(0, 0, W, H);//Clear the canvas before drawing again
			
			for (int i=0 ; i<_sokobanData.size() ; i++){
				for(int j=0 ; j<_sokobanData.get(i).size() ; j++){
					Position p = this._sokobanData.get(i).get(j).get_position();
					this._sokobanData.get(i).get(j).draw(gc, p, w, h);//Sends to every item type draw method
				}
			}
		}
	}
}

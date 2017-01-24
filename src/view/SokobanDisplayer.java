package view;

import java.util.ArrayList;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import model.data.Item;
import model.data.Position;

public class SokobanDisplayer extends Canvas{
	ArrayList<ArrayList<Item>> _sokobanData;//im tazin li maarah du meimadi shel intim, ani eda lehazig et ze betor mavoh
	int cCol, cRow;//character place
	
	public SokobanDisplayer() {
		cCol = 0;
		cRow = 0;
	}
	
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
	
	public void redraw(){//I will want to activate it everytime that something changed
		if(_sokobanData != null){
			//get the canvas dimentions
			double W = getWidth();
			double H = getHeight();
			double w = W / _sokobanData.size();
			double h = H / _sokobanData.size();
			
			GraphicsContext gc = getGraphicsContext2D();
						
			gc.clearRect(0, 0, W, H);
			
			for (int i=0 ; i<_sokobanData.size() ; i++){
				for(int j=0 ; j<_sokobanData.get(i).size() ; j++){
					Position p = this._sokobanData.get(i).get(j).get_position();
					this._sokobanData.get(i).get(j).draw(gc, p, w, h);
				}
			}
		}
	}
}

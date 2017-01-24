package viewnewwwwwwww;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class SokobanDisplayer extends Canvas{
	int[][] sokobanData;//im tazin li maarah du meimadi shel intim, ani eda lehazig et ze betor mavoh
	private StringProperty wallFileName;
	int cCol, cRow;//character place
	
	public int[][] getsokobanData() {
		return sokobanData;
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

	public SokobanDisplayer() {
		wallFileName = new SimpleStringProperty();
		cCol = 0;
		cRow = 1;
	}
	
	public String getWallFileName() {
		return wallFileName.get();
	}

	public void setWallFileName(String wallFileName) {
		this.wallFileName.set(wallFileName);
	}

	public void setSokobanData(int[][] sokobanData) {
		this.sokobanData = sokobanData;
		redraw();
	}
	
	public void redraw(){//I will want to activate it everytime that something changed
		if(sokobanData != null){
			//get the canvas dimentions
			double W = getWidth();
			double H = getHeight();
			double w = W / sokobanData[0].length;
			double h = H / sokobanData.length;
			
			GraphicsContext gc = getGraphicsContext2D();
			Image wall = null;
			try {
				wall = new Image(new FileInputStream(wallFileName.get()));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
						
			gc.clearRect(0, 0, W, H);
			
			for (int i=0 ; i<sokobanData.length ; i++){
				for(int j=0 ; j<sokobanData[i].length ; j++){
					if(sokobanData[i][j] != 0){
						if(wall == null){
							gc.fillRect(j*w, i*h, w, h);
						}
						else{
							gc.drawImage(wall, j*w, i*h, w, h);
						}
					}
				}
			}
			gc.setFill(Color.RED);
			gc.fillOval(cCol*w, cRow*h, w, h);
		}
	}
}

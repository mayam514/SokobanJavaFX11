package adapters;

import model.data.Position;

public class PlayerAndBox {
	//Data members
	private Position posBox;
	private Position posPlayer;

	//Constructor
	public PlayerAndBox(Position posBox,Position posPlayer) {
		this.posBox = posBox;
		this.posPlayer = posPlayer;
	}
	
	//Get and Set methods
	public Position getPosBox() {
		return posBox;
	}

	public void setPosBox(Position posBox) {
		this.posBox = posBox;
	}

	public Position getPosPlayer() {
		return posPlayer;
	}

	public void setPosPlayer(Position posPlayer) {
		this.posPlayer = posPlayer;
	}
}
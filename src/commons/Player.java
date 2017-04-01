package commons;

import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Serializable {
	//Data members
	private static final long serialVersionUID = 1L;
	private String _playerName;
	private ArrayList<Integer> _numOfMoves;
	private ArrayList<Long> _gameDuration;
	
	//Constructor
	public Player() {
		this._playerName = "";
		this._numOfMoves = new ArrayList<Integer>();
		this._gameDuration = new ArrayList<Long>();
	}

	public Player(String playerName, int numOfMoves, long gameDuration) {
		this._playerName = playerName;
		this._numOfMoves = new ArrayList<Integer>();
		this._numOfMoves.add(numOfMoves);
		this._gameDuration = new ArrayList<Long>();
		this._gameDuration.add(gameDuration);
	}
	
	
	
}

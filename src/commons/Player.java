package commons;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="Players")
public class Player implements Serializable {
	//Data members
	private static final long serialVersionUID = 1L;
	
	@Column(name="Name")
	@Id
	private String _playerName;
	
	@Column(name="NumOfMoves")
	private int _numOfMoves;
	
	@Column(name="Time")
	private long _gameDuration;
	
	//Constructors
	public Player() {
		this._playerName = "";
		this._numOfMoves = 0;
		this._gameDuration = 0;
	}

	public Player(String playerName, int numOfMoves, long gameDuration) {
		this._playerName = playerName;
		this._numOfMoves = numOfMoves;
		this._gameDuration = gameDuration;
	}

	@Override
	public String toString() {
		return "Player [_playerName=" + _playerName + ", _numOfMoves=" + _numOfMoves + ", _gameDuration="
				+ _gameDuration + "]";
	}
	
}

package commons;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="Scores")
public class Score {
	//Data members
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="Id")
	private int _id;
	
	@Column(name="PlayerName")
	private String _playerName;
	
	@Column(name="LevelName")
	private String _levelName;
	
	@Column(name="NumOfMoves")
	private int _numOfMoves;
	
	@Column(name="GameTime")
	private long _gameDuration;
	
	//Constructor
	public Score(String _playerName, String _levelName, int _numOfMoves, long _gameDuration) {
		this._playerName = _playerName;
		this._levelName = _levelName;
		this._numOfMoves = _numOfMoves;
		this._gameDuration = _gameDuration;
	}
	
	public Score() {
		this._id = 0;
		this._playerName = "";
		this._levelName = "";
		this._numOfMoves = 0;
		this._gameDuration = 0;
	}
	
	//Getters and setters
	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public String get_playerName() {
		return _playerName;
	}

	public void set_playerName(String _playerName) {
		this._playerName = _playerName;
	}

	public String get_levelName() {
		return _levelName;
	}

	public void set_levelName(String _levelName) {
		this._levelName = _levelName;
	}

	public int get_numOfMoves() {
		return _numOfMoves;
	}

	public void set_numOfMoves(int _numOfMoves) {
		this._numOfMoves = _numOfMoves;
	}

	public long get_gameDuration() {
		return _gameDuration;
	}

	public void set_gameDuration(long _gameDuration) {
		this._gameDuration = _gameDuration;
	}

	@Override
	public String toString() {
		return "Score [_id=" + _id + ", _playerName=" + _playerName + ", _levelName=" + _levelName + ", _numOfMoves="
				+ _numOfMoves + ", _gameDuration=" + _gameDuration + "]";
	}

	
}

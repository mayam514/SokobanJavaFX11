package commons;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.CascadeType;

@Entity(name="Players")
public class Player implements Serializable {
	//Data members
	private static final long serialVersionUID = 1L;
	
	@Column(name="PlayerName")
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
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name="Level_Player", catalog = "SokobanDbManager"
    									, joinColumns={@JoinColumn(name="PlayerName")}
                                        , inverseJoinColumns={@JoinColumn(name="LevelName")}) 
	private List<Level> levels = new ArrayList<Level>();
	
	public List<Level> getLevels() {
		return levels;
	}

	public void setLevels(List<Level> levels) {
		this.levels = levels;
	}
}

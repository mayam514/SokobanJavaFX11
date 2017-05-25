package commons;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import model.data.Character;
import model.data.Item;
import model.data.Position;


public class Level implements Serializable{
	//Data members
	private static final long serialVersionUID = 1L;
	
	private int _levelNumber;
	private String _levelName;
	private double _score;
	private int _numOfMoves;
	private int _numOfFinalPositions;
	private int _numOfBoxesOnFinalPositions;
	private int _width;
	private int _height;
	private ArrayList<ArrayList<Item>> _items;
	private ArrayList<Character> _characters;
	private boolean _isWinner;
	private long _startTime;
	private long _finishTime;
	
	//Constructor
	public Level() {
		this._levelNumber = 1;
		this._levelName = "";
		this._score = 0;
		this._numOfMoves = 0;
		this._numOfFinalPositions = 0;
		this._numOfBoxesOnFinalPositions = 0;
		this._width = 0;
		this._height = 0;
		this._items=new ArrayList<ArrayList<Item>>();
		this._characters=new ArrayList<Character>();
		this._isWinner = false;
		this._startTime = 0;
		this._finishTime = 0;
	}
	
	public Level(String name){
		this._levelName = new String(name);
	}

	//Get and set methods
	public int get_number() {
		return _levelNumber;
	}
	
	public void set_number(int level_number) {
		this._levelNumber = level_number;
	}
	
	public String get_name() {
		return _levelName;
	}

	public void set_name(String _levelName) {
		this._levelName = new String(_levelName);
	}
	
	public double get_score() {
		return _score;
	}

	public void set_score(double level_score) {
		this._score = level_score;
	}
	
	public int getLevel_numOfMoves() {
		return _numOfMoves;
	}

	public long get_finishTime(){
		return _finishTime;
	}
	
	public void set_finishTime(){
		long currentTime = System.currentTimeMillis();
		this._finishTime = currentTime - this._startTime;
	}
	
	public long get_startTime(){
		return _startTime;
	}
	
	public void set_startTime(){
		this._startTime = System.currentTimeMillis();
	}
	
	public void setLevel_numOfMoves(int level_numOfMoves) {
		this._numOfMoves = level_numOfMoves;
	}
	
	public int get_numOfBoxesOnFinalPositions() {
		return _numOfBoxesOnFinalPositions;
	}

	public void set_numOfBoxesOnFinalPositions(int _numOfBoxesOnFinalPositions) {
		this._numOfBoxesOnFinalPositions = _numOfBoxesOnFinalPositions;
	}

	public int get_numOfFinalPositions() {
		return _numOfFinalPositions;
	}

	public void set_numOfFinalPositions(int _numOfFinalPositions) {
		this._numOfFinalPositions = _numOfFinalPositions;
	}
	
	public int get_width() {
		return _width;
	}

	public void set_width(int _width) {
		this._width = _width;
	}

	public int get_height() {
		return _height;
	}

	public void set_height(int _height) {
		this._height = _height;
	}

	public ArrayList<ArrayList<Item>> get_items() {
		return _items;
	}

	public void set_items(ArrayList<ArrayList<Item>> _items) {
		this._items = _items;
	}

	public ArrayList<Character> get_characters() {
		return _characters;
	}

	public void set_characters(ArrayList<Character> _characters) {
		this._characters = _characters;
	}
	
	public boolean _isWinner() {
		return _isWinner;
	}

	public void set_isWinner(boolean _isWinner) {
		this._isWinner = _isWinner;
	}
	
	/**
	 * The method initiates an item in the items array
	 * @param item the item that we want to initiate in the items array
	 * @param p the position of the item
	 */
	public void initiateItemInItemsArray(Item item, Position p){
		if(this._items.size() <= p.getP_y()){//If the position of the item is outside level boundaries
			this._items.add(new ArrayList<Item>(p.getP_x()));//Opens a new arraylist in _items
		}
		this._items.get(p.getP_y()).add(item);//Add the new item to the items array
	}
	

	/**
	 * The method sets an item in the items array
	 * @param item the item that we want to set in the items array
	 * @param p the position of the item
	 */
	public void setItemInItemsArray(Item item, Position p){
		if(this._items.size() <= p.getP_y()){//If the position of the item is outside level boundaries
			this._items.add(new ArrayList<Item>(p.getP_x()));//Opens a new arraylist in _items
		}
		this._items.get(p.getP_y()).set(p.getP_x(), item);//Set the item in the items array
	}
	
	
	/**
	 * The method returns an item by position from the items array
	 * @param p the position that we want to check
	 * @return the item that is in the position
	 */
	public Item getItemFromArrayByPosition(Position p){
		if(p.getP_x() < 0 || p.getP_y() < 0 || p.getP_x() > this._width || p.getP_y() > this._height){//If the position is out of the boundaries
			return null;
		}
		return this._items.get(p.getP_y()).get(p.getP_x());
	}
	
	/**
	 * The method gets an item and returns a hash map with all it's possible neighbors (with directions)
	 * @param p the position of the item we want to check it's neighbors
	 * @return a hash map of positions by direction of the possible neighbors
	 */
	public HashMap<String, Position> getAllPossibleNeighbors(Position p){
		HashMap<String, Position> map = new HashMap<String, Position>();
		
		p.setP_x(p.getP_x() + 1);
		if(this.getItemFromArrayByPosition(p).isFreeSpace()){
			map.put("Right", new Position(p));
		}
		
		p.setP_x(p.getP_x() - 2);
		if(this.getItemFromArrayByPosition(p).isFreeSpace()){
			map.put("Left", new Position(p));
		}
		p.setP_x(p.getP_x() + 1);
		p.setP_y(p.getP_y() + 1);
		if(this.getItemFromArrayByPosition(p).isFreeSpace()){
			map.put("Down", new Position(p));
		}
		
		p.setP_y(p.getP_y() - 2);
		if(this.getItemFromArrayByPosition(p).isFreeSpace()){
			map.put("Up", new Position(p));
		}
		
		return map;
	}
	
	@Override
	public String toString() {
		String str = "";
		for(ArrayList<Item> a : this.get_items()){
			for(Item item : a){//For each Item in the arraylist
				switch(item.getClass().toString().substring(17)){//check by the name of the Item
				case "Wall":
					str += "#";
					break;
				case "Box":
					str += "@";
					break;
				case "Character":
					str += "A";
					break;
				case "FinalPosition":
					str += "o";
					break;
				case "Floor":
					str += " ";
					break;
				}
			}
			str += "\n";//prints a new line
		}
		return str;
	}

	

}

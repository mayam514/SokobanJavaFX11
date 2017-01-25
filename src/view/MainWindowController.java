package view;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Observable;
import java.util.ResourceBundle;

import commons.Level;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import model.data.Item;

public class MainWindowController extends Observable implements Initializable, IView{
	//Data members
	ArrayList<ArrayList<Item>> _sokobanData;
	int _numOfMoves = 0;
	@FXML
	SokobanDisplayer _sokobanDisplayer;
	@FXML
	Label _numOfMovesLabel;
	
	/**
	 * The method activates the game 
	 */
	public void start(){
		load();//Load the level that we want to play for the first time
		//Display the level we loaded
		String command = "Display";
		LinkedList<String> params = new LinkedList<String>();
		params.add(command);
		
		this.setChanged();
		this.notifyObservers(params);
		String ssound = "resources/song.mp3";
		Media sound = new Media(new File(ssound).toURI().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.play();
	}
	
	/**
	 *  The method loads a level
	 */
	public void load(){
		FileChooser fc = new FileChooser();
		fc.setTitle("Load level");
		fc.setInitialDirectory(new File("./resources"));//The first directory that opens
		//Shows only XML, text and object files
		fc.getExtensionFilters().addAll(
		         new ExtensionFilter("Text Files", "*.txt"),
		         new ExtensionFilter("Oblect Files", "*.obj"),
		         new ExtensionFilter("XML Files", "*.xml"));
		//Get the chosen path from the user to load
		File chosen = fc.showOpenDialog(this._sokobanDisplayer.getScene().getWindow());
		if(chosen != null){
			//Insert load command
			String command = "Load ";
			String path = chosen.toString();
			command += path;
			String [] arr = command.split(" ");
			LinkedList<String> params = new LinkedList<String>();
			for(String param : arr){
				params.add(param);
			}
			
			this.setChanged();
			this.notifyObservers(params);
			
			//Display the level that we loaded
			String command1 = "Display";
			LinkedList<String> params1 = new LinkedList<String>();
			params1.add(command1);
			
			this.setChanged();
			this.notifyObservers(params1);
		}
	}
	
	/**
	 *  The method saves the level
	 */
	public void save(){
		FileChooser fc = new FileChooser();
		fc.setTitle("Save level");
		fc.setInitialDirectory(new File("./resources"));//The first directory that opens
		File chosen = fc.showSaveDialog(this._sokobanDisplayer.getScene().getWindow());//Get the chosen path from the user to save
		if(chosen != null){
			//Insert save command
			String command = "Save ";
			String path = chosen.toString();
			command += path;
			String [] arr = command.split(" ");
			LinkedList<String> params = new LinkedList<String>();
			for(String param : arr){
				params.add(param);
			}
			
			this.setChanged();
			this.notifyObservers(params);
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this._sokobanDisplayer.setSokobanData(this._sokobanData);
		this._sokobanDisplayer.addEventFilter(MouseEvent.MOUSE_CLICKED, (e)->_sokobanDisplayer.requestFocus());//Asks that the focus will be on _sokobanDisplayer
		this._sokobanDisplayer.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				String direction = "";
				String str = "";
				
				if(event.getCode() == KeyCode.UP){
					direction = "Up";
					_numOfMoves++;
					str = String.valueOf(_numOfMoves);
					_numOfMovesLabel.setText("Number Of Moves: " + str);
				}
				
				if(event.getCode() == KeyCode.DOWN){
					direction = "Down";
					_numOfMoves++;
					str = String.valueOf(_numOfMoves);
					_numOfMovesLabel.setText("Number Of Moves: " + str);
				}
				
				if(event.getCode() == KeyCode.RIGHT){
					direction = "Right";
					_numOfMoves++;
					str = String.valueOf(_numOfMoves);
					_numOfMovesLabel.setText("Number Of Moves: " + str);
				}
				
				if(event.getCode() == KeyCode.LEFT){
					direction = "Left";
					_numOfMoves++;
					str = String.valueOf(_numOfMoves);
					_numOfMovesLabel.setText("Number Of Moves: " + str);
				}
				String command = "Move";
				LinkedList<String> params = new LinkedList<String>();
				params.add(command);
				params.add(direction);
				
				setChanged();
				notifyObservers(params);
			}
		});
	}
	
	@Override
	public void display(Level level) {
		this._sokobanDisplayer.setSokobanData(level.get_items());
	}

	@Override
	public void displayMessage(String msg) {
		Platform.runLater(new Runnable() {
		      public void run() {
		          Alert alert = new Alert(Alert.AlertType.INFORMATION);
		          alert.setHeaderText(null);
		          alert.setContentText(msg);
		          alert.showAndWait();
		      }
		    });
	}
}

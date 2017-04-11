package view;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.TimerTask;

import commons.Level;
import commons.Score;
import db.SokobanDbManager;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.data.Item;

public class MainWindowController extends Observable implements Initializable, IView {
	// Data members
	ArrayList<ArrayList<Item>> _sokobanData;
	int _numOfMoves = 0;
	@FXML
	SokobanDisplayer _sokobanDisplayer;
	@FXML
	Label _numOfMovesLabel;
	@FXML
	Label _timeLabel;
	@FXML
	private javafx.scene.control.Button closeButton;
	@FXML
	TextField _searchLine;

	int _time = 0;
	SokobanDbManager manager = new SokobanDbManager();
	String _levelName;
	
	//TIMER
	Timeline myTimer;
	TimerTask task;
    final HBox hb = new HBox();

	/**
	 * The method activates the game
	 */
	public void start() {
		load();// Load the level that we want to play for the first time
		
		// Display the level we loaded
		String command = "Display";
		LinkedList<String> params = new LinkedList<String>();
		params.add(command);

		this.setChanged();
		this.notifyObservers(params);

		// Add music to the game
		String ssound = "resources/song.mp3";
		Media sound = new Media(new File(ssound).toURI().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.play();
	}

	/**
	 * The method closes the game
	 */
	public void close() {
		String command = "Exit";
		LinkedList<String> params = new LinkedList<String>();
		params.add(command);

		this.setChanged();
		this.notifyObservers(params);
	}

	/**
	 * The method loads a level
	 */
	public void load() {
		FileChooser fc = new FileChooser();
		fc.setTitle("Load level");
		fc.setInitialDirectory(new File("./resources"));// The first directory that opens
		// Shows only XML, text and object files
		fc.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"),
				new ExtensionFilter("Oblect Files", "*.obj"), new ExtensionFilter("XML Files", "*.xml"));
		// Get the chosen path from the user to load
		File chosen = fc.showOpenDialog(this._sokobanDisplayer.getScene().getWindow());
		if (chosen != null) {
			// Insert load command
			String command = "Load ";
			String path = chosen.toString();
			command += path;
			String[] arr = command.split(" ");
			LinkedList<String> params = new LinkedList<String>();
			for (String param : arr) {
				params.add(param);
			}

			this.setChanged();
			this.notifyObservers(params);

			String command1 = "Display";
			LinkedList<String> params1 = new LinkedList<String>();
			params1.add(command1);
			this.setChanged();
			this.notifyObservers(params1);
		}
	}

	/**
	 * The method saves the level
	 */
	public void save() {
		FileChooser fc = new FileChooser();
		fc.setTitle("Save level");
		fc.setInitialDirectory(new File("./resources"));// The first directory that opens
		File chosen = fc.showSaveDialog(this._sokobanDisplayer.getScene().getWindow());// Get the chosen path from the user to save
		if (chosen != null) {
			// Insert save command
			String command = "Save ";
			String path = chosen.toString();
			command += path;
			String[] arr = command.split(" ");
			LinkedList<String> params = new LinkedList<String>();
			for (String param : arr) {
				params.add(param);
			}

			this.setChanged();
			this.notifyObservers(params);
		}
	}

	public void addDataToTable(TableView<Score> table, ObservableList<Score> data, VBox vbox, List<Score> rs){
		  try{
          	int i = 0;

  	        int size = rs.size();
  	        while(i<size){
  	        	Score s1 = rs.get(i++);
  	        	data.add(s1);
  	        }
  	        table.setItems(data);
  	        table.refresh();
  	    }
  	    catch(Exception e1){
  	          e1.printStackTrace();
  	          System.out.println("Error on Building Data");
  	    }
	}

	/**
	 * The method shows the scores for the level
	 */
	public void showScores(){
		TableView<Score> table = new TableView<>();
		ObservableList<Score> data = FXCollections.observableArrayList();
		ObservableList<Score> data1 = FXCollections.observableArrayList();
		ObservableList<Score> data2 = FXCollections.observableArrayList();
		ObservableList<Score> data3 = FXCollections.observableArrayList();
		Label label = new Label();
		TextField textField = new TextField();
		Button button = new Button("Submit");;

        Scene scene = new Scene(new Group());
		Stage stage = new Stage();

        stage.setTitle("'" + _levelName + "' Scores");
        stage.setWidth(650);
        stage.setHeight(600);

        TableColumn<Score, String> levelName = new TableColumn<Score, String>("Level Name");
        levelName.setMinWidth(150);
        levelName.setCellValueFactory(new PropertyValueFactory<Score,String>("_levelName"));

        TableColumn<Score, String> playerName = new TableColumn<Score, String>("Player Name");
        playerName.setMinWidth(150);
        playerName.setCellValueFactory(new PropertyValueFactory<Score,String>("_playerName"));
        playerName.setCellFactory(tc -> {//When the player clicks on a playerName in the table, it will show him all the levels that this player played
            TableCell<Score, String> cell = new TableCell<Score, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty) ;
                    setText(empty ? null : item);
                }
            };
            cell.setOnMouseClicked(e -> {
                if (! cell.isEmpty()) {
                	Stage stage1 = new Stage();

                	Score pickedRow = (Score)(cell.getTableRow().getItem());
                    String tablePlayerName = pickedRow.get_playerName();

                    stage1.setTitle(tablePlayerName + " games");
                    stage1.setWidth(650);
                    stage1.setHeight(600);

                    final VBox vbox1 = new VBox();
                    vbox1.setSpacing(5);
                    vbox1.setPadding(new Insets(10, 0, 0, 10));

                    ((Group) scene.getRoot()).getChildren().addAll(vbox1);

          	        List<Score> rs1 = manager.addQuery("PlayerName", tablePlayerName, "GameTime", false);
          	        addDataToTable(table, data1, vbox1, rs1);

          	        label.setText("Search by Level Name");
	                button.setOnAction(new EventHandler<ActionEvent>() {
	                  @Override public void handle(ActionEvent e) {
	                	  data3.clear();
	                  	  String playerName = textField.getText();
	                      List<Score> rs = manager.addQuery("LevelName", _levelName, "GameTime", false);
	                      addDataToTable(table, data3, vbox1, rs1);
	                    }
	                });

	                HBox hb1 = new HBox();
	                hb1.getChildren().addAll(label, textField, button);
	                hb1.setSpacing(10);

	                vbox1.getChildren().addAll(hb1, table, hb);

                    stage1.setScene(scene);
                    stage1.show();
                }
            });
            return cell ;
        });

        TableColumn<Score, Integer> numOfMoves = new TableColumn<Score, Integer>("Number Of Moves");
        numOfMoves.setMinWidth(150);
        numOfMoves.setCellValueFactory(new PropertyValueFactory<Score,Integer>("_numOfMoves"));

        TableColumn<Score, Long> gameTime = new TableColumn<Score, Long>("Game Time");
        gameTime.setMinWidth(150);
        gameTime.setCellValueFactory(new PropertyValueFactory<Score,Long>("_gameDuration"));

        table.setItems(data);
        table.getColumns().add(levelName);
        table.getColumns().add(playerName);
        table.getColumns().add(numOfMoves);
        table.getColumns().add(gameTime);

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));

        label.setText("Search by Player Name");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	data2.clear();
            	String playerName = textField.getText();
            	List<Score> rs1 = manager.addQuery("PlayerName", playerName, "GameTime", false);
      	        addDataToTable(table, data2, vbox, rs1);
            }
        });

        HBox hb1 = new HBox();
        hb1.getChildren().addAll(label, textField, button);
        hb1.setSpacing(10);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        List<Score> rs = manager.addQuery("LevelName", _levelName, "GameTime", false);
        addDataToTable(table, data, vbox, rs);

        vbox.getChildren().addAll(hb1, table, hb);
        stage.setScene(scene);
        stage.show();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this._sokobanDisplayer.setSokobanData(this._sokobanData);
		this._sokobanDisplayer.addEventFilter(MouseEvent.MOUSE_CLICKED, (e) -> _sokobanDisplayer.requestFocus());// Asks that the focus will be on _sokobanDisplayer
		this._sokobanDisplayer.setOnKeyPressed(new EventHandler<KeyEvent>() {// Listens to keypressed event
			@Override
			public void handle(KeyEvent event) {
				String direction = "";

				if (event.getCode() == KeyCode.UP) {
					direction = "Up";
				} else if (event.getCode() == KeyCode.DOWN) {
					direction = "Down";
				} else if (event.getCode() == KeyCode.RIGHT) {
					direction = "Right";
				} else if (event.getCode() == KeyCode.LEFT) {
					direction = "Left";
				} else {
					return;
				}

				// Insert move command
				String command = "Move";
				LinkedList<String> params = new LinkedList<String>();
				params.add(command);
				params.add(direction);

				setChanged();
				notifyObservers(params);

				String moves = String.valueOf(_numOfMoves);
				_numOfMovesLabel.setText("Number Of Moves: " + moves);
			}
		});
	}

	public void timer(){
		myTimer = new Timeline(new KeyFrame(Duration.seconds(2), ev -> {
			_time++;
			String time = String.valueOf(_time);
    		_timeLabel.setText("Time passed: " + time + " seconds");
    		_sokobanDisplayer.redraw();
	    }));
		myTimer.setCycleCount(Animation.INDEFINITE);
		myTimer.play();
	}
	
	@Override
	public void display(Level level) {
		this._sokobanDisplayer.setSokobanData(level.get_items());
		this._numOfMoves = level.getLevel_numOfMoves() + 1;// Update the number of moves
		Platform.runLater(new Runnable() {//Update time
            public void run() {
            	timer();
            }
        });
		this._levelName = level.get_name();
	}

	@Override
	public void displayMessage(String msg) {
		Platform.runLater(new Runnable() {// Popup the msg to the screen
			public void run() {
				TextInputDialog dialog = new TextInputDialog();
				dialog.setTitle("YOU WON! :)");
				dialog.setHeaderText(msg);
				dialog.setContentText("Please enter your name: ");
				
				// Traditional way to get the response value.
				Optional<String> result = dialog.showAndWait();
				if (result.isPresent()) {
					Score score = new Score(result.get(),_levelName, _numOfMoves, _time);
					manager.addScore(score);
				}
			}
		});
	}
}
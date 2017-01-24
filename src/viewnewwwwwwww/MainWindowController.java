package viewnewwwwwwww;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

public class MainWindowController implements Initializable{
	int[][] mazeData = {
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},			
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1},			
			{1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,1},			
			{1,0,0,0,1,0,0,0,1,1,1,0,1,1,0,1},			
			{1,0,0,0,1,1,1,1,1,0,0,0,0,1,0,1},			
			{1,0,0,0,0,0,0,0,0,0,1,1,0,0,0,1},			
			{1,0,0,1,1,1,1,1,1,0,1,1,0,0,0,1},			
			{1,0,0,1,0,0,0,0,1,1,1,1,1,0,0,1},			
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},			
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
	};
	
	@FXML
	SokobanDisplayer sokobanDisplayer;//haithul shelo neesa ba"FXML".......tzarih lihiot toem lamishtane shehegdarnu ba"XML"!!
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		sokobanDisplayer.setSokobanData(mazeData);
		
		sokobanDisplayer.addEventFilter(MouseEvent.MOUSE_CLICKED, (e)->sokobanDisplayer.requestFocus());//Asks that the focus will be on sokobanDisplayer
		
		sokobanDisplayer.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				int r = sokobanDisplayer.getcRow();
				int c = sokobanDisplayer.getcCol();
				
				if(event.getCode() == KeyCode.UP){
					sokobanDisplayer.setCharacterPosition(r-1, c);
				}
				
				if(event.getCode() == KeyCode.DOWN){
					sokobanDisplayer.setCharacterPosition(r+1, c);
				}
				
				if(event.getCode() == KeyCode.RIGHT){
					sokobanDisplayer.setCharacterPosition(r, c+1);
				}
				
				if(event.getCode() == KeyCode.LEFT){
					sokobanDisplayer.setCharacterPosition(r, c-1);
				}
			}
		});
	}
	
	public void start(){
		System.out.println("start");
	}
	public void openFile(){
		FileChooser fc = new FileChooser();
		fc.setTitle("open maze file");
		//Hatikia tihie hadifoltivit ksheniftach et ha"openfiledialog"
		fc.setInitialDirectory(new File("./resources"));
		fc.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("XML", "*.xml"));
		File chosen = fc.showOpenDialog(null);
		if(chosen != null){
			System.out.println(chosen.getName());
		}
	}
}

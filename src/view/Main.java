package view;
	
import controller.Controller;
import controller.SokobanController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.SokobanModel;


public class Main extends Application {
	
	private Controller controller;
	
	/**
	 * The method initiates the MainWindowController
	 * @param view the MainWindowController we want to initiate
	 */
	private void init(MainWindowController view) {
		SokobanModel model = new SokobanModel();
		SokobanController controller = new SokobanController(model, view);
		
		model.addObserver(controller);
		view.addObserver(controller);
		view.start();	
	}
	
	//The method starts the GUI
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
			BorderPane root = (BorderPane)loader.load();	
			MainWindowController view = loader.getController();
			SokobanModel model = new SokobanModel();
			
			//Get the args from the main
			/*List<String> args = getParameters().getRaw();
			if(args.size() > 1){
				if(args.get(0).equals("-server")){
					HandleOneClient hoc = new HandleOneClient();
					int port = Integer.parseInt(args.get(1));
					MyServer server = new MyServer(port, hoc);
					controller = new Controller();
					
					SokobanController sokobanController = new SokobanController(model, view, server);
					model.addObserver(sokobanController);
					view.addObserver(sokobanController);
					hoc.addObserver(sokobanController);
					server.start();
				}
			}
			
			else{*/
				Scene scene = new Scene(root,900,900);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				init(view);
				primaryStage.setOnCloseRequest(e -> view.close());//Defines the functionality that happens when we press the exit button(x)
				primaryStage.show();
				
			//}
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		/*SokobanDbManager manager = new SokobanDbManager();
		Player p = new Player("Moshe", 5, 100);
		manager.addPlayer(p);*/
		launch(args);
		
	}
}

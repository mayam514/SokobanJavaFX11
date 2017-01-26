package view;
	
import controller.SokobanController;
import controller.server.HandleOneClient;
import controller.server.MyServer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.SokobanModel;


public class Main extends Application {
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
			
			Scene scene = new Scene(root,900,900);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			init(view);
			primaryStage.setOnCloseRequest(e -> view.close());//Defines the functionality that happens when we press the exit button(x)
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		//Launch the server
		if(args[0].equals("-server")){
			HandleOneClient h = new HandleOneClient();
			MyServer S =new MyServer(40305, h);
			S.start();
		}
		
		//Launch the GUI
		launch(args);
	}
}

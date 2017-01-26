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
	 * 
	 * @param view
	 */
	private void init(MainWindowController view) {
		SokobanModel model = new SokobanModel();
		SokobanController controller = new SokobanController(model, view);
		
		model.addObserver(controller);
		view.addObserver(controller);
		view.start();	
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			//BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
			FXMLLoader loader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
			BorderPane root = (BorderPane)loader.load();	
			MainWindowController view = loader.getController();
			
			Scene scene = new Scene(root,900,900);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			init(view);
			primaryStage.setOnCloseRequest(e -> view.close());
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		if(args[0].equals("-server")){
			HandleOneClient h = new HandleOneClient();
			MyServer S =new MyServer(40305, h);
			S.start();
		}
		
		launch(args);
	}
}

package boot;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import controller.SokobanController;
import model.SokobanModel;
import view.CLInew;

public class Run {

	public static void main(String[] args) {
		SokobanModel model = new SokobanModel();
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter writer = new PrintWriter(System.out);
		CLInew view = new CLInew(reader, writer, "Exit");
		
		SokobanController controller = new SokobanController(model, view);
		model.addObserver(controller);
		view.addObserver(controller);
		
		view.start();
	}

}

package displayers;

import java.util.ArrayList;

import commons.Level;
import model.data.Item;

public class MyTextLevelDisplayer implements ILevelDisplayer{
	//Constructor
	public MyTextLevelDisplayer() {
		
	}
	
	//Override method
	@Override
	public void display(Level level) {
		for(ArrayList<Item> a : level.get_items()){
			for(Item item : a){//For each Item in the arraylist
				switch(item.getClass().toString().substring(13)){//check by the name of the Item
				case "Wall":
					System.out.print("#");
					break;
				case "Box":
					System.out.print("@");
					break;
				case "Character":
					System.out.print("A");
					break;
				case "FinalPosition":
					System.out.print("o");
					break;
				case "Floor":
					System.out.print(" ");
					break;
				}
			}
			System.out.print(System.lineSeparator());//prints a new line
		}
		
	}

}

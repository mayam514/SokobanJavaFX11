package model.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import commons.Level;

public class MyTextLevelLoader implements ILevelLoader{
	
	//The method loads level from text file
	 @Override
	public Level loadLevel(InputStream is) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		Level myLevel = new Level();
		int x = 0 , y = 0;
		try{
			while(reader.ready()){
				String line;
				char c;
				while ((line = reader.readLine()) != null){//Read lines until the line is null
					for (int i = 0 ; i < line.length() ; i++ , x++){//Go over all chars on line
						c = line.charAt(i);//Get the char that symbolize an item
						Position itemPosition = new Position(x,y);
						switch(c){
						case '#': //There's a wall
							myLevel.initiateItemInItemsArray(new Wall(itemPosition), itemPosition);
							break;
						case 'o': //There's a final position
							myLevel.initiateItemInItemsArray(new FinalPosition(itemPosition), itemPosition);
							myLevel.set_numOfFinalPositions(myLevel.get_numOfFinalPositions() + 1);//Increase number of final positions by one
							break;
						case '@'://There's a wall
							myLevel.initiateItemInItemsArray(new Box(itemPosition), itemPosition);
							break;
						case 'A': 
							Character character = new Character(itemPosition);
							myLevel.initiateItemInItemsArray(character, itemPosition);
							myLevel.get_characters().add(character);//Add character to characters array
							break;
						case ' ':
							myLevel.initiateItemInItemsArray(new Floor(itemPosition), itemPosition);
							break;
						}
						
						if(x > myLevel.get_width()){//Update level width
							myLevel.set_width(x);
						}
					}
					x = 0;//Initiate width for the new line
					y++;
				}
				myLevel.set_height(y);//In the end of the loop, set the level height 
			}
			reader.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		return myLevel;
	}
}

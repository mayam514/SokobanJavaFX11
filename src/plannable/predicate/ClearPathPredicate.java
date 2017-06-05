package plannable.predicate;

import adapters.PlayerAndBox;
import adapters.SearchablePushAdapter;
import commons.Level;
import model.data.Box;
import model.data.Character;
import model.data.FinalPosition;
import searchLib.BFS;

public class ClearPathPredicate extends Predicate{
	//Data members
	private Level level;
	private Box box;
	private FinalPosition finalPosition;
	private Character character;

	//Constructor
	public ClearPathPredicate(String name, Object[] args, Level level) {
		super(name, args);

		character = (Character)args[0];
		box = (Box)args[1];
		finalPosition = (FinalPosition)args[2];
		this.level = level;
	}

	@Override
	public boolean isSatisfied(){
		PlayerAndBox pab = new PlayerAndBox(box.get_position(), character.get_position());
		SearchablePushAdapter a = new SearchablePushAdapter(level, pab, box.get_position());
		BFS<PlayerAndBox> bfs = new BFS<PlayerAndBox>();
		if(bfs.search(a) == null){//Check if there's a clear path to the target
			return false;
		}
		return true;
	}
}
package plannable;

import java.util.List;

import searchLib.Action;
import strips.ActionPlan;

public class Move extends ActionPlan {
	private List<Action> searchResult;

	public Move(String id, String value, List<Action> searchResult) {
		super("move", id, value);
		this.searchResult=searchResult;
	}

	public List<Action> getSearchResult() {
		return searchResult;
	}

	public void setSearchResult(List<Action> searchResult) {
		this.searchResult = searchResult;
	}
}
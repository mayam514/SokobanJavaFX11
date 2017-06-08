package searchLib;

public interface Searcher<T> {
	/**
	 * The method activates the search on the searchable object
	 * @param s The searchable we want to search in
	 * @return The solution
	 */
	public Solution search(Searchable<T> s);
	
	/**
	 * The method returns the number of nodes that we evaluated
	 * @return The number of nodes evaluated
	 */
	public int getNumberOfNodesEvaluated();
	
	/**
	 * The method initiates the searcher class
	 */
	public void init();
}

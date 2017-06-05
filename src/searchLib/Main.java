package searchLib;

import searchLib.Solution;
import searchLib.BFS;
public class Main {
	public static void main(String[] args) {
		EightPuzzle game = new EightPuzzle();
		int[][] board = { {1, 2, 5 },  {0 , 4, 8}, {3, 6, 7}};
		game.setBoard(board);
		EightPuzzleAdapter adapter = new EightPuzzleAdapter(game);
		BFS<EightPuzzleState> bfs = new BFS<>();
		Solution sol = bfs.search(adapter);
		if(sol != null){
			System.out.println(sol);
		}
		else System.out.println("NULL");
	}
}
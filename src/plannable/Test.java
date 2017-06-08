package plannable;

import strips.Clause;
import strips.Plannable;
import strips.Predicate;

public class Test {
	public static void main(String[] args) {
		Predicate p1 = new SokobanPredicate("boxAt", "b1", "5,3");
		Predicate p2 = new SokobanPredicate("boxAt", "b2", "0,0");

		Predicate p4 = new SokobanPredicate("clearAt", "?", "5,3");
		
		Clause effect = new Clause(p4);
		
		
		Clause kb = new Clause(p1,p2);
		System.out.println(kb);
		kb.update(effect);
		System.out.println(kb);
		
		System.out.println((effect instanceof Clause));
		System.out.println((p1 instanceof Clause));
		
		Plannable plannable = PredicateLevelBuilder.readFile("resources/level.txt");
		System.out.println(plannable.getKnowledgeBase());
		System.out.println(plannable.getGoal());
	}
}

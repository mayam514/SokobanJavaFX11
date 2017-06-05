package run;

import blocksWorld.BlocksWorldAdapter;
import plannable.Plannable;
import planners.Plan;
import planners.Planner;
import planners.Strips;

public class Run {

	public static void main(String[] args) {
		//Predicate p = new Predicate("On", "A", "B");
		//System.out.println(p);

		Planner planner = new Strips();
		Plannable plannable = new BlocksWorldAdapter();
		
		Plan plan = planner.computePlan(plannable, null);
		System.out.println(plan);
		
	}

}

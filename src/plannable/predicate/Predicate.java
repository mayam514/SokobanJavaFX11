package plannable.predicate;

import java.util.Map;

public class Predicate {
	//Data members
	private String name;
	private Object[] args;

	//Constructor
	public Predicate(String name, Object... args) {
		this.name = name;
		this.args = args;
	}

	/**
	 * The method checks if the predicate is satisfied
	 */
	public boolean isSatisfied(){
		return false;
	}
	
	@Override
	public boolean equals(Object obj) {
		Predicate p = (Predicate)obj;
		if (!name.equals(p.name))
			return false;
		if (args != null) {
			if (args.length != p.args.length)
				return false;
			for (int i = 0; i < args.length; i++) {
				if (!args[i].equals(p.args[i]))
					return false;
			}
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(name);

		if (args != null) {
			sb.append("(");
			for (Object arg : args) {
				sb.append(arg + ",");
			}
			sb.deleteCharAt(sb.length() - 1);
			sb.append(")");
		}
		return sb.toString();
	}

	@Override
	public int hashCode() {
		return toString().hashCode();
	}

	public String getName() {
		return name;
	}

	public Object[] getArgs() {
		return args;
	}

	public Predicate instantiate(Map<String, Object> assignment) {
		Predicate p = null;
		switch(this.name){
		case "On":
		case "on":
		case "ON":
			break;
		case "BoxOnTarget":
		case "boxontarget":
		case "Boxontarget":
		case "BOXONTARGET":
			break;
		default:
			System.out.println("Predicate not found");
			break;
		}
		p.args = new Object[this.args.length];
		int count = 0;
		for (Object arg: this.args) {
			Object newArg = assignment.get((String) arg);
			p.args[count++] = newArg;
		}
		return p;
	}

	

}
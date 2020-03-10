import java.util.HashMap;

public class Rules {
	
	Term leftSide;
	Term rightSide[];
	
	public Rules(Term left, Term[] right) {
		leftSide = left;
		rightSide = right;
	}
	
	public static Term[] createRHS(Term ...terms ) {
		Term[] temp = new Term[2];
		int size = 0;
		for (Term t: terms) {
			temp[size] = t;
			size++;
		}
		return temp;
	}
	
	public String toString() {
		String output = leftSide.toString();
		for (Term t: rightSide) {
			output = output + " " + t.toString();
		} 
		return output;
	}
}

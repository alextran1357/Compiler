import java.util.HashMap;

public class Parser {
	
	
	public static void main(String[] args) {
		
		HashMap<Integer, Rules> ruleList = new HashMap<>();
		Term[] one = Rules.createRHS(Term.KWDVARS, Term.PPVARLIST);
		ruleList.put(1, new Rules(Term.VARGROUP, one));
		System.out.println(new Rules(Term.VARGROUP, one));
	}
}

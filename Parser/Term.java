
public class Term {
	
	public static final Term PGM = new Term(1, true);
	public static final Term MAIN = new Term(2, true);
	public static final Term VARGROUP = new Term(3, true);
	public static final Term PPVARLIST = new Term(4, true);
	public static final Term VARLIST = new Term(5, true);
	public static final Term VARITEM = new Term(6, true);
	public static final Term VARDECL = new Term(7, true);
	public static final Term VARINIT = new Term(8, true);
	public static final Term CLASSDECL = new Term(9, true);
	public static final Term CLASSDEF = new Term(10, true);
	public static final Term SIMPLEKIND = new Term(11, true);
	public static final Term VARSPEC = new Term(12, true);
	public static final Term BASEKIND = new Term(13, true);
	public static final Term CLASSID = new Term(14, true);
	public static final Term ARRSPEC = new Term(15, true);
	public static final Term DEREF_ID = new Term(16, true);
	public static final Term KKINT = new Term(17, true);
	public static final Term DEREF = new Term(18, true);
	
	private int id;
	private boolean nonTerm;
	private Token t;
	
	public Term(int num, boolean nonTerm) {
		id = num;
		this.nonTerm = nonTerm;
	}
	
	public Term(Token token) {
		id = token.getID();
		nonTerm = false;
		t = token;
	}
	
	public int getID() {
		return id;
	}
	
	public boolean isTerm() {
		return nonTerm;
	}
	
	public String toString() {
		if (nonTerm) {
			return "Non-Terminal : " + id;
		} else {
			return "Terminal : " + id;
		}
	}
	
}

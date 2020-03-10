import java.util.HashMap;
public class Token 
{
	private int id;
	private String value;
	private String type;
	
	private static HashMap<Integer, String> tokens = new HashMap<>();
	static {
		tokens.put(0, "EOF");
		tokens.put(2,  "ID");
		tokens.put(3, "INT");
		tokens.put(4, "FLOAT");
		tokens.put(5, "STRING");
		tokens.put(6, "COMMA");
		tokens.put(7, "SEMI");
		tokens.put(10, "KPROG");
		tokens.put(11, "KMAIN");
		tokens.put(12, "KFCN");
		tokens.put(13, "KCLASS");
		tokens.put(15, "KFLOAT");
		tokens.put(16, "KINT");
		tokens.put(17, "KSTRING");
		tokens.put(18, "KIF");
		tokens.put(19, "KELSEIF");
		tokens.put(20, "KELSE");
		tokens.put(21, "KWHILE");
		tokens.put(22, "KINPUT");
		tokens.put(23, "KPRINT");
		tokens.put(24, "KNEW");
		tokens.put(25, "KRETURN");
		tokens.put(26, "KVAR");
		tokens.put(31, "ANGLE1");
		tokens.put(32, "ANGLE2");
		tokens.put(33, "BRACE1");
		tokens.put(34, "BRACE2");
		tokens.put(35, "BRACKET1");
		tokens.put(36, "BRACKET2");
		tokens.put(37, "PARENS1");
		tokens.put(38, "PARENS2");
		tokens.put(41, "ASTER");
		tokens.put(42, "CARET");
		tokens.put(43, "COLON");
		tokens.put(44, "DOT");
		tokens.put(45, "EQUAL");
		tokens.put(46, "MINUS");
		tokens.put(47, "PLUS");
		tokens.put(48, "SLASH");
		tokens.put(49, "AMPERSAND");
		tokens.put(51, "OPARROW");
		tokens.put(52, "OPEQ");
		tokens.put(53, "OPNE");
		tokens.put(54, "OPLE");
		tokens.put(55, "OPGE");
		tokens.put(56, "OPSHL");
		tokens.put(57, "OPSHR");
	}
	
	
	public Token(int id, String value)
	{
		this.id = id;
		this.value = value;
		type = tokens.get(id);
		
	}
	
	public int getID() {
		return id;
	}
	
	public String toString()
	{
		return "ID = " + id + ", Value = " + this.value + ", Type = " + type;
	}
}

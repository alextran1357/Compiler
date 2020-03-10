public class Node
{
	// Attributes for Node
	String value;
	String type;
	int tokenNum;
	Node lkid, rkid, mom;
	Symtab symTab;
	
	// Constructors for Node
	public Node()
	{
		value = null;
		type = null;
		tokenNum = 0;
		lkid = null;
		rkid = null;
		symTab = null;
	}
	
	public Node(String value, String type, int tokenNum)
	{
		this.setValue(value);
		this.setType(type);
		this.setTokenNum(tokenNum);
		lkid = null;
		rkid = null;
		mom = null;
		symTab = null;
	}

	// Setters and Getters
	public String getValue() 
	{
		return value;
	}

	public void setValue(String value) 
	{
		this.value = value;
	}
	
	public String getType() 
	{
		return type;
	}

	public void setType(String type) 
	{
		this.type = type;
	}
	
	public int getTokenNum()
	{
		return tokenNum;
	}
	
	public void setTokenNum(int num) 
	{
		this.tokenNum = num;
	}

	public void setLkid(Node lkid) 
	{
		this.lkid = lkid;
	}

	public void setRkid(Node rkid) 
	{
		this.rkid = rkid;
	}
	
	public void setMom(Node mom) 
	{
		this.mom = mom;
	}
	
	public Symtab getSymtab() 
	{
		return symTab;
	}
	
	public void setSymtab(Symtab symTab)
	{
		this.symTab = symTab;
	}
	
	// Override toString. Gets node value.
	public String toString()
	{
		return this.getValue();
	}
}
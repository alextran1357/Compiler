public class SymTabEntry {
	// Node it is linked to in the AST
	Node node;
	
	// Name and type of variable in the node
	String name;
	String type;
	
	// Constructor
	public SymTabEntry(Node node, String name, String type) 
	{
		this.node = node;
		this.name = name;
		this.type = type;
	}
	
	// Setters and getters
	public Node getNode() 
	{
		return node;
	}

	public void setNode(Node node) {
		this.node = node;
	}
	
	public String getName() 
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getType()
	{
		return type;
	}
	
	public void setType(String type)
	{
		this.type = type;
	}
}

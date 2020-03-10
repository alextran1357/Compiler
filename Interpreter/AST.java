import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class AST 
{
	Node root;
	
	public AST()
	{
		this.root = new Node();
	}
	
	public AST(Node root)
	{
		this.root = root;
	}
	
//	public AST(String ASTnodes)
//	{
//		this.root = this.Deserialize(ASTnodes);
//	}
//	
	public String Serialize()
	{
		if (this.root == null) return "null ";
		
		String ret;
		
		ret = this.root.value + " ";
		ret += Serialize(this.root.lkid);
		ret += Serialize(this.root.rkid);

		return ret;
	}
	
	public static String Serialize(Node root)
	{
		if (root == null) return "null ";
		
		String ret;
		
		ret = root.value + " ";
		ret += Serialize(root.lkid);
		ret += Serialize(root.rkid);

		return ret;
	}
	
//	public Node Deserialize(String data)
//	{
//	    Queue<String> nodes = new LinkedList<>();
//	    nodes.addAll(Arrays.asList(data.split(" ")));
//	    return DeserializeHelper(nodes);
//	}
//
//	private Node DeserializeHelper(Queue<String> nodes)
//	{
//		String value = nodes.poll();
//	    
//	    if (value.equals("null")) { return null; }
//	    
//	    Node node = new Node(value);
//	    node.lkid = DeserializeHelper(nodes);
//	    node.rkid = DeserializeHelper(nodes);
//	    
//	    return node;
//	}
	
	public String toString()
	{
		return this.Serialize();
	}
}
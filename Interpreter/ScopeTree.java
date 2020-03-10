import java.util.*;
public class ScopeTree {
	
	// All the nodes in the Scope Tree
	ArrayList<Node> scopeNodes = new ArrayList<Node>();
	
	// If there's more than one scope, these attributes are to keep track of the next and previous scope trees
	ScopeTree mom;
	ScopeTree nextTree;
	
	// Constructor for Scope Tree
	public ScopeTree(AST tree)
	{
		// New scope tree creates a new node
		Node scopeNode = new Node();
		// Symbol table gets created based on given AST, treewalks it and updates the symbol table with any new entries
		Symtab symTab = new Symtab(tree);
		// Link the symbol table to the scope node
		scopeNode.setSymtab(symTab);
		// Node gets added to list of all nodes in the scope tree
		scopeNodes.add(scopeNode);
		mom = null;
		nextTree = null;
	}
	
	// Setters and getters
	public ScopeTree getMom()
	{
		return mom;
	}
	
	public void setMom(ScopeTree mom) 
	{
		this.mom = mom;
	}
	
	public void setNextTree(ScopeTree nextTree)
	{
		this.nextTree = nextTree;
	}
	
	// Print all symbol tables in the scope tree
	public void printSymtabs() 
	{
		for (int i = 0; i < scopeNodes.size(); i++)
		{
			System.out.println(scopeNodes.get(i).getSymtab());
		}
	}
}

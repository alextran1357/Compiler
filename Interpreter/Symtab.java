import java.util.HashMap;
import java.util.Arrays;

public class Symtab {

	// Symbol Table. Holds all entries of the symbol table
	static HashMap<Integer, SymTabEntry> symTab = new HashMap<Integer, SymTabEntry>();
	int counter = 1; // Keeps track of numbered entries in symbol table

	// Creating a Symtab (given an AST tree) will treewalk through the tree and add entries to the symbol table
	// Primarily used in the ScopeTree class when creating a new Scope Tree
	public Symtab(AST tree) {
		treewalk(tree.root);
	}

	// Add entry to symbol table. Takes SymTabEntry object for the node information
	public void addEntry(int rowNum, SymTabEntry nodeInfo) {
		symTab.put(rowNum, nodeInfo);
	}

	// Treewalk method, goes through AST in post order and adds any user-defined variables to the symbol table
	public void treewalk(Node node) {
		if (node == null)
			return;
		treewalk(node.lkid);
		treewalk(node.rkid);
		
		// If the variable has token number 2, meaning it is an identifier, it's a user-defined variable. Have to add to the symbol table.
		if (node.getTokenNum() == 2) {
			boolean alreadyIn = false;
			
			// If symbol table is already empty, add in the new entry
			if (symTab.size() == 0)
			{
				// Links the symbol table entry to the node in the AST, and gets the name of the variable and its type. Gets stored in symbol table.
				SymTabEntry nodeInfo = new SymTabEntry(node, node.getValue(), node.getType());
				symTab.put(counter, nodeInfo);
				counter++;
			} else { 
				// Go through the current symbol table to see if a variable name is already in the symbol table
				for (int i = 1; i < symTab.size() + 1; i++) 
				{
					// If it's already in the symbol table, boolean turns true
					if (node.getValue().equals(symTab.get(i).getName())) 
					{
						alreadyIn = true;
					}
				}
				
				// If the variable is not already in the symbol table, then add it in as a new entry
				if (!alreadyIn)
				{
					// Links the symbol table entry to the node in the AST, and gets the name of the variable and its type. Gets stored in symbol table.
					SymTabEntry nodeInfo = new SymTabEntry(node, node.getValue(), node.getType());
					symTab.put(counter, nodeInfo);
					counter++;
				}
			}
			
		}
		return;
	}

	// Override toString. Print each entry in the symbol table
	public String toString() {
		String output = "";
		for (int i = 1; i < symTab.size() + 1; i++) {
			String entry = "";
			entry += i + ".  Name: " + symTab.get(i).getName() + " | Type: " + symTab.get(i).getType() + " | Linked node in AST: " + symTab.get(i).getNode().getValue() + "\n";
			output += entry;
		}
		return output;
	}
	
	
}

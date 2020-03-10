import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
public class Main {
	public static void main(String[] args) throws Exception {
		
		Path inputFile = null;
		List<String> stringList = null;
		// Taking in file input
		try {
			inputFile = Paths.get("src\\ast.txt");
			stringList = Files.readAllLines(inputFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Setting the root
		String[] temp = stringList.get(0).split(" ");
		String[] breaker = temp[0].split("_");
		Node r = new Node(breaker[0], breaker[1], Integer.valueOf(breaker[2]));
		readAST(r, temp, 1);
		
		// Create AST based on root node
		AST tree = new AST(r);
		
		// New scope tree created
		ScopeTree scope = new ScopeTree(tree);
		
		// Showing it worked, prints out all symbol tables in the scope tree
		scope.printSymtabs();
	}
	
	public static int readAST(Node n, String[] l, int index) {
		// Index out of bounds
		if (index > l.length) {
			return index;
		}
//		System.out.println(n.getValue());
		// LR
		if(l[index].equals("null")) {
//			System.out.println(n.getValue() + " adding '"+l[index]+"' left child");
			n.lkid = null;
			index++;
		} else {
//			System.out.println(n.getValue() + " adding '"+l[index]+"' left child");
			String[] breaker = l[index].split("_");
			n.lkid = new Node(breaker[0], breaker[1], Integer.valueOf(breaker[2]));
			n.setLkid(n.lkid);// Setting parents
			n.lkid.setMom(n);
			index = readAST(n.lkid, l, index += 1);
		}
		// RR
		if(l[index].equals("null")) {
//			System.out.println(n.getValue() + " adding '"+l[index]+"' right child");
			n.rkid = null;
			index++;
		} else {
//			System.out.println(n.getValue() + " adding '"+l[index]+"' right child");
			String[] breaker = l[index].split("_");
			n.rkid = new Node(breaker[0], breaker[1], Integer.valueOf(breaker[2]));
			n.setRkid(n.rkid);// Setting parents
			n.rkid.setMom(n);
			index = readAST(n.rkid, l, index += 1);
		}
//		System.out.println(n.getValue() + " "+index+ " ENDING");
		return index;
	}
	
	public static void readTree(Node n) {
		if (n == null) {
			return;
		}
		readTree(n.lkid);
		System.out.println(n.getValue());
		readTree(n.rkid);
	}
}














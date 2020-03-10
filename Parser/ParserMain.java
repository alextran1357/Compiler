import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ParserMain 
{
	public static void main(String[] args) 
	{
		List<String> tokenList = null;
		Path inputFile = null;
		
		try 
		{
			inputFile = Paths.get("H:\\Downloads\\A5-sample-1.alex"); //Paths.get(args[0]);
			tokenList = Files.readAllLines(inputFile);
			System.out.println(tokenList);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		for (int i = 0; i < tokenList.size(); i++)
		{
			String[] content = tokenList.get(i).split(" ");
			int id = Integer.parseInt(content[1]);
			Token t = new Token(id, content[5]);
			
			System.out.println(t);
		}
	}
}
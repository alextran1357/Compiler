import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.util.Pair;

public class Test {
	public static void main(String[] args) {

		// Initialize dictionary of keywords to be recognized
		List<String[]> dic = initializeDictionary();

		// Reads file and stores each line in the file in a list of Strings
		List<String> content = null;
		try {
			content = Files.readAllLines(Paths.get("src\\A5-sample-1.acod"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		String[] contentArray = content.toArray(new String[content.size()]);

		// Keeps track of keyword being checked
		String keyword = "";

		// List of data types to keep track of any upcoming variable names
		String[] dataTypes = new String[5];
		dataTypes[0] = "var";
		dataTypes[1] = "int";
		dataTypes[2] = "float";
		dataTypes[3] = "String";
		dataTypes[4] = "class";

		// Stores any created variables in the code to save as a keyword
		ArrayList<String> createdVariables = new ArrayList<String>();

		// Keeps track of index when going through each char in the line
		int counter = 0;

		boolean isString = false;
		boolean printedString = false;
		boolean isNum = false;
		boolean isFloat = false;
		boolean end = false;
		boolean isVariable = false;

		// For loop that goes through each line in the file read. Keeps track of
		// line number.
		for (int lineNumber = 0; lineNumber < contentArray.length; lineNumber++) {
			// Split line of string into separate chars, removes any tab spaces
			String removeTab = contentArray[lineNumber].replace("\t", "");
			String[] lineArray = removeTab.split("");
			end = false;
			counter = 0;

			// Goes through each char in the line
			for (String s : lineArray) {
				{
					// Hasn't reached end of line
					if (end != true) {
						if (!s.contains(" ")) {
							// Checks for comments
							if (s.equals("/")) {
								if (peek(lineArray, counter).equals("/"))
									break;
							}
							// Checks for beginning of String
							if (s.equals("\"") && isString == false)
								isString = true;
							// Checks for end of String
							else if (s.equals("\"") && printedString == true) {
								isString = false;
								printedString = false;
								// Checks if it's a numerical value
							} else if (s.matches(".*\\d.*") && isNum == false && isString == false) {
								isNum = true;
								keyword += s;
							} else {
								keyword += s;
							}

							// If char is a whitespace and is part of a string,
							// adds the whitespace to the keyword
						} else if (s.contains(" ") && isString == true) {
							keyword += s;
						}

						// Marks number input a float if it reaches a period,
						// goes to next character
						if (s.equals(".") && isNum == true) {
							isFloat = true;
							counter++;
							continue;
						}

						// Checks the last keyword stored if it's reached the
						// end of the line
						if (counter + 1 == lineArray.length) {
							Pair<Boolean, String[]> check = contains(dic, keyword);
							if (check.getKey())
								printToken(check.getValue()[0], lineNumber + 1, keyword);
							end = true;
							keyword = "";
							// Prints if it's the end of input
							if (lineNumber + 1 == contentArray.length)
								printToken("0", lineNumber + 1, keyword);

							// Checks if keyword is valid token when there is a
							// whitespace upcoming
						} else if (peek(lineArray, counter).contains(" ") && isString == false && isFloat == false
								&& isNum == false && !keyword.equals("")) {
							Pair<Boolean, String[]> check = contains(dic, keyword);
							if (check.getKey()) {
								printToken(check.getValue()[0], lineNumber + 1, keyword);
								// If keyword is part of created variables,
								// print keyword
							} else if (createdVariables.contains(keyword)) {
								printToken("2", lineNumber + 1, keyword);
							}
							// If keyword is flagged as a possible variable name
							// and is a valid identifier name, add to
							// list of variables and print
							if (isVariable == true && isIdentifier(keyword, keyword.length())) {
								isVariable = false;
								createdVariables.add(keyword);
								printToken("2", lineNumber + 1, keyword);
							}

							// If it encounters a data type, flags that
							// upcoming word is possibly a variable name
							for (String key : dataTypes) {
								if (keyword.equals(key))
									isVariable = true;
							}
							keyword = "";

							// If the keyword is a numerical value, prints out
							// whether it's an int or float
						} else if (isNum == true && !peek(lineArray, counter).matches(".*\\d.*") && isFloat == false
								&& !peek(lineArray, counter).equals(".")) {
							printToken("3", lineNumber + 1, keyword);
							isNum = false;
							keyword = "";
						} else if (isNum == true && !peek(lineArray, counter).matches(".*\\d.*") && isFloat == true) {
							printToken("4", lineNumber + 1, keyword);
							isNum = false;
							isFloat = false;
							keyword = "";
						}
						// If keyword is currently a part of a String input and
						// the peek() function sees an upcoming quotation mark,
						// prints out string
						else if (isString == true && peek(lineArray, counter).equals("\"")) {
							printToken("5", lineNumber + 1, keyword);
							printedString = true;
							keyword = "";
							// Prints out separate tokens for certain characters
							// in cases where they
							// are right next to each other
						} else if (isString == false && !s.contains(" ")
								&& (peek(lineArray, counter).equals(";") || peek(lineArray, counter).equals("(")
										|| peek(lineArray, counter).equals(")") || peek(lineArray, counter).equals("{")
										|| peek(lineArray, counter).equals("}"))) {
							if (createdVariables.contains(keyword)) {
								printToken("2", lineNumber + 1, keyword);
								isVariable = false;
							} else {
								Pair<Boolean, String[]> check = contains(dic, keyword);
								if (check.getKey())
									printToken(check.getValue()[0], lineNumber + 1, keyword);
								isVariable = false;
								isNum = false;
							}
							keyword = "";
						}
						counter++;
					}
				}
			}
		}
	}

	public static List<String[]> initializeDictionary() {
		List<String[]> dictionary = new ArrayList<String[]>();

		dictionary.add(new String[] { "3", "int", "[0-9]*" });
		dictionary.add(new String[] { "5", "string", "\"[\\s\\S]*\"" });

		dictionary.add(new String[] { "6", "comma", "," });
		dictionary.add(new String[] { "7", "semi", ";" });

		String[] keywordsArray = { "kprog", "kmain", "kfcn", "kclass", "kfloat", "kint", "kstring", "kif", "kelseif",
				"kelse", "kwhile", "kwhile", "kinput", "kprint", "knew", "kreturn", "kvar" };

		int startIndex = 10;
		for (String keyword : keywordsArray) {
			String[] s = new String[3];

			s[0] = Integer.toString(startIndex++);
			s[1] = keyword;
			s[2] = keyword.substring(1);

			dictionary.add(s);
		}

		dictionary.add(new String[] { "31", "angle1", "<" });
		dictionary.add(new String[] { "32", "angle2", ">" });
		dictionary.add(new String[] { "33", "brace1", "{" });
		dictionary.add(new String[] { "34", "brace2", "}" });
		dictionary.add(new String[] { "35", "brackets1", "[" });
		dictionary.add(new String[] { "36", "brackets2", "]" });
		dictionary.add(new String[] { "37", "parens1", "(" });
		dictionary.add(new String[] { "38", "parens2", ")" });

		dictionary.add(new String[] { "41", "aster", "*" });
		dictionary.add(new String[] { "42", "caret", "^" });
		dictionary.add(new String[] { "43", "colon", ":" });
		dictionary.add(new String[] { "44", "dot", "." });
		dictionary.add(new String[] { "45", "equal", "=" });
		dictionary.add(new String[] { "46", "minus", "-" });
		dictionary.add(new String[] { "47", "plus", "+" });
		dictionary.add(new String[] { "48", "slash", "/" });
		dictionary.add(new String[] { "49", "ampersand", "&" });

		dictionary.add(new String[] { "51", "oparrow", "->" });
		dictionary.add(new String[] { "52", "opeq", "==" });
		dictionary.add(new String[] { "53", "opne", "!=" });
		dictionary.add(new String[] { "54", "ople", "<=" });
		dictionary.add(new String[] { "55", "opge", ">=" });
		dictionary.add(new String[] { "56", "opshl", "<<" });
		dictionary.add(new String[] { "57", "opshr", ">>" });

		return dictionary;

	}

	public static Pair<Boolean, String[]> contains(List<String[]> dictionary, String word) {
		Pair<Boolean, String[]> ret = new Pair<Boolean, String[]>(false, null);

		for (int i = 0; i <= 1; i++) {
			Pattern p = Pattern.compile(dictionary.get(i)[2]);
			Matcher m = p.matcher(word);
			Boolean foundRegex = m.matches();
			if (foundRegex) {
				return ret = new Pair<Boolean, String[]>(foundRegex, dictionary.get(i));
			}
		}

		for (String[] item : dictionary) {
			Boolean found = word.equals(item[2]);
			if (found)
				return ret = new Pair<Boolean, String[]>(found, item);
		}

		return ret;
	}

	public static void printToken(String tokenNumber, int lineNumber, String token) {
		boolean printed = false;
		System.out.print("(Tok: " + tokenNumber + " lin= " + lineNumber + " str= \"" + token + "\"");
		try {
			Integer.parseInt(token);
			int intToken = Integer.valueOf(token);
			System.out.print(" int = " + intToken);
			System.out.print(")\n");
			printed = true;
		} catch (NumberFormatException e) {
		}

		if (printed == false) {
			try {
				Float.parseFloat(token);
				float intToken = Float.valueOf(token);
				System.out.print(" flo = " + intToken);
			} catch (NumberFormatException e) {
			} finally {
				System.out.print(")\n");
			}
		}

	}

	// Checks if variable being created is a valid name
	public static boolean isIdentifier(String str, int length) {
		// Checks first character
		if (!((str.charAt(0) >= 'a' && str.charAt(0) <= 'z') || (str.charAt(0) >= 'A' && str.charAt(1) <= 'Z')
				|| str.charAt(0) == '_'))
			return false;

		// Checks remaining characters
		for (int i = 1; i < str.length(); i++) {
			if (!((str.charAt(i) >= 'a' && str.charAt(i) <= 'z') || (str.charAt(i) >= 'A' && str.charAt(i) <= 'Z')
					|| (str.charAt(i) >= '0' && str.charAt(i) <= '9') || str.charAt(i) == '_'))
				return false;
		}

		return true;
	}

	// Checks character one space ahead of current position
	public static String peek(String[] s, int i) {
		return s[i + 1];
	}
}
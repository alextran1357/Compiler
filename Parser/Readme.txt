Class Number: CECS 444-01
Project Name: Parser
Team Name: CMP
Team Members: Dane Dinh, Sukriti Agarwal, Mina Messiha, Alex Tran

Introduction:
The “Parser” project is the second part of a bigger semester-long project.
This project is a Java implementation of an A5 Language Parser. 
The objective of this assignment is to write an LL predictive parser. This parser uses the input from the previous part of the project (Lexer) and consists of three parts. The parts are the parser machine, LL parse table for the involved grammar, and the prediction stack.  

Contents in the .zip submission:
The “444-p2_CMP.zip” folder contains the following files:
Readme.txt
Node.java
ParserMain.java
Rules.java
Term.java
Token.java
Rules and LRE and Lfactoring.pdf
LL Table Spreadsheet.xlsx
A5-sample-1.alex
A5-sample-2.alex
A5-sample-3.alex

External Requirements:
JDK 8.0 or higher

Set up and Installation and Execution:
1. Extract the contents of the 444-p2_CMP.zip file into a new folder.
2. Go to the terminal (on Ubuntu) or the Command Prompt (on Windows).
3. Change the directory in ther terminal to the folder where all the files are extracted to
4. Enter in "Javac ParserMain.java" to compile then "Java ParserMain A5-sample-p1.alex" to run

Sample Invocation and Results to See:
Input is from "A5-sample-p1.alex" file, error prints if LL Parse Table Steps go wrong

Features:
1. Node class
2. Rules class to store rules
3. Basic functions of the LL Parse Table steps
4. Term class to store T and Non-T's
5. Token class to break up input into token objects
  
Missing:
1. Creating a Parse Tree
2. Converting the PST to AST
3. Printing the AST

Bugs: 
1. LL Parse Table Steps (m1 -> m4) may not be working properly

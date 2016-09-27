/* WORD LADDER Main.java
 * EE422C Project 3 submission by
 * Replace <...> with your actual data.
 * Xiaoyong Liang
 * XL5432
 * 16480
 * Yuankai Yue
 * <yy7347>
 * <16465>
 * Slip days used: <0>
 * Git URL: https://github.com/KyleYue/Project3_WordLadder
 * Fall 2016
 */


package assignment3;
import java.util.*;
import java.io.*;

public class Main {
	
	// static variables and constants only here.
	
	public static void main(String[] args) throws Exception {
		
		Scanner kb;	// input Scanner for commands
		PrintStream ps;	// output file
		// If arguments are specified, read/write from/to files instead of Std IO.
		if (args.length != 0) {
			kb = new Scanner(new File(args[0]));
			ps = new PrintStream(new File(args[1]));
			System.setOut(ps);			// redirect output to ps
		} else {
			kb = new Scanner(System.in);// default from Stdin
			ps = System.out;			// default to Stdout
		}
		initialize();
		
		// TODO methods to read in words, output ladder
	}
	
	public static void initialize() {
		// initialize your static variables or constants here.
		// We will call this method before running our JUNIT tests.  So call it 
		// only once at the start of main.
	}
	
	/**
	 * @param keyboard Scanner connected to System.in
	 * @return ArrayList of 2 Strings containing start word and end word. 
	 * If command is /quit, return empty ArrayList. 
	 */
	public static ArrayList<String> parse(Scanner keyboard) {
		// TO DO
		return null;
	}
	
	public static ArrayList<String> getWordLadderDFS(String start, String end) {
		
		// Returned list should be ordered start to end.  Include start and end.
		// Return empty list if no ladder.
		// TODO some code
		Set<String> dict = makeDictionary();
		// TODO more code
		
		return null; // replace this line later with real return
	}
	
    public static ArrayList<String> getWordLadderBFS(String start, String end) {
		Queue<String> q = new LinkedList<String>();
		Set<String> visited = new HashSet<String>();
		HashMap<String, String> connect = new HashMap<String,String>();
		ArrayList<String> neighbors = new ArrayList<String>();
		String current,next;
		q.add(start);
		while(!q.isEmpty()){
			current=(String)q.poll();
			visited.add(current);
			if(current.equals(end)){
				connect.put(end, current);
				break;
			}
			neighbors = getNeighbors(current);
			Iterator<String> itr = neighbors.iterator();
			while(itr.hasNext()){
				next = (String)itr.next();
				if(!visited.contains(next)){
					q.add(next);
					connect.put(next, current);
				}
			}
		}
		return getLadder(connect,end); // replace this line later with real return
	}
    /**
     * Returns a list of neighbors of word. Neighbors different from word by only 1 letter.
     * @param 	input word. 
     * @return	a list of neighbors.
     */
    private static ArrayList<String> getNeighbors(String word){
    	Set<String> dict = makeDictionary();
    	ArrayList<String> neighbors = new ArrayList<String>();
    	Iterator itr= dict.iterator();
    	while(itr.hasNext()){
    		String next = (String) itr.next();
    		if(isNeighbor(word,next.toLowerCase()))
    			neighbors.add(next);
    	}
    	return neighbors;
    }
    
    /**
     * Test if two words are neighbors.
     * @return	true if two words are neighbors.
     */
    private static boolean isNeighbor(String word1, String word2){
    	int counter=0;
    	char[] word1Char = word1.toCharArray();
    	char[] word2Char = word2.toCharArray();
    	for(int i=0; i<word1.length(); i++){
    		if(word1Char[i]!=word2Char[i])
    			counter++;
    	}
    	return counter==1;
    }
    
    private static ArrayList<String> getLadder(HashMap<String, String> map, String endWord){
    	 ArrayList<String> ladder = new  ArrayList<String>();
    	 String key = endWord;
    	 ladder.add(key);
    	 while(map.containsKey(key)){
    		 ladder.add(map.get(key));
    	 }
    	return ladder;
    }
    
	public static Set<String>  makeDictionary () {
		Set<String> words = new HashSet<String>();
		Scanner infile = null;
		try {
			infile = new Scanner (new File("five_letter_words.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("Dictionary File not Found!");
			e.printStackTrace();
			System.exit(1);
		}
		while (infile.hasNext()) {
			words.add(infile.next().toUpperCase());
		}
		return words;
	}
	
	/**
	 * @param	ladder.  word ladder between the start and the finish word, including the start and finish word.
	 * ladder size small than 2 indicates a code problem. The method will return after printing out a 
	 * "Invalid ladder" message. 
	 */
	
	public static void printLadder(ArrayList<String> ladder){
		int ladderHeight = ladder.size();
		if(ladderHeight <2){
			System.out.println("Invalid ladder");
			return;
		}
		if(ladderHeight==2){
			System.out.println("no word ladder can be found between <start> and <finish>.");
		}else{
			System.out.println("a 8-rung word ladder exists between smart and money.");
			for(int i=1; i<ladderHeight-1; i++){
				System.out.println(ladder.get(i));
			}
		}
	}
	// TODO
	// Other private static methods here
}

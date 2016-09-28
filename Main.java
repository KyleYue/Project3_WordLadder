/* WORD LADDER Main.java
 * EE422C Project 3 submission by
 * Replace <...> with your actual data.
 * Xiaoyong Liang
 * XL5432
 * 16480
 * Yuankai Yue
 * yy7347
 * 16465
 * Slip days used: <0>
 * Git URL: https://github.com/KyleYue/Project3_WordLadder
 * Fall 2016
 */


package assignment3;
import java.util.*;
import java.io.*;

public class Main {
	
	// static variables and constants only here.

	static Set<String> dict;
	static String START,END;

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
		ArrayList<String> inputs = parse(kb);
		if (inputs.size()<2)
			return;
		System.out.println("Breadth first search: ");
		printLadder(getWordLadderBFS(inputs.get(0),inputs.get(1)));
		System.out.println("Depth first search: ");
		printLadder(getWordLadderDFS(inputs.get(0),inputs.get(1)));
	}
	
	public static void initialize() {
		// initialize your static variables or constants here.
		// We will call this method before running our JUNIT tests.  So call it 
		// only once at the start of main.
		dict = makeDictionary();
	}
	
	/**
	 * @param keyboard Scanner connected to System.in
	 * @return ArrayList of 2 Strings containing start word and end word. 
	 * If command is /quit, return empty ArrayList. 
	 */
	public static ArrayList<String> parse(Scanner keyboard) {
		// TO DO
		return parseLine(keyboard.nextLine());
	}

	public static ArrayList<String> parseLine(String line) {
		ArrayList<String> inputs = new ArrayList<String>(Arrays.asList((line.toLowerCase()).split(" ")));
		for (String word: inputs) {
			if(word.equals("/quit")){
				return new ArrayList<String>();
			}
		}
		return inputs;
	}

	public static ArrayList<String> getWordLadderDFS(String start, String end) {
		
		// Returned list should be ordered start to end.  Include start and end.
		// Return empty list if no ladder.
		START = start;
		END = end;
		Set<String> visited = new HashSet<>();
		ArrayList<String> ladder = new ArrayList<>();
		ladder.add(start);
		int diff = getDifference(start, end);
		ArrayList<String> list = getWordLadderDFSRec(start, end, diff, visited);
		return list;
	}

	private static ArrayList<String> getWordLadderDFSRec(String start, String end, int lastDiff, Set<String> visited){
		if(start.equals(end)){
			ArrayList<String> ladder = new ArrayList<>();
			ladder.add(start);
			return ladder;
		}
		if(visited.contains(start)){
			return null;
		}

		visited.add(start);

		ArrayList<String> neighbors = getNeighbors(start);
		String[] filteredNeighbors = filterOutMoreDifferentiatedStrings(end, neighbors.toArray(new String[neighbors.size()]), lastDiff);

		for(String newNode : filteredNeighbors){
			ArrayList<String> fromChildren = getWordLadderDFSRec(newNode, end, getDifference(newNode, end), visited);
			if(fromChildren == null){
				continue;
			}
			fromChildren.add(0, start);
			return fromChildren;
		}
		return null;
	}
	
	/**
	 * Get the word Ladder from the start word and the end word.
	 * @param start the start word of the ladder.
	 * @param end	the end word of the ladder.
	 * @return		the an Arraylist<String> of word ladder.
	 */
    public static ArrayList<String> getWordLadderBFS(String start, String end) {
		START = start;
		END = end;
		Queue<String> q = new LinkedList<String>();
		Set<String> visited = new HashSet<String>();
		HashMap<String, String> connect = new HashMap<String,String>();
		ArrayList<String> neighbors;
		Iterator<String> itr;
		String current,next;
		boolean ladderFound=false;
		q.add(start);
		visited.add(start);
		while(!q.isEmpty()){
			current=(String)q.poll();
			if(current.equals(end)){
				ladderFound=true;
				break;
			}
			neighbors = getNeighbors(current);
			itr = neighbors.iterator();
			while(itr.hasNext()){
				next = (String)itr.next();
				if(!visited.contains(next)){
					visited.add(next);
					q.add(next);
					connect.put(next, current);
				}
			}
		}
		if(ladderFound)
			return getLadder(connect,end);
		else{
			return null;
		}
	}
    
    /**
     * Construct a dictionary
     * @return
     */
	public static Set<String>  makeDictionary () {
		Set<String> words = new HashSet<String>();
		Scanner infile = null;
		try {
			infile = new Scanner (new File(Main.class.getResource("five_letter_words.txt").getPath()));
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
	 * @param	ladder  word ladder between the start and the finish word, including the start and finish word.
	 * ladder size small than 2 indicates a code problem. The method will return after printing out a 
	 * "Invalid ladder" message. 
	 */
	
	public static void printLadder(ArrayList<String> ladder){
		if(ladder==null){
			System.out.println("no word ladder can be found between " + START+" and "+END+".");
			return;
		}
		int ladderHeight = ladder.size();
		if(ladderHeight<2)
			throw new IllegalArgumentException("Illegal ladder arraylist input, ladder length shuold be larger than 1.");
		else{
			System.out.println("a "+(ladderHeight-2)+"-rung word ladder exists between "+START+" and "+END +" .");
			for(int i=0; i<ladderHeight; i++){
				System.out.println(ladder.get(i));
			}
		}
	}
	// TODO
	// Other private static methods here
	/**
	 * Calculate the number of differences between two words.
	 * @param start	the start word
	 * @param end	the end word
	 * @return		difference between the start word and the end word.
	 */
	public static int getDifference(String start, String end){
		int size1 = start.length();
		int size2 = end.length();
		int smallerSize = size1 > size2 ? size2 : size1;
		int count = 0;
		for (int i = 0; i < smallerSize; i ++) {
			if(start.charAt(i) != end.charAt(i)){
				count++;
			}
		}
		return count;
	}

	public static String[] filterOutMoreDifferentiatedStrings(String start, String[] list, int lastDiff){
		ArrayList<String> result = new ArrayList<>();
		for (String tar: list) {
			if(getDifference(start, tar) <= lastDiff){
				result.add(tar);
			}
		}
		String[] arr = new String[result.size()];
		return result.toArray(arr);
	}

    /**
     * Returns a list of neighbors of word. Neighbors different from word by only 1 letter.
     * @param  word
     * @return	a list of neighbors.
     */
    private static ArrayList<String> getNeighbors(String word){
    	ArrayList<String> neighbors = new ArrayList<String>();
    	Iterator itr= dict.iterator();
    	String next;
    	while(itr.hasNext()){
    		next = (String) itr.next();
    		if(isNeighbor(word,next.toLowerCase()))
    			neighbors.add(next.toLowerCase());
    	}
    	return neighbors;
    }
    
    /**
     * Test if two words are neighbors.
     * @param word1 word to be tested.
     * @param word2 word to be tested.
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
    
    /**
     * Construct a ladder from a map, the key and value are neighboring words.
     * @param map a map with neighboring words.
     * @param endWord the end word.
     * @return ArrayList with of the word ladder
     */
    private static ArrayList<String> getLadder(HashMap<String, String> map, String endWord){
    	 ArrayList<String> ladder = new  ArrayList<String>();
    	 String key = endWord;
    	 String next;
    	 ladder.add(key);
    	 while(map.containsKey(key)){
    		 next = map.remove(key);
    		 ladder.add(next);
    		 key=next;
    	 }

    	return reverse(ladder);
    }
    
    /**
     * Reverse a arraylist.
     * @param list list to be reversed
     * @return reversed list
     */
    
    private static ArrayList<String> reverse(ArrayList<String> list){
	   	 ArrayList<String> reverse= new ArrayList<String>();
	   	 int size = list.size();
	   	 for(int i =0; i<size;i++){
	   		 reverse.add(list.get(size-1-i));
	   	 }
	   	 return reverse;
    }
   
}

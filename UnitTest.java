/* WORD LADDER UnitTest.java
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

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import static org.junit.Assert.*;

public class UnitTest {
	@Before
	public void setup(){
		Main.initialize();
	}

    @Test
    public void testParse() {
        String input = "hello world";
        Scanner scan = new Scanner(input);
        ArrayList<String> expected = new ArrayList<>();
        expected.add("hello");
        expected.add("world");
        assertEquals(expected, Main.parse(scan));

    	ArrayList<String> words = Main.parse(new Scanner("start money"));
    	assertTrue(words.get(0).equals("start")&&words.get(1).equals("money"));
    	words = Main.parse(new Scanner("start			money"));
    	assertTrue(words.get(0).equals("start")&&words.get(1).equals("money"));
    	words = Main.parse(new Scanner("START MONEY"));
    	assertTrue(words.get(0).equals("start")&&words.get(1).equals("money"));
    }

    @Test(timeout = 30000)
    public void testCountDiff() {
        Assert.assertEquals(2, Main.getDifference("apple", "apppp"));
        Assert.assertEquals(5, Main.getDifference("11111", "apppp"));
        Assert.assertEquals(1, Main.getDifference("count", "cbunt"));
        Assert.assertEquals(4, Main.getDifference("apple", "sspss"));
        Assert.assertEquals(5, Main.getDifference("apple", "sssss"));
        Assert.assertEquals(3, Main.getDifference("apple", "apsss"));
        Assert.assertEquals(0, Main.getDifference("apple", "apple"));
    }

    @Test
    public void testSortNeighbors() {
        Assert.assertArrayEquals(new String[]{"appli", "apppp", "ppppp"}, Main.sortNeighbors("apple", new ArrayList<>(Arrays.asList(new String[]{"ppppp", "appli", "apppp"}))).toArray());
        Assert.assertArrayEquals(new String[]{"appli", "apppp", "ppppp"}, Main.sortNeighbors("apple", new ArrayList<>(Arrays.asList(new String[]{"appli", "apppp", "ppppp"}))).toArray());
        Assert.assertArrayEquals(new String[]{"appli", "apppp", "apiii", "azzzz", "zzzzz"}, Main.sortNeighbors("apple", new ArrayList<>(Arrays.asList(new String[]{"zzzzz", "appli", "apppp", "apiii", "azzzz"}))).toArray());
    }

    @Test
    public void testBFSAndDFS(){
    	Random randomGenerator = new Random();
    	Set<String> dict = Main.makeDictionary();
    	String[] dictString =dict.toArray(new String[dict.size()]);
    	int BFSNullCounter=0;
    	int DFSNullCounter=0;
    	for(int i=0; i<10; i++){
        	int i1 = randomGenerator.nextInt(dictString.length);
        	int i2 = randomGenerator.nextInt(dictString.length);
        	String word1 = dictString[i1].toLowerCase();
        	String word2 = dictString[i2].toLowerCase();
        	if(Main.getWordLadderBFS(word1,word2).size()==0)
        		BFSNullCounter++;
        	if(Main.getWordLadderDFS(word1,word2).size()==0)
        		DFSNullCounter++;
    	}
    	assertEquals(BFSNullCounter,DFSNullCounter);
    }
    
    @Test 
    public void testBFS(){
    	assertTrue(Main.getWordLadderBFS("start", "start").size()==0);
    	assertTrue(Main.getWordLadderBFS("bazoo", "habit").size()==0);

        ArrayList<String> stoneAtone = Main.getWordLadderBFS("stone", "atone");
        Assert.assertEquals(2, stoneAtone.size());
        assertTrue(Main.ladderFound);
        assertTrue(stoneAtone.contains("stone"));
        assertTrue(stoneAtone.contains("atone"));

        ArrayList<String> startMoney = Main.getWordLadderBFS("smart", "money");
    	assertTrue(startMoney.contains("smart"));
    	assertTrue(startMoney.size()>2);
    	assertTrue(startMoney.contains("money"));

        ArrayList<String> stoneMoney = Main.getWordLadderBFS("stone", "money");
        assertTrue(stoneMoney.contains("stone"));
        assertTrue(stoneMoney.size()>2);
        assertTrue(stoneMoney.contains("money"));
    }
    
    @Test
    public void testDFS(){
    	assertTrue(Main.getWordLadderDFS("start", "start").size()==0);
    	assertTrue(Main.getWordLadderDFS("bazoo", "habit").size()==0);

        ArrayList<String> stoneAtone = Main.getWordLadderDFS("stone", "atone");
        Assert.assertEquals(2, stoneAtone.size());
        assertTrue(Main.ladderFound);
        assertTrue(stoneAtone.contains("stone"));
        assertTrue(stoneAtone.contains("atone"));

        ArrayList<String> startMoney = Main.getWordLadderBFS("smart", "money");
        assertTrue(startMoney.contains("smart"));
        assertTrue(startMoney.size()>2);
        assertTrue(startMoney.contains("money"));

        ArrayList<String> stoneMoney = Main.getWordLadderBFS("stone", "money");
        assertTrue(stoneMoney.contains("stone"));
        assertTrue(stoneMoney.size()>2);
        assertTrue(stoneMoney.contains("money"));
    }

    @Test(timeout = 30000)
    public void testBFSNoDuplicates1() {
        ArrayList<String> list = Main.getWordLadderBFS("start", "start");
        Assert.assertEquals(list.size(), new HashSet(list).size());
    }

    @Test(timeout = 30000)
    public void testBFSNoDuplicates2() {
        ArrayList<String> list = Main.getWordLadderBFS("bazoo", "habit");
        Assert.assertEquals(list.size(), new HashSet(list).size());
    }

    @Test(timeout = 30000)
    public void testBFSNoDuplicates3() {
        ArrayList<String> list = Main.getWordLadderBFS("smart", "money");
        Assert.assertEquals(list.size(), new HashSet(list).size());
    }

    @Test(timeout = 30000)
    public void testBFSNoDuplicates4() {
        ArrayList<String> list = Main.getWordLadderBFS("stone", "money");
        Assert.assertEquals(list.size(), new HashSet(list).size());
    }

    @Test(timeout = 30000)
    public void testDFSNoDuplicates1() {
        ArrayList<String> list = Main.getWordLadderDFS("start", "start");
        Assert.assertEquals(list.size(), new HashSet(list).size());
    }

    @Test(timeout = 30000)
    public void testDFSNoDuplicates2() {
        ArrayList<String> list = Main.getWordLadderDFS("bazoo", "habit");
        Assert.assertEquals(list.size(), new HashSet(list).size());
    }

    @Test(timeout = 30000)
    public void testDFSNoDuplicates3() {
        ArrayList<String> list = Main.getWordLadderDFS("smart", "money");
        Assert.assertEquals(list.size(), new HashSet(list).size());
    }

    @Test(timeout = 30000)
    public void testDFSNoDuplicates4() {
        ArrayList<String> list = Main.getWordLadderDFS("stone", "money");
        Assert.assertEquals(list.size(), new HashSet(list).size());
    }
}

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
import org.junit.Test;


import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by Eric on 9/27/16.
 */
public class UnitTest {

    @Test(timeout = 30000)
    public void testParse() {
        Main.initialize();
        int x = 0;
    }

    @Test(timeout = 30000)
    public void testGetWordLadderDFS() {
        Main.initialize();
        ArrayList<String> ladder = Main.getWordLadderDFS("smart", "stars");
    }

    @Test(timeout = 30000)
    public void testCountDiff() {
        Assert.assertEquals(2, Main.getDifference("apple", "apppp"));
        Assert.assertEquals(5, Main.getDifference("11111", "apppp"));
        Assert.assertEquals(1, Main.getDifference("count", "cbunt"));
        Assert.assertEquals(4, Main.getDifference("apple", "sspss"));
        Assert.assertEquals(5, Main.getDifference("apple", "sssss"));
        Assert.assertEquals(3, Main.getDifference("apple", "apsss"));
    }

    @Test
    public void testFilter() {
        Assert.assertArrayEquals(new String[]{"apple", "appll"}, Main.filterOutMoreDifferentiatedStrings("apple", new String[]{"apppp", "apple", "appll"}, 1));
        Assert.assertArrayEquals(new String[]{"apppp", "apple", "appll"}, Main.filterOutMoreDifferentiatedStrings("apple", new String[]{"apppp", "apple", "appll"}, 2));
        Assert.assertArrayEquals(new String[]{"apsss"}, Main.filterOutMoreDifferentiatedStrings("apple", new String[]{"sspss", "sssss", "apsss"}, 3));
    }

    @Test
    public void testBFSAndDFS(){
    	Random randomGenerator = new Random();
    	Set<String> dict = Main.makeDictionary();
    	Main.initialize();
    	String[] dictString =dict.toArray(new String[dict.size()]);
    	int BFSNullCounter=0;
    	int DFSNullCounter=0;
    	for(int i=0; i<30; i++){
        	int i1 = randomGenerator.nextInt(dictString.length);
        	int i2 = randomGenerator.nextInt(dictString.length);
        	String word1 = dictString[i1].toLowerCase();
        	String word2 = dictString[i2].toLowerCase();
        	System.out.println(word1 +"   "+ word2);
        	if(Main.getWordLadderBFS(word1,word2)==null)
        		BFSNullCounter++;
        	if(Main.getWordLadderDFS(word1,word2)==null)
        		DFSNullCounter++;
    	}
    	System.out.println("BFS null times: "+BFSNullCounter +". DFS null times: "+DFSNullCounter+".");
    	assertEquals(BFSNullCounter,DFSNullCounter);
    }
    
}

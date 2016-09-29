/* WORD LADDER UnitTest.java
 * EE422C Project 3 submission by
 * Replace <...> with your actual data.
 * Xiaoyong Liang
 * XL5432
 * 16480
 * Yuankai Yue
 * <Student2 EID>
 * <Student2 5-digit Unique No.>
 * Slip days used: <0>
 * Git URL:
 * Fall 2016
 */
package assignment3;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

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
        Assert.assertFalse(ladder.size() == 0);
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
        //Assert.assertArrayEquals(new String[]{"apple", "appll"}, Main.filterOutMoreDifferentiatedStrings("apple", new String[]{"apppp", "apple", "appll"}, 1));
        //Assert.assertArrayEquals(new String[]{"apppp", "apple", "appll"}, Main.filterOutMoreDifferentiatedStrings("apple", new String[]{"apppp", "apple", "appll"}, 2));
        //Assert.assertArrayEquals(new String[]{"apsss"}, Main.filterOutMoreDifferentiatedStrings("apple", new String[]{"sspss", "sssss", "apsss"}, 3));
    }
}

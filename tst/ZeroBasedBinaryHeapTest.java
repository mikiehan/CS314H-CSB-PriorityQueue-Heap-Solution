import genericheap.ZeroBasedBinaryHeap;
import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

/**
 * Tests ZeroBasedBinaryHeap with Integers
 */
public class ZeroBasedBinaryHeapTest {
    //get random based on numItems except 0
    private static int getRandomData(int numItems) {
        Random r = new Random();
        int data = r.nextInt() % numItems + 1;
        while (data == 0) {
            data = r.nextInt() % numItems + 1;
        }
        return data;
    }

    //If both tests fail, then need to fix insert method first!

    @Test
    public void insertTest() {
        int numItems = 100;
        ZeroBasedBinaryHeap<Integer> h = new ZeroBasedBinaryHeap<>();
        Comparable[] intArray = h.getArray();
        for (int i = 0; i < numItems; i++) {
            h.insert(getRandomData(numItems));
            Assert.assertTrue(h.toString() + "is incorrect", h.isCorrect());
        }
    }

    @Test
    public void deleteMinTest() {
        int numItems = 100;
        ZeroBasedBinaryHeap<Integer> h = new ZeroBasedBinaryHeap<>();
        for (int i = 0; i < numItems; i++) {
            h.insert(getRandomData(numItems));
        }
        Comparable[] h_array = h.getArray();
        for (int i = 0; i < numItems; i++) {
            int expected_min = (int) h_array[0];
            Assert.assertTrue(h.getCurrentSize() == numItems - i);
            int real_min = (int) h.deleteMin();
            Assert.assertTrue("expected " + expected_min + "real min " + real_min, expected_min == real_min);
            Assert.assertTrue(h.toString() + "is incorrect when i is " + i, h.isCorrect());
        }
        Assert.assertTrue(h.getCurrentSize() == 0);
    }

}

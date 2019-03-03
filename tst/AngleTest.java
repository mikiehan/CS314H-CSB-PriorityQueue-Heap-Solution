import genericheap.Angle;
import genericheap.ZeroBasedBinaryHeap;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests ZeroBasedBinaryHeap with Angle objects
 */
public class AngleTest {
    @Test
    public void testAngleHeap() {
        Angle[] angleArray = {new Angle(20, 26),
                new Angle(10, 48),
                new Angle(30, 19),
                new Angle(30, 12),
                new Angle(30, 55)};
        int numItems = 100;
        ZeroBasedBinaryHeap<Angle> h = new ZeroBasedBinaryHeap<>(angleArray, 10);
        h.insert(new Angle(15, 15));
        Assert.assertTrue(((Angle) h.getElement(2)).equals(new Angle(15, 15)));
        h.deleteMin();
        Assert.assertTrue(((Angle) h.getElement(0)).equals(new Angle(15, 15)));
    }
}

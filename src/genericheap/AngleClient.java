package genericheap;

// Short client program to test the Angle class.  It constructs two specific
// angles and their sum and then adds several more angles and sorts and prints
// the list.

public class AngleClient {

    // Test program
    public static void main(String[] args) {
        Angle[] angleArray = {new Angle(20, 26), new Angle(10, 48), new Angle(30, 19),
                new Angle(30, 12),
                new Angle(30, 55)};

        System.out.println(angleArray);
        int numItems = 10000;
        OneBasedBinaryHeap<Angle> h = new OneBasedBinaryHeap<>(angleArray);

        h.insert(new Angle(15, 100));
        Angle a = h.deleteMin();
    }
}
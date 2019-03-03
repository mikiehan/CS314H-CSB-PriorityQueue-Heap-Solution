package intheap;// IntBinaryHeap class
//
// CONSTRUCTION: with optional capacity (that defaults to 100)
//               or an array containing initial items
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// Comparable deleteMin( )--> Return and remove smallest item
// Comparable findMin( )  --> Return smallest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// ******************ERRORS********************************
// Throws UnderflowException as appropriate

import utils.UnderflowException;

/**
 * Implements a binary heap.
 * @author Mikyung Han
 * @version SP 2017
 */
public class IntBinaryHeap
{
    private static final int DEFAULT_CAPACITY = 100;
    private int currentSize;      // Number of elements in heap
    private int [ ] array; // The heap array

    /**
     * Construct the binary heap.
     * @param capacity the capacity of the binary heap.
     */
    public IntBinaryHeap(int capacity )
    {
        currentSize = 0;
        array = new int[ capacity ];
    }

    /**
     * Construct the binary heap given an array of items.
     */
    public IntBinaryHeap(int[ ] items , int capacity)
    {
        currentSize = items.length;
        array = new int[ capacity ];
        int i = 0;
        for( int item : items )
            array[ i++ ] = item;
        buildHeap( );
    }

    /**
     * Establish heap order property from an arbitrary
     * arrangement of items. Runs in linear time.
     */
    private void buildHeap( )
    {
        for( int i = (currentSize + 1) / 2; i >= 0; i-- )
            percolateDown( i );
    }


    /**
     * Insert into the priority queue, maintaining heap order.
     * Duplicates are allowed.
     * @param data the item to insert.
     */
    public void insert( int data ) {
        if( currentSize == array.length - 1 )
            enlargeArray( array.length * 2 + 1 );

        // Percolate up
        int hole = currentSize++;
        array[hole] = data; //insert data

        percolateUp(hole);
    }

    /**
     * Find the smallest item in the priority queue.
     * @return the smallest item, or throw an UnderflowException if empty.
     */
    public int findMin( ) {
        if( isEmpty( ) )
            throw new UnderflowException( );
        //Implement this Method
        return array[ 0 ];
    }

    /**
     * Remove the smallest item from the priority queue.
     * @return the smallest item, or throw an UnderflowException if empty.
     */
    public int deleteMin( )
    {
        if( isEmpty( ) )
            throw new UnderflowException( );

        //Implement this Method
        int minItem = findMin( );
        array[ 0 ] = array[ currentSize - 1 ];
        currentSize--;
        percolateDown( 0 );
        return minItem;
    }

    /**
     * Test if the priority queue is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty( )
    {
        //Implement this Method
        return currentSize == 0;
    }

    /**
     * Internal method to percolate down in the heap.
     * @param hole the index at which the percolate begins.
     */
    private void percolateDown( int hole )
    {
        //Implement your method
        int data = array[ hole ];
        int leftIndex = 2 * hole + 1;
        int rightIndex = 2 * (hole + 1);

        //find the correct hole
        while( (leftIndex < currentSize && data > array[leftIndex]) ||
                (rightIndex < currentSize && data > array[rightIndex] )) {
            int smallerIndex = leftIndex;
            if(rightIndex < currentSize && array[leftIndex] > array[rightIndex])
                smallerIndex = rightIndex;
            array[hole] = array[smallerIndex]; //swap

            //update indices
            hole = smallerIndex;
            leftIndex = 2 * hole + 1;
            rightIndex = 2 * (hole + 1);
        }

        //put data in the hole
        array[hole] = data;
    }

    /**
     * Internal method to percolate up in the heap.
     * @param hole the index at which the percolate begins.
     */
    private void percolateUp( int hole )
    {
        //Implement your method
        int data = array[ hole ];

        int parentIndex = (hole - 1) / 2;
        //find the correct hole
        while(hole > 0 && data < array[parentIndex] ) {
            array[hole] = array[parentIndex]; //swap

            //update indices
            hole = parentIndex;
            parentIndex = (hole - 1) / 2;
        }
        //place data in the hole
        array[hole] = data;
    }

    //------------- DO NOT CHANGE ANY METHODS BELOW THIS LINE -----------------//
    //------------- Below utility methods are provided for convenience -----------------//
    /**
     * Construct the binary heap.
     */
    public IntBinaryHeap( )
    {
        this( DEFAULT_CAPACITY );
    }


    private void enlargeArray( int newSize ) {
        int [] old = array;
        array = new int[ newSize ];
        for( int i = 0; i < old.length; i++ )
            array[ i ] = old[ i ];
    }

    /**
     * Make the priority queue logically empty.
     */
    public void makeEmpty( )
    {
        currentSize = 0;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int data : array) {
            sb.append(data + " ");
        }
        return sb.toString();
    }

    //------------- Below methods are only for JUnit testing -----------------//
    public boolean isCorrect(){
        //check whether it is a complete array
        for(int i = 0 ; i < currentSize ; i++ ) {
            if(array[i] == 0) return false;
        }
        //check whether a node is always smaller than both child
        for(int i = 0 ; i < currentSize; i++ ) {
            int leftChild = 2 * i + 1;
            int rightChild = 2 * (i + 1);
            if( (leftChild < currentSize && array[i] > array[leftChild]) ||
                    (rightChild < currentSize && array[i] > array[rightChild]))
                return false;
        }
        return true;
    }

    public int[] getArray(){
        return array;
    }

    public int getCurrentSize() {
        return currentSize;
    }
}

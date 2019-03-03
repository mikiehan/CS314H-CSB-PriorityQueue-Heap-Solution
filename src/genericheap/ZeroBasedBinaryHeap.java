package genericheap;// OneBasedBinaryHeap class

// OverallRoot is placed at index 0
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
 * Note that all "matching" is based on the compareTo method.
 * @author Mikie Han
 * @version SP 2017
 */
public class ZeroBasedBinaryHeap<AnyType extends Comparable<? super AnyType>>
{
    private static final int DEFAULT_CAPACITY = 10;

    private int currentSize;      // Number of elements in heap
    private AnyType [ ] array; // The heap array

    /**
     * Construct the binary heap.
     * @param capacity the capacity of the binary heap.
     */
    public ZeroBasedBinaryHeap(int capacity )
    {
        //Implement this method
        currentSize = 0;
        array = (AnyType[]) new Comparable [ capacity ];
    }

    /**
     * Construct the binary heap given an array of items.
     */
    public ZeroBasedBinaryHeap(AnyType [ ] items, int capacity)
    {
        //Implement this method
        //This method should use buildHeap helper method
        currentSize = items.length;
        array = (AnyType[]) new Comparable[ capacity ];

        int i = 0;
        for( AnyType item : items )
            array[ i++ ] = item;
        buildHeap( );
    }

    /**
     * Establish heap order property from an arbitrary
     * arrangement of items. Runs in linear time.
     */
    private void buildHeap( )
    {
        //Implement this method
        for( int i = (currentSize + 1) / 2; i >= 0; i-- )
            percolateDown( i );
    }


    /**
     * Insert into the priority queue, maintaining heap order.
     * Duplicates are allowed.
     * @param data the item to insert.
     */
    public void insert( AnyType data )
    {
        //Implement this method
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
    public AnyType findMin( )
    {
        if( isEmpty( ) )
            throw new UnderflowException( );
        //Implement this method
        return array[0];
    }

    /**
     * Remove the smallest item from the priority queue.
     * @return the smallest item, or throw an UnderflowException if empty.
     */
    public AnyType deleteMin( )
    {
        if( isEmpty( ) )
            throw new UnderflowException( );

        //Implement this method
        AnyType minItem = findMin( );
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
        //Implement this method
        return currentSize == 0;
    }

    /**
     * Internal method to percolate down in the heap.
     * @param hole the index at which the percolate begins.
     */
    private void percolateDown( int hole )
    {
        ///Implement this method
        AnyType data = array[ hole ];
        int leftIndex = 2 * hole + 1;
        int rightIndex = 2 * (hole + 1);

        //find the correct hole
        while( (leftIndex < currentSize && data.compareTo(array[leftIndex]) > 0) ||
                (rightIndex < currentSize && data.compareTo(array[rightIndex]) > 0)) {
            int smallerIndex = leftIndex;
            if(rightIndex < currentSize && array[leftIndex].compareTo(array[rightIndex]) > 0)
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
        AnyType data = array[ hole ];

        int parentIndex = (hole - 1) / 2;
        //find the correct hole
        while(hole > 0 && data.compareTo(array[parentIndex]) < 0 ) {
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
    public ZeroBasedBinaryHeap( )
    {
        this( DEFAULT_CAPACITY );
    }

    private void enlargeArray( int newSize )
    {
        AnyType [] old = array;
        array = (AnyType []) new Comparable[ newSize ];
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
        StringBuilder result = new StringBuilder( "[ " );

        for( AnyType obj : array )
            result.append( obj + " " );

        result.append( "]" );

        return result.toString( );
    }

    //------------- Below methods are only for JUnit testing -----------------//
    //Do not change this method
    //Used for JUnit testing
    public AnyType getElement(int index){
        return array[index];
    }

    //Do not change this method
    //Used for JUnit Testing
    public AnyType[] getArray(){
        return array;
    }

    public boolean isCorrect(){
        //check whether it is a complete array
        for(int i = 0 ; i < currentSize ; i++ ) {
            if(array[i] == null) return false;
        }
        //check whether a node is always smaller than both child
        for(int i = 0 ; i < currentSize; i++ ) {
            int leftChild = 2 * i + 1;
            int rightChild = 2 * (i + 1);
            if( (leftChild < currentSize && array[i].compareTo(array[leftChild]) > 0) ||
                    (rightChild < currentSize && array[i].compareTo(array[rightChild]) > 0 ))
                return false;
        }
        return true;
    }

    public int getCurrentSize() {
        return currentSize;
    }
}

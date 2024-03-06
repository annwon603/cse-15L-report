/*
   Name: Annie Wong
   Email: anw031@ucsd.edu
   PID: A18136256
   Sources used: Textbook
  
   This file contains my implementation of the
   min heap.
   
 */
import java.util.ArrayList;
import java.util.Collection;

/**
 * This class implements the methods for min heap
 * Reminder: compareTo(T o) returns 0 if both objects are equal, -1 if
 * this object is less than o, and 1 if this objecct is greater than o
 * 
 * Instance Variable:
 * 
 * protected ArrayList<E> data - The underlying data structure of MyMinHeap
 * private static final int divisor - for finding parent index
 */
public class MyMinHeap<E extends Comparable<E>> implements MinHeapInterface<E> {

    protected ArrayList<E> data;
    private static final int TWO = 2;
    /**
     * A constructor to initialize MyMinHeap
     */
    public MyMinHeap(){
        data = new ArrayList<>();
    }

    /**
     * Initializes a min-heap using the elements in
     * collection 
     * 
     * @param collection stores in data
     * @throws NullPointerException if collection or 
     * any element in collection is null
     */
    public MyMinHeap(Collection<? extends E> collection){
        //if collection is null or contains null, throw exception
        if(collection == null || collection.contains(null)){
            throw new NullPointerException();
        }else{
            data = new ArrayList<>(collection);

            for(int i = 0; i < size() - 1; i++){
                percolateDown(i);
            }
        }

    }

    /**
     * Swap the elements at 'from' and 'to' indices in data
     * 
     * @param from upper range index
     * @param to lower range index
     */
    protected void swap(int from, int to){
        E temp = data.get(from);
        data.set(from, data.get(to));
        data.set(to, temp);
    }

    /**
     * Returns the parent index of the given child's index
     * 
     * @param index children's index
     * @return parent's index of given node's index
     */
    protected static int getParentIdx(int index){
        int parentIdx = (index-1)/TWO;
        return parentIdx;
    }

    /**
     * Returns the left child index of the given node's index
     * 
     * @param index given node's index
     * @return left child index
     */
    protected static int getLeftChildIdx(int index){
        int leftChildIdx = index+(index+1);
        return leftChildIdx;
    }

    /**
     * Return the right child index of the given node's index
     * 
     * @param index given node's index
     * @return right child index
     */
    protected static int getRightChildIdx(int index){
        int rightChildIdx = index+(index+TWO);
        return rightChildIdx;
    }

    /**
     * Returns the index of the smallest child
     * with the given index of the current node
     * @param index - given node's index
     * @return - index of the smallest child, -1 if given node is leaf node
     */
    protected int getMinChildIdx(int index){
        int minChildIdx = index;
        //if it's a leaf node
        if(getLeftChildIdx(index) > size()-1 
            && getRightChildIdx(index) > size()-1){
            return -1;
        //if it's a leaf node
        //if the given node is a leaf node (no kids)
        }else if(data.get(getLeftChildIdx(index)) != null && 
            getRightChildIdx(index) > size()-1){
            return getLeftChildIdx(index);
        //if it's a leaf node
        }else if(data.get(getLeftChildIdx(index)) == null && 
            getRightChildIdx(index) > size()-1){
            return -1;
        //if the given node is a leaf node (no kids)
        }else if(data.get(getLeftChildIdx(index)) == null && 
            data.get(getRightChildIdx(index))==null){

            return -1;
        //if the given node only have one kid
        }else if(data.get(getLeftChildIdx(index)) != null && 
            data.get(getRightChildIdx(index))==null){

            return getLeftChildIdx(index);
        //if both child are equal
        }else if(data.get(getLeftChildIdx(index)) == 
            data.get(getRightChildIdx(index))){
            
            return getLeftChildIdx(index);
        }else{

            E left = data.get(getLeftChildIdx(index));
            E right =  data.get(getRightChildIdx(index));
            //if left is bigger than right
            if(left.compareTo(right) == 1){
                minChildIdx = getRightChildIdx(index);
            }else{
                minChildIdx = getLeftChildIdx(index);
            }
        }
        return minChildIdx;
    }

    /**
     * Percolate the element at index up until no heap 
     * properties are violated by this element
     * Meaning the property won't be voliated as long
     * this element's parent is less than the element
     * 
     * @param index given node's index
     */
    protected void percolateUp(int index){
        if(data.get(index).compareTo(data.get(getParentIdx(index))) == 0 ||
                data.get(index).compareTo(data.get(getParentIdx(index)))>0){
                
            return;
        }else{
            while(index > 0){
                //if parent and child share the same element or
                //if parent is less than the child

                //The base?
                if(data.get(index).compareTo
                    (data.get(getParentIdx(index))) == 0 ||
                        data.get(index).compareTo
                            (data.get(getParentIdx(index))) > 0){

                    return;
                }else{
                    swap(getParentIdx(index), index);
                    index = getParentIdx(index);
                
                }
            }
        }    
        
    }

    /**
     * Percolate the element at index down until no heap properties
     * are voilated by this element
     * Meaning the property won't be voliated as long
     * this element is less thaen its children
     * @param index given node's index
     */
    protected void percolateDown(int index){
        //while the node is not a leaf node
        while(index < data.size()-1 ){
            int minValue = getMinChildIdx(index);
            if(minValue == -1){
                return;
            }
            //if current node is equal to or less than 
            //its smaller child, then no swap
            else if(data.get(index).compareTo(data.get(minValue)) == 0 ||
                data.get(index).compareTo(data.get(minValue)) <= -1){
                return;
            //if current node is a leaf node, then stop
            }else{
                
                //swap current node with its smallest child
                swap(minValue, index);
                int areThereKids = minValue + minValue + 1;
                //determines which value to set index
                if(areThereKids > data.size()-1){
                    index  = getParentIdx(index);
                }else{
                    index = minValue;
                }
                
                
            }
        }

    }

    /**
     * Removes the element at index from data
     * and return it
     * @param index given node's index
     * @return the removed element
     */
    protected E deleteIndex(int index){
        E removed = data.get(index);
        //if we're removing the last element or a leaf node
        if(index == data.size()-1){
            data.remove(index);
        //otherwise replace the deleted element with last element in heap
        }else{
            swap(index, data.size()-1);
            data.remove(data.size()-1);
            //data.get(index) should contain the swapped value
            //if its a left node
            // if(getLeftChildIdx(index) > size()-1){
            //     return removed;
            // //if the swapped value is greater than its left child
            // }else if(data.get(index).compareTo
            //     (data.get(getLeftChildIdx(index))) > 0){
            //     percolateDown(index);
            // }else if(data.get(index).compareTo
            //     (data.get(getRightChildIdx(index))) > 0){
            //     percolateDown(index);
            // } else{
               
            // }
            percolateUp(index);
            percolateDown(index);
        }
        return removed;
    }

    /**
     * Add element to the end of the heap
     * 
     * @param element inserted element
     * @throws NullPointerException if element is null
     */
    @Override
    public void insert(E element) {
        if(element == null){
            throw new NullPointerException();
        }else{
            //it should add to the end of the arraylist
            data.add(element);
            //if the element is less than its parent's data
            if(element.compareTo(data.get(getParentIdx(size()-1))) < 0){
                percolateUp(size()-1);
            }
            
        }
    }

    /**
     * Return the root element of the heap
     * 
     * @return the min element otherwise null
     */
    @Override
    public E getMin() {
        if(data.size() == 0){
            return null;
        }else{
           
            return (E) data.get(0);
        }
       
    }

    /**
     * Remove and return the root
     * 
     * @return the deleted root otherwise null
     */
    @Override
    public E remove() {
        if(data.size() == 0){
            return null;
        }else{
            return (E) deleteIndex(0);
        }
    }

    /**
     * Return the number of elements in this min-heap
     * 
     * @return number of valid elements
     */
    @Override
    public int size() {
        return data.size();
    }


    /**
     * Clear out the entire heap
     */
    @Override
    public void clear() {
        data = new ArrayList<>();
        
    }

    
}
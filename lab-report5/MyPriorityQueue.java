/*
   Name: Annie Wong
   Email: anw031@ucsd.edu
   PID: A18136256
   Sources used: Textbook
  
   This file contains my implementation of the
   MyPriorityQueue.
   
 */

import java.util.Collection;
/**
 * This class contains methods for MyPriorityQueue
 * 
 * Instance Variable
 * 
 * protected MyMinHeap<E> heap- the ADT for MyPriorityQueue
 * it holds and sorts all elment for priority queue
 * null should not be allowed in this priority queue
 */

public class MyPriorityQueue<E extends Comparable<E>> {
    //Root node of the min-heap is one with the highest priority
    protected MyMinHeap<E> heap;

    /**
     * No argument constructor that initializes heap to be empty heap
     */
    public MyPriorityQueue(){
        heap = new MyMinHeap<>();
    }

    /**
     * Construtor that initializes the heap to contain the collection
     * 
     * @param collection - contains the elements
     * @throws NullPointerException if collection is null or 
     * contains any null elements
     */
    public MyPriorityQueue(Collection<? extends E> collection){
        if(collection == null || collection.contains(null)){
            throw new NullPointerException();
        }else{
            heap = new MyMinHeap<>(collection);
        }
    }

    /**
     * Adds an element to the queue
     * 
     * @param element the element to insert in
     * @throws NullPointerException if element is null
     */
    public void push(E element){
        if(element == null){
            throw new NullPointerException();
        }
        heap.insert(element);
    }

    /**
     * Peeks at the element with highest priority or in front
     * 
     * @return the root of the heap otherwise null
     */
    public E peek(){
        if(heap.size() == 0){
            return null;
        }else{
            return heap.getMin();
        }

    }

    /**
     * Removes and return the removed element with highest priority
     * 
     * @return the removed element
     */
    public E pop(){
        if(heap.size() == 0){
            return null;
        }else{
            return heap.remove();
        }
    }

    /**
     * Return the number of elements in priority queue
     * 
     * @return number of valid elements
     */
    public int getLength(){
        return heap.size();
    }

    /**
     * Clear out the entire queue
     */
    public void clear(){
        heap = new MyMinHeap<>();
    }
}

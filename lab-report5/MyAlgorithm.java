/*
   Name: Annie Wong
   Email: anw031@ucsd.edu
   PID: A18136256
   Sources used: Textbook
  
   This file contains the algorithm using MyPriorityQueue
   
 */
import java.util.ArrayList;

/**
 * This class conatins the method to run experiments
 * on a list of atoms
 */
public class MyAlgorithm{
    /**
     * It should return the type of the last remaining atom
     * @param atoms list of atoms
     * @return type of last remaining atom otherwise null
     */
    public static Integer lastAtom(ArrayList<Integer> atoms){
        //maybe should be sorted?
        MyPriorityQueue<Integer> queue = new MyPriorityQueue<>(atoms);
        ArrayList<Integer> sorted = new ArrayList<>();
        int counter = 0;
        int stop = queue.getLength();
        //while the queue have more than 1 element
        while(counter < stop-1){
            Integer firstAddend = queue.pop();
            Integer secondAddend =  queue.pop();
            if(firstAddend==secondAddend){
                Integer sum = firstAddend+secondAddend;
                queue.push(sum);
            }else{
                Integer difference = Math.abs(firstAddend - secondAddend);
                queue.push(difference);
            }
            counter++;
            
        }
        return queue.peek();
        
    }

}
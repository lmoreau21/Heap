package heap;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Heap {
    static int[] array;
    static int index = -1;
    static int finalOut = 10000000;
    
    public static void main(String[] args) throws FileNotFoundException {
        File doc = new File("inputFile.txt");
        Scanner scan = new Scanner(doc);
        int numOfInstruction = scan.nextInt();
        array = new int[numOfInstruction];
        String text;
        for(int i=0; i<numOfInstruction; i++){
            text = scan.next();
            if(text.contains("IN")){
                insert(scan.nextInt());
            }else if(text.contains("EM")){
                extractMin();
                
            }else if(text.contains("DK")){
                decreaseKey(index, scan.nextInt(), scan.nextInt());
            }
            System.out.println(Arrays.toString(array));
        }
        System.out.println(finalOut);
        scan.close();
    } 
    
    public static void insert(int element){
        index++;
        array[index] = element;
        floatUp(index); 
    }
    
    public static void extractMin(){
        finalOut = array[0];
        array[0] = array[index];
        array[index] = 0;
        index--;
        sinkDown(0, index);
    }
    
    public static void decreaseKey(int index, int numIndex, int newElement){
        if(numIndex < index){
            array[numIndex] = newElement;
            floatUp(numIndex);
            sinkDown(0, numIndex);
        }
    }
    
    public static void sinkDown(int root, int bottom){
        int minChild = (root*3) + 1;
        int otherChild = (root*3) + 2;
        if(minChild>bottom) return;
        if (minChild < bottom) {
            minChild = (array[minChild] < array[otherChild])? minChild:otherChild;
            if(otherChild < bottom && array[otherChild+1]!=0)
                minChild = (array[minChild] < array[otherChild+1])? minChild:(otherChild+1);
        }
        if(array[root] > array[minChild]){
            swap(root, minChild);
            sinkDown(root, minChild);
        }
    }
    
    public static void swap(int one, int two){
        int temp = array[one];
        array[one] = array[two];
        array[two] = temp;
    }
    
    public static void floatUp(int pos){
        int parent = (pos - 1)/3;
        if(array[parent]>array[pos]){
            swap(parent, pos);
            floatUp(parent);
        }
    }
}
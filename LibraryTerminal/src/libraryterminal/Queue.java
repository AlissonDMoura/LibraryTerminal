/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libraryterminal;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author aliss
 */
public class Queue {
    
    private QueueNode first;
    private QueueNode last;
    private int size;
    
    String[][] temp;
    
    public Queue(){
        this.first = null;
        this.last = null;
        this.size = 0;
    }
    //Constructor for a brand new Queue.
 
    
    public void Add(QueueNode data){
        QueueNode node = new QueueNode(data.toString());
        if (size == 0){
            last = node;
            first = node;
        size++;}else{
        //In case this is the list is empty
        
        node.setNext(first);
        first = node;
        size++;}
        //If it's the first element added, the first and the last are the same.
    }
    //Add a new element as first position
    
    
    private QueueNode NodePosition(int position){
       
        if(size <= 0 || position > (size - 1)){
            System.out.println("Invalid Position");
            return null;        }
        //Validates the position to avoid any pointer exception.
        
        QueueNode currentNode = first;
        int i = 0;
        
        while(i < position){
          i++;
          currentNode = currentNode.getNext();
          
        }
        
        return currentNode;
    }
    //Method to find a node whithin the position asked for.
    
    

    
    public String[][] QueueDetails() throws IOException{
        
            String[] temp;
            //Temporary array, Objective is to store an line from the Csv File.
            
            FileReader file = new FileReader("QUEUE.csv");
            BufferedReader br = new BufferedReader(file);
            
            FileReader file2 = new FileReader("QUEUE.csv");
            BufferedReader br2 = new BufferedReader(file2);
            String read = null;
            //Reader and string for read, data from the line in the csv file
            
            int size = 0;
            
            while(br.readLine() != null){
                size++;
            }
            
            String[][] TempArray = new String[size][];
            //count the size we need for our array of arrays from the number
            //of lines the csv document has, then create the array with the size
            
           
            //Resets the reader to the beggining of the document.
            int i = 0;
            
            while(i < size){
                
                
                temp = br2.readLine().split(",");
                //Stores a line in an temporary array.
                TempArray[i] = temp;
                //Stores the line array into the array of arrays.
                i++;                
            }
          
            
        return TempArray;
    }
      //This Method returns an array that contains all lines from the Csv file stored as arrays for further interaction.
    
    private String BookRemovalPosition() throws IOException{
        String[][] Qd = QueueDetails();
        String temp;
        int i = 0;
        
        
        while(i < Qd.length){
            
            
            if(Qd[i].length <= 1){
                
                //If the book is in the list and There is no
                temp = String.valueOf(i);
                return temp;}          
            //If the book Was found, Return the index value for it in the Array.
            i++;}
        
        
        return "Book Not in The list Or Book Contains Queue";
        }
    //This method checks in the Queue Details if and book is empty and returns the Position as an String.
    //String was chosen so an Error message could be used in the same method, to sabe code-lines.
    
    private void QueueCsvRemoveWrite() throws IOException{
        
        String position = BookRemovalPosition();
        int p = Integer.valueOf(position);
        
        String[][] Qd = QueueDetails();
        temp = new String[(Qd.length - 1)][2];
        int i = 0;
            
        while(i < Qd.length){
            
            if(i != p){
                
                temp[i][0] = Qd[i][0];
                temp[i][1] = Qd[i][1];
                
                //when I is different than p, It copies the array normally
                      }else{                
                try{
               temp[i][0] = Qd[i+1][0];
               temp[i][0] = Qd[i+1][0];
               i++;
               p++;
               //When i is equal to p, it writes the next and keep i equals to p until it finds an null exception
            }catch(ArrayIndexOutOfBoundsException e){
                System.out.println("End of bounds, New Array Ready. \n");         }
                
            }
                //It writes all in the array until the position is reached.
                //Once the position is reached, it ignores the position and writes the nexts.
        
    i++;}
        
        //The array is ready, It's time to rewrite the Queue:
        
         FileWriter fw = new FileWriter("QUEUE.csv", false);
         i = 0;
         while(i < temp.length){
             fw.append(temp[i][0] + ",");
             fw.append(temp[i][1] + "\n");
             i++;
         }
         fw.flush();
         fw.close();
         System.out.println("Queue Lists updated successfuly. \n");
        
        
        
    }
    //This method removes a book that doesn't have any Queue and rewrite the Queue File with updated information.
    
    public void RemoveReaderFromQueue(String BookId) throws FileNotFoundException, IOException{
        
         String[][] temp = QueueDetails();
         
         FileWriter fw = new FileWriter("QUEUE.csv", false);
         int i = 0;
         while(i < temp.length){
             
             if(temp[i][0].equals(BookId)){
             String y = temp[i][1];
             temp[i][1]= y.substring(0, y.length() -2);}
             //Remove the last two characters from the list (an space and the last User Id).
             i++;
             
             fw.append(temp[i][0] + ",");
             fw.append(temp[i][1] + "\n");             
         }
        fw.flush();
        fw.close();
        
        
        
        
    }
    
    public void QueueRefresh() throws IOException{
                
        try{
        QueueCsvRemoveWrite();}
        catch(NumberFormatException e){
            System.out.println("The list contains only books with Queue");
        }
    }
    //Encapsulation to just apply the private Method to refresh the Csv File.
    //Validates for Format exception that occurs when there is now book without queues.
    
    public String RemoveLast(){
        
        QueueNode temp = last;
        
        if(size == 0){
        return null; }
        //If it's empty there is no queue, returns null
        
        
        if(size == 1){
        last = null;
        first = null;
        size--;
        return temp.getData();        }
        //If there was one item, remove it and return the value of it.
        
        QueueNode secondLast = NodePosition(size - 2);
        
        secondLast.setNext(null);
        
        last = secondLast;
        size--;
        
        
    return temp.getData();
}
    //Removes the last node, and return the string equivalent to it.    
    
    public void AddToQueue(Queue queue, String ReaderId){
        QueueNode qn = new QueueNode(ReaderId);
        queue.Add(qn);
        
        
        
    }
    
    public int getSize() {
        return size;
    }
    
    
    
    
    
    
    
    
    
}

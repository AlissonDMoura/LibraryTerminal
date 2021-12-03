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
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author aliss
 */
public class BorrowData {
    
    private String transactionID;
    private String readerID;    
    private String bookID;
    
    private Date Drented;
    private String Srented;
    
    private Date Dreturned;
    private String Sreturned;
    
    private String Next;
    
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
    Date date = new Date();  
    
       
    public String getTransactionID() {
        return transactionID;
    }
    public String getReaderID() {
        return readerID;
    }
    public String getBookID() {
        return bookID;
    }
    
    

    public String getSrented() {
        return Srented;
    }

    public String getSreturned() {
        return Sreturned;
    }
    
    
    

    public void setTransactionID(String transactioID) {
        this.transactionID = transactioID;
    }
    public void setReaderID(String readerID) {
        this.readerID = readerID;
    }
    public void setBookID(String bookID) {
        this.bookID = bookID;
    }
    
    public void setSrented(String Srented) {
        this.Srented = Srented;
    }

    public void setSreturned(String Sreturned) {
        this.Sreturned = Sreturned;
    }
    
       
    
    
    public BorrowData[] Loadborrows() throws FileNotFoundException, IOException{
        FileReader file = new FileReader("BORROW_DATA.csv");
        BufferedReader br = new BufferedReader(file);
        String read = null;
        String[] data;
        BorrowData[] load = new BorrowData[200];
        int i = 0;
        
         while((read = br.readLine())!=null)
        {
            
        data = read.split(",");
        //Initializes the String array with all information in one line, splitting by line.
        
        //------------
        
        BorrowData borrow = new BorrowData();
        borrow.setTransactionID(data[0]);
        borrow.setReaderID(data[1]);
        borrow.setBookID(data[2]);
        borrow.setSrented(data[3]);
        //writes into an borrow all information stored from an line.
        
        try{
        borrow.setSreturned(data[4]);    
        } catch(ArrayIndexOutOfBoundsException a){
            System.out.println("Book Not returned");
            borrow.setSreturned("no");
            load[i] = borrow;
            return load;
            //In case the array doesn't have an 5th space, catch the exception and return the array we have so far.
        }
               
        load[i] = borrow;
        i++;    }
                
        return load;
    }
    //Load all borrowals from the BORROW_DATA csv file and stores into an array of BorrowData to further usage.
    
    public boolean BookIsBorrowed(BorrowData[] borrowlist, String bookId){
        
        int i = 0;
        while(i < borrowlist.length){
            System.out.println("Reading: " +borrowlist[i]);
            
            try{
                if(borrowlist[i].getBookID().equals(bookId) && borrowlist[i].getSreturned().equals("no")){
                    return true;
                }} catch(NullPointerException npe){
                    System.out.println("End of List");
                    return false;
            }
            i++;}
        
    return false;
    }
    // Check BORROW_DATA csv for an Id that contains the Id informed, return true if the book was found and has returned status as "no".   
    // Returns false if the book wasn't found or if the returned status is diferent than "no"
       
    public String NextTransaction(BorrowData bd) throws IOException{
        
        int i = 0;
        int next;
        
        BorrowData[] load = bd.Loadborrows();
        //Store the BorrowData array into an array, so It is read just once before the loop.
        
        while(i < load.length){
        System.out.println("reading " + load[i]);  
                
        try
            {
            if(!load[i].getTransactionID().isEmpty()){
                next = (Integer.valueOf(load[i].getTransactionID()) + 1);
                Next = String.valueOf(next);}                         
            //If the reading is not empty, Changes the string into an Int and stores in a int variable adding one into it, this would be the next transaction ID.
            }
        catch (NullPointerException npe){
            System.out.println("End Of List");
            //It will find the end of the list whenever it reaches a null pointer exception, this way we know the value next is the next ID in the list.
            return Next;
                                        }
        i++;
        
    }
        return Next;
        
        
        
    }
    //Checks the borrowal data file and return the next int value for a new transaction ID.
        
    public void WriteBorrowal(BorrowData bd, String ReaderId, String bookID) throws IOException{
        
        FileWriter fw = new FileWriter("BORROW_DATA.csv", true);
        fw.append(bd.NextTransaction(bd)+",");
        fw.append(ReaderId +",");
        fw.append(bookID +",");
        fw.append(formatter.format(date) + ",");
        fw.append("no \n");
        fw.flush();
        fw.close();    
        //Inserts the line into the next available line in the document, and saves it after flushing the stream.
            System.out.println("new Transaction Added successfully");
            //inform the user the transaction was successful.
        
    }
    //Writes a borrow into the Csv file.
    
    public void WriteReturn(Reader reader, Book[] BkList, BorrowData bd, BorrowData[] bdList, BookLoader Bl) throws IOException{
                
        FileWriter fw = new FileWriter("BORROW_DATA.csv", false);
        int i = 0;
        while(i < bdList.length ){
        fw.append(bdList[i].getTransactionID() +",");
        fw.append(bdList[i].getReaderID() +",");
        fw.append(bdList[i].getBookID() +",");
        fw.append(bdList[i].getSrented() + ",");
        
            if(!bdList[i].getSreturned().equals("no")){
                //If the value stored in Sreturned is not "no" it will be a date, meaning the book was returned, 
            fw.append(bdList[i].getSreturned() + "\n");}
            //knowing the book was returned, writes the date of return into the csv File.
            else{
                fw.append("no \n");                
                //knowing the book wasn't returned, append "no" into the return column.
            }
        }
        fw.flush();
        fw.close();    
        //Inserts the line into the next available line in the document, and saves it after flushing the stream.
            System.out.println("new Transaction Added successfully");
            //inform the user the transaction was successful.
        
    }
    //Overwrites the Borrowing file with the updated information in memory.
    
    public void ReturnBook(String bookId, BorrowData[] bdList){
        
        int i = 0;
        
        while(i < bdList.length){
        if(bdList[i].equals(bookId) && bdList[i].getSreturned().equals("no")){
           bdList[i].setSreturned(formatter.format(date));           
            System.out.println("Book written into Memory");
        }else{
        i++;}
        }
        
        
    }
    
    @Override
    public String toString() {
        return "BorrowData{" + "transactionID=" + transactionID + ", readerID=" + readerID +
               ", bookID=" + bookID + ", Srented=" + Srented + ", Sreturned=" + Sreturned + '}';
                
                }

}


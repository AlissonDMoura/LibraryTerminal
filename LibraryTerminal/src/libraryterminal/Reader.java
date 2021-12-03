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
public class Reader {
    
    private String Id;
    private String name;
    private String email;
    
    Queue q = new Queue();
    
    
    
    
    String Next;
    int next;

    public void setId(String Id) {
        this.Id = Id;
    }
       public String getId() {
        return Id;
    }

    public void setName(String name) {
        this.name = name;
    }
        public String getName() {
        return name;
    }

    public void setEmail(String email) {
        this.email = email;
    }
        public String getEmail() {
        return email;
    }
    
        
    public Queue ReaderQueue(String bookId) throws FileNotFoundException, IOException{
        FileReader file = new FileReader("QUEUE.csv");
        BufferedReader br = new BufferedReader(file);
        String read = null;
        String[] data;
        
        int i = 0;
        
        
               
        while((read = br.readLine()) != null){
        data = read.split(",");
        //Colect a line from the csv File, stores in an array.
        //Position zero will be the book Id and position 1 will be my Queue.
            String temp;
        try{
            temp = data[0];            
        }catch(ArrayIndexOutOfBoundsException e){
            //Sometimes the array might be empty, in that case,
            //The loop already went trought the list and If any value matches with
            //The book ID, we have our queue populated by now.
            System.out.println("End of List");            
            return q;
        }
        
        
            if(data[0].equals(bookId)){
                //If the book Id has a queue, We collect the queue and store in a array.
            System.out.println("BookId Has a Queue");
                
            String[] queue = data[1].split(" ");
             
               
                int j = 0;
                while(j < queue.length){
                QueueNode qn = new QueueNode(queue[j]);
                q.Add(qn);
                j++;                
                //populate my Queue with all nodes containing an String with the reader Id.
                }
              
            }
        
        
    }
        
     return q;   
        
    }
    //Method returns a NodeList for the specified book,
    //The first Item removed from that list will be the next person in line
    
    public Reader[] ReaderLoader() throws FileNotFoundException, IOException{
        FileReader file = new FileReader("READER_DATA.csv");
        BufferedReader br = new BufferedReader(file);
        String read = null;
        String[] data;
        Reader[] ReaderArray = new Reader[200];
        int i = 0;
        
         while((read = br.readLine())!=null)
        {
            
        data = read.split(",");
        //
        
        //------------
        Reader reader = new Reader();
        reader.setId(data[0]);
        reader.setName(data[1]);
        reader.setEmail(data[2]);
                
                
        ReaderArray[i] = reader;
        i++;
        
    }
        return ReaderArray;
    }
    //Writes all readers containing in the CSV file into a Reader array
    
    public boolean ReaderExists(Reader[] r, String name, String email) throws IOException{
               
        int i = 0;
        while(i < r.length){
          
        try
            {
            if(r[i].getName().equals(name) && r[i].getEmail().equals(email)){
                return true;
            }
                
            
            }
        catch (NullPointerException npe){
            System.out.println("End Of List");
            return false;
                                        }
        i++;
        
    }
        return false;
        
        
    }
    //Check the user list and return true if user already exists, or false if no user with combination name and email was found.
    
    public boolean ReaderExists(String id) throws IOException{
        Reader [] Rlist = ReaderLoader();
        
        int i = 0;
        while(i < Rlist.length){
          
        try
            {
            if(Rlist[i].getId().equals(id)){
                return true;
            }               
            }
        catch (NullPointerException npe){
            System.out.println("End Of List");
            return false;
                                        }
        i++;
        
    }
    
        
        
        return false;
    }
    // Verify if the reader Id informed is in READER_DATA vsc file, returns false if not found, or true if found.
    
    public String NextReader(Reader r) throws IOException{
        int i = 0;
        
        Reader[] readerList = r.ReaderLoader();
        
        
        while(i < readerList.length){
        System.out.println("reading " + readerList[i]);  
        
        
        try
            {
            if(!readerList[i].getId().isEmpty()){
                next = (Integer.valueOf(readerList[i].getId()) + 1);
                Next = String.valueOf(next);}                          
            }
        catch (NullPointerException npe){
            System.out.println("End Of List");
            return Next;
                                        }
        catch (NumberFormatException nfe){
            System.out.println("End Of List");
            return Next;
        }
        
        i++;
        
        
        
    }
        return Next;
    }
    //Reads The users Ids we have and return the next available ID.
    
    public void WriteReader(Reader r, String name, String email) throws FileNotFoundException, IOException{
        
        
        if(ReaderExists(ReaderLoader(), name, email)){
            System.out.println("The user already exists, Impossible to register");
        }else{
        
        FileWriter fw = new FileWriter("READER_DATA.csv", true);
        fw.append(r.NextReader(r)+",");
        fw.append(name+",");
        fw.append(email+"\n");
        fw.flush();
        fw.close();    
            System.out.println("new User Added successfully");
    }
    }
    //Writes information into the Reader File and goes to next line.
    
    public void ReaderDetailsById(String id) throws IOException{
        
        int pointer;
        
        try{
        pointer = Integer.valueOf(id);}
        catch(NumberFormatException e){
            
        }
        if(ReaderExists(id)){
            int i = Integer.valueOf(id) -1;
        Reader[] readerList = ReaderLoader();
        pointer = i;
            System.out.println("Reader Name: " + readerList[pointer].getName()+". \n"
                             + "Reader E-Mail "+ readerList[pointer].getEmail()+ ". \n"
                            +  "Reader Registered number: "+ readerList[pointer].getId() + ". \n");           
        }
        else{
            System.out.println("READER NOT FOUND, PLEASE CHECK YOUR TYPPING OR REGISTER.");
        }
        
    }
    //Writes all Readers Details Sorting it by ID.

    public String ReaderDetailsbyName(String name) throws IOException{
        
        
        String Statement = " **User not Found** ";
        int i = 0;
        Reader[] readerList = ReaderLoader();
        while(i < readerList.length){
            
            try{
            if(readerList[i].getName().equals(name)){
                
                Statement = "Reader Name: " + readerList[i].getName()+". \n"
                             + "Reader E-Mail "+ readerList[i].getEmail()+ ". \n"
                            +  "Reader Registered number: "+ readerList[i].getId() + ". \n";
                
                return Statement;
            }}
            catch(NullPointerException e){
                
            }
            
            
            i++;
        }
        
        
        
        return Statement;
    }
    //Check if the user is in the csv file, returns an String with different results depending on if the user was found or not.
    
    public void ListReaderByPosition(Reader reader) throws IOException {
    
        Reader[] Rl = ReaderLoader();
        
        int i = 0;
        while(i < Rl.length){
            
            try{
            System.out.println("Reader Name: " + Rl[i].getName()+". \n"
                             + "Reader E-Mail "+ Rl[i].getEmail()+ ". \n"
                            +  "Reader Registered number: "+ Rl[i].getId() + ". \n");}
            catch(NullPointerException e){
                                        
            }
            
            
            i++;
        }
        
        
    }
    //Prints all readers by position in the Csv file.

   public void ListReaderbyName(Reader reader) throws IOException {
       
        String tempId;
        String tempName;
        String tempEmail;
        
        Reader[] Rl = ReaderLoader();
        
        
        for (int i = 0; i < Rl.length; i++) {
            for (int j = i + 1; j < Rl.length; j++) {
                
                // to compare one string with other strings
                try{
                if (Rl[i].getName().compareTo(Rl[j].getName()) > 0) {
                    

                    // swapping
                    tempId = Rl[i].getId();
                    tempName = Rl[i].getName();
                    tempEmail = Rl[i].getEmail();
                    //Saves all information stored in Position i into an temporary array.
                    
                    Rl[i].setId(Rl[j].getId());
                    Rl[i].setName(Rl[j].getName());
                    Rl[i].setEmail(Rl[j].getEmail());
                    //Transfers all Strings from position j to Position i
                    
                    Rl[j].setId(tempId);
                    Rl[j].setName(tempName);
                    Rl[j].setEmail(tempEmail);
                    //Finishes the swap.
                }}
                catch(NullPointerException e){
                    
                }
                
            }
        }
        
        // print output array
        System.out.println(
            "-- ** LIST OF READERS IN ALPHABETICAL ORDER ** -- ");
        ListReaderByPosition(reader);
        
        
    }
    //Resorts the Reader array and print it, now in alphabetical order.
    
    public void AppendToQueue(String BookId, String UserId) throws IOException{
        
        String[][] Queue = q.QueueDetails();
        
        int i = 0;
        boolean oldQueue = false;
        
        
        
        
        while(i < Queue.length){
            if(Queue[i][0].equals(BookId)){
                Queue[i][1] += " "+UserId;
            oldQueue = true;}
            i++;}
        
        if(oldQueue){
            FileWriter fw = new FileWriter("QUEUE.csv", false);
            i = 0;
            while(i < Queue.length){
                fw.append(Queue[i][0] + ",");
                fw.append(Queue[i][1] + "\n");
                i++;            }
            fw.flush();
            fw.close();}
        //If This was a book already in the list, append the Reader to the queue.
        else{
            FileWriter fw = new FileWriter("QUEUE.csv", false);
            i = 0;
            while(i < Queue.length){
                fw.append(Queue[i][0] + ",");
                fw.append(Queue[i][1] + "\n");
                i++;}
                //Writes all the other Queues
                fw.append(BookId + ",");
                fw.append(UserId + " \n");
                //Writes a new Queue for the New book.
        }
                
    }
    //Appends user to an BookQueue, or create a new Queue for a book If there is none
        
    
    
    @Override
    public String toString() {
        return "Reader{" + "Id=" + Id + ", name=" + name + ", email=" + email + '}';
    }

   
    
    
    
    
    }

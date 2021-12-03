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
public class BookLoader {
    
    public Book[] FileLoader() throws FileNotFoundException, IOException{
        
        FileReader file = new FileReader("BOOK_DATA.csv");
        BufferedReader br = new BufferedReader(file);
        String read = null;
        String[] data;
        Book[] BookArray = new Book[501];
                
        int i=0;
        
          while((read = br.readLine())!=null)
        {
            
            
        data = read.split(",,");
        //
        
        //------------
        
        
        
        String id = data[0];
        String author_first_name = data[1];
        String author_last_name = data[2];
        String title = data[3];
        String genre = data[4];
        String borrowed = data[5];
        
        Book book = new Book(id, author_first_name,author_last_name,title, genre, borrowed );
        
        BookArray[i] = book;
        
        i++;
        
    }
        
    return BookArray;
}
    //Loads onto memory all books in the csv file, using an array of books.
        
    public void FileWriter(Book[] BookList) throws IOException{
        int i = 0;
        FileWriter fw = new FileWriter("TEST_DUMMY.csv", false);
        
        while(i < BookList.length){
            
            fw.append(BookList[i].getID() + ",,");
            fw.append(BookList[i].getAuthor_first_name() + ",,");
            fw.append(BookList[i].getAuthor_last_name() + ",,");
            fw.append(BookList[i].getTitle() + ",,");
            fw.append(BookList[i].getGenre() + ",,");
            fw.append(BookList[i].getBorrowed() + "\n");
            
           
            i++;
        }
            fw.flush();
            fw.close();
        
        
    }
    //write onto the csv file all information stored in the book array.
    
    public int BookPositionByTitle(String title) throws IOException{
        int i = 0;
        Book[] booklist = FileLoader();
        
        while(i < booklist.length){
            if(booklist[i].getTitle().equalsIgnoreCase(title)){
                return i;}
            i++;            
            
        }
                
        
        return 0;
    }
    //Verify for each book if the title given matches a book
    //and return the line number it seats on the array
    
    public int BookPositionById(String bookId) throws IOException{
        int i = 0;
        Book[] booklist = FileLoader();
        
        while(i < booklist.length){
            if(booklist[i].getID().equalsIgnoreCase(bookId)){
                return i;}
            i++;            
            
        }
        return 0;
        
    }
    //Verify for each book if the ID given matches a book
    //and return the line number it seats on the array
    
    public int BookPositionTitleAndAuthor(String title, String authorFname, String authorLname) throws IOException{
        
        int i = 0;
        Book[] booklist = FileLoader();
        
        while(i < booklist.length){
            if(booklist[i].getTitle().equalsIgnoreCase(title) && booklist[i].getAuthor_first_name().equals(authorFname) && booklist[i].getAuthor_last_name().equals(authorLname)){
                return i;}
            i++;            
            
        }
        return 0;
        
    }
    //checks author name and title, if the entry matches all strings, returns the book position in the Book array.
    
    public void ListBookPosition(Book[] bookList){
        
        System.out.println("LISTING ALL BOOKS IN MEMORY: \n");
        int i = 0;  
        while(i < bookList.length){
            System.out.println("Book Id: " + bookList[i].getID()+"\n"
                              +"Author First Name: "+ bookList[i].getAuthor_first_name() + "\n"
                              +"Author Last Name: "+ bookList[i].getAuthor_last_name() + "\n"
                              +"Title: " + bookList[i].getTitle() + "\n"
                              +"Genre: " + bookList[i].getGenre() + "\n"
                              +"Book is Borrowed status: " +bookList[i].getBorrowed()+"\n");
            i++;
        }
        
        
        
        System.out.println("----** End of List **----");
        
        

}
    //List all books in memory to the terminal, with all its details
    
    public void ListBookAuthor(Book[] bookList){
        String tempId;
        String tempFirstName;
        String tempLastName;
        String tempTitle;
        String tempGenre;
        String tempBorrowed;
        
        
        
        for (int i = 0; i < bookList.length; i++) {
            for (int j = i + 1; j < bookList.length; j++) {
                
                // to compare one string with other strings
                if (bookList[i].getAuthor_first_name().compareTo(bookList[j].getAuthor_first_name()) > 0) {
                    

                    // swapping
                    tempId = bookList[i].getID();
                    tempFirstName = bookList[i].getAuthor_first_name();
                    tempLastName = bookList[i].getAuthor_last_name();
                    tempTitle = bookList[i].getTitle();
                    tempGenre = bookList[i].getGenre();
                    tempBorrowed = bookList[i].getBorrowed();
                    //Saves all information stored in Position i into an temporary array.
                    
                    bookList[i].setID(bookList[j].getID());
                    bookList[i].setAuthor_first_name(bookList[j].getAuthor_first_name());
                    bookList[i].setAuthor_last_name(bookList[j].getAuthor_last_name());
                    bookList[i].setTitle(bookList[j].getTitle());
                    bookList[i].setGenre(bookList[j].getGenre());
                    bookList[i].setBorrowed(bookList[j].getBorrowed());
                    //Transfers all Strings from position j to Position i
                    
                    bookList[j].setID(tempId);
                    bookList[j].setAuthor_first_name(tempFirstName);
                    bookList[j].setAuthor_last_name(tempLastName);
                    bookList[j].setTitle(tempTitle);
                    bookList[j].setGenre(tempGenre);
                    bookList[j].setBorrowed(tempBorrowed);
                    //Finishes the swap.
                }
            }
        }
        
        // print output array
        System.out.println(
            "-- ** LIST OF BOOKS IN AUTHORS ALPHABETICAL ORDER ** -- ");
            ListBookPosition(bookList);
            
        
    
    }
    //Reposition books in author first name alphabetical order and displays the array using the ListBookPosition Method.
    
    public void ListBookTitle(Book[] bookList){
        String tempId;
        String tempFirstName;
        String tempLastName;
        String tempTitle;
        String tempGenre;
        String tempBorrowed;
        
        
        
        for (int i = 0; i < bookList.length; i++) {
            for (int j = i + 1; j < bookList.length; j++) {
                
                // to compare one string with other strings
                if (bookList[i].getTitle().compareTo(bookList[j].getTitle()) > 0) {
                    

                    // swapping
                    tempId = bookList[i].getID();
                    tempFirstName = bookList[i].getAuthor_first_name();
                    tempLastName = bookList[i].getAuthor_last_name();
                    tempTitle = bookList[i].getTitle();
                    tempGenre = bookList[i].getGenre();
                    tempBorrowed = bookList[i].getBorrowed();
                    //Saves all information stored in Position i into an temporary array.
                    
                    bookList[i].setID(bookList[j].getID());
                    bookList[i].setAuthor_first_name(bookList[j].getAuthor_first_name());
                    bookList[i].setAuthor_last_name(bookList[j].getAuthor_last_name());
                    bookList[i].setTitle(bookList[j].getTitle());
                    bookList[i].setGenre(bookList[j].getGenre());
                    bookList[i].setBorrowed(bookList[j].getBorrowed());
                    //Transfers all Strings from position j to Position i
                    
                    bookList[j].setID(tempId);
                    bookList[j].setAuthor_first_name(tempFirstName);
                    bookList[j].setAuthor_last_name(tempLastName);
                    bookList[j].setTitle(tempTitle);
                    bookList[j].setGenre(tempGenre);
                    bookList[j].setBorrowed(tempBorrowed);
                    //Finishes the swap.
                }
            }
        }
        
        // print output array
        System.out.println(
            "-- ** LIST OF BOOKS by Title in ALPHABETICAL ORDER ** -- ");
        ListBookPosition(bookList);
        
        
    }
    // List all books sorting by title in alphabetical order.
    
    
    
    public String BookIdByTitle(String BookTitle) throws IOException{
        int i = 0;
        Book[] booklist = FileLoader();
        
        while(i < booklist.length){
            if(booklist[i].getTitle().equalsIgnoreCase(BookTitle)){
                return booklist[i].getID();}
            i++;            
    }
    
        return "Book Not found";
}}
//Returns a BookId That contains the title informed.

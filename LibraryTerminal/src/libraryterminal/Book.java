/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libraryterminal;

/**
 *
 * @author aliss
 */
public class Book {
    
    private String ID;
    private String author_first_name;
    private String author_last_name;
    private String title;
    private String genre;
    private String borrowed;
    
    public Book(String id, String afn, String aln, String title, String genre, String borrowed){
        this.ID = id;
        this.author_first_name = afn;
        this.author_last_name = aln;
        this.title = title;
        this.genre = genre;
        this.borrowed = borrowed;
    }
    
    
    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }
    
    public void setID(String ID) {
        this.ID = ID;
    }
    public String getID() {
        return ID;
    }

    public void setAuthor_first_name(String author_first_name) {
        this.author_first_name = author_first_name;
    }
    public String getAuthor_first_name() {
        return author_first_name;
    }

    public void setAuthor_last_name(String author_last_name) {
        this.author_last_name = author_last_name;
    }
    public String getAuthor_last_name() {
        return author_last_name;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
    public String getGenre() {
        return genre;
    }

    public String getBorrowed() {
        return borrowed;}
    public void setBorrowed(String borrowed) {
        this.borrowed = borrowed;
    }
  

    @Override
    public String toString() {
        return "Book{" + "ID=" + ID + ", author_first_name=" + author_first_name + ", author_last_name=" + author_last_name + ", title=" + title + ", genre=" + genre + ", borrowed=" + borrowed + '}';
    }
    
    
    
    

    
  

    

    



    
    
    
    
}

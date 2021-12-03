/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libraryterminal;


import java.io.IOException;


/**
 *
 * @author aliss
 */
public class LibraryTerminal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        
        Interactor interactor = new Interactor();
        
        
        Reader reader = new Reader();
        
        BorrowData bd = new BorrowData();
        BorrowData[] bdList = bd.Loadborrows();
        BookLoader bl = new BookLoader();
        Book[] BkList = bl.FileLoader();
        
        interactor.Welcome(reader, BkList, bd, bdList, bl);
        
      
}
}
        
        
        
        
        
   
        
       
    


            
                                
        
        
        
        
    
    
    
    

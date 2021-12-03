/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libraryterminal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author aliss
 */
public class Interactor {
    private BufferedReader read = new BufferedReader(new InputStreamReader(System.in)); //reader used to collect user input.
    private String choice; //String to store buffered data to be used in other menus.
    private String buffer; //String to store buffered data to be used in other menus, secondary.
    private int menu; // integer stores the menu item chosen after parsed into int.
    
    
    
    
public void Welcome(Reader reader, Book[] BkList, BorrowData bd, BorrowData[] bdList, BookLoader Bl) throws IOException{
System.out.println
  ("*-------------------------------------------------------------------*\n"
 + "|                Welcome to the Easy Library System                 |\n"
 + "|         Please type the options in the interactive menu           |\n"
 + "|                                                                   |\n"
 + "|     1: Create new Reader                                          |\n"
 + "|     2: Borrowings                                                 |\n"
 + "|     3: Search                                                     |\n"
 + "|     4: Queues                                                     |\n"
 + "|     5: History                                                    |\n"
 + "|     6: ShutDown                                                   |\n"
 + "*-------------------------------------------------------------------*"                 
);       

choice = read.readLine();
System.out.println("You typed: " + choice);

            
        if(IsNumber(choice)){
            //If checks if the input can be converted into an int.
            menu = Integer.valueOf(choice);
            switch(menu){
                case 1:
                    System.out.println("You've selected: NEW READER\n ");
                    NewReader(reader, BkList, bd, bdList, Bl);
                    //case one informs the user the option selected and open the respective method/menu
                    break;
                case 2:
                    System.out.println("You've selected: BORROWINGS\n");
                    Borrowings(reader, BkList, bd, bdList, Bl);
                    //case two informs the user the option selected and open the respective method/menu
                    break;
                case 3:
                    System.out.println("You've selected: SEARCH");
                    SearchMenu(reader, BkList, bd, bdList, Bl);
                    //case three informs the user the option selected and open the respective method/menu
                    break;
                case 4:
                    System.out.println("You've selected: QUEUES");
                    Queues(reader, BkList, bd, bdList, Bl);
                    
                    //case four informs the user the option selected and open the respective method/menu
                    break;
                case 5:
                    System.out.println("You've selected HISTORY");
                    History(reader, BkList, bd, bdList, Bl);
                    
                    //case five informs the user the option selected and open the respective method/menu
                    break;
                    
                case 6:
                    System.out.println("Bye!!!");
                    System.exit(0);
                    
                    
                default: System.out.println("Please select a number from the menu\n");
                    Welcome(reader, BkList, bd, bdList, Bl);
                    //case default informs the user the option isn't one option and open this menu again.
            }
            } else{
            System.out.println("Just type the number, try again\n");
                    //End of if one, the input is not a number, opens this menu again.
            Welcome(reader, BkList, bd, bdList, Bl);}
            
            
    }
//dispays welcome message and instructs the user how to navigate through the Menu.
//each case sends the user to a new different menu with instructions
//Validate inputs if it's a number and if the number is in the menu.

///////////// FIRST MENU ITEM    
public void NewReader(Reader reader, Book[] BkList, BorrowData bd, BorrowData[] bdList, BookLoader Bl) throws IOException{
    System.out.println("What would you like to do?\n"
                     + "1: Go Back                \n"
                     + "2: Add new Reader         \n"); 

    choice = read.readLine();        
    System.out.println("You typed: " + choice);
    //Collect user input


    if(IsNumber(choice)){
        //If to check if the input was a number, If it was a number it continues
        menu = Integer.valueOf(choice);
        switch(menu){
            case 1:
                Welcome(reader, BkList, bd, bdList, Bl);
                //Goes back to the menu.
                break;
            case 2:
                System.out.println("Please type the reader name \n");
                 choice = read.readLine();        
                 System.out.println("You typed: " + choice);
                 reader.setName(choice);

                 System.out.println("Please type the reader email \n");
                 choice = read.readLine();        
                 System.out.println("You typed: " + choice +"\n");
                 reader.setEmail(choice);

                 System.out.println("Are these information correct? \n"
                                  + "Name: "+ reader.getName()+"    \n"
                                  + "Email:"+ reader.getEmail() + " \n"
                                  + "1: Yes                         \n"
                                  + "2: No                          \n");
                 choice = read.readLine();        
                 System.out.println("You typed: " + choice);

                                  if(IsNumber(choice)){
                                      //If to check if the input was a number, If it was a number it continues
                                  menu = Integer.valueOf(choice);
                                  switch(menu){
                                  case 1: 
                                      System.out.println("Attempting to add new User with new Id: "+ reader.NextReader(reader));
                                      reader.WriteReader(reader, reader.getName(), reader.getEmail());
                                      Welcome(reader, BkList, bd, bdList, Bl);
                                      //case one write the reader information into the csv file and sends the user back to the welcome menu.
                                  break;
                                  case 2: NewReader(reader, BkList, bd, bdList, Bl);                  
                                  //case two resets the method so the user can try again.
                                  break;
                                  default: System.out.println("Please select a number from the menu\n");
                                  NewReader(reader, BkList, bd, bdList, Bl);}//end of switch two, sends the user back to the previous menu in case default
                                       } 
                                  else{
                                  System.out.println("Just type the number, try again\n");
                                  NewReader(reader, BkList, bd, bdList, Bl);}
                                  //end if/else 2, for number typed, not a number sends the user back to the beggining of this method.

                  default: System.out.println("Please select a number from the menu\n");
                  NewReader(reader, BkList, bd, bdList, Bl);
                  //End of switch one, sends the user back in case default activated.
                          }
        } 
        else{
        System.out.println("Just type the number, try again \n");
        NewReader(reader, BkList, bd, bdList, Bl);}
    //end if/else 1, for number typed, not a number sends the user back to the beggining of this method.


}
//Interaction to add new user to the CSV readers file.
//Colects user name and email, checks for duplicates, and writes the csv file.
// also options to go back and confirm if information is correct before submit.
// validates data entered in the menu interaction is correct and asks for correction if not.

//////////// SECOND MENU ITEMS
public void Borrowings(Reader reader, Book[] BkList, BorrowData bd, BorrowData[] bdList, BookLoader Bl) throws IOException{
    System.out.println("What would you like to do?              \n"
                     + "1: Register a book to a user            \n"
                     + "2: Register a book back to the Shelves  \n"
                     + "3: Go back                              \n"); 

choice = read.readLine();        
System.out.println("You typed: " + choice);

if(IsNumber(choice)){
    //If checks if the value typed is an integer, if so, proceeds the menu interaction
    menu = Integer.valueOf(choice);
    switch(menu){
        case 1: 
            BookToUser(reader, BkList, bd, bdList, Bl);
            //Directs the user to the menu for registering  a book to an user.
            break;

        case 2:
            ReturnBook(reader, BkList, bd, bdList, Bl);
            //Directs the user to the method for returning a book to the shelves.
            break;

        case 3:
            Welcome(reader, BkList, bd, bdList, Bl);
            //Directs the user back to Main menu.
            break;
        
        default: System.out.println("Please select a number from the menu\n");
        Borrowings(reader, BkList, bd, bdList, Bl);}
        //end of switch two, sends the user back to the previous menu in case default
    }else{
        System.out.println("Just type the number, try again \n");
        Borrowings(reader, BkList, bd, bdList, Bl);}
        //end if/else, for number typed, not a number sends the user back to the beggining of this method.

}
//Menu directs the user to three options: Borrow a book to a reader, Return a book to the shelves or Go back to Main menu.
// Validates data entered in the menu interaction before proceeding, informing the user what was typed, so they can correct typping.

public void BookToUser(Reader reader, Book[] BkList, BorrowData bd, BorrowData[] bdList, BookLoader Bl) throws IOException{

        System.out.println("Please chose one option                 \n"
                         + "1: Book by Title                        \n"
                         + "2: Book by Id                           \n"
                         + "3: Go Back                              \n");

    choice = read.readLine();        
    System.out.println("You typed: " + choice);

    if(IsNumber(choice)){
        menu = Integer.valueOf(choice);
        //Check if the value informed is actualy an number to proceed with the menu.
        switch(menu){
            case 1: 
                System.out.println("Please inform the Title:");
                buffer = read.readLine();        
                System.out.println("You typed: " + buffer);

                System.out.println("is the title correct?           \n"
                                  + "Title: "+ buffer +"            \n"
                                  + "1: Yes                         \n"
                                  + "2: No                          \n");
                 choice = read.readLine();        
                 System.out.println("You typed: " + choice);

                                  if(IsNumber(choice)){
                                  menu = Integer.valueOf(choice);
                                  //Check if the value informed is actualy an number to proceed with the menu.
                                  switch(menu){
                                  case 1: 
                                      System.out.println("Checking the book in memory");
                                      if(bd.BookIsBorrowed(bdList, BkList[Bl.BookPositionByTitle(buffer)].getID())){
                                          System.out.println("This book Is rented at the momment                 \n"
                                                            +"Please try again Later or chose another book       \n"
                                                            +"If you want to be on queue, please go to main menu \n");
                                      BookToUser(reader, BkList, bd, bdList, Bl);                                  }
                                      //Verify if the book informed is rented, If so directs the user back to this method to chose more options
                                      else{                                              
                                          System.out.println("inform reader ID");
                                          choice = read.readLine();        
                                          System.out.println("Writting Borrowal");
                                          
                                          
                                          if(reader.ReaderExists(choice)){
                                              bd.WriteBorrowal(bd, choice, BkList[Bl.BookPositionByTitle(buffer)].getID()); // Write in BORROW_DATA.csv
                                              BkList[Bl.BookPositionByTitle(choice)].setBorrowed("yes"); // Modify the array Book[]
                                              Bl.FileWriter(BkList);//Write the modification on BOOK_LIST.csv
                                              
                                              Welcome(reader, BkList, bd, bdList, Bl);

                                          }else{
                                              System.out.println("This User Id isn't in our Database  \n"
                                                               + "Please Register in Main menu        \n"
                                                                +"Directing you to Main menu...         ");
                                              Welcome(reader, BkList, bd, bdList, Bl);  //Directs the User back to main menu
                                                }
                                          }//end Case 1
                                  break;

                                  case 2: System.out.println("Directing you back to the previous menu");
                                  BookToUser(reader, BkList, bd, bdList, Bl);                                        

                                  break;

                                  default: System.out.println("Please select a number from the menu\n");
                                  BookToUser(reader, BkList, bd, bdList, Bl);                                        
                                       }
                                  //End of switch 2
                                  }
                                  //If the number in the menu isn't a number, this else is reached.
                                  else{
                                  System.out.println("Just type the number, try again\n");
                                  BookToUser(reader, BkList, bd, bdList, Bl);  
                                  }
                                  break;

            case 2:
                System.out.println("Please try again. \n");
                BookToUser(reader, BkList, bd, bdList, Bl); 
                //Case the title informed is wrong, Directs the user back to try again.
            break;
                
            case 3:
                System.out.println("Directing user back to Main Menu");
                Welcome(reader, BkList, bd, bdList, Bl);

            default: System.out.println("Please select a number from the menu\n");
                     BookToUser(reader, BkList, bd, bdList, Bl);     }
        //End of Switch one

}// End of If one, ask the user to try again.
    else{
    System.out.println("Please select a number from the menu\n");
    BookToUser(reader, BkList, bd, bdList, Bl);

}
}
//Method Register a book to a user and uses uther methods to check data and write data into csv files for Borrowals and BookList.
   
public void ReturnBook(Reader reader, Book[] BkList, BorrowData bd, BorrowData[] bdList, BookLoader Bl) throws IOException{

    System.out.println("Please inform the book Title.");
    choice = read.readLine();        
    System.out.println("You typed: " + choice);

    int position = Bl.BookPositionByTitle(choice);
    if(position == 0){
        System.out.println("Book Not Found, Directing User to Borrowing Menu");
        Borrowings(reader, BkList, bd, bdList, Bl);}
    //For position zero, means no book was found with this Id.
    //Therefore the user will be sent back to previous Menu.
    
    buffer = BkList[position].getID();
    //Information validated, this is a real book Id.
    if(bd.BookIsBorrowed(bdList, buffer)){
        //If the book informed shows as borrowed in the BookList, proceeds the method.

        Queue qn = reader.ReaderQueue(buffer);
        //Loads queue to verify is there is any
        if(qn.getSize() != 0){
            //if The queue is zero, it transfers the book to the new Reader
            
            String nextReader = qn.RemoveLast();
            //remove user from the queue and saves it's ID to a String.
            qn.RemoveReaderFromQueue(buffer);
            //Remove the user from from Csv file
            qn.QueueRefresh();
            //Refresh Queue csv File.
            bd.WriteBorrowal(bd, nextReader, buffer);
            //append new Borrowal to the book
        }else{

        //If not queue, proceed here
        BkList[position].setBorrowed("no");
        //Apply the modification into the book Array as Not borrowed.
        bd.ReturnBook(buffer, bdList);
        //Apply the modification into the Memory array as Not borrowed
        bd.WriteReturn(reader, BkList, bd, bdList, Bl);
        //Overwrite the csv file with the updated information.

        System.out.println("Return successful, returning to Main menu...");
        Welcome(reader, BkList, bd, bdList, Bl);}

    }else{
        System.out.println("This book wasn't borrowed, Please check the information typed and try again. \n"
                        +  "You will be directed to Borrowing Menu...                                   \n");
        Borrowings(reader, BkList, bd, bdList, Bl);
        //in case the information was incorrect, the user will be directed to the borrowing menu.
    }

}
//Verify by book title the ids into csv files, modifies the files with new information and directs the user to the main menu
// --- to add: queue automatic borrow --- ( possible to send an e-mail to next user informing the book is available)
// validates the information and redirects the user to same menu if any typing error

//THIRD MENU ITEM

public void SearchMenu (Reader reader, Book[] BkList, BorrowData bd, BorrowData[] bdList, BookLoader Bl) throws IOException{
    
    System.out.println("Please select one option of the menu    \n"
                      +"1: Search a Book                        \n"
                      +"2: List Books                           \n"
                      +"3: Search a Reader                      \n"
                      +"4: List Readers                         \n"
                      +"5: Go Back                              \n");
    
    
    choice = read.readLine();        
    System.out.println("You typed: " + choice);
    //Collect user input


    if(IsNumber(choice)){
        //If to check if the input was a number, If it was a number it continues
        menu = Integer.valueOf(choice);
        switch(menu){
            
            case 1:
                SearchAbook(reader, BkList, bd, bdList, Bl);
                //Directs the user to the Search a book Menu.              
                break;
                
            case 2:
                ListBooks(reader, BkList, bd, bdList, Bl);
                //Directs the user to the List a book Menu.
                break;
            case 3:
                SearchAreader(reader, BkList, bd, bdList, Bl);
                //Directs the user to Single reader research
                
                break;
            case 4:
                ListReaders(reader, BkList, bd, bdList, Bl);
                //Directs the user to List readers
                break;
                
            case 5:
                Welcome(reader, BkList, bd, bdList, Bl);
                //Directs the user back to Main menu.
                                
            default:
                
                break;
            
            
        }//End of First switch
        
        }else{
        System.out.println("Just type the number, try again \n");
        SearchMenu(reader, BkList, bd, bdList, Bl);}
        //end if/else 1, for number typed, not a number sends the user back to the beggining of this method.
    
    
    
}
//Load SearchMenu        

public void SearchAbook(Reader reader, Book[] BkList, BorrowData bd, BorrowData[] bdList, BookLoader Bl) throws IOException{
    
    System.out.println("Would you like to search a book by: \n"
                     + "1: Search by title                  \n"
                     + "2: Search by Author and title       \n"
                     + "3: Go Back.                         \n");
    
    choice = read.readLine();        
    System.out.println("You typed: " + choice);
    //Collect user input


    if(IsNumber(choice)){
        //If to check if the input was a number, If it was a number it continues
        menu = Integer.valueOf(choice);
        switch(menu){
            
            case 1: System.out.println("Please type the book title \n");
            choice = read.readLine();        
            System.out.println("You typed: " + choice);
            //Collect user input
            
                System.out.println("---- This is your book details ---- \n" + BkList[Bl.BookPositionByTitle(choice)].toString());
                SearchMenu(reader, BkList, bd, bdList, Bl);
                //Displays the book details and return to search menu.
                
                break;
                
            case 2:
            System.out.println("Please type the book title \n");
            choice = read.readLine();        
            System.out.println("You typed: " + choice);
            
            System.out.println("Please type the author first name \n");
            buffer = read.readLine();        
            System.out.println("You typed: " + buffer);
            
            System.out.println("Please type the author last name \n");
            String temp = read.readLine();
            
                System.out.println("You Typed:                    \n"
                                +  "Book Title: "+ choice +"      \n"
                                +  "Author first name: "+ buffer+"\n"
                                +  "Author last name: "+ temp +  "\n" );
                int position = Bl.BookPositionTitleAndAuthor(choice, buffer, temp);
                //Checks if the book mathes any combination in the book array.
                //If it doesn't I will return a zero.
                if(position != 0){
                    System.out.println("---- This is your book details ---- \n" + BkList[position].toString());
                    SearchMenu(reader, BkList, bd, bdList, Bl);
                    //inform the user the book details, and redirect to Search menu.
                }else{
                    System.out.println("Book not found, redirecting you to the previous menu");
                    SearchAbook(reader, BkList, bd, bdList, Bl);
                    //inform the user the book wasn't found, and redirect to this menu
                }            
                break;
                
            case 3:
                SearchMenu(reader, BkList, bd, bdList, Bl);
                //Redirect the user back to the previous menu.
                break;
           
            default:
                System.out.println("This is not a menu option, try again \n");
                SearchAbook(reader, BkList, bd, bdList, Bl);
                
                break;
            
            
        }//End of First switch
        
        }else{
        System.out.println("Just type the number, try again \n");
       SearchAbook(reader, BkList, bd, bdList, Bl);}
        //end if/else 1, for number typed, not a number sends the user back to the beggining of this method.
    
    
    
}
//Searches a single book whether by title or a combination of title and author name.
//Writes the book details in the terminal if found.
//Validates users input.

public void ListBooks(Reader reader, Book[] BkList, BorrowData bd, BorrowData[] bdList, BookLoader Bl) throws IOException{
    
      System.out.println("Would you like to List all book by: \n"
                     + "1: Position in Memory                 \n"
                     + "2: Alphabetic Author's name           \n"
                     + "3: Alphabetic Title                   \n"
                     + "4: Go Back                            \n");
    
    choice = read.readLine();        
    System.out.println("You typed: " + choice);
    //Collect user input


    if(IsNumber(choice)){
        //If to check if the input was a number, If it was a number it continues
        menu = Integer.valueOf(choice);
        switch(menu){
            case 1:
                Bl.ListBookPosition(BkList);
                System.out.println("You are being redirected to Search Menu... \n");
                SearchMenu(reader, BkList, bd, bdList, Bl);
                //List all books in memory to the user and directs them back to the search menu
                break;
            
            case 2:
                Bl.ListBookAuthor(BkList);
                System.out.println("You are being redirected to Search Menu... \n");
                SearchMenu(reader, BkList, bd, bdList, Bl);
                //List all books in memory sorting by author's name and directs them back to the search menu               
                break;
                
            case 3:
                Bl.ListBookTitle(BkList);
                System.out.println("You are being redirected to Search Menu... \n");
                SearchMenu(reader, BkList, bd, bdList, Bl);
                
                break;
                
            case 4:
                SearchMenu(reader, BkList, bd, bdList, Bl);
                //Redirects user to the search menu.
                break;      
                
            default: System.out.println("Wrong number, please choose a number from the menu \n");    
            ListBooks(reader, BkList, bd, bdList, Bl);
                break;
        }
    
}else{
        System.out.println("Just type the number, try again \n");
       ListBooks(reader, BkList, bd, bdList, Bl);}
        //end if/else 1, for number typed, not a number sends the user back to the beggining of this method.
}
//Menu offers option to List all books in memory, To sort in alphabetical order and list, or by author's alphabetical order also.

public void SearchAreader(Reader reader, Book[] BkList, BorrowData bd, BorrowData[] bdList, BookLoader Bl) throws IOException{
    System.out.println("Would you like to search a Reader by: \n"
                     + "1: Search by id                       \n"
                     + "2: Search by name                     \n"
                     + "3: Go Back.                           \n");
    
    choice = read.readLine();        
    System.out.println("You typed: " + choice);
    //Collect user input


    if(IsNumber(choice)){
        //If to check if the input was a number, If it was a number it continues
        menu = Integer.valueOf(choice);
        switch(menu){
            
            case 1: System.out.println("Please type the reader ID \n");
            choice = read.readLine();        
            System.out.println("You typed: " + choice);
            //Collect user input
            Reader[] readerList = reader.ReaderLoader();
                System.out.println("---- This is your reader details ---- \n" );
                reader.ReaderDetailsById(choice);
                
                SearchAreader(reader, BkList, bd, bdList, Bl);
                //Displays the reader details and return to search menu.
                
                break;
                
            case 2:
            System.out.println("Please type the Name \n");
            choice = read.readLine();        
            System.out.println("You typed: " + choice);
            System.out.println(reader.ReaderDetailsbyName(choice));
            //Displays the reader details and return to search menu.
            SearchAreader(reader, BkList, bd, bdList, Bl);
            
                break;
                
            case 3:
                SearchMenu(reader, BkList, bd, bdList, Bl);
                //Redirect the user back to the previous menu.
                break;
           
            default:
                System.out.println("This is not a menu option, try again \n");
                SearchAbook(reader, BkList, bd, bdList, Bl);
                
                break;
            
            
        }//End of First switch
        
        }else{
        System.out.println("Just type the number, try again \n");
       SearchAbook(reader, BkList, bd, bdList, Bl);}
        //end if/else 1, for number typed, not a number sends the user back to the beggining of this method.
    
    
    
}
//Menu offers option to search a Reader by ID or by name.
//Validates the input and returns communication if any problem.

public void ListReaders(Reader reader, Book[] BkList, BorrowData bd, BorrowData[] bdList, BookLoader Bl) throws IOException{
    
     System.out.println("Would you like to List all readers by: \n"
                     + "1: ID                                   \n"
                     + "2: Name, Alphabetically                 \n"
                     + "3: Go Back                              \n");
    
    choice = read.readLine();        
    System.out.println("You typed: " + choice);
    //Collect user input


    if(IsNumber(choice)){
        //If to check if the input was a number, If it was a number it continues
        menu = Integer.valueOf(choice);
        switch(menu){
            case 1:
                reader.ListReaderByPosition(reader);
                System.out.println("You are being redirected to Search Menu... \n");
                SearchMenu(reader, BkList, bd, bdList, Bl);
                //List all books in memory to the user and directs them back to the search menu
                break;
            
            case 2:
                reader.ListReaderbyName(reader);
                System.out.println("You are being redirected to Search Menu... \n");
                SearchMenu(reader, BkList, bd, bdList, Bl);
                //List all books in memory sorting by author's name and directs them back to the search menu               
                break;
                
            case 3:
                System.out.println("Sending you back to Search Menu... \n");
                SearchMenu(reader, BkList, bd, bdList, Bl);
                
                //Redirects user to the search menu.
                break;      
                
            default: System.out.println("Wrong number, please choose a number from the menu \n");    
            ListReaders(reader, BkList, bd, bdList, Bl);
                break;
        }
    
}else{
        System.out.println("Just type the number, try again \n");
       ListReaders(reader, BkList, bd, bdList, Bl);}
        //end if/else 1, for number typed, not a number sends the user back to the beggining of this method.
    
    
}
//Load ListReader Menu

//FOURTH MENU ITEM
public void Queues(Reader reader, Book[] BkList, BorrowData bd, BorrowData[] bdList, BookLoader Bl) throws IOException{
    
    System.out.println("Welcome to the Queue menu, please choose the according options               \n"
                    +  "1: Enter in a Queue for a Book                                               \n"
                    +  "2: Check the next on queue for a book                                                  \n"
                    +  "3: Go back.                                                                  \n");
    
    choice = read.readLine();        
    System.out.println("You typed: " + choice);
    //Collect user input


    if(IsNumber(choice)){
        //If to check if the input was a number, If it was a number it continues
        menu = Integer.valueOf(choice);
        switch(menu){
    
            case 1:
                System.out.println("Please inform the Book title. \n");
                choice = read.readLine();        
                System.out.println("You typed: " + choice);
                buffer = choice;
                
                if(BkList[Bl.BookPositionByTitle(choice)].getBorrowed().equals("no")){
                    System.out.println("The book is not rented and doens't have a queue \n"
                                     + "You will be directed to the Borrowing menu... \n");
                    Borrowings(reader, BkList, bd, bdList, Bl);
                    //If the book found states as NOT rented, the user is directed to borrowal menu.
            }else{
                    if(BkList[Bl.BookPositionById(choice)].getBorrowed().equals("yes")){
                        System.out.println("Please inform the User who wants to enter in Queue for this book. \n");
                        choice = read.readLine();        
                        System.out.println("You typed: " + choice);
                        reader.AppendToQueue(buffer, choice);
                        System.out.println("Reader added, The book Queue for this book is "+ reader.ReaderQueue(buffer) + "\n");
                        System.out.println("You'll be redirected to Main Menu...");
                        Welcome(reader, BkList, bd, bdList, Bl);}
                        }      
                    
                break;
                
            case 2:
                System.out.println("Please inform the Book Title: ");
                choice = read.readLine();        
                System.out.println("You typed: " + choice);
                
                System.out.println(reader.ReaderQueue(Bl.BookIdByTitle(choice)).RemoveLast()+ "Is the Next in Line.");
                System.out.println("You're being redirected to Main menu...");
                Welcome(reader, BkList, bd, bdList, Bl);
                break;
                
            case 3:
                System.out.println("You're being redirected to Main menu...");
                Welcome(reader, BkList, bd, bdList, Bl);
                break;
                
            default: System.out.println("Wrong number, please choose a number from the menu \n");    
            Queues(reader, BkList, bd, bdList, Bl);
                break;
        }
}else{
        System.out.println("Just type the number, try again \n");
       Welcome(reader, BkList, bd, bdList, Bl);}
        //end if/else 1, for number typed, not a number sends the user back to the beggining of this method.
}
//Load Queues Menu

//FIFTH MENU ITEM
public void History(Reader reader, Book[] BkList, BorrowData bd, BorrowData[] bdList, BookLoader Bl) throws IOException{
    int i = 0;
            System.out.println("Would you like to List           \n"
                     + "1: All History                           \n"
                     + "2: Reader History                        \n"
                     + "3: Go Back                               \n");
    
    choice = read.readLine();        
    System.out.println("You typed: " + choice);
    //Collect user input
    
     if(IsNumber(choice)){
        //If to check if the input was a number, If it was a number it continues
        menu = Integer.valueOf(choice);
    switch(menu){
            case 1:
                
                
                System.out.println("Printing History List: \n \n");
                while(i< bdList.length){
                    try{
                    System.out.println("Transaction Id : " + bdList[i].getTransactionID());
                    System.out.println("Reader Id : " + bdList[i].getReaderID());
                    System.out.println("Date Rented : " + bdList[i].getSrented());
                    System.out.println("Date Returned : " + bdList[i].getSreturned());
                    System.out.println("Book Id: : " + bdList[i].getBookID());
                    System.out.println("---------------------------------------- \n");
                    i++;        }
                catch(NullPointerException e){
                System.out.println("END OF LIST, Redirecting user to Main Menu... \n \n \n");
                Welcome(reader, BkList, bd, bdList, Bl);    
                }}
                
                System.out.println("END OF LIST, Redirecting user to Main Menu... \n \n \n");
                Welcome(reader, BkList, bd, bdList, Bl);
                break;
                
            case 2:
                System.out.println("Please inform Reader Id... \n");
                choice = read.readLine();        
                System.out.println("You typed: " + choice);
                //Collect user input
                
                i = 0;
                System.out.println("Printing History List for userId:" + choice + " \n \n");
                while(i < bdList.length){
                    try{
                    if(bdList[i].getReaderID().equals(choice)){
                    System.out.println("Transaction Id : " + bdList[i].getTransactionID());
                    System.out.println("Reader Id : " + bdList[i].getReaderID());
                    System.out.println("Date Rented : " + bdList[i].getSrented());
                    System.out.println("Date Returned : " + bdList[i].getSreturned());
                    System.out.println("Book Id: : " + bdList[i].getBookID());
                    System.out.println("Transaction Id : " + Bl.BookIdByTitle(bdList[i].getBookID()));
                    System.out.println("---------------------------------------- \n");
                    }}
                    catch(NullPointerException e){
                        
                    }
                    
                    i++;
                }
                
                System.out.println("END OF LIST, Redirecting user to Main Menu... \n \n \n");
                Welcome(reader, BkList, bd, bdList, Bl);
                
                
                
                break;
                
                
            case 3:
                System.out.println("Redirecting user to Main Menu... \n \n \n");
                Welcome(reader, BkList, bd, bdList, Bl);
                break;
                
                
            default:     
            System.out.println("Wrong number, please choose a number from the menu \n");    
            History(reader, BkList, bd, bdList, Bl);
}
     }else{
        System.out.println("Just type the number, try again \n");
       History(reader, BkList, bd, bdList, Bl);}
        //end if/else 1, for number typed, not a number sends the user back to the beggining of this method.
}
//Load history Menu

//TOOL
public boolean IsNumber(String choice){
    
        if(choice.isEmpty()){
            return false;   }
        
        try{
        int i = Integer.parseInt(choice);    
} catch (NumberFormatException nfe) {
    return false;
}
    return true;       
        
    }
    //Checks if the String collected in Choice is a number.   
    
  


    
}


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
public class QueueNode {
    private String data;
    private QueueNode next;
    
    
    public QueueNode(String data){
        this.data = data;
        this.next = null;
        //Constructor of nodes for my Queue.
        //The next node has to be empty.
        
    }

    public String getData() {
        return data;
    }

    public QueueNode getNext() {
        return next;
    }

       public void setNext(QueueNode next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return data;
    }

   

        
}

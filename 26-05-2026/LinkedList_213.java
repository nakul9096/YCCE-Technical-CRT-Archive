class Node_213{
    int data;
    Node_213 next;
    Node_213(int data){
        this.data = data;
    }
}
public class LinkedList_213{
    public static void main(String[] args){
        Node_213 head = new Node_213(1);
        head.next = new Node_213(2);
        head.next.next = new Node_213(3);
        head.next.next.next=new Node_213(4);
        head.next.next.next.next = new Node_213(5);
        head.next.next.next.next.next=head.next;
        
        //print(head);
    }
    static void print(Node_213 head){
        Node_213 current = head;
        while(current!=null){
            System.out.println(current.data+" ");
            current=current.next;
        }
    }
}
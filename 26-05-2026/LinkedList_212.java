//CREATING A CUSTOM LINKED LIST MANUALLY
class Node{
    int data;
    Node next;
    Node(int data){
        this.data = data;
        this.next = null;
    }
}
public final class LinkedList_212{
    Node head;
    public void add(int data){
        Node newNode = new Node(data);
        if(head==null){
            head = newNode;
        }else{
            Node current = head;
            while(current.next!=null){
                current = current.next;
            }
            current.next = newNode;
        }
    }
    public static void main(String[] args){
        LinkedList_212 list = new LinkedList_212();
        list.add(10);
        list.add(30);
        list.add(50);
        list.add(70);
        list.add(90);
    }
}
//In a food delivery application customer orders are stored in a queue using a singly linked list each order contains orderid, customername and fooditems, perform following operations addneworder, cancelorderusingorderid, display all the orders & searchorderbyorderid.
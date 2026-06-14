import java.lang.Thread;
class Bus_1411 extends Thread{
    int available=5;
    int seats;
    Bus_1411(int a){
        seats = a;
    }
    public synchronized void bookTicket() {

        String name = Thread.currentThread().getName();

        if (available >= seats) {
            System.out.println(name + " Seats are booked.");

            available = available - seats;

            System.out.println("Remaining Seats : " + available);
        } else {
            System.out.println(name + " Sorry No Seats available.");
        }
        System.out.println();
    }

    @Override
    public void run() {
        bookTicket();
    }
}
public class MT_BUS_1411{
    public static void main(String[] args){
        Bus_1411 bus = new Bus_1411(2);
        Thread t1 = new Thread(bus);
        Thread t2 = new Thread(bus);
        Thread t3 = new Thread(bus);
        t1.setName("Nakul");
        t2.setName("Shantanu");
        t3.setName("Pawan");
        t1.start();
        t2.start();
        t3.start();
    }
}
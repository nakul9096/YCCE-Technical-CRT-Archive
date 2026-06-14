class Order {
    int orderId;
    String customerName;
    String foodItems;
    Order next;

    Order(int orderId, String customerName, String foodItems) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.foodItems = foodItems;
        this.next = null;
    }
}
class FoodDeliveryQueue {
    Order front = null;
    Order rear = null;
    void addNewOrder(int id, String name, String items) {
        Order newOrder = new Order(id, name, items);

        if (front == null) {
            front = rear = newOrder;
        } else {
            rear.next = newOrder;
            rear = newOrder;
        }        System.out.println("Order Added");
    }

    void cancelOrderUsingOrderId(int id) {
        if (front == null) {
            System.out.println("No Orders");
            return;
        }

        if (front.orderId == id) {
            front = front.next;

            if (front == null) {
                rear = null;
            }

            System.out.println("Order Cancelled");
            return;
        }

        Order temp = front;

        while (temp.next != null && temp.next.orderId != id) {
            temp = temp.next;
        }

        if (temp.next == null) {
            System.out.println("Order Not Found");
        } else {
            if (temp.next == rear) {
                rear = temp;
            }

            temp.next = temp.next.next;
            System.out.println("Order Cancelled");
        }
    }

    void displayAllOrders() {
        if (front == null) {
            System.out.println("No Orders");
            return;
        }

        Order temp = front;

        while (temp != null) {
            System.out.println("Order ID: " + temp.orderId);
            System.out.println("Customer Name: " + temp.customerName);
            System.out.println("Food Items: " + temp.foodItems);
            System.out.println();
            temp = temp.next;
        }
    }

    void searchOrderByOrderId(int id) {
        Order temp = front;

        while (temp != null) {
            if (temp.orderId == id) {
                System.out.println("Order Found");
                System.out.println("Order ID: " + temp.orderId);
                System.out.println("Customer Name: " + temp.customerName);
                System.out.println("Food Items: " + temp.foodItems);
                return;
            }

            temp = temp.next;
        }

        System.out.println("Order Not Found");
    }
}

public class FoodDeliveryApplication {
    public static void main(String[] args) {
        FoodDeliveryQueue q = new FoodDeliveryQueue();

        q.addNewOrder(101, "NAKUL DHANDE", "Pizza");
        q.addNewOrder(102, "ANSHUL", "Burger");
        q.addNewOrder(103, "RUPESH", "Pasta");

        System.out.println("\nAll Orders:");
        q.displayAllOrders();

        System.out.println("Search Order:");
        q.searchOrderByOrderId(102);

        System.out.println("\nCancel Order:");
        q.cancelOrderUsingOrderId(102);

        System.out.println("\nOrders After Cancellation:");
        q.displayAllOrders();
    }
}
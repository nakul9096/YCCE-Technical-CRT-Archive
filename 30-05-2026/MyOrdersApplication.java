//Orders sort ( java without predefine func and comments )
// e.g orderid=101, type=regular, ordervalue = 500, deliverytime.
//given 101 102 103 104 105, reg prime prime reg pri, 500 300 700 500 700, 0.5 0.3 0.4 0.2 0.2
//priority 1.type 2.orderval 3. dt
class Order {
    int id, value;
    String type;
    double deliveryTime;

    Order(int id, String type, int value, double deliveryTime) {
        this.id = id;
        this.type = type;
        this.value = value;
        this.deliveryTime = deliveryTime;
    }
}

public class MyOrdersApplication {
static boolean compare(Order a, Order b) {
        if(!a.type.equals(b.type))
            return a.type.equals("prime");

        if(a.value != b.value)
            return a.value > b.value;

        return a.deliveryTime < b.deliveryTime;
    }

    static void merge(Order orders[], int start, int mid, int end) {

        Order temp[] = new Order[10];

        int left = start;
        int right = mid + 1;
        int index = 0;

        while(left <= mid && right <= end)
            temp[index++] = compare(orders[left], orders[right]) 
                            ? orders[left++] 
                            : orders[right++];

        while(left <= mid)
            temp[index++] = orders[left++];

        while(right <= end)
            temp[index++] = orders[right++];

        for(left = start, index = 0; left <= end; left++, index++)
            orders[left] = temp[index];
    }

    static void mergeSort(Order orders[], int start, int end) {

        if(start < end) {

            int mid = (start + end) / 2;

            mergeSort(orders, start, mid);
            mergeSort(orders, mid + 1, end);

            merge(orders, start, mid, end);
        }
    }

    public static void main(String args[]) {

        Order orders[] = {
            new Order(101,"reg",500,0.5),
            new Order(102,"prime",300,0.3),
            new Order(103,"prime",700,0.4),
            new Order(104,"reg",500,0.2),
            new Order(105,"prime",700,0.2)
        };

        mergeSort(orders, 0, orders.length - 1);

        for(Order order : orders)
            System.out.println(order.id);
    }
}
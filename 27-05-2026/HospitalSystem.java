//Hospital Emergency Patient Management System
//PS: A hospital emergency system manages the systen using priority linked list
//Each patients contains 1) Patient_ID 2) Patient_Name 3) Severity Level ( critical , serious, normal ).4) Arrival time.
//Insert Patient According to severity priority: 1)
//Treat Highest priority patient 2)
//Search Patient 3)
//Display Waiting Patient 4) 
//Count total Patients 5)
//Reverse Patient List: 6)
//Detect cycle in the linkedlist 7)
import java.util.*;

class Patient {
    int id;
    String name, severity;
    Patient next;

    Patient(int id, String name, String severity) {
        this.id = id;
        this.name = name;
        this.severity = severity;
        this.next = null;
    }
}

public class HospitalSystem {

    static Patient head = null;

    static int priority(String s) {
        if (s.equalsIgnoreCase("critical"))
            return 1;
        else if (s.equalsIgnoreCase("serious"))
            return 2;
        else
            return 3;
    }

    static void insert(int id, String name, String severity) {

        Patient p = new Patient(id, name, severity);

        if (head == null || priority(severity) < priority(head.severity)) {
            p.next = head;
            head = p;
        } else {

            Patient t = head;

            while (t.next != null &&
                    priority(t.next.severity) <= priority(severity)) {
                t = t.next;
            }

            p.next = t.next;
            t.next = p;
        }

        System.out.println("Patient Added");
    }

    static void treat() {

        if (head == null) {
            System.out.println("No Patient");
            return;
        }

        System.out.println("Treated Patient: " + head.name);
        head = head.next;
    }

    static void search(int id) {

        Patient t = head;

        while (t != null) {

            if (t.id == id) {
                System.out.println("Patient Found");
                System.out.println(t.id + " " + t.name + " " + t.severity);
                return;
            }

            t = t.next;
        }

        System.out.println("Patient Not Found");
    }

    static void display() {

        if (head == null) {
            System.out.println("No Patients");
            return;
        }

        Patient t = head;

        while (t != null) {
            System.out.println(t.id + " " + t.name + " " + t.severity);
            t = t.next;
        }
    }

    static void count() {

        int c = 0;

        Patient t = head;

        while (t != null) {
            c++;
            t = t.next;
        }

        System.out.println("Total Patients: " + c);
    }

    static void detectCycle() {

        Patient slow = head;
        Patient fast = head;

        while (fast != null && fast.next != null) {

            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                System.out.println("Cycle Detected");
                return;
            }
        }

        System.out.println("No Cycle");
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("\n***** HOSPITAL MANAGEMENT SYSTEM *****");
            System.out.println("1. Insert Patient");
            System.out.println("2. Treat Highest Priority Patient");
            System.out.println("3. Search Patient");
            System.out.println("4. Display Waiting Patients");
            System.out.println("5. Count Total Patients");
            System.out.println("7. Detect Cycle");
            System.out.println("8. Exit");

            System.out.print("Enter Choice: ");
            int choice = scanner.nextInt();

            switch (choice) {

                case 1:

                    System.out.print("Enter Patient ID: ");
                    int id = scanner.nextInt();

                    scanner.nextLine();

                    System.out.print("Enter Patient Name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter Severity: ");
                    String severity = scanner.nextLine();

                    insert(id, name, severity);
                    break;

                case 2:
                    treat();
                    break;

                case 3:

                    System.out.print("Enter ID to Search: ");
                    int searchId = scanner.nextInt();

                    search(searchId);
                    break;

                case 4:
                    display();
                    break;

                case 5:
                    count();
                    break;

                case 7:
                    detectCycle();
                    break;

                case 8:
                    System.exit(0);

                default:
                    System.out.println("Invalid Choice");
            }
        }
    }
}
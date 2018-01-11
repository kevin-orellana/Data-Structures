import java.util.Scanner;

/**
 * Created by kfo21 on 6/15/2017.
 */
public class Main {
    public static void main(String args[]){
        LinkedQueue LQ = new LinkedQueue();
        LQ.enqueue("Stern");
        LQ.enqueue("CAS");
        LQ.enqueue("Grad");

        System.out.println(LQ);

    }
}

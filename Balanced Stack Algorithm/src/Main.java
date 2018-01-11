/**
 * Created by kfo21 on 6/7/2017.
 */

public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome to ScheduleGenerator 1.0!");
        System.out.println("Select a file to read from (1-7): ");

        Schedule tutoringSchedule = new Schedule();
        tutoringSchedule.processData();

    }
}

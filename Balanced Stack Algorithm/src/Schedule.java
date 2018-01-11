/**
 * Created by kfo21 on 6/7/2017.
 */

import File.File;
import balanced.Balanced;
import balanced.UnbalancedExpressionException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Schedule {
    File file;
    ArrayList<Student> students = new ArrayList<Student>();
    List[][] mainSchedule = new ArrayList[5][24];

    public Schedule(){
        file = new File();
    }

    public void processData(){
        try {
            String data = file.readData();
            if (!balancedData(data)) {
                throw new UnbalancedExpressionException("This file contains an unbalanced expression!");
            }
            else {
                interpretData(data);
                addStudentSchedules();
                printSchedule();
                analyzeSchedule();
            }
        }
        catch (UnbalancedExpressionException ex) {
        }
    }

    public boolean balancedData(String data){
        Balanced balanced = new Balanced("(<[",")>]");
        int result;
        result = balanced.test(data);
        return (result == 0);
    }

    public void interpretData(String data) {
        while (data.length() > 1) {
            int studentStartIndex;
            int studentEndIndex;

            studentStartIndex = data.indexOf("(");
            studentEndIndex = data.indexOf(")");

            String studentInformation = data.substring(studentStartIndex+1, studentEndIndex);
            Student tempStudent = new Student(studentInformation);

            students.add(tempStudent);

            data = data.substring(studentEndIndex + 1);
        }
    }

    public void addStudentSchedules(){
        for (int d = 0; d < this.mainSchedule.length; d++){
            for (int h = 0; h < this.mainSchedule[d].length; h++){
                if (this.mainSchedule[d][h] == null){
                    this.mainSchedule[d][h] = new ArrayList<String>();
                }
            }
        }
        for (Student s: students){
            String[][] tempSchedule = s.getSchedule();

            for (int d = 0; d < tempSchedule.length; d++){
                for (int h = 0; h < tempSchedule[d].length; h++){
                    if (tempSchedule[d][h] != null){
                        this.mainSchedule[d][h].add(s.getName());
                    }
                }
            }
        }
    }

    public void printSchedule(){
        System.out.println("------------------- 24 HOUR STUDENT AVAILABILITY  -------------------");
        System.out.println("DAY   | HOURS (0000-2300)");

        for (int d = 0; d < this.mainSchedule.length; d++){
            System.out.println("Day " + d + " | " + Arrays.toString(this.mainSchedule[d]));
        }
    }

    public void analyzeSchedule(){
        ArrayList<String> studentsCovered = new ArrayList<String>();
        ArrayList<String> studentsNotCovered = new ArrayList<String>();

        System.out.println("\n------ Ideal Time Slots ------ ");


        int[][] idealTimeSlots = new int[3][2];

        for (int sweep = 0; sweep < 3; sweep++){
            int maxStudents = 0;
            int[] idealTimeSlot = new int[2];
            for (int d = 0; d < this.mainSchedule.length; d++){
                for (int h = 0; h < this.mainSchedule[d].length; h++){
                    int numStudentsInSlot = this.mainSchedule[d][h].size();
                    if (numStudentsInSlot > maxStudents) {
                        idealTimeSlot[0] = d;
                        idealTimeSlot[1] = h;
                        maxStudents = numStudentsInSlot;
                    }
                }
            }
            String dayString = "";
            String hourString = Integer.toString(idealTimeSlot[1] * 100);
            switch (idealTimeSlot[0]) {
                case 0:
                    dayString = "Monday ";
                    break;
                case 1:
                    dayString = "Tuesday ";
                    break;
                case 2:
                    dayString = "Wednesday ";
                    break;
                case 3:
                    dayString = "Thursday ";
                    break;
                case 4:
                    dayString = "Friday ";
                    break;
            }

            System.out.println("Time Slot " + Integer.toString(sweep + 1)+ ": " + dayString + "at " + hourString);
            idealTimeSlots[sweep] = idealTimeSlot;
            for (Object s: this.mainSchedule[idealTimeSlot[0]][idealTimeSlot[1]]){
                studentsCovered.add(s.toString());
            }

            for (int d = 0; d < this.mainSchedule.length; d++){
                for (int h = 0; h < this.mainSchedule[d].length; h++){
                   this.mainSchedule[d][h].removeAll(studentsCovered);
                }
            }
        }

        for (int d = 0; d < this.mainSchedule.length; d++){
            for (int h = 0; h < this.mainSchedule[d].length; h++){
                for (Object s: this.mainSchedule[d][h]){
                    if (!studentsNotCovered.contains(s.toString())){
                        studentsNotCovered.add(s.toString());
                    }
                }
            }
        }
        System.out.println("\n------ Statistics ------");
        System.out.println("Students covered: " + studentsCovered);
        System.out.println("Students NOT covered: " + studentsNotCovered);
    }
}

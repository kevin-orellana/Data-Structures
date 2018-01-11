import java.lang.reflect.Array;

/**
 * Created by kfo21 on 6/8/2017.
 */
public class Student {

    String studentName;
    String[][] schedule = new String[5][24];
    boolean verbose = true;

    public Student(String studentData) {
        this.studentName = studentData.substring(0, studentData.indexOf("<"));
        String studentSchedule = studentData.substring(studentData.indexOf("<"));
        interpretData(studentSchedule);
    }


    public String getName(){
        return this.studentName;
    }

    public void interpretData(String schedule){
        String scheduleString = schedule;
        while (scheduleString.length() > 2) {

            int dayStartIndex = scheduleString.indexOf("<");
            int dayEndIndex = scheduleString.indexOf(">");

            int dayValue = 0;

            String dayValueString = scheduleString.substring(dayStartIndex + 1, dayStartIndex + 2);

            switch (dayValueString) {
                case "M":
                    dayValue = 0;
                    break;
                case "T":
                    dayValue = 1;
                    break;
                case "W":
                    dayValue = 2;
                    break;
                case "H":
                    dayValue = 3;
                    break;
                case "F":
                    dayValue = 4;
                    break;
            }

            String dayString = scheduleString.substring(dayStartIndex, dayEndIndex + 1);

            while (dayString.length() > 2){
                int hourStartIndex = dayString.indexOf("[");
                int hourEndIndex = dayString.indexOf("]");
                String hourString = (dayString.substring(hourStartIndex + 1, hourEndIndex));
                int hourValue = Integer.parseInt(hourString) / 100;
                addHour(dayValue, hourValue);
                dayString = dayString.substring(hourEndIndex + 1);
            }
            scheduleString = scheduleString.substring(dayEndIndex + 1);
        }
    }

    public void setStudentName(String input){
        this.studentName = input;
    }

    public void printStudentSchedule() {
        for (int d = 0; d < this.schedule.length; d++) {
            for (int h = 0; h < this.schedule[d].length; h++) {
                if (this.schedule[d][h] != null) {
                    System.out.println(this.schedule[d][h] + " Day: " + d + " Hour: " + h);
                }
            }
        }
    }

    public String[][] getSchedule(){
        return this.schedule;
    }

    public void addHour(int day, int hour){
        this.schedule[day][hour] = this.studentName;
    }
}

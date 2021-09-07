
import java.util.ArrayList;
import java.util.Scanner;

public class Validation {

    private final static Scanner in = new Scanner(System.in);

    //check user input number limit
    public static int checkInputINT(int min, int max) {
        while (true) {
            try {
                int result = Integer.parseInt(checkInputString()); //.trim() remove character space.
                if (result < min || result > max) { // user input number not into range min-max
                    System.err.println("Please input number in rage [" + min + ", " + max + "]");
                    System.out.print("Re-input: ");
                    continue;
                }
                return result;
            } catch (NumberFormatException e) { // user input "character-letter" or "1dfd"
                System.err.println("you must not input chracters. only number.");
                System.out.print("Re-input : ");
            }
        }
    }

    //check user input string
    public static String checkInputString() {
        //loop until user input correct
        while (true) {
            String result = in.nextLine().trim();
            if (result.isEmpty()) {
                System.err.println("Not empty");
                System.out.print("Enter again: ");
            } else {
                return result;
            }
        }
    }

    public static boolean getStudentExist(ArrayList<Student> ls, String id, String name) {
        for (Student l : ls) {
            if (id.equalsIgnoreCase(l.getId())
                    && name.equalsIgnoreCase(l.getStudentName())) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkInputYN() {
        //loop until user input correct
        while (true) {
            String result = checkInputString();
            //return true if user input y/Y
            if (result.equalsIgnoreCase("Y")) {
                return true;
            }
            //return false if user input n/N
            if (result.equalsIgnoreCase("N")) {
                return false;
            }
            System.err.println("Please input y/Y or n/N.");
            System.out.print("Enter again: ");
        }
    }

    //check user input u / d
    public static boolean checkInputUD() {
        //loop until user input correct
        while (true) {
            String result = checkInputString();
            //return true if user input u/U
            if (result.equalsIgnoreCase("U")) {
                return true;
            }
            //return false if user input d/D
            if (result.equalsIgnoreCase("D")) {
                return false;
            }
            System.err.println("Please input u/U or d/D.");
            System.out.print("Enter again: ");
        }
    }

    //check user input course
    public static String checkInputCourse() {
        //loop until user input correct
        while (true) {
            String result = checkInputString();
            //check input course in java/ .net/ c/c++
            if (result.equalsIgnoreCase("java")
                    || result.equalsIgnoreCase(".net")
                    || result.equalsIgnoreCase("c/c++")) {
                return result;
            }
            System.err.println("There are only three courses: Java, .Net, C/C++");
            System.out.print("Enter again: ");
        }
    }

    //check student exist
    public static boolean checkStudentExist(ArrayList<Student> ls, String id,
            String studentName, String semester, String courseName) {
        //int size = ls.size();
        for (Student student : ls) {
            if (id.equalsIgnoreCase(student.getId())
                    && studentName.equalsIgnoreCase(student.getStudentName())
                    && semester.equalsIgnoreCase(student.getSemester())
                    && courseName.equalsIgnoreCase(student.getCourseName())) {
                return false;
            }
        }
        return true;
    }

    //check report exist
    static boolean isReportExist(ArrayList<Report> lr, Report r) {
        for (Report report : lr) {
            if (r.getStudentName().equalsIgnoreCase(report.getStudentName())
                    && r.getCourseName().equalsIgnoreCase(report.getCourseName())) {
                return false;
            }
        }
        return true;
    }

    public static Student getStudentById(String id, ArrayList<Student> ls) {
        for (Student student : ls) {
            if (id.equalsIgnoreCase(student.getId())) {
                return student;
            }
        }
        return null;
    }

    //check id and exist
    public static boolean checkIdExist(ArrayList<Student> ls, String id) {
        for (Student student : ls) {
            if (id.equalsIgnoreCase(student.getId())) {
                return false;
            }
        }
        return true;
    }
}


import java.util.ArrayList;
import java.util.Collections;

public class Manager {

    public static int menu() {
        System.out.println(" 1.	Create");
        System.out.println(" 2.	Find and Sort");
        System.out.println(" 3.	Update/Delete");
        System.out.println(" 4.	Report");
        System.out.println(" 5.	Exit");
        System.out.print(" Enter your choice: ");
        int choice = Validation.checkInputINT(1, 5);
        return choice;
    }

    public static int createStudent(ArrayList<Student> ls, int count) {
        while (true) {
            System.out.println(count);
            //if number of students greater than 10 ask user continue or not
            if (count >= 10) {
                System.out.print("Do you want to continue (Y/N): ");
                if (Validation.checkInputYN() == false) {
                    return count; //output menu();
                }
            }
            String name = "";
            System.out.print("Enter id: ");
            String id = Validation.checkInputString();
            // Validation.checkIdExist == true --> ID not exist --> input name
            if (Validation.checkIdExist(ls, id) == true) {
                System.out.print("Enter name student: ");
                name = Validation.checkInputString();
            } else { // Validation.checkIdExist == false --> ID  exist -->  name = getStudentName();
                name = Validation.getStudentById(id, ls).getStudentName();
                System.out.println("Name Student: " + name);
            }
            System.out.print("Enter semester: ");
            String semester = Validation.checkInputString();
            System.out.print("Enter name course: ");
            String course = Validation.checkInputCourse();
            //check student exist or not
            if (Validation.checkStudentExist(ls, id, name, semester, course) == true) {
                ls.add(new Student(id, name, semester, course));
                System.out.println("Add student success.");
                count++;
                continue;
            }
            System.err.println("Duplicate.");
            return count;
        }
    }

    public static void findAndSort(ArrayList<Student> ls) {
        //check list empty 
        if (ls.isEmpty()) {
            System.err.println("List empty.");
            return;
        }
        ArrayList<Student> list = listStudentFindByName(ls);
        if (list.isEmpty()) {
            System.out.println("Not exist.");
        } else {
            Collections.sort(list);
            System.out.printf("%-15s%-15s%-15s\n", "Student name", "semester", "Course Name");
            //loop from first to last element of list student
            for (Student student : list) {
                student.print();
            }
        }
    }

    //List student found by name
    public static ArrayList<Student> listStudentFindByName(ArrayList<Student> ls) {
        ArrayList<Student> list = new ArrayList<>();
        // accept user input lowerCase or upperCase to search.
        System.out.print("Enter name to search: ");
        String name = Validation.checkInputString().toLowerCase();
        for (Student student : ls) {
            //check student have name contains input
            if (student.getStudentName().toLowerCase().contains(name)) {
                list.add(student);
            }
        }
        return list;
    }

    public static int getUpdateOrDelete(ArrayList<Student> ls, int count) {
        //if list empty 
        if (ls.isEmpty()) {
            System.err.println("List empty.");
            return count;
        }
        while (true) {
            System.out.print("Enter id: ");
            String id = Validation.checkInputString();
            ArrayList<Student> list = getListStudentById(ls, id); // gọi hàm check ID ở dưới
            //check list empty
            if (list.isEmpty()) {
                System.err.println("Not found student.");
                return count;
            } else {
                String ID = "";
                String name = "";
                Student student = getStudentByListFound(list);  // gọi hàm listFound ở dưới
                System.out.print("Do you want to update (U) or delete (D) student: ");
                //check user want to update or delete
                if (Validation.checkInputUD()) {
                    // if teacher need change ID.
                    System.out.print("Enter ID: ");
                    ID = Validation.checkInputString();
                    if (Validation.checkIdExist(ls, ID) == true) {
                        System.out.print("Enter name student: ");
                        name = Validation.checkInputString();
                    } else { // Validation.checkIdExist == false --> ID  exist -->  name = getStudentName();
                        name = Validation.getStudentById(ID, ls).getStudentName();
                    }
                    System.out.print("Enter semester: ");
                    String semester = Validation.checkInputString();
                    System.out.print("Enter name course: ");
                    String course = Validation.checkInputCourse();
                    //check student exist or not
                    if (Validation.checkStudentExist(ls, ID, name, semester, course)) {
                        student.setId(ID);
                        student.setStudentName(name);
                        student.setSemester(semester);
                        student.setCourseName(course);
                        System.err.println("Update Successful.");
                        return count;
                    } else {
                        System.err.println("Nothing change.");
                        return count;
                    }
                } else {
                    ls.remove(student);
                    System.err.println("Delete Successful.");
                    count--;
                    return count;
                }
            }
        }
    }

    public static Student getStudentByListFound(ArrayList<Student> listStudentFindByName) {
        System.out.println("List student found: ");
        int count = 1;
        System.out.printf("%-10s%-15s%-15s%-15s%-15s\n", "Number", "StudentID", "Student name",
                "semester", "Course Name");
        //display list student found
        for (Student student : listStudentFindByName) {
            System.out.printf("%-10d%-15s%-15s%-15s%-15s\n", count, student.getId(),
                    student.getStudentName(), student.getSemester(),
                    student.getCourseName());
            count++;
        }
        System.out.print("Enter student: ");
        int choice = Validation.checkInputINT(1, listStudentFindByName.size());
        return listStudentFindByName.get(choice - 1);
    }

    //Get list student find by id
    public static ArrayList<Student> getListStudentById(ArrayList<Student> ls, String id) {
        ArrayList<Student> list = new ArrayList<>();
        for (Student student : ls) {
            if ((student.getId()).contains(id)) {
                list.add(student);
            }
        }
        return list;
    }

    //Print report
    public static void report(ArrayList<Student> ls) {
        if (ls.isEmpty()) {
            System.err.println("List empty.");
            return;
        }
        ArrayList<Report> lr = new ArrayList<>();
        for (Student st : ls) {
            Report r = new Report();  // these loop will add into Report(); --> de ra ngoai thi chi duoc 1 infor student last list.
            int total = 0;  // sau moi vong lap thi no lai reset total = 0; --> de ra ngoai thi total se tang 1 (k reset lai ve 0)
            for (Student st1 : ls) {
                if (st.getStudentName().equalsIgnoreCase(st1.getStudentName())
                        && st.getCourseName().equalsIgnoreCase(st1.getCourseName())) {
                    total++;
                }
                // set nguoc lai cho class report --> Printf(report);
                r.setStudentName(st.getStudentName());
                r.setCourseName(st.getCourseName());
                r.setTotalCourse(total);
                if (Validation.isReportExist(lr, r)) {
                    lr.add(r);
                }
            }
        }
        for (int i = 0; i < lr.size(); i++) {
            System.out.printf("%-15s|%-10s|%-5d\n", lr.get(i).getStudentName(),
                    lr.get(i).getCourseName(), lr.get(i).getTotalCourse());
        }
    }
}


import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<Student> ls = new ArrayList<>();
        //loop until user want to exit program
        int count = 8;
        while (true) {
            int choice = Manager.menu();
            switch (choice) {
                case 1:
                    count = Manager.createStudent(ls, count);
                    break;
                case 2:
                    Manager.findAndSort(ls);
                    break;
                case 3:
                    count = Manager.getUpdateOrDelete(ls, count);
                    break;
                case 4:
                    Manager.report(ls);
                    break;
                case 5:
                    return;
            }
        }
    }
}

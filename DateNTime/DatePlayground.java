package DateNTime;

import java.time.LocalDate;
import java.time.Period;

public class DatePlayground {
    public static void main(String[] args) {

        // Date 
        LocalDate now = LocalDate.now();
        System.out.println(now);
        LocalDate joiningDate = LocalDate.of(2022, 5, 6);
        System.out.println(joiningDate);
    }
}

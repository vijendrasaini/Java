package DateNTime;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Formatting {
    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        System.out.println(today);

        // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-YYYY");
        // System.out.println(today.format(formatter));
        // System.out.println(today.format(formatter2));
        
        String joiningDate = "22/06/2026";
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate jDate = LocalDate.parse(joiningDate, formatter2);
        System.out.println(jDate);


    }
}

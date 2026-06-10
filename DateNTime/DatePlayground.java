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

        // Period
        Period experiance = Period.between(joiningDate, now);
        System.out.println(experiance);
        System.out.println("%d day(s), %d month(s), %d year(s)".formatted(experiance.getDays(), experiance.getMonths(), experiance.getYears()));
        System.out.println(experiance.getChronology());
        System.out.println(experiance.getUnits());
    }
}

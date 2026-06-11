package DateNTime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;

public class DatePlayground {
    public static void main(String[] args) {

        // // Date 
        // LocalDate now = LocalDate.now();
        // System.out.println(now);
        // LocalDate joiningDate = LocalDate.of(2022, 5, 6);
        // System.out.println(joiningDate);

        // // Period
        // Period experiance = Period.between(joiningDate, now);
        // System.out.println(experiance);
        // System.out.println("%d day(s), %d month(s), %d year(s)".formatted(experiance.getDays(), experiance.getMonths(), experiance.getYears()));
        // System.out.println(experiance.getChronology());
        // System.out.println(experiance.getUnits());

        // DateTime
        LocalDateTime sentAt = LocalDateTime.of(2026, 6, 10, 10, 30);
        System.out.println(sentAt);

        LocalDateTime expiryTime = sentAt.plusMinutes(30);
        System.out.println(expiryTime);

        LocalDateTime now = LocalDateTime.now();
        sentAt = LocalDateTime.of(LocalDate.now(), LocalTime.of(15, 5));
        expiryTime = sentAt.plusMinutes(30);

        System.out.println(expiryTime);
        System.out.println(now);
        if(now.isAfter(expiryTime)) {
            System.out.println("Expired");
        } else {
            System.out.println("Active");
        }
    }
}

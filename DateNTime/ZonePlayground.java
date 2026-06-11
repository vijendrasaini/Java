package DateNTime;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class ZonePlayground {
    public static void main(String[] args) throws InterruptedException{
        // Instant now = Instant.now();
        // System.out.println(now);

        // ZoneId india = ZoneId.of("Asia/Kolkata");
        // System.out.println(india);

        // ZonedDateTime zonedDateTime = now.atZone(india);
        // System.out.println(zonedDateTime);

        // LocalDateTime localDateTime = LocalDateTime.now();
        // LocalDateTime expiresAt = localDateTime.plus(Duration.ofMinutes(30));

        // System.out.println(Duration.ofMinutes(30));

        Instant start = Instant.now();
        Thread.sleep(2500);
        Instant end = Instant.now();

        Duration duration = Duration.between(start, end);
        System.out.println(duration);
        System.out.println(duration.getSeconds());
        System.out.println(duration.toMillis());
    }
}

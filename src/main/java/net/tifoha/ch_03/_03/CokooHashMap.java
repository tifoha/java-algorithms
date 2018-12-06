package net.tifoha.ch_03._03;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

import static java.util.stream.Collectors.joining;

/**
 * @author Vitalii Sereda
 */
public class CokooHashMap {
    public static void main(String[] args) {
        ZonedDateTime dateTime = ZonedDateTime.now()
                .withMinute(0)
                .withSecond(0)
                .withNano(0);
//        LocalDateTime startDateTime = dateTime.minusHours(12);
//        LocalDateTime endDateTime = dateTime.plusHours(12);
        Collection<ZoneId> zoneIds = Arrays.asList(
                ZoneId.of("America/Buenos_Aires"),
                ZoneId.of("Europe/Kiev"),
                ZoneId.of("PST8PDT"));
        System.out.println(zoneIds.stream().map(Objects::toString).collect(joining("\t")));
        for (int i = -12; i <= 12; i++) {
            ZonedDateTime currentDateTime = dateTime.plusHours(i);
            String result = zoneIds.stream()
                    .map(currentDateTime::withZoneSameInstant)
                    .map(ZonedDateTime::toLocalTime)
                    .map(Objects::toString)
                    .collect(joining("\t\t\t"));
            System.out.println(result);
        }
        System.out.println(ZonedDateTime.now(ZoneId.of("PST8PDT")).withZoneSameInstant(ZoneId.of("Europe/Kiev")).toLocalTime());
    }
}

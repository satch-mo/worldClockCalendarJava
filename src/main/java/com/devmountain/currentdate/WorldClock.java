package com.devmountain.currentdate;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class WorldClock {

    public LocalDate getNowForDate() {
        LocalDate localDate = LocalDate.now();
        return localDate;
    }

    public LocalDateTime getNowForDateAndTime() {
        LocalDateTime localDateTime = LocalDateTime.now();
        return localDateTime;
    }

    public DayOfWeek getDayOfWeekForNow() {
        LocalDate localDate = LocalDate.now();
        return  localDate.getDayOfWeek();
    }

    public int getDayOfMonthForNow() {
        LocalDate localDate = LocalDate.now();
        return  localDate.getDayOfMonth();
    }

    public int getDayOfYearForNow() {
        LocalDate localDate = LocalDate.now();
        return  localDate.getDayOfYear();
    }

    public ZonedDateTime getNowDateTimeForNewYork() {
        ZonedDateTime now = ZonedDateTime.now();
        return now.withZoneSameInstant(ZoneId.of("America/New_York"));
    }

    public ZonedDateTime getNowDateTimeForLA() {
        ZonedDateTime now = ZonedDateTime.now();
        return now.withZoneSameInstant(ZoneId.of("America/Los_Angeles"));
    }

    public ZonedDateTime getNowDateTimeForLondon() {
        ZonedDateTime now = ZonedDateTime.now();
        return now.withZoneSameInstant(ZoneId.of("Europe/London"));
    }

    public ZonedDateTime getNowDateTimeForMoscow() {
        ZonedDateTime now = ZonedDateTime.now();
        return now.withZoneSameInstant(ZoneId.of("Europe/Moscow"));
    }

    public ZonedDateTime getNowDateTimeForTokyo() {
        ZonedDateTime now = ZonedDateTime.now();
        return now.withZoneSameInstant(ZoneId.of("Asia/Tokyo"));
    }
}

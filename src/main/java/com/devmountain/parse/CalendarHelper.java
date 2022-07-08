package com.devmountain.parse;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CalendarHelper {

    private DateTimeFormatter formatter;
    private LocalDate birthdayLocalDate;
    private LocalDate nowLocalDate;
    private int currentYear;
    private List<LocalDate> holidayList;

    public CalendarHelper(String birthdayString) {

        formatter = DateTimeFormatter.ofPattern("MM/dd/uuuu");
        birthdayLocalDate = LocalDate.parse(birthdayString, formatter);
        nowLocalDate = LocalDate.now();
        currentYear = nowLocalDate.getYear();
        holidayList = initHolidayList(currentYear, nowLocalDate);

    }

    public void displayDaysRemainingToHolidaysAndBirthday() {
        /*
         * Step 1: print out the current date
         */
        System.out.println("The current date is: " + formatter.format(nowLocalDate));

        /*
         * Hint:
         * 1. you need to loop through each of the holiday to
         *    calculate the difference of days (e.g. remainingDay) between the current day
         *    and the holiday. If your current day has passed a holiday,
         *    you need to calculate the different days between your current
         *    day of the year and the corresponding holiday in next year
         * 2. you need to find the holiday with the smallest "remainingDay"
         * 3. you then calculate the "remainingDay" between the current date and the
         *    coming birthday
         */

        int dayOfYearForNow = nowLocalDate.getDayOfYear();  // getDayOfYear() method
        int remainingDay = 0;
        int smallestRemainingDays = 1000;
        LocalDate closestHoliday = null;
        for (LocalDate eachHoliday : holidayList) {
            int dayOfYearHoliday = eachHoliday.getDayOfYear();
            if (dayOfYearForNow <= dayOfYearHoliday) {
                remainingDay = dayOfYearHoliday - dayOfYearForNow;
                if (isRemainingDaySmallest(smallestRemainingDays, remainingDay)) {
                    smallestRemainingDays = remainingDay;
                    closestHoliday = eachHoliday;
                    System.out.println();
                }
                //Display the remainingDay between the current day and the corresponding holiday
                System.out.println("There are " + remainingDay + " days remaining before Holiday (" + formatter.format(eachHoliday) + ")");
            } else {
                remainingDay = 365 - dayOfYearForNow + dayOfYearHoliday;
                if (isRemainingDaySmallest(smallestRemainingDays, remainingDay)) {
                    smallestRemainingDays = remainingDay;
                    closestHoliday = eachHoliday;
                    System.out.println();
                }
                //Display the remainingDay between the current day and the corresponding holiday
                System.out.println("There are " + remainingDay + " days remaining before Holiday (" + formatter.format(eachHoliday) + ")");
            }
        }
        //Display the Holiday which is closest to the current day
        System.out.println("the closest Holiday to the current date (" + formatter.format(nowLocalDate) + ") is: " + formatter.format(closestHoliday));

        /*
         * Now start calculating the remaining day between the current date and the birthday
         */
        int dayOfYearForBirthday = birthdayLocalDate.getDayOfYear();
        int age = nowLocalDate.getYear() - birthdayLocalDate.getYear();
        LocalDate nextBirthdayLocalDate = birthdayLocalDate.withYear(nowLocalDate.getYear());
        if (dayOfYearForNow <= dayOfYearForBirthday) {
            remainingDay = dayOfYearForBirthday - dayOfYearForNow;
        } else {
            remainingDay = dayOfYearForBirthday + 365 - dayOfYearForNow;
            age++;
            nextBirthdayLocalDate = birthdayLocalDate.withYear(nowLocalDate.getYear() + 1);
        }
        //Display the remainingDay between the current day and the birthday
        System.out.println("There are " + remainingDay + " days remaining before your " + age + "th birthday (" + formatter.format(nextBirthdayLocalDate) + ")");
    }

    private boolean isRemainingDaySmallest(int smallestRemainingDay, int remainingDay) {
        boolean isSmallest = false;
        if(remainingDay < smallestRemainingDay)
            isSmallest = true;
        return isSmallest;
    }


    private List<LocalDate> initHolidayList(int currentYear, LocalDate nowLocalDate) {
        List<LocalDate> holidayList = new ArrayList<>(5);
        int nextYear = currentYear + 1;
        //initialize July 4th holiday
        LocalDate julyFourth = LocalDate.of(currentYear, Month.JULY, 4);
        if(nowLocalDate.isAfter(julyFourth))
            julyFourth = LocalDate.of(nextYear, Month.JULY, 4);
        holidayList.add(julyFourth);

        //initialize New Year holiday
        LocalDate newYear = LocalDate.of(currentYear, Month.JANUARY, 1);
        if(nowLocalDate.isAfter(newYear))
            newYear = LocalDate.of(nextYear, Month.JANUARY, 1);
        holidayList.add(newYear);

        //initialize Christmas holiday
        LocalDate christmas = LocalDate.of(currentYear, Month.DECEMBER, 25);
        if(nowLocalDate.isAfter(christmas))
            christmas = LocalDate.of(nextYear, Month.DECEMBER, 25);
        holidayList.add(christmas);

        return holidayList;
    }


}

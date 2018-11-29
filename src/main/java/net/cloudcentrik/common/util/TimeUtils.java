package net.cloudcentrik.common.util;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Date;
import java.util.Locale;

public class TimeUtils {
    //private static DateTimeFormatter defaultFormat=DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    private static DateTimeFormatter defaultFormat=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static LocalDateTime now(){
        LocalDateTime date = LocalDateTime.now();
        String text = date.format(defaultFormat);
        LocalDateTime parsedDate = LocalDateTime.parse(text, defaultFormat);
        return date;
    }

    public static String nowAsString(){
        return now().toString();
    }

    public static LocalDate today(){
       return now().toLocalDate();
    }

    public static String todayAsString(){
        return now().toLocalDate().toString();
    }


    public static LocalTime timeNow(){
        return now().toLocalTime();
    }

    public static String timeAsString(){
        return now().toLocalTime().toString();
    }

    public static long timeStampNow(){
        Instant timestamp = Instant.now();
        return timestamp.toEpochMilli();
    }

    public static LocalDateTime dateTimeOf(int year, int month, int dayOfMonth, int hour, int minute){
        return LocalDateTime.of(year,month,dayOfMonth,hour,minute);
    }

    public static String weekDay(){
        return now().getDayOfWeek().toString();
    }

    public static int weekNumber(){
        TemporalField woy = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
        return now().get(woy);
    }

    public static LocalDateTime parseDateTime(String date){

        return LocalDateTime.parse(date,defaultFormat);
    }

    public static String parseDateTime(String date,DateTimeFormatter dateTimeFormatter){
        LocalDateTime dateTime=LocalDateTime.parse(date,defaultFormat);
        return LocalDateTime.parse(dateTime.toString(),dateTimeFormatter).toString();
    }

    public static String parseToIsoDateTime(String date){
        LocalDateTime dateTime=LocalDateTime.parse(date,defaultFormat);
        return LocalDateTime.parse(dateTime.toString(),DateTimeFormatter.ISO_DATE_TIME).toString();
    }
}

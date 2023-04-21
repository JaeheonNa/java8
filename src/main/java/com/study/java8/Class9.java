package com.study.java8;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

// Date와 Time
public class Class9 {
    public static void main(String[] args){
        /* java8 이전의 date 관련 라이브러리
           <Date, Calendar, SimpleDateFormat>
           문제1. 이름 자체가 이상함. date 객체에서 time을 뽑아낼 수 있다든지..
           문제2. time을 뽑아내면 millisecond로 나옴.
           문제3. thread safe하지 않음.
           문제4. calendar에서 time을 뽑으면 datetime이 나옴.
           문제5. calendar에서 1990년 5월 28일을 뽑아내려면 1990, 4, 28을 입려해야됨. 1월이 0이므로.(혹은 enum타입의 May)
         */
        System.out.println("=====preJava8=====");
        Date date = new Date();
        System.out.println("date.getTime() : " + date.getTime()); // 1682051238581
        Calendar calendar = new GregorianCalendar(); // Fri Apr 21 13:27:18 KST 2023
        System.out.println("calendar.getTime() : " + calendar.getTime());
        Calendar myBirthDay = new GregorianCalendar(1990, 5, 28);
        System.out.println("myBirthDay.getTime() : " + myBirthDay.getTime()); // Thu Jun 28 00:00:00 KST 1990

        /* java8에서 제공하는 Date/Time API
        Instant, ZonedDateTime, LocalDateTime, LocalDate, Period, ChronoUnit, Duration
        DateTimeFormatter
         */
        System.out.println("=====Java8=====");
        Instant instant = Instant.now();
        System.out.println("instant : " + instant); // 2023-04-21T04:34:53.342639Z <-- 그리니치 기준 시

        ZoneId zone = ZoneId.systemDefault();
        System.out.println("zone : " + zone); // Asia/Seoul <-- 시스템의 zone
        ZonedDateTime zonedDateTime = instant.atZone(zone);
        System.out.println("zonedDateTime : " + zonedDateTime); // 2023-04-21T13:36:52.485565+09:00[Asia/Seoul] <-- 현재 서버 zone의 시간.
        ZonedDateTime parisDateTime = ZonedDateTime.now(ZoneId.of("Europe/Paris")); //
        System.out.println("parisDateTime : " + parisDateTime); // 2023-04-21T06:45:24.784553+02:00[Europe/Paris] <-- 원하는 zone의 시간.

        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("localDateTime : " + localDateTime); // 2023-04-21T13:40:56.655167 <-- 서버가 위치한 zone의 시간.
        LocalDateTime myBirthDate = LocalDateTime.of(1990, 5, 28, 14, 10, 00);
        System.out.println("myBirthDate : " + myBirthDate); // 1990-05-28T14:10 <-- 달에 원하는 시간을 넣어도 적절히 출력됨.

        LocalDate localDate = LocalDate.now();
        LocalDate thisYearBirthday = LocalDate.of(2023, Month.MAY, 28);

        Period period = Period.between(localDate, thisYearBirthday);
        System.out.println("localDate : " + localDate);
        System.out.println("thisYearBirthday : " + thisYearBirthday);
        System.out.println("period : " + period.getDays()); // <-- 기간을 표현.

        long days = ChronoUnit.DAYS.between(localDate, thisYearBirthday);
        System.out.println("ChronoUnit : " + days);
    }
}

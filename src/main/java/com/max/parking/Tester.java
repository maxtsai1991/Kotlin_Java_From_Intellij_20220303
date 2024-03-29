package com.max.parking;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class Tester {
    public static void main(String[] args) {
        LocalDateTime enter = LocalDateTime.of(2022,02,16,8,0,0);
        LocalDateTime leave = LocalDateTime.of(2022,02,16,10,8,0);


        Car car = new Car("AA-00001",enter);
        car.setLeave(leave);
        System.out.println("計算停留在停車場多久時間: " + car.getDuration() + "分鐘");
        long hours = (long) Math.ceil(car.getDuration() / 60.0); // Math.ceil() 無條件進位 回傳double 強制轉型成long ,這樣做才不會虧錢
        System.out.println("停幾小時 : " + hours);
        System.out.println("停車費用(1小時$30) : " + 30 * hours + "$");

//        Car car = new Car("AA-00001",enter.atZone(ZoneId.systemDefault()).toEpochSecond()); // "AA-00001" -> id車號 , 進場時間
//        car.setLeave(System.currentTimeMillis() + 1000*60*60*2); // 設置離場時間為進場時間+2小時

        // 設定時間寫法 有分java8 及 java8之前的寫法
//        java8();
//        java();
    }

    private static void java8() {
        Instant instant = Instant.now();
        System.out.println(instant); // 2022-02-15T03:30:27.417Z  T = Time時間　Ｚ= zone時區 ,格林威治的標準時間
        // Local
        LocalDateTime now = LocalDateTime.now();
        System.out.println("now : " + now);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        System.out.println(formatter.format(now));
        System.out.println("now時間加三小時: "+now.plus(Duration.ofHours(3)));
        LocalDateTime other = LocalDateTime.of(2018,11,23,12,0,0);
        System.out.println("自訂年月日時分秒:" + other);
    }

    private static void java() {
    /*
    Date內部使用
    long 長整數
    1970/1/1 00:00:00 至今的毫秒數
    1000毫秒 = 1秒鐘
     */
        Date date = new Date();
        System.out.println("印出當下時間 : " + date);
        System.out.println("印出long長整數值 : " + date.getTime());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); // 參考https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html
        System.out.println("印出時間格視為(yyyy/MM/dd HH:mm:ss) : " + sdf.format(date));

        String s = "2022/02/15 10:32:37";
        try {
            Date other = sdf.parse(s);
            System.out.println("字串物件轉換Date : " + other);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // 使用Calendar日歷物件,因Date物件比較陽春 ,以及 可以特別對時間做處理 EX: 設定修改月份 日期 等等
        Calendar calendar = Calendar.getInstance();
        System.out.println("Calendar物件,印出當下時間: " + calendar.getTime());
        calendar.set(Calendar.MONDAY,9); // 補充: 0是1月 , 9是10月
        System.out.println("將月份改成10月" + calendar.getTime()); // Sat Oct 15 10:48:09 CST 2022
        calendar.add(Calendar.DAY_OF_YEAR,3);
        System.out.println("將日期往後加三天:" + calendar.getTime()); // Tue Oct 18 10:53:49 CST 2022
    }
}

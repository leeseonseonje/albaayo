package albaAyo.albaayo;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ttttt {

    @Test
    public void dateTest() {
        LocalDateTime nowTime = LocalDateTime.of(2021, 9, 20, 13, 0);
        String type = "regular";
        List<String> list = new ArrayList<>();
        long count = 0L;

//
//        if (type.equals("regular")) {
//            count += 1L;
//        }
//        if (LocalTime.parse("13:00").isAfter(LocalTime.from(nowTime))) {
//            count += 1L;
//        }

        for (int i = 0; i < 5; i++) {
            if (nowTime.plusDays(count).getDayOfWeek().toString().equals("SATURDAY")
                    || nowTime.plusDays(count).getDayOfWeek().toString().equals("SUNDAY")) {
                count++;
                System.out.println(count);
            }
        }

        LocalDateTime dateTime = nowTime.plusDays(count+1L);
        String s = dateTime.format(DateTimeFormatter.ofPattern("M월 dd일 E요일"));
        LocalDateTime dateTime1 = nowTime.plusDays(count+2L);
        String s1 = dateTime1.format(DateTimeFormatter.ofPattern("M월 dd일 E요일"));
        LocalDateTime dateTime2 = nowTime.plusDays(count+3L);
        String s2 = dateTime2.format(DateTimeFormatter.ofPattern("M월 dd일 E요일"));
        LocalDateTime dateTime3 = nowTime.plusDays(count+4L);
        String s3 = dateTime3.format(DateTimeFormatter.ofPattern("M월 dd일 E요일"));
        LocalDateTime dateTime4 = nowTime.plusDays(count+5L);
        String s4 = dateTime4.format(DateTimeFormatter.ofPattern("M월 dd일 E요일"));
        list.add(s);
        list.add(s1);
        list.add(s2);
        list.add(s3);
        list.add(s4);
        for (String d : list) {
            System.out.println(d);
        }
    }

    @Test
    public void timeTest() {
        LocalDateTime now = LocalDateTime.now();
        LocalTime time = LocalTime.parse("13:00");
        System.out.println(time.isAfter(LocalTime.from(now)));
    }
}

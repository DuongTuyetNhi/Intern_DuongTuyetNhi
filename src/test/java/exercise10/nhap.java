package exercise10;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class nhap {
    public static void main(String[] args) {
        LocalDate s = LocalDate.now();
        System.out.println(LocalDate.now());
        System.out.println((LocalDate.now()).plusDays(12));
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("M/d/yyyy");
        String a = dateTimeFormatter.format(s);
        System.out.println("a: "+ dateTimeFormatter.format((LocalDate.now()).plusDays(12)));
    }
}

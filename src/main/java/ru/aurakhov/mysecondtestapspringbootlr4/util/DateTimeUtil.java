package ru.aurakhov.mysecondtestapspringbootlr4.util;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;

@Slf4j

public class DateTimeUtil {
    public static SimpleDateFormat getCustomFormat() {
        log.info("вызовом этого метода мы задаем формат даты и времени в виде год-месяц-деньT часы:минуты:секунды.миллисекунды часовой поя");


        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

    }
}

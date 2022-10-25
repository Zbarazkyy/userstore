package com.gdmgroup.userstore.utils;

import java.time.format.DateTimeFormatter;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DateUtils {
    private static final String DATE_TIME_FORMAT_S = "yyyy-MM-dd HH:mm:ss";

    private static final DateTimeFormatter DATE_TIME_FORMATTER_S = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT_S);

    public static DateTimeFormatter getDateTimeFormatter() {
        return DATE_TIME_FORMATTER_S;
    }
}

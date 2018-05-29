package co.com.education.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(DateUtil.class);
    public static final String DATE_PATTERN = "dd-MM-yyyy";
    public static final String DATE_TIME_PATTERN = "dd-MM-yyyy HH:mm:ss";

    private DateUtil() {
    }

    public static Date asDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date asDate(String date, String pattern) {
        try {
            return new SimpleDateFormat(pattern, Locale.ENGLISH).parse(date);
        } catch (ParseException e) {
            LOGGER.warn(e.getMessage(), e);
        }
        return new Date();
    }

    public static LocalDate asLocalDate(Date date) {
        return date != null ? Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate() : null;
    }

    public static LocalDateTime asLocalDateTime(Date date) {
        return date != null ? Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime() : null;
    }

    public static LocalDateTime calendarToLocalDateTime(Calendar date) {
        return date != null ? LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()) : null;
    }

    public static long dateDiffMinutes(LocalDateTime startDate, LocalDateTime endDate) {
        return ChronoUnit.MINUTES.between(startDate, endDate);
    }


}

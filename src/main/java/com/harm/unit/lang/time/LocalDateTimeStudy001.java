package com.harm.unit.lang.time;

import com.harm.unit.Unit;
import com.harm.unit.data.xml.jaxb.JaxBStudy001;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.time.*;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.TimeZone;

public class LocalDateTimeStudy001 implements Unit {

    private Logger logger = LoggerFactory.getLogger(JaxBStudy001.class);

    @Override
    public Object execute(Object[] obj) throws Exception {

        //local date
        logger.debug("LocalDate.now() -> {}", LocalDate.now());
        logger.debug("LocalDate.of(2020, 01, 16) -> {}", LocalDate.of(2020, 01, 16));

        logger.debug("LocalDate.now().getYear() -> {}", LocalDate.now().getYear());
        logger.debug("LocalDate.now().get(ChronoField.YEAR) -> {}", LocalDate.now().get(ChronoField.YEAR));

        logger.debug("Year.of(2020).atMonth(01).atDay(16).atTime(18, 30, 22) -> {}", Year.of(2020).atMonth(01).atDay(16).atTime(18, 30, 22));

        logger.debug("Period.ofYears(2) -> {}", Period.ofYears(2));
        logger.debug("Period.ofMonths(3) -> {}", Period.ofMonths(3));
        logger.debug("Duration.ofDays(30) -> {}", Duration.ofDays(30));
        logger.debug("Duration.ofMinutes(30) -> {}", Duration.ofMinutes(30));

        //local date time
        logger.debug("LocalDateTime.now(), LocalDateTime.now().plusDays(1) -> {}, {}", LocalDateTime.now(), LocalDateTime.now().plusDays(1));
        logger.debug("LocalDateTime.now(), LocalDateTime.now().plusMinutes(10) -> {}, {}", LocalDateTime.now(), LocalDateTime.now().plusMinutes(10));
        logger.debug("LocalDate.now(), LocalDate.now().plus(Period.ofDays(2)) -> {}, {}", LocalDate.now(), LocalDate.now().plus(Period.ofDays(2)));
        logger.debug("LocalDateTime.now(), LocalDateTime.now().plus(Duration.ofMinutes(10)) -> {}, {}", LocalDateTime.now(), LocalDateTime.now().plus(Duration.ofMinutes(10)));
        logger.debug("{}, {}", LocalDate.now(), LocalDate.now().minus(Period.ofDays(10)));
        logger.debug("{}, {}", LocalDateTime.now(), LocalDateTime.now().minus(Period.ofDays(10)));
        logger.debug("{}, {}", LocalDateTime.now(), LocalDateTime.now().minus(Duration.ofMinutes(10)));
        logger.debug("{}, {}", LocalDateTime.now(), LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)));


        //instance
        logger.debug("Instant.now() -> {}", Instant.now());



        //conversion
        LocalDateTime localDateTime = LocalDateTime.now();
        logger.debug("local date time now() -> {}", localDateTime.toString());

        Date javaUtilDate = new Date();
        logger.debug("java.util.date -> {}", javaUtilDate.toString());

        localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(javaUtilDate.getTime()), TimeZone.getDefault().toZoneId());
        logger.debug("java.util.date convert to local date time -> {}", localDateTime.toString());

        Timestamp timestamp = new Timestamp(javaUtilDate.getTime());
        logger.debug("java.sql.timestamp -> {}", new Date(timestamp.getTime()).toString());

        localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp.getTime()), TimeZone.getDefault().toZoneId());
        logger.debug("java.sql.timestamp convert to local date time -> {}", localDateTime.toString());

        return null;
    }
}

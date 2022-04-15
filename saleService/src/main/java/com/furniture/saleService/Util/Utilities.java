package com.furniture.saleService.Util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class Utilities {

    public static Date getActualDate(){
        LocalDate currentLocalDate = LocalDate.now();
        ZoneId systemTimeZone = ZoneId.systemDefault();
        ZonedDateTime zonedDateTime = currentLocalDate.atStartOfDay(systemTimeZone);
        Date utilDate = Date.from(zonedDateTime.toInstant());
        return utilDate;
    }
}

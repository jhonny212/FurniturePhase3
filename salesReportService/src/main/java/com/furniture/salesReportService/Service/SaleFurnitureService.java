package com.furniture.salesReportService.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.Optional;

public interface SaleFurnitureService {

    public Object getEarningsTotal(Optional<String> date1, Optional<String> date2) throws ParseException;
}

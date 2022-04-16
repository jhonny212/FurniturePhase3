package com.furniture.reportService.Service;

import java.util.List;
import java.util.Optional;

public interface FurnitureReportController {
    public List<Object[]> getLost(Optional<String> date1,Optional<String> date2);
}

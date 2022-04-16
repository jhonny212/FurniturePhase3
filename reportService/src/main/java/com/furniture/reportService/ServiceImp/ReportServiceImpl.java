package com.furniture.reportService.ServiceImp;

import com.furniture.reportService.Model.Furniture;
import com.furniture.reportService.Model.reports.BestEarnerReport;
import com.furniture.reportService.Repository.FurnitureRepository;
import com.furniture.reportService.Service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.function.EntityResponse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private FurnitureRepository furnitureRepository;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public ResponseEntity<Object> getBestSellerInXPeriod(Optional<String> initialDate, Optional<String> finalDate) {
        ResponseEntity<Object> response = this.restTemplate.getForEntity("http://localhost:8085/sale/bill/best-seller?initialDate="+initialDate.orElse("0001-01-01")+"&finalDate="+finalDate.orElse("2100-01-01"), Object.class);
        if(response.getStatusCode().equals(HttpStatus.OK)) return ResponseEntity.status(HttpStatus.OK).body(response.getBody());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response.getBody());
    }

    @Override
    public ResponseEntity<Object> getBestEarnerInXPeriod(Optional<String> initialDate, Optional<String> finalDate) {
        ResponseEntity<Object> response = this.restTemplate.getForEntity("http://localhost:8085/sale/bill/best-earner?initialDate="+initialDate.orElse("0001-01-01")+"&finalDate="+finalDate.orElse("2100-01-01"), Object.class);
        if(response.getStatusCode().equals(HttpStatus.OK)) return ResponseEntity.status(HttpStatus.OK).body(response.getBody());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response.getBody());
    }

    @Override
    public List<Furniture> getReportMinFurnitureXPeriod(Optional<String> date1, Optional<String> date2) throws ParseException {
        SimpleDateFormat formatter1=new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatter2=new SimpleDateFormat("yyyy-MM-dd");
        Date dates1 = formatter1.parse("0001-01-01");
        Date dates2 = formatter2.parse("9999-01-01");
        if (!date1.isEmpty()){
            dates1 = formatter1.parse(date1.get());
        }

        if (!date2.isEmpty()){
            dates2 = formatter2.parse(date2.get());
        }

        Optional<Date> d1 = Optional.of(dates1);
        Optional<Date> d2 = Optional.of(dates2);

        List<Furniture> list = this.furnitureRepository.findMinFurniture(d1.get(), d2.get());
        List<Double> list1 = this.furnitureRepository.findMinFurnitureNum(d1.get(), d2.get());
        for (int i = 0; i < list.size(); i++) {
            list.get(i).amount = list1.get(i);
        }
        return list;
    }

    @Override
    public List<Furniture> getReportMaxFurnitureXPeriod(Optional<String> date1, Optional<String> date2) throws ParseException {
        SimpleDateFormat formatter1=new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatter2=new SimpleDateFormat("yyyy-MM-dd");
        Date dates1 = formatter1.parse("0001-01-01");
        Date dates2 = formatter2.parse("9999-01-01");
        if (!date1.isEmpty()){
            dates1 = formatter1.parse(date1.get());
        }

        if (!date2.isEmpty()){
            dates2 = formatter2.parse(date2.get());
        }

        Optional<Date> d1 = Optional.of(dates1);
        Optional<Date> d2 = Optional.of(dates2);

        List<Furniture> list = this.furnitureRepository.findMaxFurniture(d1.get(), d2.get());
        List<Double> list1 = this.furnitureRepository.findMaxFurnitureNum(d1.get(), d2.get());
        for (int i = 0; i < list.size(); i++) {
            list.get(i).amount = list1.get(i);
        }
        return list;
    }

    @Override
    public Object getReportEarningsXPeriod(Optional<Date> date1, Optional<Date> date2, Optional<Integer> page){
        List<Furniture> list = this.furnitureRepository.findMinFurniture(date1.get(), date2.get());
        List<Double> list1 = this.furnitureRepository.findMinFurnitureNum(date1.get(), date2.get());
        for (int i = 0; i < list.size(); i++) {
            list.get(i).amount = list1.get(i);
        }
        return null;
    }
}

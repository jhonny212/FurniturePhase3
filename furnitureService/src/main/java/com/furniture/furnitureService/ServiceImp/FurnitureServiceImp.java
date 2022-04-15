package com.furniture.furnitureService.ServiceImp;

import com.furniture.furnitureService.Model.Furniture;
import com.furniture.furnitureService.Repository.FurnitureRepository;
import com.furniture.furnitureService.Service.FurnitureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FurnitureServiceImp implements FurnitureService {
    @Autowired
    private FurnitureRepository furnitureRepository;
    @Override
    public boolean putOnSale(Integer id) {
        Furniture tmp = furnitureRepository.findById(id).orElse(null);
        if(tmp!=null){
            tmp.setStatus(1);
            furnitureRepository.save(tmp);
            return true;
        }
        return false;
    }

    @Override
    public Page<Furniture> getAllFurniture(Optional<String> filter, Optional<Integer> page){
        if (filter.isPresent()){
            return this.furnitureRepository.findByNameContains(
                    filter.orElse(""),
                    PageRequest.of(page.orElse(0),15, Sort.by("name").descending())
            );
        }

        return getAllFurniture(page);
    }

    @Override
    public Page<Furniture> getAllFurniture(Optional<Integer> page){
        List<Furniture> l = this.furnitureRepository.findAll();
        return this.furnitureRepository.findAll(
                PageRequest.of(
                        page.orElse(0),
                        15
                )
        );
    }

    @Override
    public Page<Furniture> getAllFurnitureFilter(Optional<String> date1, Optional<String> date2, Optional<Integer> sort, Optional<Integer> page) throws ParseException {
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

        Sort s = Sort.by("name").ascending();
        if (!sort.isEmpty() && sort.get()==0){
            s = Sort.by("name").descending();
        }

        return this.furnitureRepository.findByCreationDateBetween(d1.orElse(new SimpleDateFormat("yyyy-MM-dd").parse("0001-01-01")),
                d2.orElse(new SimpleDateFormat("yyyy-MM-dd").parse("9999-01-01")),
                PageRequest.of(page.orElse(0), 10, s ));
    }

    public boolean isExisteFurniture(Integer id){
        Optional<Furniture> f = this.furnitureRepository.findById(id);
        if (f.isPresent()){
            return true;
        }
        return false;
    }

    @Override
    public Furniture getFurniture(Integer id){
        Optional<Furniture> tmp = this.furnitureRepository.findById(id);
        return tmp.orElseGet(() -> new Furniture("No existe el mueble"));
    }
}

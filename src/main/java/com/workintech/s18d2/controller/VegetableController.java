package com.workintech.s18d2.controller;

import com.workintech.s18d2.dto.VegetableResponseRecord;
import com.workintech.s18d2.entity.Vegetable;
import com.workintech.s18d2.exceptions.PlantException;
import com.workintech.s18d2.services.VegetableService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workintech/vegetables")
public class VegetableController {

    private final VegetableService vegetableService;

    public VegetableController(VegetableService vegetableService) {
        this.vegetableService = vegetableService;
    }


    @GetMapping
    public List<Vegetable> getByPriceAsc() {
        return vegetableService.getByPriceAsc();
    }


    @GetMapping("/desc")
    public List<Vegetable> getByPriceDesc() {
        return vegetableService.getByPriceDesc();
    }


    @GetMapping("/{id}")
    public VegetableResponseRecord getById(@PathVariable long id) {
        if (id < 0) {
            throw new PlantException("Id must be greater than or equal to 0", HttpStatus.BAD_REQUEST);
        }
        Vegetable vegetable = vegetableService.getById(id);
        return new VegetableResponseRecord("Vegetable found successfully", vegetable);
    }


    @PostMapping
    public VegetableResponseRecord save(@Valid @RequestBody Vegetable vegetable) {
        Vegetable saved = vegetableService.save(vegetable);
        return new VegetableResponseRecord("Vegetable saved/updated successfully", saved);
    }


    @PostMapping("/{name}")
    public List<Vegetable> searchByName(@PathVariable String name) {
        return vegetableService.searchByName(name);
    }


    @DeleteMapping("/{id}")
    public VegetableResponseRecord delete(@PathVariable long id) {
        if (id < 0) {
            throw new PlantException("Id must be greater than or equal to 0", HttpStatus.BAD_REQUEST);
        }
        Vegetable deleted = vegetableService.delete(id);
        return new VegetableResponseRecord("Vegetable deleted successfully", deleted);
    }
}

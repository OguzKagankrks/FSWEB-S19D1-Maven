package com.workintech.s18d2.controller;

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
    public Vegetable getById(@PathVariable long id) {
        if (id < 0) {
            throw new PlantException("Id must be greater than or equal to 0", HttpStatus.BAD_REQUEST);
        }
        return vegetableService.getById(id);
    }


    @PostMapping
    public Vegetable save(@Valid @RequestBody Vegetable vegetable) {
        return vegetableService.save(vegetable);
    }


    @PostMapping("/{name}")
    public List<Vegetable> searchByName(@PathVariable String name) {
        return vegetableService.searchByName(name);
    }


    @DeleteMapping("/{id}")
    public Vegetable delete(@PathVariable long id) {
        if (id < 0) {
            throw new PlantException("Id must be greater than or equal to 0", HttpStatus.BAD_REQUEST);
        }
        return vegetableService.delete(id);
    }
}

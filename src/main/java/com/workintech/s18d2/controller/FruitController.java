package com.workintech.s18d2.controller;

import com.workintech.s18d2.dto.FruitResponseRecord;
import com.workintech.s18d2.entity.Fruit;
import com.workintech.s18d2.exceptions.PlantException;
import com.workintech.s18d2.services.FruitService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/workintech/fruits")
public class FruitController {

    private final FruitService fruitService;


    @GetMapping
    public List<Fruit> getByPriceAsc() {
        return fruitService.getByPriceAsc();
    }


    @GetMapping("/desc")
    public List<Fruit> getByPriceDesc() {
        return fruitService.getByPriceDesc();
    }


    @GetMapping("/{id}")
    public FruitResponseRecord getById(@PathVariable Long id) {
        if (id < 0) {
            throw new PlantException("Id must be greater than or equal to 0", HttpStatus.BAD_REQUEST);
        }
        Fruit fruit = fruitService.getById(id);
        return new FruitResponseRecord("Fruit found successfully", fruit);
    }


    @PostMapping("/{name}")
    public List<Fruit> searchByName(@PathVariable String name) {
        return fruitService.searchByName(name);
    }


    @PostMapping
    public Fruit save(@Valid @RequestBody Fruit fruit) {
        return fruitService.save(fruit);
    }


    @DeleteMapping("/{id}")
    public Fruit delete(@PathVariable Long id) {
        if (id < 0) {
            throw new PlantException("Id must be greater than or equal to 0", HttpStatus.BAD_REQUEST);
        }
        return fruitService.delete(id);
    }
}

package com.platzi.pizza.web.controller;

import com.platzi.pizza.persistence.entity.PizzaEntity;
import com.platzi.pizza.service.PizzaService;
import com.platzi.pizza.service.dto.UpdatePizzaPriceDto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pizzas")
public class PizzaController {

    private final PizzaService service;

    @Autowired
    public PizzaController(PizzaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<PizzaEntity>> getAll(@RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "8") int elements) {
        return ResponseEntity.ok(this.service.getAll(page, elements));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PizzaEntity> get(@PathVariable int id) {
        return ResponseEntity.ok(this.service.get(id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<PizzaEntity> getByName(@PathVariable String name) {
        return ResponseEntity.ok(this.service.getByName(name));
    }

    @GetMapping("/vegan")
    public ResponseEntity<Integer> getVeganCount() {
        return ResponseEntity.ok(this.service.vegan());
    }

    @GetMapping("/cheapest/{price}")
    public ResponseEntity<List<PizzaEntity>> getCheapestPizzas(@PathVariable Double price) {
        return ResponseEntity.ok(this.service.getCheapest(price));
    }

    @GetMapping("/with/{description}")
    public ResponseEntity<List<PizzaEntity>> getWith(@PathVariable String description) {
        return ResponseEntity.ok(this.service.getWith(description));
    }

    @GetMapping("/without/{description}")
    public ResponseEntity<List<PizzaEntity>> getWithout(@PathVariable String description) {
        return ResponseEntity.ok(this.service.getWithout(description));
    }

    @GetMapping("/available")
    public ResponseEntity<Page<PizzaEntity>> getAvailable(
        @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "8") int elements,
        @RequestParam(defaultValue = "price") String sortBy,
        @RequestParam(defaultValue = "ASC") String sortOrder) {
        return ResponseEntity.ok(this.service.getAvailable(page, elements, sortBy, sortOrder));
    }

    @PostMapping
    public ResponseEntity<PizzaEntity> add(@RequestBody PizzaEntity pizza) {
        if (pizza.getIdPizza() == null || !this.service.exists(pizza.getIdPizza())) {
            return ResponseEntity.ok(this.service.save(pizza));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping
    public ResponseEntity<PizzaEntity> update(@RequestBody PizzaEntity pizza) {
        if (pizza.getIdPizza() != null && this.service.exists(pizza.getIdPizza())) {
            return ResponseEntity.ok(this.service.save(pizza));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/price")
    public ResponseEntity<Void> updatePrice(@RequestBody UpdatePizzaPriceDto pizzaPriceDto) {
        if (this.service.exists(pizzaPriceDto.getPizzaId())) {
            this.service.updatePrice(pizzaPriceDto);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        if (this.service.exists(id)) {
            this.service.delete(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}

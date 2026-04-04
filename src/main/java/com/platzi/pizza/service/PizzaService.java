package com.platzi.pizza.service;

import com.platzi.pizza.persistence.entity.PizzaEntity;
import com.platzi.pizza.persistence.repository.PizzaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PizzaService {

    private final PizzaRepository pizzaRepository;

    @Autowired
    public PizzaService(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    public List<PizzaEntity> getAll() {
        return this.pizzaRepository.findAll();
    }

    public PizzaEntity findById(int id) {
        return this.pizzaRepository.findById(id).orElse(null);
    }

    public PizzaEntity save(PizzaEntity pizza) {
        return this.pizzaRepository.save(pizza);
    }

    public void delete(int id) {
        this.pizzaRepository.deleteById(id);
    }

    public boolean exists(int id) {
        return this.pizzaRepository.existsById(id);
    }
}

package com.platzi.pizza.service;

import com.platzi.pizza.persistence.entity.PizzaEntity;
import com.platzi.pizza.persistence.repository.PizzaPagSortRepository;
import com.platzi.pizza.persistence.repository.PizzaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PizzaService {

    private final PizzaRepository repository;
    private final PizzaPagSortRepository pagSortRepository;

    @Autowired
    public PizzaService(PizzaRepository repository, PizzaPagSortRepository pagSortRepository) {
        this.repository = repository;
        this.pagSortRepository = pagSortRepository;
    }

    public Page<PizzaEntity> getAll(int page, int elements) {
        Pageable pageRequest = PageRequest.of(page, elements);
        return this.pagSortRepository.findAll(pageRequest);
    }

    public List<PizzaEntity> getAvailable() {
        return this.repository.findAllByAvailableTrueOrderByPrice();
    }

    public PizzaEntity get(int id) {
        return this.repository.findById(id).orElse(null);
    }

    public PizzaEntity getByName(String name) {
        return this.repository.findFirstByAvailableTrueAndNameIgnoreCase(name);
    }

    public List<PizzaEntity> getWith(String ingredient) {
        return this.repository.findAllByAvailableTrueAndDescriptionContainingIgnoreCase(ingredient);
    }

    public List<PizzaEntity> getWithout(String ingredient) {
        return this.repository.findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(ingredient);
    }

   public List<PizzaEntity> getCheapest(double price) {
        return this.repository.findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(price);
    }

    public PizzaEntity save(PizzaEntity pizza) {
        return this.repository.save(pizza);
    }

    public void delete(int id) {
        this.repository.deleteById(id);
    }

    public boolean exists(int id) {
        return this.repository.existsById(id);
    }

    public int vegan() {
        return this.repository.countByVeganTrue();
    }
}

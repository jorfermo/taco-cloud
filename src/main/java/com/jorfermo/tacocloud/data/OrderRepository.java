package com.jorfermo.tacocloud.data;

import com.jorfermo.tacocloud.tacos.TacoOrder;

import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {

}

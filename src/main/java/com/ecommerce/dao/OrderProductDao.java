package com.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.model.OrderProduct;

public interface OrderProductDao extends JpaRepository<OrderProduct, Long> {

}

package com.ecommerce.dao;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.modal.Cart;


public interface CartDao extends JpaRepository<Cart, Long>{
   Optional<Cart> findByName(String name);



//
//   @Modifying
//   @Transactional
//   @Query("update Cart cart set cart.quantity=:qty,cart.price=:price WHERE cart.id=:cart_id")
//   void updateQtyByCartId(@Param("cart_id")Long cart_id,@Param("price")double price,@Param("qty")Integer qty);

}

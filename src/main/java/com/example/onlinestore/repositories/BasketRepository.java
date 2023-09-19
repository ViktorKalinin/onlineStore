package com.example.onlinestore.repositories;


import com.example.onlinestore.model.Basket;
import com.example.onlinestore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BasketRepository extends JpaRepository<Basket, Long> {
    Basket findByUser(User user);

    @Query("SELECT COUNT(p) from Basket b join b.products p where b.id = :basketId")
    int countProductsInBasket(@Param("basketId") long basketId);
}

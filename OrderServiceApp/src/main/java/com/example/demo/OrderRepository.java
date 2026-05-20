package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{

	List<Order> findByCustomerName(String customerName);

    List<Order> findByMobileNumber(String mobileNumber);

    @Query("SELECT o FROM Order o JOIN FETCH o.items WHERE o.id = :id")
    Order findOrderWithItems(@Param("id") Long id);

}

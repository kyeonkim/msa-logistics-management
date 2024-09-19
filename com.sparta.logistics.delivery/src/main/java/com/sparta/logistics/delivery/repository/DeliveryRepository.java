package com.sparta.logistics.delivery.repository;

import com.sparta.logistics.delivery.entity.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, UUID> {
    List<Delivery> findAllByDeliveryManagerIdAndIsDeletedFalse(UUID deliveryManagerId);
}

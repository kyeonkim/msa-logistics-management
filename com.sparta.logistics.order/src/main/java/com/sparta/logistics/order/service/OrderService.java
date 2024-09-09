package com.sparta.logistics.order.service;

import com.sparta.logistics.order.dto.OrderRequestDto;
import com.sparta.logistics.order.entity.Order;
import com.sparta.logistics.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Transactional
    public void createOrder(OrderRequestDto.Create request) {
        orderRepository.save(Order.create(request));
    }

    @Transactional
    public void updateOrder(UUID orderId, OrderRequestDto.Update request) {
        Order order = orderRepository.findById(orderId).orElseThrow(() ->
                new NullPointerException("해당 주문을 찾을 수 없습니다.")
        );

        if (request.getDeliveryId() != null) {
            order.setDeliveryId(request.getDeliveryId());
        }
        if (request.getAmount() != null) {
            order.setAmount(request.getAmount());
        }
        if (request.getStatus() != null) {
            order.setStatus(request.getStatus());
        }
    }

    @Transactional
    public void cancelOrder(UUID orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() ->
                new NullPointerException("해당 주문을 찾을 수 없습니다.")
        );
        order.updateIsDeleted();
    }
}

package com.sparta.logistics.delivery.application;

import com.sparta.logistics.delivery.application.dtos.DeliveryRouteRequestDtos;
import com.sparta.logistics.delivery.domain.DeliveryRoute;
import com.sparta.logistics.delivery.domain.DeliveryRouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeliveryRouteService {

    private final DeliveryRouteRepository deliveryRouteRepository;

    @Transactional
    public void createDeliveryRoutes(List<DeliveryRouteRequestDtos.CreateDto> requests) {
        for (DeliveryRouteRequestDtos.CreateDto request : requests) {
            DeliveryRoute deliveryRoute = DeliveryRoute.create(request);
            deliveryRouteRepository.save(deliveryRoute);
        }
    }

    @Transactional
    public void updateDeliveryRoute(UUID id, DeliveryRouteRequestDtos.UpdateDto request) {
        DeliveryRoute deliveryRoute = deliveryRouteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 배송 경로를 찾을 수 없습니다."));

        if (request.getRealDistance() != null) {
            deliveryRoute.setRealDistance(request.getRealDistance());
        }
        if (request.getRealTime() != null) {
            deliveryRoute.setRealTime(request.getRealTime());
        }
        if (request.getStatus() != null) {
            deliveryRoute.setStatus(request.getStatus());
        }
    }
}

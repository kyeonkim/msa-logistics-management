package com.sparta.logistics.hub.service;

import com.sparta.logistics.hub.dto.HubRequestDto;
import com.sparta.logistics.hub.dto.HubResponseDto;
import com.sparta.logistics.hub.entity.Hub;
import com.sparta.logistics.hub.repository.HubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HubService {

    private final HubRepository hubRepository;

    public HubResponseDto.Create createHub(HubRequestDto.Create requestDto) {
        return HubResponseDto.Create.of(hubRepository.save(Hub.create(requestDto)));
    }


    @Cacheable(cacheNames = "hubCache", key = "args[0]")
    public HubResponseDto.Get getHub(UUID hubId) {
        return HubResponseDto.Get.of(findById(hubId));
    }

    @Cacheable(cacheNames = "hubAllCache", key = "getMethodName()")
    public List<HubResponseDto.Get> getHubList() {
        return hubRepository.findAllAndIsDeletedFalse().stream().map(HubResponseDto.Get::of).toList();
    }

    @CachePut(cacheNames = "hubCache", key = "args[0].hubId")
    @CacheEvict(cacheNames = "hubAllCache", allEntries = true)
    @Transactional
    public HubResponseDto.Update updateHub(HubRequestDto.Update requestDto) {
        Hub hub = findById(requestDto.getHubId());
        hub.update(requestDto);
        return HubResponseDto.Update.of(hub);
    }


    @CacheEvict(cacheNames = {"hubAllCache", "hubCache", "hubSearchCache"}, allEntries = true)
    @Transactional
    public HubResponseDto.Delete deleteHub(UUID hubId) {
        Hub hub = findById(hubId);
        hub.delete();
        return HubResponseDto.Delete.of(hub);
    }

    @Cacheable(cacheNames = "hubSearchCache", key = "{args[0],args[1]}")
    public List<HubResponseDto.Get> searchHub(String address, String name) {
        return hubRepository.findAllByAddressAndName(address, name);
    }

    public Hub findById(UUID hubId){
        return hubRepository.findByIdAndIsDeletedFalse(hubId).orElseThrow(NoSuchElementException::new);
    }

}

package com.sparta.logistics.hub.presentation.dtos;

import com.sparta.logistics.hub.domain.entity.hubroot.HubRoute;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

public class HubRouteResponseDto {


    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Create{

        private UUID hubRouteId;

        public static Create of(HubRoute hubRoute){
            return Create.builder()
                    .hubRouteId(hubRoute.getId())
                    .build();
        }
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Get{

        private UUID hubRouteId;
        private UUID prevHubRouteId;
        private UUID currentHubId;
        private UUID nextHubRouteId;
        private Integer time;

        public static Get of(HubRoute hubRoute){
            return Get.builder()
                    .hubRouteId(hubRoute.getId())
                    .prevHubRouteId((hubRoute.getPrevHubRoute()) != null ? hubRoute.getPrevHubRoute().getId() : null)
                    .currentHubId(hubRoute.getCurrentHub().getId())
                    .nextHubRouteId((hubRoute.getNextHubRoute()) != null ? hubRoute.getNextHubRoute().getId() : null)
                    .time(hubRoute.getTime())
                    .build();
        }
    }



    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Update {
        private UUID hubRouteId;

        public static Update of(HubRoute hubRoute){
            return Update.builder()
                    .hubRouteId(hubRoute.getId())
                    .build();
        }
    }
}

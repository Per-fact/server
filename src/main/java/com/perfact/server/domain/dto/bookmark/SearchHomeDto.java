package com.perfact.server.domain.dto.bookmark;

import com.perfact.server.domain.Home;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SearchHomeDto {
    private double homeLat;
    private double homeLong;
    private String homeAddr;


    public SearchHomeDto(Home entity){
        homeLat = entity.getHomeLat();
        homeLong = entity.getHomeLong();
        homeAddr = entity.getHomeAddr();
    }


}

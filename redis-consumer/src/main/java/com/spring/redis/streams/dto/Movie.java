package com.spring.redis.streams.dto;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class Movie {
    private Integer id;
    private String name;
    private String productionHouse;
}

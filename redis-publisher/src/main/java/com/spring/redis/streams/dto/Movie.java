package com.spring.redis.streams.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Movie {
    private Integer id;
    private String name;
    private String productionHouse;
}

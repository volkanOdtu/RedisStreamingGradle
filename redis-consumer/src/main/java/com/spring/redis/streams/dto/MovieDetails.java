package com.spring.redis.streams.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieDetails {
    private Movie movie;
    private Boolean likes = false;
    private Boolean disLike = false;
    private Double rating;
}

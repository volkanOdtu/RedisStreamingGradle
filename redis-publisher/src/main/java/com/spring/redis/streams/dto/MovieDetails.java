package com.spring.redis.streams.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieDetails {
    private Movie movie;
    private Boolean like = false;
    private Boolean dislike = false;
    private Double rating;
}

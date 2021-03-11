package com.spring.redis.streams.config;

import com.spring.redis.streams.dto.MovieDetails;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.stream.ObjectRecord;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class MovieEventConsumer implements StreamListener<String , ObjectRecord<String, MovieDetails>> {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    @Autowired
    private ReactiveRedisTemplate<String ,String> redisTemplate;

    @Override
    @SneakyThrows
    public void onMessage(ObjectRecord<String, MovieDetails> message) {

        if(message.getValue().getLikes()){
            this.redisTemplate
                    .opsForZSet()
                    .incrementScore(message.getValue().getMovie().getName() ,"Likes" ,1)
                    .subscribe();
        }
        if(message.getValue().getDisLike()){
            this.redisTemplate
                    .opsForZSet()
                    .incrementScore(message.getValue().getMovie().getName() ,"Dislikes" ,1)
                    .subscribe();
        }

        this.redisTemplate.opsForZSet()
                .incrementScore(message.getValue().getMovie().getName() ,"Views" ,1)
                .subscribe();

        this.redisTemplate.opsForZSet()
                .incrementScore(message.getValue().getMovie().getName() ,"Rating" ,message.getValue().getRating())
                .subscribe();

        atomicInteger.incrementAndGet();
    }

    @Scheduled(fixedRate = 10000)
    public void showPublishedEventsSoFar(){

        System.out.println("Total Consumer :: " + atomicInteger.get());
    }
}


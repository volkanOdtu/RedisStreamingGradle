package com.spring.redis.streams.publisher;

import com.spring.redis.streams.dto.MovieDetails;
import com.spring.redis.streams.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.stream.ObjectRecord;
import org.springframework.data.redis.connection.stream.StreamRecords;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class MovieEventPublisher {
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    @Value("${stream.key}")
    private String streamKey;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ReactiveRedisTemplate<String, String> redisTemplate;

    @Scheduled(fixedRateString= "${publish.rate}")
    public void publishEvent(){
        MovieDetails movieDetails = movieRepository.getRandomMovie();

        ObjectRecord<String ,MovieDetails> record = StreamRecords.newRecord()
                                                    .ofObject(movieDetails)
                                                    .withStreamKey(streamKey);

        this.redisTemplate.opsForStream().add(record).subscribe(System.out::println);

        atomicInteger.incrementAndGet();
    }
    @Scheduled(fixedRate = 10000)
    public void  showPublishedEventsSoFar(){

        System.out.println("Total Events :: " +atomicInteger.get());
    }
}

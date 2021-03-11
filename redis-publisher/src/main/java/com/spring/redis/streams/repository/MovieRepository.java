package com.spring.redis.streams.repository;

import com.spring.redis.streams.dto.Movie;
import com.spring.redis.streams.dto.MovieDetails;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Repository
public class MovieRepository {
    List<Movie> MOVIE_LIST = new ArrayList<Movie>();

     MovieRepository(){
         MOVIE_LIST.add(new Movie(1, "Avengers End Game", "Marvel Studios"));
         MOVIE_LIST.add(new Movie(2, "Avengers Infinity War", "Marvel Studios"));
         MOVIE_LIST.add(new Movie(3, "Dark Knight", "Warner Bros"));
         MOVIE_LIST.add(new Movie(4, "Pulp Fiction", "MiraMax"));
         MOVIE_LIST.add(new Movie(5, "Fight Club", "Warner Bros"));
         MOVIE_LIST.add(new Movie(6, "Good Fellas", "Warner Bros"));
         MOVIE_LIST.add(new Movie(7, "Seven", "Warner Bros"));
         MOVIE_LIST.add(new Movie(8, "Cast Away", "ImageMovers Playtone"));
         MOVIE_LIST.add(new Movie(9, "Forest Gump", "The Tisch Company"));
         MOVIE_LIST.add(new Movie(10, "King Kong", "Warner Bros"));
         MOVIE_LIST.add(new Movie(11, "The Silence Of Lambs", "Strong Heart Productions"));
         MOVIE_LIST.add(new Movie(12, "Usual Suspects", "PolyGram Filmed Entertainment"));
         MOVIE_LIST.add(new Movie(13, "Green Mile", "Castle Rock Entertainment"));
         MOVIE_LIST.add(new Movie(14, "No Country For Old Men", "Scott Rudin Productions"));
         MOVIE_LIST.add(new Movie(15, "Train to Busan", "Next Entertainment World"));
         MOVIE_LIST.add(new Movie(16, "Parasite", "Barunson E&A"));
         MOVIE_LIST.add(new Movie(17, "Whiplash", "Sony Pictures"));
         MOVIE_LIST.add(new Movie(18, "The Prestige", "Warner Bros"));
         MOVIE_LIST.add(new Movie(19, "Joker", "Warner Bros"));
         MOVIE_LIST.add(new Movie(20, "Old Boy", "Show East"));
         MOVIE_LIST.add(new Movie(21, "I Saw Devil", "Peppermint and company"));
         MOVIE_LIST.add(new Movie(22, "The Perfect Murder", "Warner Bros"));
         MOVIE_LIST.add(new Movie(23, "The Chaser", "Snow Box"));
         MOVIE_LIST.add(new Movie(24, "Goodwill Hunting", "Be Gentlemen"));
         MOVIE_LIST.add(new Movie(25, "Snatch", "Columbia Pictures"));
     }

    public MovieDetails getRandomMovie(){
        Integer index = ThreadLocalRandom.current().nextInt(0 ,25);
        Movie movie = MOVIE_LIST.get(index);
        Random random = new Random();
        Integer value = random.ints(0,1000).findFirst().getAsInt();
        Double rating = random.doubles(1.0 ,10.0).findFirst().getAsDouble();

        //like mi ,dislike mi ve rating (1..10 arasi) random olarak olusturup her movie ye set ediyoruz
        return  new MovieDetails(movie ,value % 2 ==0 ,value % 2== 1 ,rating);
    }
}

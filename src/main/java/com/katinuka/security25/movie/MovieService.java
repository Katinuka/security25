package com.katinuka.security25.movie;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 @author    Katinuka
 @project   security25
 @version   1.0.0
 @since     18-Mar-25
 
 @see https://github.com/Katinuka/security25
 */
@Service
@AllArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;

    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    public Movie findById(String id) {
        return movieRepository.findById(id).orElse(null);
    }

    public void deleteById(String id) {
        movieRepository.deleteById(id);
    }

    public void create(Movie movie) {
        movieRepository.save(movie);
    }

    public void update(Movie movie) {
        movieRepository.save(movie);
    }
}

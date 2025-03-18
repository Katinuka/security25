package com.katinuka.security25.movie;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 @author    Katinuka
 @project   security25
 @version   1.0.0
 @since     18-Mar-25
 
 @see https://github.com/Katinuka/security25
 */
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {
    private final MovieService movieService;

    @GetMapping
    public List<Movie> getAllMovies() {
        return movieService.findAll();
    }

    @GetMapping("/{id}")
    public Movie getMovieById(@PathVariable String id) {
        return movieService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteMovieById(@PathVariable String id) {
        movieService.deleteById(id);
    }

    @PostMapping
    public void createMovie(@RequestBody Movie movie) {
        movieService.create(movie);
    }

    @PutMapping
    public void updateMovie(@RequestBody Movie movie) {
        movieService.update(movie);
    }

    @GetMapping("/hello/user")
    public String helloUser() {
        return "Hello User!";
    }

    @GetMapping("hello/admin")
    public String helloAdmin() {
        return "Hello Admin!";
    }

    @GetMapping("hello/unknown")
    public String helloUnknown() {
        return "Hello Unknown!";
    }
}

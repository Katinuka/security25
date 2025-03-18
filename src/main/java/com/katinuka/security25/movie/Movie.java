package com.katinuka.security25.movie;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

/*
 @author    Katinuka
 @project   security25
 @version   1.0.0
 @since     18-Mar-25
 
 @see https://github.com/Katinuka/security25
 */

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Movie {
    @Id
    private String id;
    private String name;
    private String genre;
    private LocalDate date;
    private double rating;
    private String cast;
    private int minAge;
    private String description;

    public Movie(String name, String genre, LocalDate date, double rating, String cast, int minAge, String description) {
        this.name = name;
        this.genre = genre;
        this.date = date;
        this.rating = rating;
        this.cast = cast;
        this.minAge = minAge;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        return id.equals(movie.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}

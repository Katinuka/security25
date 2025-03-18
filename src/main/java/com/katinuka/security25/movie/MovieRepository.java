package com.katinuka.security25.movie;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/*
 @author    Katinuka
 @project   security25
 @version   1.0.0
 @since     18-Mar-25
 
 @see https://github.com/Katinuka/security25
 */
@Repository
public interface MovieRepository extends MongoRepository<Movie, String> {
}

package com.jakobjoachim.hibernatesandbox;

import com.jakobjoachim.hibernatesandbox.entities.Post;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {
}

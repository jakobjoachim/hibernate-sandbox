package com.jakobjoachim.hibernatesandbox;

import com.jakobjoachim.hibernatesandbox.entities.Comment;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {
}

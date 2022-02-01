package com.jakobjoachim.hibernatesandbox;

import java.util.ArrayList;
import java.util.List;

import com.jakobjoachim.hibernatesandbox.entities.Author;
import com.jakobjoachim.hibernatesandbox.entities.Comment;
import com.jakobjoachim.hibernatesandbox.entities.Post;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.support.TransactionTemplate;

@SpringBootTest
@Slf4j
public class IdMatchingTest {

	@Autowired
	TransactionTemplate transactionTemplate;

	@Autowired
	PostRepository postRepository;

	@Test
	void idMatchingOneToMany() {
		Post initialPost = Post.builder().comments(new ArrayList<>(List.of(Comment.builder().comment("first").build()))).build();

		transactionTemplate.execute(status -> postRepository.save(initialPost));

		Long postId = initialPost.getId();
		Long commentId = initialPost.getComments().get(0).getId();

		Comment updateComment = Comment.builder().id(commentId).comment("update").build();
		Comment second = Comment.builder().id(commentId).comment("update2").build();

		log.info("-----------INSERTING POST DONE-----------");

		transactionTemplate.executeWithoutResult(status -> {
			Post postFromDb = postRepository.findById(postId).get();
			log.info("-----------FINDIG POST DONE--------------");
			postFromDb.getComments().clear();
			postFromDb.getComments().add(updateComment);
			postFromDb.getComments().add(second);
			postRepository.save(postFromDb);
			log.info("-----------SAVE POST DONE----------------");
		});
	}

	@Test
	void idMatchingManyToOne() {
		Post initialPost = Post.builder().author(Author.builder().name("jef").build()).build();

		transactionTemplate.execute(status -> postRepository.save(initialPost));

		Long postId = initialPost.getId();
		Long authorId = initialPost.getAuthor().getId();

		Author updateAuthor = Author.builder().id(authorId).name("jeff").build();

		transactionTemplate.executeWithoutResult(status -> {
			Post postFromDb = postRepository.findById(postId).get();
			postFromDb.setAuthor(updateAuthor);
			postRepository.save(postFromDb);
		});
	}
}

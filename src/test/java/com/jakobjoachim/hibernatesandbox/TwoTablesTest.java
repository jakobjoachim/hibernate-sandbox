package com.jakobjoachim.hibernatesandbox;

import com.jakobjoachim.hibernatesandbox.entities.Author;
import com.jakobjoachim.hibernatesandbox.entities.Comment;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.support.TransactionTemplate;

@SpringBootTest
@Slf4j
public class TwoTablesTest {

	@Autowired
	TransactionTemplate transactionTemplate;

	@Autowired
	CommentRepository commentRepository;

	@Test
	void duplicateDataInTwoTables() {
		Comment comment = Comment.builder().comment("Finde ich gut").author(Author.builder().name("jeff").build()).build();

		commentRepository.save(comment);
	}
}

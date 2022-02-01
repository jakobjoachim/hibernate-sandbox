package com.jakobjoachim.hibernatesandbox;

import java.util.ArrayList;
import java.util.List;

import com.jakobjoachim.hibernatesandbox.entities.Comment;
import com.jakobjoachim.hibernatesandbox.entities.Post;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.support.TransactionTemplate;

@DataJpaTest
@Slf4j
public class ListReplacementTest {

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    PostRepository postRepository;

    @Test
    void replaceList() {
        Post initialPost = Post.builder()
            .comments( new ArrayList<>( List.of( Comment.builder().comment( "first" ).build() ) ) )
            .build();

        Post post = testEntityManager.persistFlushFind( initialPost );

        log.info( "-----------FINDIG POST DONE--------------" );

        List<Comment> newCommentList = new ArrayList<>( 1 );
        newCommentList.add( Comment.builder().comment( "first" ).build() );
        post.setComments( newCommentList );
        postRepository.save( post );
        testEntityManager.flush();
        log.info( "-----------SAVE POST DONE----------------" );
    }
}

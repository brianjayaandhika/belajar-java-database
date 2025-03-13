package programmer.zaman.now.database;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import programmer.zaman.now.database.entity.Comment;
import programmer.zaman.now.database.repository.CommentRepository;
import programmer.zaman.now.database.repository.CommentRepositoryImpl;

import java.util.List;

public class RepositoryTest {

    private CommentRepository commentRepository;

    @BeforeEach
    void setUp() {
        commentRepository = new CommentRepositoryImpl();
    }

    @Test
    void testInsert() {
        Comment comment = new Comment("eko@test.com", "Haloo");
        commentRepository.insert(comment);

        Assertions.assertNotNull(comment.getId());
    }

    @Test
    void testFindById() {
        int idToFind = 5199;
        Comment comment = commentRepository.findById(idToFind);
        Assertions.assertNotNull(comment);

        Comment notFound = commentRepository.findById(99999999);
        Assertions.assertNull(notFound);

    }

    @Test
    void testFindAll() {
        List<Comment> comments = commentRepository.findAll();
        Assertions.assertNotNull(comments);
    }

    @Test
    void testFindAllByEmail() {
        String emailToFind = "findEmail@test.com";
        List<Comment> comments = commentRepository.findAllByEmail(emailToFind);
        Assertions.assertNotNull(comments);

        String notFound = "notFound@test.com";
        List<Comment> shouldBeNull = commentRepository.findAllByEmail(notFound);
        Assertions.assertNotNull(shouldBeNull);
    }
}

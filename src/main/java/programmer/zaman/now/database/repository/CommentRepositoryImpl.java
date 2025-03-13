package programmer.zaman.now.database.repository;

import programmer.zaman.now.database.ConnectionUtil;
import programmer.zaman.now.database.entity.Comment;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommentRepositoryImpl implements CommentRepository {

    @Override
    public void insert(Comment comment) {
        String sql = "INSERT INTO comments(email, comment) VALUES(?, ?)";

        try (Connection connection = ConnectionUtil.getDataSource().getConnection();
             PreparedStatement prep = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            prep.setString(1, comment.getEmail());
            prep.setString(2, comment.getComment());

            int updated = prep.executeUpdate();
            System.out.println(updated + " data affected");

            try (ResultSet res = prep.getGeneratedKeys()) {
                if (res.next()) {
                    int generatedId = res.getInt(1);
                    comment.setId(generatedId);
                    System.out.println("New id #" + generatedId);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting comment", e);
        }
    }

    @Override
    public Comment findById(Integer id) {
        String sql = "SELECT * FROM comments WHERE id = ?";

        try (Connection connection = ConnectionUtil.getDataSource().getConnection();
             PreparedStatement prep = connection.prepareStatement(sql)
        ) {

            prep.setInt(1, id);

            try (ResultSet res = prep.executeQuery()) {
                if (res.next()) {
                    return new Comment(
                            res.getInt("id"),
                            res.getString("email"),
                            res.getString("comment")
                    );
                } else {
                    return null;
                }
            }


        } catch (SQLException e) {
            throw new RuntimeException("Error find by id", e);
        }
    }

    @Override
    public List<Comment> findAll() {
        String sql = "SELECT id, email, comment FROM comments";
        List<Comment> comments = new ArrayList<>();

        try (Connection connection = ConnectionUtil.getDataSource().getConnection();
             Statement statement = connection.createStatement()) {
            try (ResultSet res = statement.executeQuery(sql)) {
                while (res.next()) {
                    comments.add(new Comment(
                            res.getInt("id"),
                            res.getString("email"),
                            res.getString("comment")
                    ));
                }
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return comments;
    }

    @Override
    public List<Comment> findAllByEmail(String email) {
        String sql = "SELECT id, email, comment FROM comments WHERE email = ?";
        List<Comment> comments = new ArrayList<>();

        try (Connection connection = ConnectionUtil.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, email);

            try (ResultSet res = statement.executeQuery()) {
                while (res.next()) {
                    comments.add(new Comment(
                            res.getInt("id"),
                            res.getString("email"),
                            res.getString("comment")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return comments;
    }
}

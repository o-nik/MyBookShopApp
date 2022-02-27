package com.example.MyBookShopApp.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public BookService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> getBooksData(){

        List<Book> books = jdbcTemplate.query("SELECT b.id, b.price, b.price_old, b.title, a.last_name, a.first_name  \n" +
                "FROM BOOKS b\n" +
                "join authors a on b.author_id = a.id", (ResultSet rs, int rownum)->{
            Book book = new Book();
            book.setId(rs.getInt("id"));
            book.setAuthor(rs.getString("last_name") + " " + rs.getString("first_name"));
            book.setTitle(rs.getString("title"));
            book.setPriceOld(rs.getInt("price_old"));
            book.setPrice(rs.getInt("price"));
            return book;
        });
        return new ArrayList<>(books);
    }
}

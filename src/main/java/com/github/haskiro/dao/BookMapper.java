//package com.github.haskiro.dao;
//
//import com.github.haskiro.models.Book;
//import org.springframework.jdbc.core.RowMapper;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class BookMapper implements RowMapper {
//    @Override
//    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
//        Book book = new Book();
//
//        book.setBookId(rs.getInt("book_id"));
//        Integer personId = (Integer) rs.getObject("person_id");
//        if (personId != null) book.setPersonId(personId);
//        book.setName(rs.getString("name"));
//        book.setAuthor(rs.getString("author"));
//        book.setYear(rs.getInt("year"));
//
//        return book;
//    }
//}

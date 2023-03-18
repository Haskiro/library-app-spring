package com.github.haskiro.springlibrary.repositories;

import com.github.haskiro.springlibrary.models.Book;
import com.github.haskiro.springlibrary.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {
    @Modifying
    @Query("UPDATE Book book SET book.owner=:owner, book.takenAt=:takenAt  WHERE book.id=:id")
    void setOwner(@Param("id") int id, @Param("owner") Person person, @Param("takenAt")OffsetDateTime takenAt);
    @Modifying
    @Query("UPDATE Book book SET book.owner=NULL, book.takenAt=NULL WHERE book.id=:id")
    void unsetOwner(@Param("id") int id);

    List<Book> findByTitleContainingIgnoreCase(String value);
}

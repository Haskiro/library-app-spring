package com.github.haskiro.repositories;

import com.github.haskiro.models.Book;
import com.github.haskiro.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {
    @Modifying
    @Query("UPDATE Book book SET book.owner=:owner  WHERE book.id=:id")
    void setOwner(@Param("owner") Person person, @Param("id") int id);
}

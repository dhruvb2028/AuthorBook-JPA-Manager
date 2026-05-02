package com.example.library.repository;

import com.example.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    
    // Custom query method performing an inner join between Book and Author
    @Query("SELECT b FROM Book b JOIN b.author a")
    List<Book> findAllBooksWithAuthors();
}

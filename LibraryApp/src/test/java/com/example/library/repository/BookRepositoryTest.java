package com.example.library.repository;

import com.example.library.model.Author;
import com.example.library.model.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    void testFindAllBooksWithAuthors() {
        Author author = new Author("Test Author", "Test Nationality");
        author = authorRepository.save(author);

        Book book = new Book("Test Book", "Test Genre", author);
        bookRepository.save(book);

        List<Book> books = bookRepository.findAllBooksWithAuthors();

        assertFalse(books.isEmpty());
        assertEquals("Test Book", books.get(0).getTitle());
        assertNotNull(books.get(0).getAuthor());
        assertEquals("Test Author", books.get(0).getAuthor().getName());
    }
}

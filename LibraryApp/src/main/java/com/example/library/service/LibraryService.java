package com.example.library.service;

import com.example.library.model.Author;
import com.example.library.model.Book;
import com.example.library.repository.AuthorRepository;
import com.example.library.repository.BookRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class LibraryService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    // Populate the database with sample data (10 rows in each table)
    @PostConstruct
    @Transactional
    public void populateDatabase() {
        if (authorRepository.count() == 0) {
            for (int i = 1; i <= 10; i++) {
                Author author = new Author("Author " + i, "Nationality " + i);
                authorRepository.save(author);
                
                Book book = new Book("Book Title " + i, "Genre " + i, author);
                bookRepository.save(book);
            }
        }
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public List<Book> getAllBooks() {
        // Fetch using custom query
        return bookRepository.findAllBooksWithAuthors();
    }

    public Author getAuthorById(Long id) {
        return authorRepository.findById(id).orElse(null);
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Transactional
    public void saveAuthor(Author author) {
        authorRepository.save(author);
    }

    @Transactional
    public void saveBook(Book book) {
        if (book.getAuthor() == null || book.getAuthor().getId() == null) {
            throw new IllegalArgumentException("Book must have an associated author");
        }
        bookRepository.save(book);
    }

    @Transactional
    public void updateBook(Book book) {
        bookRepository.save(book);
    }
}

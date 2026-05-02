package com.example.library.service;

import com.example.library.model.Author;
import com.example.library.model.Book;
import com.example.library.repository.AuthorRepository;
import com.example.library.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LibraryServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private LibraryService libraryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllAuthors() {
        Author author1 = new Author("John Doe", "American");
        Author author2 = new Author("Jane Smith", "British");
        when(authorRepository.findAll()).thenReturn(Arrays.asList(author1, author2));

        List<Author> authors = libraryService.getAllAuthors();
        
        assertEquals(2, authors.size());
        verify(authorRepository, times(1)).findAll();
    }

    @Test
    void testGetAllBooks() {
        Author author = new Author("John Doe", "American");
        Book book1 = new Book("Book 1", "Sci-Fi", author);
        Book book2 = new Book("Book 2", "Fantasy", author);
        when(bookRepository.findAllBooksWithAuthors()).thenReturn(Arrays.asList(book1, book2));

        List<Book> books = libraryService.getAllBooks();
        
        assertEquals(2, books.size());
        verify(bookRepository, times(1)).findAllBooksWithAuthors();
    }

    @Test
    void testSaveBook() {
        Author author = new Author("John Doe", "American");
        author.setId(1L);
        Book book = new Book("New Book", "Action", author);
        
        libraryService.saveBook(book);
        
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    void testSaveBookWithoutAuthor() {
        Book book = new Book("New Book", "Action", new Author());
        // Author has no ID set
        
        assertThrows(IllegalArgumentException.class, () -> {
            libraryService.saveBook(book);
        });
        
        verify(bookRepository, never()).save(book);
    }
}

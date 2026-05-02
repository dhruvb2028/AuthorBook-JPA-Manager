package com.example.library.controller;

import com.example.library.model.Author;
import com.example.library.model.Book;
import com.example.library.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LibraryController {

    @Autowired
    private LibraryService libraryService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("authors", libraryService.getAllAuthors());
        model.addAttribute("books", libraryService.getAllBooks());
        return "index";
    }

    @GetMapping("/addAuthor")
    public String showAddAuthorForm(Model model) {
        model.addAttribute("author", new Author());
        return "addAuthor";
    }

    @PostMapping("/addAuthor")
    public String addAuthor(@ModelAttribute("author") Author author, Model model) {
        try {
            libraryService.saveAuthor(author);
            return "redirect:/";
        } catch (Exception e) {
            model.addAttribute("error", "Error adding author: " + e.getMessage());
            return "addAuthor";
        }
    }

    @GetMapping("/addBook")
    public String showAddBookForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("authors", libraryService.getAllAuthors());
        return "addBook";
    }

    @PostMapping("/addBook")
    public String addBook(@ModelAttribute("book") Book book, Model model) {
        try {
            libraryService.saveBook(book);
            return "redirect:/";
        } catch (Exception e) {
            model.addAttribute("error", "Error adding book: " + e.getMessage());
            model.addAttribute("authors", libraryService.getAllAuthors());
            return "addBook";
        }
    }

    @GetMapping("/updateBook/{id}")
    public String showUpdateBookForm(@PathVariable("id") Long id, Model model) {
        Book book = libraryService.getBookById(id);
        if (book == null) {
            return "redirect:/";
        }
        model.addAttribute("book", book);
        model.addAttribute("authors", libraryService.getAllAuthors());
        return "updateBook";
    }

    @PostMapping("/updateBook")
    public String updateBook(@ModelAttribute("book") Book book, Model model) {
        try {
            libraryService.updateBook(book);
            return "redirect:/";
        } catch (Exception e) {
            model.addAttribute("error", "Error updating book: " + e.getMessage());
            model.addAttribute("authors", libraryService.getAllAuthors());
            return "updateBook";
        }
    }

    @GetMapping("/updateAuthor/{id}")
    public String showUpdateAuthorForm(@PathVariable("id") Long id, Model model) {
        Author author = libraryService.getAuthorById(id);
        if (author == null) {
            return "redirect:/";
        }
        model.addAttribute("author", author);
        return "updateAuthor";
    }

    @PostMapping("/updateAuthor")
    public String updateAuthor(@ModelAttribute("author") Author author, Model model) {
        try {
            libraryService.saveAuthor(author);
            return "redirect:/";
        } catch (Exception e) {
            model.addAttribute("error", "Error updating author: " + e.getMessage());
            return "updateAuthor";
        }
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception ex) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("message", ex.getMessage());
        return modelAndView;
    }
}

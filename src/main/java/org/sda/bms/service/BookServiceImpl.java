package org.sda.bms.service;

import org.sda.bms.model.Author;
import org.sda.bms.model.Book;
import org.sda.bms.repository.AuthorRepository;
import org.sda.bms.repository.BookRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public void create(String title, String description, int authorid) {
        if (title == null || description.isBlank() || description.isEmpty()) {
            throw new IllegalArgumentException(
                    "Provided description is empty or blank. Insert a valid value"
            );
        }
        if (authorid <= 0) {
            throw new IllegalArgumentException(
                    "Provided id is negative or 0. Provided a valid value"
            );
        }
        Optional<Author> authorOptional = authorRepository.findById(authorid);
        if (authorOptional.isEmpty()) {
            throw new EntityNotFoundException(
                    "Author with provided is was not found in the system"
            );
        }
        Author author = authorOptional.get();

        Book book = new Book(title,description);
        book.setAuthor(author);
        bookRepository.create(book);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(int id) {
        if (id <= 0){
            throw new IllegalArgumentException(
                    "Provided id is negative or 0. Provided a valid value"
            );
        }
        return bookRepository.findById(id);

    }

    @Override
    public void deleteById(int id) {
        if (id <= 0){
            throw new IllegalArgumentException(
                    "Provided id is negative or 0. Provided a valid value"
            );
        }
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isEmpty()){
            throw new EntityNotFoundException(
                    "Book with provided is was not found in the system"
            );
        }
        Book book = optionalBook.get();
        bookRepository.delete(book);
    }

    @Override
    public void updateById(int id, String title, String description) {
        if (id <= 0) {
            throw new IllegalArgumentException(
                    "Provided id is negative or 0. Provided a valid value"
            );
        }
        if (title == null || description.isBlank() || description.isEmpty()) {
            throw new IllegalArgumentException(
                    "Provided description is empty or blank. Insert a valid value"
            );
        }
        if (id <= 0){
            throw new IllegalArgumentException(
                    "Provided id is negative or 0. Provided a valid value"
            );
        }
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isEmpty()){
            throw new EntityNotFoundException(
                    "Book with provided is was not found in the system"
            );
        }
        Book book = optionalBook.get();
        book.setTitle(title);
        book.setDescription(description);

        bookRepository.update(book);
    }
}

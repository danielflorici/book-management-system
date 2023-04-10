package org.sda.bms.controller;

import org.sda.bms.model.Book;
import org.sda.bms.repository.exception.EntityCreationFailedException;
import org.sda.bms.repository.exception.EntityDeleteFailedException;
import org.sda.bms.repository.exception.EntityFetchingFailedException;
import org.sda.bms.repository.exception.EntityUpdateFailedException;
import org.sda.bms.service.BookService;


import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class BookController {
    //dependencies
    private final BookService bookService;
    private final Scanner scanner;

    public BookController(BookService bookService, Scanner scanner) {
        this.bookService = bookService;
        this.scanner = scanner;
    }

    public void create() {
        try {
            System.out.println("Please insert title:");
            String title = scanner.nextLine().trim();
            System.out.println("Please insert description:");
            String description = scanner.nextLine().trim();
            System.out.println("Please provide author id:");
            int authorId = Integer.parseInt(scanner.nextLine().trim());

            bookService.create(title, description, authorId);
            System.out.println("Book successfully create:");
        } catch (NumberFormatException e) {
            System.err.println("Provided id is not a number. Provide a valid value ");
        } catch (EntityNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (EntityFetchingFailedException e) {
            System.err.println(e.getMessage());
        } catch (EntityCreationFailedException e) {
            System.err.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println("Internal server error. Please contact your administrator.");
        }
    }

    public void displayAll() {
        try {
            List<Book> existingBooks = bookService.findAll();
            if (existingBooks.isEmpty()) {
                System.out.println("No books found in the system");
            } else {
                for (Book book : existingBooks) {
                    System.out.println(
                            "Id: " + book.getId() +
                                    " Title: " + book.getTitle() +
                                    " Author: " + book.getAuthor().getFirstName() +
                                    " " + book.getAuthor().getLastName()
                    );
                }
            }
        } catch (EntityFetchingFailedException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println("Internal server error. Please contact your administrator.");
        }
    }

    public void displayById() {
        try {
            System.out.println("Please provide book id: ");
            int bookId = Integer.parseInt(scanner.nextLine().trim());
            Optional<Book> optionalBook = bookService.findById(bookId);
            if (optionalBook.isEmpty()) {
                System.out.println(
                        "Book was not found in the system"
                );
            } else {
                Book book = optionalBook.get();
                System.out.println("Id: " + book.getId());
                System.out.println("Title: " + book.getTitle());
                System.out.println("Description: " + book.getDescription());
                System.out.println("Author: " + book.getAuthor());
                System.out.println("Author Id: " + book.getAuthor().getId());
                System.out.println("Author First Name: " + book.getAuthor().getFirstName());
                System.out.println("Author Last Name: " + book.getAuthor().getLastName());
            }
        } catch (NumberFormatException e) {
            System.err.println("Provided id is not a number. Provide a valid value ");
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        } catch (EntityFetchingFailedException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println("Internal server error. Please contact your administrator.");
        }
    }

    private static String formatBookDescription(String description) {
        String result = "";

        final int maxNumberOfWords = 5;
        String[] words = description.split(" ");

        for (int index = 0; index < words.length; index++) {
            if (index % maxNumberOfWords == 0) {
                result = result + "\t";
            }
            result = result + " " + words[index];
        }
        return result;
    }

    public void deleteById() {
        try {
            System.out.println("Please provide book id: ");
            int bookId = Integer.parseInt(scanner.nextLine().trim());

            bookService.deleteById(bookId);
            System.out.println("Book was successfully deleted");
        } catch (NumberFormatException e) {
            System.err.println("Provided id is not a number. Provide a valid value ");
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        } catch (EntityFetchingFailedException e) {
            System.err.println(e.getMessage());
        } catch (EntityDeleteFailedException e) {
            System.err.println(e.getMessage());
        } catch (EntityNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println("Internal server error. Please contact your administrator.");
        }
    }

    public void updateById() {
        try {
            System.out.println("Please provide book id: ");
            int bookId = Integer.parseInt(scanner.nextLine().trim());
            System.out.println("Please insert title:");
            String title = scanner.nextLine().trim();
            System.out.println("Please insert description:");
            String description = scanner.nextLine().trim();
            bookService.updateById(bookId, title, description);
            System.out.println("Update Book successully");
        } catch (NumberFormatException e) {
            System.err.println("Provided id is not a number. Provide a valid value ");
        }catch (IllegalArgumentException e){
            System.err.println(e.getMessage());
        }catch (EntityFetchingFailedException e){
            System.err.println(e.getMessage());
        }catch (EntityUpdateFailedException e){
            System.err.println(e.getMessage());
        }catch (EntityNotFoundException e){
            System.err.println(e.getMessage());
        }catch (Exception e) {
            System.err.println("Internal server error. Please contact your administrator.");
        }
    }
}

    
        

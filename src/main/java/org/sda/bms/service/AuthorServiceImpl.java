package org.sda.bms.service;

import org.sda.bms.model.Author;
import org.sda.bms.repository.AuthorRepository;

public class AuthorServiceImpl implements AuthorService {
    private static final String NAME_VALIDATION_REGEX = "^[a-zA-Z]+$";
    //dependencies
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void create(String firstName, String lastName) {
        if (firstName == null || firstName.isBlank() || firstName.isEmpty()) {
            throw new IllegalArgumentException(
                    "Provided first name is empty or blank.Provide a valid value."
            );
        }
        if (lastName == null || firstName.isBlank() || lastName.isEmpty()) {
            throw new IllegalArgumentException(
                    "Provided first name is empty or blank.Provide a valid value."
            );
        }
        if (!firstName.matches(NAME_VALIDATION_REGEX)) {
            throw new IllegalArgumentException("" +
                    "Provided first name contains invalid characters. Provide a valid value "
            );
        }
        if (!lastName.matches(NAME_VALIDATION_REGEX)) {
            throw new IllegalArgumentException("" +
                    "Provided first name contains invalid characters. Provide a valid value "
            );
        }
        Author author = new Author(firstName, lastName);
        authorRepository.create(author);
    }
}

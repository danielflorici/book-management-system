package org.sda.bms.repository;

import org.sda.bms.model.Book;

public class BookRepositoriImpl extends BaseRepositoryImpl<Book> implements BookRepository{
    public BookRepositoriImpl() {
        super(Book.class);
    }
}

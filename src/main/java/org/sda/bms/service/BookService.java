package org.sda.bms.service;

import org.sda.bms.model.Book;

public interface BookService {
    void create (String title,String description,int authorId);
}

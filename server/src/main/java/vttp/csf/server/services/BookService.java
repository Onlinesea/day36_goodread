package vttp.csf.server.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp.csf.server.models.Book;
import vttp.csf.server.repositories.BookRepository;

@Service
public class BookService {
    
    @Autowired
    private BookRepository bookRepo;

    public List<Book> getBooks(){

        return bookRepo.getBooks();
    }

    public Optional<Book> getBookById(String bookId){
        return bookRepo.getBook(bookId);
    }

}

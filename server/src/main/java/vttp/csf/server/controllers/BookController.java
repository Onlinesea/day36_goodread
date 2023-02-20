package vttp.csf.server.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import vttp.csf.server.models.Book;
import vttp.csf.server.services.BookService;

@Controller
@RequestMapping(path="/api")
public class BookController {
    
    @Autowired
    private BookService svc;

    @GetMapping(path="/books")
    @ResponseBody
    // Responsebody is to state that it returns a object instead of a view
    public ResponseEntity<String> getBooks(){

        List<Book> books = svc.getBooks();
        JsonArrayBuilder arrbuilder = Json.createArrayBuilder();
        svc.getBooks().stream()
        .forEach(v->{arrbuilder.add(v.toBookSummary());
        });


        return ResponseEntity.ok(arrbuilder.build().toString());
    }

    @GetMapping(path="/book/{bookId}")
    @ResponseBody
    public ResponseEntity<String>getBookById(@PathVariable String bookId){

        System.out.println("BookId entered >>>>> " + bookId);
        Optional<Book> opt =svc.getBookById(bookId);
        if(opt.isEmpty())
            return ResponseEntity.status(404).body("Book id not found");

            System.out.println("data send out >>> " + opt.get().toBook().toString());
        // If there is a a result from the database
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(opt.get().toBook().toString());
        
    }
}
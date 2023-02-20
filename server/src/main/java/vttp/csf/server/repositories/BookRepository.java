package vttp.csf.server.repositories;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vttp.csf.server.models.Book;
import static vttp.csf.server.models.Queries.*;

@Repository
public class BookRepository {


    @Autowired
    private JdbcTemplate template;

    
    public List<Book> getBooks(){

        // From sql it uses sqlrowset to find the values
        SqlRowSet rs = template.queryForRowSet(SQL_SELECT_BOOKS); 

        List<Book> results = new LinkedList<>();
        while(rs.next())
            results.add(Book.create(rs));

        return results;
        

    }

    public Optional<Book> getBook(String bookId) {
        System.out.println("BookRepo search bookid >>>>> " + bookId);
        SqlRowSet rs = template.queryForRowSet(SQL_SELECT_BOOKS_BY_BOOKID, bookId);
        if (!rs.next())
            return Optional.empty();

        return Optional.of(Book.create(rs));
    }


}

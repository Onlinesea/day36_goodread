package vttp.csf.server.models;

import org.springframework.jdbc.support.rowset.SqlRowSet;
import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Book {

    private String bookId;
    private String title;
    private String authors;
    private String description;



    //Genrate all the Getter and setter
    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString(){
        return "Book[bookId=%s, title=%s]".formatted(bookId, title);
    }

    public JsonObject toBookSummary() {
        return Json.createObjectBuilder()
            .add("bookId", bookId)
            .add("title", title)
            .build();
    }

    //Return the JsonObject version of the book 
    public JsonObject toBook(){
        return Json.createObjectBuilder()
        .add("bookId", bookId)
        .add("title", title)
        .add("authors", authors)
        .add("description", description)
        .build();
    }

    //Creating book from the rs 
    public static Book create(SqlRowSet rs){
        
        //Instancing a new book 
        Book b = new Book();
        b.setBookId(rs.getString("book_id"));
        b.setTitle(rs.getString("title"));
        b.setAuthors(rs.getString("authors"));
        b.setDescription(rs.getString("description"));

        return b;
    }
}

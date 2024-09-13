package com.example.crudapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    // Get All Books
    @GetMapping
    public List<Book> getAllBooks(){
        return bookService.getAllBooks();
    }

    // Get a specific book by ID
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id){
        Optional<Book> book = bookService.getBookById(id);
        if (book.isPresent()){
            return ResponseEntity.ok(book.get()); // returns HTTP 200 with book data
        }else {
            return ResponseEntity.notFound().build(); //returns HTTP 404
        }
    }

    // Add a new book
    @PostMapping
    public Book createBook(@RequestBody Book book){
        return bookService.addBook((book));
    }

    // Update an existing book
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book updatedBook){
        Book updated = bookService.updateBook(id,updatedBook);
        if(updated != null){
            return ResponseEntity.ok(updated); // Returns HTTP 200
        } else {
            return ResponseEntity.notFound().build(); // Returns HTTP 404 if book not found
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build(); // HTTP 204 ( No content or successful deletion)
    }
}

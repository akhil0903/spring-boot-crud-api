package com.example.crudapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    // retrieves all the books from the database
    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    // finds all the books by id
    public Optional<Book> getBookById(Long id){
        return bookRepository.findById(id);
    }

    // method to add new books
    public Book addBook(Book book){
        return bookRepository.save(book);
    }

    //method to update existing book
    public Book updateBook(Long id, Book updatedBook){
        Optional<Book> optionalBook = bookRepository.findById(id);
        if(optionalBook.isPresent()){
            Book book = optionalBook.get();
            book.setTitle(updatedBook.getTitle());
            book.setAuthor(updatedBook.getAuthor());
            book.setPrice(updatedBook.getPrice());
            return bookRepository.save(book);
        }
        return null;
    }

    // method to delete a book by ID
    public void deleteBook(Long id){
        bookRepository.deleteById(id);
    }

}

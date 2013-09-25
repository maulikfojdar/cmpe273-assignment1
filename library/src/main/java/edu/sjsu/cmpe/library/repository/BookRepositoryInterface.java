package edu.sjsu.cmpe.library.repository;

import java.util.List;

import javax.ws.rs.core.Response;

import edu.sjsu.cmpe.library.domain.Author;
import edu.sjsu.cmpe.library.domain.Book;
import edu.sjsu.cmpe.library.domain.Review;


/**
 * Book repository interface.
 * 
 * What is repository pattern?
 * 
 * @see http://martinfowler.com/eaaCatalog/repository.html
 */
public interface BookRepositoryInterface {
    /**
     * Save a new book in the repository
     * 
     * @param newBook
     *            a book instance to be create in the repository
     * @return a newly created book instance with auto-generated ISBN
     */
    Book saveBook(Book newBook);
    
    Response deleteBook(Long isbn);

    /**
     * Retrieve an existing book by ISBN
     * 
     * @param isbn
     *            a valid ISBN
     * @return a book instance
     */
    
    Book getBookByISBN(Long isbn);
    
    Book updateBook(Long isbn, String status);

    // TODO: add other operations here!
    
    Response createReview(Long isbn,Review review);
    
    Review getReviewByID(Long isbn, int id);
    
    List<Review> getReviewsByISBN(Long isbn);
    
    Author getAuthorByID(Long isbn, int id);
    
    List<Author> getAuthorsByISBN(Long isbn);
}

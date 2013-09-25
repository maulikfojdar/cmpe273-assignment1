package edu.sjsu.cmpe.library.repository;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.ws.rs.core.Response;

import edu.sjsu.cmpe.library.domain.Author;
import edu.sjsu.cmpe.library.domain.Book;
import edu.sjsu.cmpe.library.domain.Review;
import edu.sjsu.cmpe.library.dto.LinkDto;
import edu.sjsu.cmpe.library.dto.LinksDto;
//import edu.sjsu.cmpe.library.dto.ReviewDto;

public class BookRepository implements BookRepositoryInterface {
    /** In-memory map to store books. (Key, Value) -> (ISBN, Book) */
    private final ConcurrentHashMap<Long, Book> bookInMemoryMap;
    /** Never access this key directly; instead use generateISBNKey() */
    private long isbnKey;
    //private int idKey;
    private int ridKey;

    
    
    public BookRepository(ConcurrentHashMap<Long, Book> bookMap) {
	checkNotNull(bookMap, "bookMap must not be null for BookRepository");
	bookInMemoryMap = bookMap;
	isbnKey = 0;
    }

    
    
    
    /**
     * This should be called if and only if you are adding new books to the
     * repository.
     * 
     * @return a new incremental ISBN number
     */
    private final Long generateISBNKey() {
	// increment existing isbnKey and return the new value
	return Long.valueOf(++isbnKey);
    }
    
    private final int generateRIDKey() {
    	// increment existing ridKey and return the new value
    	return ++ridKey;
    }
    
    
    
    /**
     * This will create author links for a particular isbn
     */
    public Author getAuthorLinks(Long isbn)
    {
    	Author author = null;
    	return author;	
    }
    
    
    
    /**
     * This will delete the book with particular isbn
     */
    public Response deleteBook(Long isbn) {
    	bookInMemoryMap.remove(isbn);
    	
    	LinksDto linkResponse = new LinksDto();
    	linkResponse.addLink(new LinkDto("create-book", "/books" , "POST"));
    	
    	return Response.status(201).entity(linkResponse).build();
    }
    
    
    
    /**
     * This will update the status for a particular ISBN
     */
    
    public Book updateBook(Long isbn, String status)
    {
    	Book updateBook = getBookByISBN(isbn);
    	updateBook.setStatus(status);
    	return updateBook;
    }
    
    
    
    
    /**
     * This will auto-generate unique ISBN for new books
     */
    @Override
    public Book saveBook(Book newBook) {
	checkNotNull(newBook, "newBook instance must not be null");
	// Generate new ISBN
	Long isbn = generateISBNKey();
	newBook.setIsbn(isbn);
	//Author response = getAuthorLinks(isbn);
	// Finally, save the new book into the map
	bookInMemoryMap.putIfAbsent(isbn, newBook);
	return newBook;
    }

    /**
     * This will create book reviews
     */
    
    public Response createReview(Long isbn,Review review) {
    	
    	int rid = generateRIDKey();
    	review.setId(rid);
    	
    	Book book = getBookByISBN(isbn);
    	List<Review> rev = new ArrayList<Review>();
    	rev = book.getReviews();
    	rev.add(review);
    	book.setReviews(rev);
    	
    	LinksDto reviewResponse = new LinksDto();
    	
    	
    	String location = "/books/"+isbn+"/reviews/"+rid;
    	reviewResponse.addLink(new LinkDto("view-review",location,"GET"));
    	return Response.status(201).entity(reviewResponse).build();
    	
    }
    
    /**
     * @see edu.sjsu.cmpe.library.repository.BookRepositoryInterface#getBookByISBN(java.lang.Long)
     */
    @Override
    public Book getBookByISBN(Long isbn)
    {
	checkArgument(isbn > 0,
		"ISBN was %s but expected greater than zero value", isbn);
	return bookInMemoryMap.get(isbn);
    }
    
    /**
     * This will fetch the review as per the ID
     */

    public Review getReviewByID(Long isbn, int id)
    {
    	return getBookByISBN(isbn).getReviews().get(id);
    }
    
    /**
     * This will fetch reviews for ISBN
     */
    
    public List<Review> getReviewsByISBN(Long isbn)
    {
    	return getBookByISBN(isbn).getReviews();
    }
    
    /**
     * This will fetch the author as per the ID
     */
    public Author getAuthorByID(Long isbn, int id)
    {
    	return getBookByISBN(isbn).getAuthors().get(id);
    }
    
    /**
     * This will fetch reviews for ISBN
     */
    
    public List<Author> getAuthorsByISBN(Long isbn)
    {
    	return getBookByISBN(isbn).getAuthors();
    }
}

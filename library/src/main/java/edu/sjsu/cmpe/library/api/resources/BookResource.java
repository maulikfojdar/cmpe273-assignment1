package edu.sjsu.cmpe.library.api.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.yammer.dropwizard.jersey.params.LongParam;
import com.yammer.metrics.annotation.Timed;

import edu.sjsu.cmpe.library.domain.Author;
import edu.sjsu.cmpe.library.domain.Book;
import edu.sjsu.cmpe.library.domain.Review;
import edu.sjsu.cmpe.library.dto.AuthorDto;
import edu.sjsu.cmpe.library.dto.AuthorsDto;
import edu.sjsu.cmpe.library.dto.BookDto;
import edu.sjsu.cmpe.library.dto.LinkDto;
import edu.sjsu.cmpe.library.dto.LinksDto;
import edu.sjsu.cmpe.library.dto.ReviewDto;
import edu.sjsu.cmpe.library.dto.ReviewsDto;
import edu.sjsu.cmpe.library.repository.BookRepositoryInterface;

@Path("/v1/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource {
    /** bookRepository instance */
    private final BookRepositoryInterface bookRepository;

    /**
* BookResource constructor
*
* @param bookRepository
* a BookRepository instance
*/
    public BookResource(BookRepositoryInterface bookRepository) {
    	this.bookRepository = bookRepository;
    }

    @GET
    @Path("/{isbn}")
    @Timed(name = "view-book")
    public BookDto getBookByIsbn(@PathParam("isbn") LongParam isbn) {
    	Book book = bookRepository.getBookByISBN(isbn.get());
    	BookDto bookResponse = new BookDto(book);
    	String location = "/books/" + book.getIsbn();
    	bookResponse.addLink(new LinkDto("view-book", location,"GET"));
    	bookResponse.addLink(new LinkDto("update-book",location, "PUT"));
    	bookResponse.addLink(new LinkDto("delete-book",location, "DELETE"));
    	bookResponse.addLink(new LinkDto("create-review",location + "/reviews", "POST"));
    	
    	if (bookRepository.getBookByISBN(isbn.get()).getReviews().size() > 0)
    	{
    		bookResponse.addLink(new LinkDto("view-all-reviews",location + "/reviews", "GET"));
    	}
    	
    	

    	return bookResponse;
    }

    @POST
    @Timed(name = "create-book")
    public Response createBook(Book request) {
    	// Store the new book in the BookRepository so that we can retrieve it.
    	Book savedBook = bookRepository.saveBook(request);

    	String location = "/books/" + savedBook.getIsbn();
    	LinksDto linkResponse = new LinksDto();
    	linkResponse.addLink(new LinkDto("view-book", location, "GET"));
    	linkResponse.addLink(new LinkDto("update-book", location, "PUT"));
    	linkResponse.addLink(new LinkDto("delete-book",location, "DELETE"));
    	linkResponse.addLink(new LinkDto("create-review",location + "/reviews", "POST"));
    	// Add other links if needed

    	return Response.status(201).entity(linkResponse).build();
    }
    
    @DELETE
    @Path("/{isbn}")
    @Timed(name = "delete-book")
    public Response deleteBookByIsbn(@PathParam("isbn") Long isbn){
    	Response res = bookRepository.deleteBook(isbn);
    	
    	
    	return res;
    	
    }
    
    @PUT
    @Path("/{isbn}")
    @Timed(name = "update-book")
    public Response updateBook(@PathParam("isbn") LongParam isbn, @QueryParam("status") String status) {
    	
    	Book updatedbook = bookRepository.updateBook(isbn.get(), status);
    	String location = "/books/" + updatedbook.getIsbn();
    	LinksDto linkResponse = new LinksDto();
    	linkResponse.addLink(new LinkDto("view-book", location,"GET"));
    	linkResponse.addLink(new LinkDto("update-book",location, "PUT"));
    	linkResponse.addLink(new LinkDto("delete-book",location, "DELETE"));
    	linkResponse.addLink(new LinkDto("create-review",location + "/reviews", "POST"));
    	if (bookRepository.getBookByISBN(isbn.get()).getReviews().size() > 0 )
    	{
    		linkResponse.addLink(new LinkDto("view-all-reviews",location + "/reviews", "GET"));
    	}
    	return Response.status(201).entity(linkResponse).build();
    	 
    }
    
    
    @POST
    @Path("/{isbn}/reviews")
    @Timed(name = "create-review")
    public Response createBookReview(@PathParam("isbn") Long isbn,Review review) {
    	
    	
    	Response response = bookRepository.createReview(isbn,review);
    	
    	return response;
    }
    
    
    @GET
    @Path("/{isbn}/reviews/{id}")
    @Timed(name = "view-review")
    public ReviewDto viewBookReview(@PathParam("isbn") Long isbn, @PathParam("id") int id, Review review) {
    	
    	review = bookRepository.getReviewByID(isbn, id);
    	
    	ReviewDto response = new ReviewDto(review);
    	
    	String location = "/books/"+isbn+"/reviews/"+id;
    	
    	response.addLink(new LinkDto("view-review",location,"GET"));
    	
    	return response;
    	
    }
    
    @GET
    @Path("/{isbn}/reviews")
    @Timed(name = "view-all-reviews")
    public ReviewsDto viewAllBookReviews(@PathParam("isbn") Long isbn, List<Review> reviews) {
    	
    	reviews = bookRepository.getReviewsByISBN(isbn);
    	
    	ReviewsDto response = new ReviewsDto(reviews);
    	//String location = "/books/"+isbn+"/reviews/;
    	
    	//response.addLink(new LinkDto("view-review",location,"GET"));
    	
    	return response;
    	
    }
    
    @GET
    @Path("/{isbn}/authors/{id}")
    @Timed(name = "view-author")
    public AuthorDto viewBookAuthor(@PathParam("isbn") Long isbn, @PathParam("id") int id, Author author) {
    	
    	author = bookRepository.getAuthorByID(isbn, id);
    	
    	AuthorDto response = new AuthorDto(author);
    	String location = "/books/"+isbn+"/authors/"+id;
    	
    	response.addLink(new LinkDto("view-author",location,"GET"));
    	
    	return response;
    	
    }
    
    
    @GET
    @Path("/{isbn}/authors")
    @Timed(name = "view-all-authors")
    public AuthorsDto viewAllBookAuthors(@PathParam("isbn") Long isbn, List<Author> authors) {
    	
    	authors = bookRepository.getAuthorsByISBN(isbn);
    	
    	AuthorsDto response = new AuthorsDto(authors);
    	//String location = "/books/"+isbn+"/reviews/;
    	
    	//response.addLink(new LinkDto("view-review",location,"GET"));
    	
    	return response;
    	
    }
 
}


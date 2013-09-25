package edu.sjsu.cmpe.library.domain;

//import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Book {
    private long isbn;
    private String title;
    private String language;
    private int numberofpages;
    private String publication_date;
    private String status;
    //private SimpleDateFormat formattedDate = new SimpleDateFormat("MM/dd/yyyy");
    private List<Author> authors = new ArrayList<Author>();
    private List<Review> reviews = new ArrayList<Review>();
    private Review review;
    

    /**
     * @return the listOfAuthors
     */

	public List<Author> getAuthors() {
		return authors;
	}
	
	/**
	 * @param listOfAuthors
	 * 						the listOfAuthors to set
	 */

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	
	/**
     * @return the listOfReviews
     */

	public List<Review> getReviews() {
		return reviews;
	}
	
	/**
	 * @param listOfReviews
	 * 						the listOfReviews to set
	 */

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	/**
     * @return the review
     */
	public Review getReview() {
		return review;
	}

	/**
     * @param review
     *            the review to set
     */
	public void setReview(Review review) {
		this.review = review;
	}

	/**
     * @return the isbn
     */
    public long getIsbn() {
	return isbn;
    }

    /**
     * @param isbn
     *            the isbn to set
     */
    public void setIsbn(long isbn) {
	this.isbn = isbn;
    }

    /**
     * @return the title
     */
    public String getTitle() {
	return title;
    }

    /**
     * @param title
     *            the title to set
     */
    public void setTitle(String title) {
	this.title = title;
    }

    /**
     * @return the language
     */
    public String getLanguage() {
		return language;
	}

    /**
     * @param language
     *            the language to set
     */
	public void setLanguage(String language) {
		this.language = language;
	}


    /**
     * @return the number of pages
     */
	@JsonProperty("num-pages")
	public int getNumberofpages() {
		return numberofpages;
	}

    /**
     * @param number of pages
     *            the number of pages to set
     */
	public void setNumberofpages(int numberofpages) {
		this.numberofpages = numberofpages;
	}


    /**
     * @return the publication date
     */
	@JsonProperty("publication-date")
	public String getPublication_date() {
		return publication_date;
	}

    /**
     * @param publication date
     *            the publication date to set
     */
	public void setPublication_date(String publication_date) {
		this.publication_date = publication_date;
	}


    /**
     * @return the status
     */
	public String getStatus() {
		return status;
	}

    /**
     * @param status
     *            the status to set
     */
	public void setStatus(String status) {
		this.status = status;
	}


}

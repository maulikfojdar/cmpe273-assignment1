package edu.sjsu.cmpe.library.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import edu.sjsu.cmpe.library.domain.Author;

@JsonPropertyOrder(alphabetic = true)
public class AuthorsDto extends LinksDto {
    private List<Author> authors = new ArrayList<Author>();

    /**
     * @param authors
     */
    public AuthorsDto(List<Author> authors) {
	super();
	this.authors = authors;
    }

    /**
     * @return authors
     */
    public List<Author> getAuthors() {
	return authors;
    }

    /**
     * @param author
     *            the author to set
     */
    public void setAuthors(List<Author> authors) {
	this.authors = authors;
    }
}

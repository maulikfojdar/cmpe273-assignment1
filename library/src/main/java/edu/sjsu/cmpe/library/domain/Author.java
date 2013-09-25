package edu.sjsu.cmpe.library.domain;

public class Author {

	private static int id1;
	private int id;
	private String name;
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * @param id
	 * 				the id to set
	 */
	public Author() {
		++id1;
		this.id = id1;
		
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name
	 * 				the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}	
}

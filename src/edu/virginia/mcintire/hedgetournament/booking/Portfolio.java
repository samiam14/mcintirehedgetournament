package edu.virginia.mcintire.hedgetournament.booking;

import java.util.HashMap;
import java.util.Map;

public class Portfolio {
    Map<String, Book> _books;
    
    public Portfolio() {
	_books = new HashMap<String, Book>();
    }
    
    public void addBook(Book bookToAdd) {
	_books.put(bookToAdd.getName(), bookToAdd);
    }
    
    public double getMarkToMarketValue() {
	double sum = 0.0;
	for(Book book : _books.values()) {
	    sum += book.getMarkToMarketValue();
	}
	return sum;
    }
}

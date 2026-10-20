package com.library;

import java.util.Objects;

/*
-String author

-ISBN isbn

+loanPeriodDays() : int */

public class Book extends Item{
    private final String author;
    private final ISBN isbn;

    
    public Book(String id, String title ,String author, ISBN isbn) {
        super(id, title);

        Objects.requireNonNull(author,"the author cant be null");
        if (author.isBlank()) {
            throw new IllegalArgumentException("The author cannot be blank");
        }


        /*  so u make sure no one write ISBN missing = null;   
        in this case the ISBN will never be called this is why u need to check it here 
        */
       Objects.requireNonNull(isbn,"the ISBN cant be null");
       this.isbn = isbn;

        

        this.author=author;
    }


    
    public String getAuthor() {
        return author;
    }



    public ISBN getIsbn() {
        return isbn;
    }



    @Override
    public int loanPeriodDays() {
        return 21 ;
    }    
    
}

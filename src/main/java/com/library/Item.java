package com.library;

import java.util.Objects;

/*
-String id

-String title

-boolean available

+getId() : String

+getTitle() : String

+isAvailable() : boolean

+markBorrowed() : void

+markReturned() : void

+loanPeriodDays() : int
*/

public abstract class Item {
    private final String id ;
    private final String title;
    private boolean available;


     public Item(String id,String title){
        Objects.requireNonNull(id,"the id cant be null");
        Objects.requireNonNull(title,"the title cant be null");
        if (id.isBlank()) {
            throw new IllegalArgumentException("The id cannot be blank");
        }
        if (title.isBlank()) {
            throw new IllegalArgumentException("The title cannot be blank");
        }

        this.id=id;
        this.title=title;
        this.available = true;
     }

    public String getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public boolean isAvailable() {
        return available;
    }

    //check if u can borrowed
    public boolean canBeBorrowed(){
        return isAvailable();
    }

    //borrowed the item
    public void markBorrowed(){
        if(!isAvailable()){
            throw new IllegalStateException("already borrowed");
        }
        this.available=false;
    }

    //return the item 
    public void markReturned(){
        if(isAvailable()){
            throw new IllegalStateException("was not borrowed");
        }
        this.available=true;
    }

    public abstract int loanPeriodDays();
    
}

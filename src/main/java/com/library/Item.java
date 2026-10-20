package com.library;

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
    private String id ;
    private String title;
    private boolean available;




    public String getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public boolean isAvailable() {
        return available;
    }

    public void markBorrowed(){

    }
    public void markReturned(){
        
    }
    public int loanPeriodDays(){
        return 0;
    }

    
    
}

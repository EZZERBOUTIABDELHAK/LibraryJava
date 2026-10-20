package com.library;

/**

Borrowable

+canBeBorrowed() : boolean

+loanPeriodDays() : int
 */
interface Borrowable {

    /*
    Pre: none.
    Post: returns true if and only if the object is currently available No state change
    */
    public boolean canBeBorrowed();

    /*
    Pre: none.
    Post: returns a strictly positive number of days No state change
     */
    public int loanPeriodDays();
}

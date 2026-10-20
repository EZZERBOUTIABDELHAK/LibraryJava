package com.library;

import java.time.LocalDate;
import java.util.Objects;

//which member borrowed which item , when,and when it is due
public record Loan(Item item ,Member member,LocalDate borrowedOn,LocalDate dueDate) {
    public Loan{
        Objects.requireNonNull(item, "the ISBN cant be null");
        Objects.requireNonNull(member, "the member cant be null");
        Objects.requireNonNull(borrowedOn, "the borrowedOn cant be null");
        Objects.requireNonNull(dueDate, "the dueDate cant be null");

        if(!dueDate.isAfter(borrowedOn)){
            throw new IllegalArgumentException("due date must be after borrow date");
        }
        
    }
    //check if the deadline pased or not 
    public boolean isOverdue(LocalDate today) {
        Objects.requireNonNull(today, "Today cannot be null");
        return today.isAfter(dueDate);
    }
}

package com.library;

import java.time.LocalDate;
import java.util.Objects;

//which member borrowed which item, when,and when it is due
public record Loan(Item item ,Member member,LocalDate borrowedOn,LocalDate dueDate) {
    public Loan{
        Objects.requireNonNull(item, "the ISBN cant be null");
        Objects.requireNonNull(member, "the ISBN cant be null");
        Objects.requireNonNull(borrowedOn, "the ISBN cant be null");
        Objects.requireNonNull(dueDate, "the ISBN cant be null");
        
    }
}

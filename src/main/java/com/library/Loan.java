package com.library;

import java.time.LocalDate;

//which member borrowed which item, when,and when it is due
public record Loan(Item item ,Member member,LocalDate borrowedOn,LocalDate dueDate) {
}

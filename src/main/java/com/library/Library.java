package com.library;

import java.time.LocalDate;
import java.util.ArrayList;

/*
-List<Item> items

-List<Member> members

-List<Loan> activeLoans
------------------------------
+addItem(item) : void

+registerMember(member) : void

+lend(memberId, itemId) : Loan

+receive(loan) : void
*/

import java.util.List;
import java.util.Objects;

public class Library {
    
    private List<Item> items;
    private List<Member> members;
    private List<Loan> activeLoans;

    //init empty
    public  Library(){
        this.items = new ArrayList<>();
        this.members = new ArrayList<>();
        this.activeLoans = new ArrayList<>();
    }

    public void addItem(Item itemAdded){
        Objects.requireNonNull(itemAdded, "the item cant be null");

        if (findItem(itemAdded.getId()) != null) {
                throw new IllegalArgumentException("An item with this ID already exists");
        
    }
    items.add(itemAdded);
}

    public void registerMember(Member newMember){
        Objects.requireNonNull(newMember, "the New member cant be null");

        
           if (findMember(newMember.getMemberId()) != null) {
                throw new IllegalArgumentException("A member with this ID already exists");
    }
        
        members.add(newMember);
    }


    public Loan lend(String memberId, String itemId){
        Objects.requireNonNull(memberId, "the memberId cant be null");
        Objects.requireNonNull(itemId, "the itemId cant be null");

        
        Member member = findMember(memberId);
        if (member == null) {
        throw new IllegalArgumentException("Member not found: " + memberId);
        }
        
       

        Item item = findItem(itemId);
        if (item == null) {
        throw new IllegalArgumentException("item not found: " + itemId);
        }

        if (!item.canBeBorrowed()) {
            throw new IllegalStateException("u cant borrowed this one");
        }
       
        int activeLoansCount = countActiveLoans(member);
        if (!member.canBorrow(activeLoansCount)) {
            throw new IllegalStateException("u cant borrow more ");
        };

        item.markBorrowed();

        LocalDate borrowedOn = LocalDate.now();
        LocalDate dueDate = borrowedOn.plusDays(item.loanPeriodDays());

        Loan loan = new Loan(item, member, borrowedOn, dueDate); 
        activeLoans.add(loan);


        return loan;
    
    }

   


    private int countActiveLoans(Member member) {
        
        int activeLoansCount=0;

        for(Loan l:activeLoans){
            if (l.member().getMemberId().equals(member.getMemberId())) {
                activeLoansCount++;
            }
        }
       return activeLoansCount;
    }

    private Member findMember(String memberId) {
    for (Member m : members) {
        if (m.getMemberId().equals(memberId)) {
            return m; 
        }
    }
    return null; 
}

    private Item findItem(String itemId) {
    for (Item m : items) {
        if (m.getId().equals(itemId)) {
            return m; 
        }
    }
    return null; 
}


    public void receive(Loan loan){
        Objects.requireNonNull(loan, "Loan cannot be null");

        if (!activeLoans.contains(loan)) {
            throw new IllegalArgumentException("loan dont exist");
        }

        activeLoans.remove(loan);
        loan.item().markReturned();
    }

    public List<Item> getItems() {
        return items;
    }

    public List<Member> getMembers() {
        return members;
    }

    public List<Loan> getActiveLoans() {
        return activeLoans;
    }

    
}

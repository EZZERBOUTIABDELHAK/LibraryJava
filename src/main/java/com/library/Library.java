package com.library;

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

public class Library {
    
    private List<Item> items;
    private List<Member> members;
    private List<Loan> activeLoans;

    public void addItem(Item item){}
    public void registerMember(Member member){}
    public Loan lend(Member memberId, Item itemId){
        return new Loan();
    }
    public void receive(Loan loan){}
}

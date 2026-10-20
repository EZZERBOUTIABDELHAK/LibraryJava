package com.library;

import java.util.Objects;

/*
Member

-String memberId

-String name

-int maxLoans

+getName() : String

+canBorrow(currentLoans) : boolean
 */
public class Member {
    private final String memberId;
    private final String name;
    private final int maxLoans;


    public Member(String memberId, String name, int maxLoans) {

        Objects.requireNonNull(memberId ,"member id should not be null");
        Objects.requireNonNull(name ,"member name should not be null");
        if (memberId.isBlank()) {
            throw new IllegalArgumentException("The member id cannot be blank");
        }
        if (name.isBlank()) {
            throw new IllegalArgumentException("The name cannot be blank");
        }

        if(!(maxLoans>0)){
            throw new IllegalArgumentException("maxLoans must be greater then 0");
        }

        this.memberId = memberId;
        this.name = name;
        this.maxLoans = maxLoans;
    }


    public boolean canBorrow(int currentLoans){
        if (0>currentLoans) {
            throw new IllegalArgumentException("currentLoans must be positive number");
        }
        if (maxLoans>currentLoans) {
            return true;
        }
        return false;
    }

    public String getMemberId() {
        return memberId;
    }


    public String getName() {
        return name;
    }


    public int getMaxLoans() {
        return maxLoans;
    }

    
    
}

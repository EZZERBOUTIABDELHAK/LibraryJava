package com.library;

/*

-int durationMinutes

+loanPeriodDays() : in */

public class DVD extends Item{

    private final int durationMinutes;

    public DVD(String id, String title,int durationMinutes) {
        super(id, title);

        if(!(durationMinutes>0)){
            throw new IllegalArgumentException("durationMinutes must be greater then 0");
        }
        
        this.durationMinutes= durationMinutes;

        }

    
    
    @Override
    public int loanPeriodDays() {
        return 7;
    }



    public int getDurationMinutes() {
        return durationMinutes;
    }
    
}

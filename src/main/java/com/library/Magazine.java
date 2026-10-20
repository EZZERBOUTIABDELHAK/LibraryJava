package com.library;
/*
-int issueNumber

+loanPeriodDays() : int
 */
public class Magazine extends Item {

    private final int issueNumber;

    public Magazine(String id, String title,int issueNumber) {
        super(id, title);
        if(!(issueNumber>0)){
            throw new IllegalArgumentException("issueNumber must be greater then 0");
        }
        
        this.issueNumber= issueNumber;

        }
    

    @Override
    public int loanPeriodDays() {
        return 3;
    }

    public int getIssueNumber() {
        return issueNumber;
    }
    
}

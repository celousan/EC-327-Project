package com.example.cellomini;

public class Promoter {
    //both letters are valid in the puzzle and are stored in each promoter
    private char let1;
    private char let2;
    //logic is here to check as to whether the Promoter stores one or two letters
    private boolean logic = false;
    //basic constructor used by the program in the generated Promoters
    public Promoter(char l1)
    {
        let1 = l1;
    }
    //constructor used by the Circuit class to make the solution
    public Promoter(char l1, char l2)
    {
        let1 = l1;
        let2 = l2;
        logic = true;
    }
    //returns logic
    public boolean getLogic()
    {
        return logic;
    }
    //returns let1
    public String getLet1()
    {
        return Character.toString(let1);
    }
    //returns let2, unless it is not defined, in which case a null character is returned
    public String getLet2()
    {
        if(this.getLogic())
            return Character.toString(let2);
        else
            return "\0";
    }
    //compares the letters stored in two promoters and if they share a letter compare returns true
    public boolean compare(Promoter p)
    {
        if((this.getLet1() == p.getLet1())||(this.getLet2() == p.getLet2())||(this.getLet1() == p.getLet2())||(this.getLet2() == p.getLet1()))
        {
            return true;
        }
        else
            return false;
    }
}

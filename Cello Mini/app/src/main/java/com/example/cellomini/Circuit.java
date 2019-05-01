package com.example.cellomini;

import java.util.Random;

public class Circuit {
    private Promoter[] p;
    private String str;

    public Circuit()
    {
        new Circuit(0, "");
    }
    public Circuit(int size, String str)
    {
        p = new Promoter[size];
        this.str = str;
        generateSolution();
    }
    //generates solutions to the circuit
    private void generateSolution()
    {
        char[] chars = str.toCharArray();
        char a = ' ', b = ' ', c = ' ', d = ' ', e = ' ';
        for(int i = 0; i < chars.length; i++)
        {
            switch(i)
            {
                case 0:
                    a = chars[i];
                    break;
                case 1:
                    b = chars[i];
                    break;
                case 2:
                    c = chars[i];
                    break;
                case 3:
                    d = chars[i];
                    break;
                case 4:
                    e = chars[i];
                    break;
            }
        }
        for(int i = 0; i < p.length; i++)
        {
            switch(i)
            {
                case 0:
                    p[i] = new Promoter(b,c);
                    break;
                case 1:
                    p[i] = new Promoter(d);
                    break;
                case 2:
                    p[i] = new Promoter(d,e);
                    break;
                case 3:
                    p[i] = new Promoter(c);
                    break;
                case 4:
                    p[i] = new Promoter(a);
                    break;
                case 5:
                    p[i] = new Promoter(c,d);
                    break;
                case 6:
                    p[i] = new Promoter(e,a);
                    break;
                case 7:
                    p[i] = new Promoter(a,e);
                    break;
            }
        }
    }
    //compares the user inputted array to acceptable solutions
    public boolean checkSol(Promoter[] sol)
    {
        int counter = 1;
        if((p[4].compare(sol[0])) || (p[7].compare(sol[0])))
            counter++;
        if((p[0].compare(sol[1])) || (p[6].compare(sol[1])))
            counter++;
        if((p[3].compare(sol[2])) || (p[5].compare(sol[2])))
            counter++;
        if((p[1].compare(sol[3])) || (p[2].compare(sol[3])))
            counter++;

        return (counter == 5);
    }
    //returns one of the possible solutions to the circuit
    public Promoter[] getSol()
    {
        Promoter[] pos_sol = new Promoter[4];
        pos_sol[0] = p[4];
        pos_sol[1] = p[0];
        pos_sol[2] = p[3];
        pos_sol[3] = p[1];
        return pos_sol;
    }
    //returns the generated promoters
    public Promoter[] getPromoters()
    {
        Random rgen = new Random();
        Promoter[] r = new Promoter[p.length];
        for(int i = 0; i < p.length; i++)
        {
            r[i] = p[i];
        }
        for(int i = 0; i < r.length; i++)
        {
            int randPos = rgen.nextInt(r.length);
            Promoter temp = r[i];
            r[i] = r[randPos];
            r[randPos] = temp;
        }
        return r;
    }
}

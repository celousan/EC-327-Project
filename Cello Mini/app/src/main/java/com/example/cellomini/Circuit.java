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
    private void generateSolution()
    {
        if(p.length == 8)
        {
            p[0] = new Promoter(str.charAt(1), str.charAt(2));
            p[1] = new Promoter(str.charAt(3));
            p[2] = new Promoter(str.charAt(3), str.charAt(4));
            p[3] = new Promoter(str.charAt(2));
            p[4] = new Promoter(str.charAt(0));
            p[5] = new Promoter(str.charAt(2), str.charAt(3));
            p[6] = new Promoter(str.charAt(4), str.charAt(1));
            p[7] = new Promoter(str.charAt(0), str.charAt(4));
        }
    }
    public boolean checkSol(Promoter[] sol)
    {
        int counter = 1;
        if((p[4].equals(sol[0])) || (p[7].equals(sol[0])))
            counter++;
        if((p[0].equals(sol[1])) || (p[6].equals(sol[1])))
            counter++;
        if((p[3].equals(sol[2])) || (p[5].equals(sol[2])))
            counter++;
        if((p[1].equals(sol[3])) || (p[2].equals(sol[3])))
            counter++;

        return (counter == 5);
    }
    public Promoter[] getSol(){return p;}
    public Promoter[] getPromoters()
    {
        Random rgen = new Random();
        Promoter[] r = p;
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

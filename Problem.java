/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.lab2_pa;
import java.util.ArrayList;
/**
 *
 * @author crist
 */
public class Problem {  
    
    private Source[] sources;
    private Destination[] destinations;
    private int[][] costMatrix;
    private int[] supply, demand;
    private int sourceAmount = 0, destinationAmount = 0;
    public Problem(int destAmount, int srcAmount)
    {
        sources = new Source[srcAmount];
        destinations = new Destination[destAmount];
        supply = new int[srcAmount];
        demand = new int[destAmount];
        costMatrix = new int[srcAmount][destAmount];
        //populate supply and demand
        for (int i = 0; i < srcAmount; i++)
            supply[i] = (int) ((Math.random() * 1000000)%100);
        for (int i = 0; i < destAmount; i++)
            demand[i] = (int) ((Math.random() * 1000000)%100);
        
        //Populate matrix
        for (int i = 0; i < srcAmount; i++)
            for (int j = 0; j < destAmount; j++)
                costMatrix[i][j] = (int) ((Math.random() * 1000000)%10);
    }
    public void setSources(String str, SourceType source)
    {
        sources[sourceAmount] = new Source(str, source);
        sourceAmount++;
    }
    public void setDestinations(String str)
    {
        destinations[destinationAmount] = new Destination(str);
        destinationAmount++;
    }

    public int getCostMatrix(int line, int column) {
        return costMatrix[line][column];
    }

    public int getSupply(int index) {
        return supply[index];
    }

    public int getDemand(int index) {
        return demand[index];
    }
    public String getSourceName(int index)
    {
        return sources[index].getName();
    }
    public String getDestinationName(int index)
    {
        return destinations[index].getName();
    }
    
}

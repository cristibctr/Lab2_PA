/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.lab2_pa;
import java.util.ArrayList;
/**
 *
 * @author cristi
 */
public class Problem {  
    
    private Source[] sources;
    private Destination[] destinations;
    private int[][] costMatrix;
    private int[] supply, demand;
    //variables to increment when creating a new object - usefull to keep track on the amount of sources and destinations created [NOT AVAILABLE IN TOTAL]
    private int sourceAmount = 0, destinationAmount = 0;
    public Problem()
    {
        System.out.println("Created a new problem!");
    }
    public void setSources(Source[] sources)
    {
        this.sources = sources;
        sourceAmount = sources.length;

        //checks the created objects if they are the same and exists if one is found
        for(int i = 0; i < sourceAmount-1; i++)
            for(int j = i+1; j < sourceAmount; j++)
            if(sources[i].equals(sources[j]))
            {
                System.out.println("Can't have the same source object more than once: " + sources[i].getName());
                sources[i] = null;
                sources[j] = null;
                System.exit(0);
            }
    }
    public void setDestinations(Destination[] destinations)
    {
        this.destinations = destinations;
        destinationAmount = destinations.length;
        //checks previous created objects if they are the same and sets null if one is found
        for(int i = 0; i < destinationAmount-1; i++)
            for(int j = i+1; j < destinationAmount; j++)
            if(destinations[i].equals(destinations[j]))
            {
                System.out.println("Can't have the same destination object more than once: " + destinations[i].getName());
                destinations[i] = null;
                destinations[j] = null;
                System.exit(0);
            }
    }
    public void randomCostMatrix()
    {
        costMatrix = new int[sourceAmount][destinationAmount];
        //Populate matrix of cost randomly
        for (int i = 0; i < sourceAmount; i++)
            for (int j = 0; j < destinationAmount; j++)
                costMatrix[i][j] = (int) ((Math.random() * 1000000)%10) + 1;
    }
    public void randomDemand()
    {
        demand = new int[destinationAmount];
        for (int i = 0; i < destinationAmount; i++)
            demand[i] = (int) ((Math.random() * 1000000)%100) + 1;
    }
    public void randomSupply()
    {
        supply = new int[sourceAmount];
        for (int i = 0; i < sourceAmount; i++)
            supply[i] = (int) ((Math.random() * 1000000)%100) + 1;
    }
    public int getCostMatrixIndex(int line, int column) {
        return costMatrix[line][column];
    }

    public int[][] getCostMatrix() {
        return costMatrix;
    }

    public int getSupply(int index) {
        return supply[index];
    }

    public int[] getSupply() {
        return supply;
    }
    
    public void setCostMatrix(int[][] costMatrix) {
        this.costMatrix = costMatrix;
    }

    public void setSupply(int[] supply) {
        this.supply = new int[sourceAmount];
        System.arraycopy(supply, 0, this.supply, 0, supply.length);
    }

    public void setDemand(int[] demand) {
        this.demand = new int[destinationAmount];
        System.arraycopy(demand, 0, this.demand, 0, demand.length);
    }
    
    public void setSupplyIndex(int position, int supply) {
        this.supply[position] = supply;
    }

    public void setDemandIndex(int position, int demand) {
        this.demand[position] = demand;
    }
    
    public int getDemand(int index) {
        return demand[index];
    }
    public int[] getDemand()
    {
        return demand;
    }
    public String getSourceName(int index)
    {
        return sources[index].getName();
    }
    public String getDestinationName(int index)
    {
        return destinations[index].getName();
    }

    public int getSourceAmount() {
        return sourceAmount;
    }

    public int getDestinationAmount() {
        return destinationAmount;
    }
    
    
    @Override
    public String toString() {
        //concatenates to strRreturn the final form for before printing. Uses String.format for formating.
        String strReturn = "";
                strReturn += String.format("%-3s", "");
        for (int i = 0; i < destinationAmount; i++)
            strReturn += String.format("%-3s", this.getDestinationName(i));
        strReturn += String.format("\n");
        for (int i = 0; i < sourceAmount; i++)
        {
            strReturn += String.format("%-3s", this.getSourceName(i));
            for (int j = 0; j < destinationAmount; j++)
                strReturn += String.format("%-3d", costMatrix[i][j]);
            strReturn += String.format("\n");
        }
        for (int i = 0; i < sourceAmount; i++)
        {
            strReturn += String.format("%s %-2d ",this.getSourceName(i) + ":", supply[i]);
        }
        strReturn += String.format("\n");
        for (int i = 0; i < destinationAmount; i++)
        {
            strReturn += String.format("%s %-2d ",this.getDestinationName(i) + ":", demand[i]);
        }
        return strReturn;
    }
}

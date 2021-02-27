/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.lab2_pa;

/**
 *
 * @author cristi
 */
public class GreedyAlgorithm extends Algorithm {
    
    public GreedyAlgorithm(Problem pb) {
        this.pb = pb;
    }
    //Always taking the minimum cost first
    @Override
    public Solution solve()
    {
        Solution greedySol = new Solution();
        greedySol.setPbCopy(pb);
        int diffSupplyDemand;
        boolean[][] usedBeforeCost = new boolean[pb.getSourceAmount()][pb.getDestinationAmount()];
        //minCost[0] - minimum value, minCost[1] - column of the min value, minCost[2] - row of the min value
        while(!supOrDemEmpty())
        {
            int minCost[] = minimumCost(usedBeforeCost);
            //if we didn't find another value for one of the reasons stated inside the function we return
            if(minCost[0] == -1)
                return greedySol;
            
            greedySol.setTransferOrder(greedySol.getUnitsSiToDjIndex(), 0, minCost[2]);
            greedySol.setTransferOrder(greedySol.getUnitsSiToDjIndex(), 1, minCost[1]);
            greedySol.incrUnitsSiToDjIndex();
            
            //set the used minimum cost
            usedBeforeCost[minCost[2]][minCost[1]] = true;
            //Subtract supply or demand from the higher of the two and set the other to 0
            if(pb.getSupply(minCost[2]) > pb.getDemand(minCost[1]))
            {
                diffSupplyDemand = pb.getSupply(minCost[2]) - pb.getDemand(minCost[1]);
                greedySol.setUnitsSiToDjByIndex(minCost[2], minCost[1], pb.getDemand(minCost[1]));
                pb.setSupplyIndex(minCost[2],diffSupplyDemand);
                pb.setDemandIndex(minCost[1], 0);
            }
            else
            {
                diffSupplyDemand = pb.getDemand(minCost[1]) - pb.getSupply(minCost[2]);
                greedySol.setUnitsSiToDjByIndex(minCost[2], minCost[1], pb.getSupply(minCost[2]));
                pb.setSupplyIndex(minCost[2],0);
                pb.setDemandIndex(minCost[1], diffSupplyDemand);
            }
            //pb.deleteMe();
        }
        return greedySol;
    }
    //Finds minimum value in costMatrix and return an array with the minimum value and the position within the array
    private int[] minimumCost(boolean[][] usedBeforeCost)
    {
        int minVal = -1, minValCol = 0, minValLine = 0;
        //finding first value for minVal - can't just assign the first value in the matrix because it might've been used already in another call
        for(int i = 0; i < pb.getSourceAmount(); i++)
        {
            for(int j = 0; j < pb.getDestinationAmount(); j++)
                if(!usedBeforeCost[i][j] && pb.getSupply(i) > 0 && pb.getDemand(j) > 0)
                {
                    minVal = pb.getCostMatrixIndex(i, j);
                    break;
                }
            if(minVal != -1)
                break;
        }
        boolean ok = false;
        for(int i = 0; i < pb.getSourceAmount(); i++)
            for(int j = 0; j < pb.getDestinationAmount(); j++)
                //if we find a value that hasn't been used before, it's lower than the current one and the supply and demand for it is higher than 0 we store it
                if(pb.getCostMatrixIndex(i, j) <= minVal && !usedBeforeCost[i][j] && pb.getSupply(i) > 0 && pb.getDemand(j) > 0)
                {
                    minVal = pb.getCostMatrixIndex(i, j);
                    minValCol = j;
                    minValLine = i;
                    ok = true;
                }
        //if it didn't find a correct min value
        if(!ok)
            return new int[]{-1};
        return new int[]{minVal, minValCol, minValLine};
    }
    private boolean supOrDemEmpty()
    {
        boolean okSupp = true, okDem = true;
        for(int i = 0; i < pb.getSourceAmount(); i++)
            if(pb.getSupply(i) != 0)
            {
                okSupp = false;
                break;
            }
        for(int i = 0; i < pb.getDestinationAmount(); i++)
            if(pb.getDemand(i) != 0)
            {
                okDem = false;
                break;
            }
        return okDem || okSupp;
    }
}

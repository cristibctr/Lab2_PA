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
public class VogelApprox extends Algorithm{
    private int[] rowDiff, colDiff;
    private Boolean[] usedRow, usedCol;
    private Solution vogelApproxSol;
    private int[][] costMatrix, transferedAmount;
    public VogelApprox(Problem pb) {
        this.pb = pb;
        rowDiff = new int[pb.getSourceAmount()];
        colDiff = new int[pb.getDestinationAmount()];
        usedRow = new Boolean[pb.getSourceAmount()];
        for(int i = 0; i < pb.getSourceAmount(); i++)
            usedRow[i]  = false;
        usedCol = new Boolean[pb.getDestinationAmount()];
        for(int i = 0; i < pb.getDestinationAmount(); i++)
            usedCol[i]  = false;
    }
    public Solution solve()
    {
        vogelApproxSol = new Solution();
        costMatrix = pb.getCostMatrix();
        transferedAmount = new int[pb.getSourceAmount()][pb.getDestinationAmount()];
        int maxColumnPenaltyIndex, maxRowPenaltyIndex, smallestCostIndex, diffSupplyDemand, penaltyCounterRow, penaltyCounterCol;
        Boolean a;
        //initialize transferedAmount
        for(int i = 0; i < transferedAmount.length; i++)
            for(int j = 0; j < transferedAmount[i].length; j++)
                transferedAmount[i][j] = 0;
        while(!supOrDemEmpty())
        {
            penaltyCounterCol = penaltyCounterRow = 0;
            
            //calculate the penalty for each row
            for(int i = 0; i < pb.getSourceAmount(); i++)
            {
                if(usedRow[i])
                {
                    rowDiff[i] = -1;
                    continue;
                }
                penaltyCounterRow++;
                rowDiff[i] = penalty(costMatrix[i], usedCol);
            }
            //calculate the penalty for each column
            for(int i = 0; i < pb.getDestinationAmount(); i++)
            {
                if(usedCol[i])
                {
                    colDiff[i] = -1;
                    continue;
                }
                penaltyCounterCol++;
                colDiff[i] = penalty(getColumn(costMatrix, i), usedRow);
            }
            
            //finding the biggest penalty for rows and columns separately
            maxColumnPenaltyIndex = maxArray(colDiff);
            maxRowPenaltyIndex = maxArray(rowDiff);
            if(penaltyCounterCol == 1 || penaltyCounterRow == 1)
                a = (colDiff[maxColumnPenaltyIndex] < rowDiff[maxRowPenaltyIndex]);
            else 
                a = (colDiff[maxColumnPenaltyIndex] > rowDiff[maxRowPenaltyIndex]);
            //finding the biggest penalty in the whole matrix
            if(a)
            {
                //Find the index of the smallest cost in the column with the highest penalty of costMatrix
                //smallestCostIndex is the row (supply) index and maxColumnPenaltyIndex is the column (demand) index in costMatrix
                smallestCostIndex = minArray(getColumn(costMatrix, maxColumnPenaltyIndex), usedRow);
                if(pb.getSupply(smallestCostIndex) > pb.getDemand(maxColumnPenaltyIndex))
                {
                    diffSupplyDemand = pb.getSupply(smallestCostIndex) - pb.getDemand(maxColumnPenaltyIndex);
                    transferedAmount[smallestCostIndex][maxColumnPenaltyIndex] = pb.getDemand(maxColumnPenaltyIndex);
                    pb.setSupplyIndex(smallestCostIndex,diffSupplyDemand);
                    pb.setDemandIndex(maxColumnPenaltyIndex, 0);
                    usedCol[maxColumnPenaltyIndex] = true;
                }
                else
                {
                    diffSupplyDemand = pb.getDemand(maxColumnPenaltyIndex) - pb.getSupply(smallestCostIndex);
                    transferedAmount[smallestCostIndex][maxColumnPenaltyIndex] = pb.getSupply(smallestCostIndex);
                    pb.setSupplyIndex(smallestCostIndex,0);
                    pb.setDemandIndex(maxColumnPenaltyIndex, diffSupplyDemand);
                    usedRow[smallestCostIndex] = true;
                }
            }
            else
            {
                //Find the index of the smallest cost in the row with the highest penalty of costMatrix
                //smallestCostIndex is the column (demand) index and maxRowPenaltyIndex is the row (supply) index in costMatrix
                smallestCostIndex = minArray(costMatrix[maxRowPenaltyIndex], usedCol);
                if(pb.getSupply(maxRowPenaltyIndex) > pb.getDemand(smallestCostIndex))
                {
                    diffSupplyDemand = pb.getSupply(maxRowPenaltyIndex) - pb.getDemand(smallestCostIndex);
                    transferedAmount[maxRowPenaltyIndex][smallestCostIndex] = pb.getDemand(smallestCostIndex);
                    pb.setSupplyIndex(maxRowPenaltyIndex, diffSupplyDemand);
                    pb.setDemandIndex(smallestCostIndex, 0);

                    usedCol[smallestCostIndex] = true;
                }
                else
                {
                    diffSupplyDemand = pb.getDemand(smallestCostIndex) - pb.getSupply(maxRowPenaltyIndex);
                    transferedAmount[maxRowPenaltyIndex][smallestCostIndex] = pb.getSupply(maxRowPenaltyIndex);
                    pb.setSupplyIndex(maxRowPenaltyIndex, 0);
                    pb.setDemandIndex(smallestCostIndex, diffSupplyDemand);
                    usedRow[maxRowPenaltyIndex] = true;
                }
            }
        }
        setSolutionVariables();
        return vogelApproxSol;
    }
    private int penalty(int[] arr, Boolean[] usedArr)
    {
        int minValue = -1, secondMinValue = -1;
        Boolean firstIteration = true, secondIteration = true;
        //Find the first and second lowest values
        for(int i = 0; i < arr.length; i++)
        {
            if(usedArr[i])
                continue;
            //If we don't have a value in minValue yet
            if(firstIteration)
            {
                minValue = arr[i];
                firstIteration = false;
            }
            else
            {
                if(arr[i] < minValue)
                {
                    secondMinValue = minValue;
                    minValue = arr[i];
                    secondIteration = false;
                }
                //If we don't have a value in secondMinValue yet
                else if(secondIteration)
                {
                    secondMinValue = arr[i];
                    secondIteration = false;
                }
                //If there's a lower value than secondMinValue but higher than minValue
                else if(arr[i] < secondMinValue)
                    secondMinValue = arr[i];
            }
        }
        if(minValue == -1 && secondMinValue == -1)
            return -1;
        if(minValue == -1)
            return secondMinValue;
        if(secondMinValue == -1)
            return minValue;
        
        return secondMinValue - minValue;
    }
    //Return the column as an array
    private int[] getColumn(int[][] matrix, int colNr)
    {
        int[] colArray = new int[matrix.length];
        for(int i = 0; i < matrix.length; i++)
        {
            colArray[i] = matrix[i][colNr];
        }
        return colArray;
    }
    //Find the minimum number in the array that wasn't used before
    private int minArray(int[] arr, Boolean[] usedArr)
    {
        int minIndex = 0, minValue = arr[0];
        Boolean firstIteration = true;
        for(int i = 0; i < arr.length; i++)
        {
            if(usedArr[i])
                continue;
            if(firstIteration)
            {
                minValue = arr[i];
                minIndex = i;
                firstIteration = false;
                continue;
            }
            if(arr[i] < minValue)
            {
                minValue = arr[i];
                minIndex = i;
            }
        }
        return minIndex;
    }
    //find the maximum number in array
    private int maxArray(int[] arr)
    {
        int maxIndex = 0, maxValue = arr[0];
        for(int i = 0; i < arr.length; i++)
        {
            if(arr[i] > maxValue)
            {
                maxValue = arr[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }
    //Check wether supply or demand is empty
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
    /*
    * Sets the variables needed to print the solution in the Solution class
    */
    private void setSolutionVariables()
    {
        vogelApproxSol.setPbCopy(this.pb);
        int k = 0;
        for(int i = 0; i < transferedAmount.length; i++)
        {
            for(int j = 0; j < transferedAmount[i].length; j++)
            {
                if(transferedAmount[i][j] != 0)
                {
                    vogelApproxSol.setTransferOrder(k, 0, i);
                    vogelApproxSol.setTransferOrder(k, 1, j);
                    vogelApproxSol.incrUnitsSiToDjIndex();
                    k++;
                }
            }
        }
        vogelApproxSol.setUnitsSiToDj(transferedAmount);
    }
}

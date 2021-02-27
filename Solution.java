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
public class Solution {
    private int[][] unitsSiToDj, transferOrder;
    private Problem pbCopy;
    private int unitsSiToDjIndex = 0;
    String[] source;
    
    @Override
    public String toString() {
        int totalCost = 0;
        String strReturn = "";
        for (int i = 0; i < unitsSiToDjIndex; i++)
        {
            strReturn += pbCopy.getSourceName(transferOrder[i][0]);
            strReturn += " -> ";
            strReturn += pbCopy.getDestinationName(transferOrder[i][1]);
            strReturn += " : ";
            strReturn += unitsSiToDj[transferOrder[i][0]][transferOrder[i][1]];
            strReturn += " units * cost ";
            strReturn += pbCopy.getCostMatrixIndex(transferOrder[i][0], transferOrder[i][1]);
            strReturn += " = ";
            strReturn += unitsSiToDj[transferOrder[i][0]][transferOrder[i][1]] * pbCopy.getCostMatrixIndex(transferOrder[i][0], transferOrder[i][1]);
            strReturn += "\n";
            totalCost += unitsSiToDj[transferOrder[i][0]][transferOrder[i][1]] * pbCopy.getCostMatrixIndex(transferOrder[i][0], transferOrder[i][1]);
        }
        strReturn += "Total cost: " + totalCost + "\n";
        return strReturn;
    }

    public void setPbCopy(Problem pbCopy) {
        this.pbCopy = pbCopy;
        unitsSiToDj = new int[pbCopy.getSourceAmount()][pbCopy.getDestinationAmount()];
        transferOrder = new int[pbCopy.getSourceAmount()*pbCopy.getDestinationAmount()][2];
    }

    public void setUnitsSiToDjByIndex(int line, int col, int unitsSiToDj) {
        this.unitsSiToDj[line][col] = unitsSiToDj;
    }

    public void setUnitsSiToDj(int[][] unitsSiToDj) {
        this.unitsSiToDj = unitsSiToDj;
    }

    public void setTransferOrder(int line, int col, int transferOrder) {
        this.transferOrder[line][col] = transferOrder;
    }
    public void incrUnitsSiToDjIndex()
    {
        unitsSiToDjIndex++;
    }

    public int getUnitsSiToDjIndex() {
        return unitsSiToDjIndex;
    }
    
}

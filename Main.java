/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.lab2_pa;

/**
 *
 * @author crist
 */
public class Main {
    public static void main(String[] args)
    {
        int sourceAmount = 3, destinationAmount = 3;
        Problem firstProblem = new Problem(3, 3);
        firstProblem.setSources("S1", SourceType.WAREHOUSE);
        firstProblem.setSources("S2", SourceType.FACTORY);
        firstProblem.setSources("S3", SourceType.FACTORY);
        
        firstProblem.setDestinations("D1");
        firstProblem.setDestinations("D2");
        firstProblem.setDestinations("D3");
        //Print costMatrix
        System.out.printf("%-3s", "");
        for (int i = 0; i < destinationAmount; i++)
            System.out.printf("%-3s", firstProblem.getDestinationName(i));
        System.out.println();
        for (int i = 0; i < sourceAmount; i++)
        {
            System.out.printf("%-3s", firstProblem.getSourceName(i));
            for (int j = 0; j < destinationAmount; j++)
                System.out.printf("%-3d", firstProblem.getCostMatrix(i,j));
            System.out.println();
        }
        for (int i = 0; i < destinationAmount; i++)
        {
            System.out.printf("%s %-2d ",firstProblem.getDestinationName(i) + ":",firstProblem.getSupply(i));
        }
        System.out.println();
        for (int i = 0; i < sourceAmount; i++)
        {
            System.out.printf("%s %-2d ",firstProblem.getSourceName(i) + ":",firstProblem.getDemand(i));
        }
    }
}

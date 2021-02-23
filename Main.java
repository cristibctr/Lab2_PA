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
        Problem firstProblem = new Problem(sourceAmount, destinationAmount);
        firstProblem.setSources("S1", SourceType.WAREHOUSE);
        firstProblem.setSources("S2", SourceType.FACTORY);
        firstProblem.setSources("S3", SourceType.FACTORY);
        
        firstProblem.setDestinations("D1");
        firstProblem.setDestinations("D2");
        firstProblem.setDestinations("D3");
        //Print costMatrix, supply and demand
        System.out.println(firstProblem);
    }
}

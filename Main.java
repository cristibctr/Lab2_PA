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
public class Main {

    /**
     *
     * @param args
     */
    public static void main(String[] args)
    {
        int sourceAmount = 100, destinationAmount = 100;
        Source sources[] = new Source[sourceAmount];
        Destination destinations[] = new Destination[destinationAmount];
        Problem pb1 = new Problem();
        Problem pb2 = new Problem();
        
//        sources[0] = new Warehouse("S1");
//        sources[1] = new Factory("S2");
//        sources[2] = new Factory("S3");
//        sources[3] = new Factory("S4");
        //randomly create sources
        for(int i = 0; i < sourceAmount; i++)
            sources[i] = new Factory("S" + i);
        pb1.setSources(sources);
        pb2.setSources(sources);
        
//        destinations[0] = new Destination("D1");
//        destinations[1] = new Destination("D2");
//        destinations[2] = new Destination("D3");
        //randomly create destinations
        for(int i = 0; i < destinationAmount; i++)
            destinations[i] = new Destination("D" + i);
        pb1.setDestinations(destinations);
        pb2.setDestinations(destinations);
        
        pb1.randomCostMatrix();
        pb1.randomDemand();
        pb1.randomSupply();
        
//        pb1.setCostMatrix(new int[][] {{2,3,1},{5,4,8},{5,6,8}});
//        pb1.setDemand(new int[] {20, 25, 25});
//        pb1.setSupply(new int[] {10, 35, 25});

//        pb1.setCostMatrix(new int[][] {{9,1,3},{3,9,8},{7,2,5}});
//        pb1.setDemand(new int[] {23, 88, 84});
//        pb1.setSupply(new int[] {45, 100, 10});
        
//        pb1.setCostMatrix(new int[][] {{9,2,5},{6,3,10},{3,7,3},{7,3,10}});
//        pb1.setDemand(new int[] {61, 48, 45});
//        pb1.setSupply(new int[] {68, 28, 76, 68});

        pb2.setCostMatrix(pb1.getCostMatrix());
        pb2.setDemand(pb1.getDemand());
        pb2.setSupply(pb1.getSupply());

        //Print costMatrix, supply and demand
        System.out.println(pb1);
        Algorithm greedy = new GreedyAlgorithm(pb1);
        Solution sol = greedy.solve();
        System.out.println(sol);
        System.out.println(pb2);
        Algorithm vogel = new VogelApprox(pb2);
        sol = vogel.solve();
        System.out.println(sol);
    }
}

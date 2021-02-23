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
public class Destination {
    private String name;
    public Destination(String destinationName)
    {
        name = destinationName;
    }

    @Override
    public String toString() {
        return "Destination{" + "name=" + name + '}';
    }
    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
}

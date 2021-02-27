/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.lab2_pa;

import java.util.Objects;

/**
 *
 * @author cristi
 */
public abstract class Source {
    private String name;
    private SourceType initType;
    
    public Source(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Source{" + "name=" + name + ", initType=" + initType + '}';
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
    
    /**
     * @return the initType
     */
    public SourceType getInitType() {
        return initType;
    }

    /**
     * @param initType the initType to set
     */
    public void setInitType(SourceType initType) {
        this.initType = initType;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Source other = (Source) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }
    
}

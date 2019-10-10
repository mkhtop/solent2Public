/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.factoryandfacade.model;

/**
 *
 * @author markhartop
 */

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class FarmFacadeImpl implements FarmFacade{
    
    List<Animal> animals = new ArrayList();
    
    @Override
    public List<Animal> getAllAnimals(){
        List<Animal> allAnimals = new ArrayList();
        for (Animal a : animals){
            allAnimals.add(a);
        }
        return allAnimals;
    }
    
    @Override
    public void addDog(String name){
        Animal a = AnimalObjectFactory.createDog();
        a.setName(name);
        animals.add(a);
    };

    @Override
    public void addCat(String name){
       Animal a = AnimalObjectFactory.createCat();
       a.setName(name);
       animals.add(a); 
    };

    @Override
    public void addCow(String name){
        Animal a = AnimalObjectFactory.createCow();
        a.setName(name);
        animals.add(a);
    };
    
    @Override
    public void addDuck(String name){
        Animal a = AnimalObjectFactory.createDuck();
        a.setName(name);
        animals.add(a);
    };
            
}

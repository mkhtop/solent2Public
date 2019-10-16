/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.factoryandfacade.impl.service.test;

import org.junit.Test;
import static org.junit.Assert.*;
import org.solent.com504.factoryandfacade.impl.service.FarmFacadeImpl;
import org.solent.com504.factoryandfacade.impl.service.ServiceObjectFactoryImpl;
import org.solent.com504.factoryandfacade.model.dao.AnimalDao;
import org.solent.com504.factoryandfacade.model.dao.AnimalTypeDao;
import org.solent.com504.factoryandfacade.model.dto.Animal;
import org.solent.com504.factoryandfacade.model.service.FarmFacade;
import org.solent.com504.factoryandfacade.model.service.ServiceObjectFactory;

/**
 *
 * @author gallenc
 */
public class FarmFacadeTest {
    
    
    
    public FarmFacadeTest() {
    }
    
    @Test
    public void getAnimalsTest(){
        
        ServiceObjectFactory serviceObjectFactory = new ServiceObjectFactoryImpl();
        FarmFacade farmFacade = new FarmFacadeImpl();
        
        System.out.println("Get Animal List Test");
        farmFacade.addAnimal("Dog", "Finn");
        farmFacade.addAnimal("Cat", "Titch");
        farmFacade.addAnimal("Cow", "Girl");
        
        
} 

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}

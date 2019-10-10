package org.solent.com504.factoryandfacade.test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.solent.com504.factoryandfacade.model.Animal;
import org.solent.com504.factoryandfacade.model.AnimalObjectFactory;
import org.solent.com504.factoryandfacade.model.FarmFacade;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cgallen
 */
public class FarmFacadeTest {

    @Test
    public void FarmFacadeTest() {

        FarmFacade farmFacade = AnimalObjectFactory.createFarmFacade();
        assertNotNull(farmFacade);
        
        // WHAT TESTS WOULD YOU CREATE HERE TO SET UP AND TEST THE FARM FACADE?
        
        farmFacade.addDog("Finn");
        farmFacade.addCat("catty");
        farmFacade.addCow("cowy");
        farmFacade.addDuck("ducky");
        farmFacade.getAllAnimals();
        List<Animal> testList;
        testList = farmFacade.getAllAnimals();
        
        
        assertEquals("Finn", testList.get(0).getName());
        assertEquals("catty", testList.get(1).getName());
        assertEquals("cowy", testList.get(2).getName());
        assertEquals("ducky", testList.get(3).getName());
        assertEquals("Quack", testList.get(3).getSound());
    }
}

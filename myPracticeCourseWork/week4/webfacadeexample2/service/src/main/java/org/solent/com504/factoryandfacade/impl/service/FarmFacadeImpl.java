package org.solent.com504.factoryandfacade.impl.service;

import java.util.ArrayList;
import java.util.List;
import org.solent.com504.factoryandfacade.model.dao.AnimalDao;
import org.solent.com504.factoryandfacade.model.dao.AnimalTypeDao;
import org.solent.com504.factoryandfacade.model.dto.Animal;
import org.solent.com504.factoryandfacade.model.service.FarmFacade;

public class FarmFacadeImpl implements FarmFacade {

    private AnimalDao animalDao = null;

    private AnimalTypeDao animalTypeDao = null;

    // setters for DAOs
    public void setAnimalDao(AnimalDao animalDao) {
        this.animalDao = animalDao;
    }

    public void setAnimalTypeDao(AnimalTypeDao animalTypeDao) {
        this.animalTypeDao = animalTypeDao;
    }

    // Farm facade methods
    @Override
    public List<Animal> getAllAnimals() {
        return animalDao.retrieveAll();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Animal addAnimal(String animalType, String name) {
        Animal animalTemp = animalDao.create(animalTypeDao.getAnimalType(animalType));
        animalTemp.setName(name);
        return animalTemp;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Animal> getAnimalsOfType(String animalType) {
        Animal tempAnimal = animalDao.create(animalTypeDao.getAnimalType(animalType));
        return animalDao.retrieve(tempAnimal);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Animal getAnimal(String name) {
        Animal tempAnimal = new Animal();
        tempAnimal.setName(name);
        List<Animal> animals = animalDao.retrieve(tempAnimal);
        if (animals.size() == 0) {
            return null;
        } else {
            return animals.get(0);
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeAnimal(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String> getSupportedAnimalTypes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

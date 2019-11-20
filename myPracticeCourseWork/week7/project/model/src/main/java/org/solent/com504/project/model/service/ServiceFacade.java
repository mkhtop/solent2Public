package org.solent.com504.project.model.service;

public interface ServiceFacade {
    
    public String getHeartbeat();
    
    boolean arrived(String username, String location);
        
}

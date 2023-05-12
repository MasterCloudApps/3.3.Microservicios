package dev.dgomezg.urjc.biciurjc.coreapi;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class RentBikeCommand {
    
    @TargetAggregateIdentifier 
    String bikeId;
    
    String userId;

    public RentBikeCommand(String bikeId, String userId) {
        this.bikeId = bikeId;
        this.userId = userId;
    }
    
    public String getBikeId() {
        return bikeId;
    }

    public String getUserId() {
        return userId;
    }
}

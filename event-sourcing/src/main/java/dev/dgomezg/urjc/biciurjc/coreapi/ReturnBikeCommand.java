package dev.dgomezg.urjc.biciurjc.coreapi;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class ReturnBikeCommand {
    
    @TargetAggregateIdentifier 
    String bikeId;
    
    String location;

    public ReturnBikeCommand(String bikeId, String location) {
        this.bikeId = bikeId;
        this.location = location;
    }
    
    public String getBikeId() {
        return bikeId;
    }

    public String getLocation() {
        return location;
    }
}

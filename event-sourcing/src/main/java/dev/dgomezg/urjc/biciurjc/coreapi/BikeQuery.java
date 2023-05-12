package dev.dgomezg.urjc.biciurjc.coreapi;

public class BikeQuery {
    
    String bikeId;

    public BikeQuery() {
        
    }
    
    public BikeQuery(String bikeId) {
        this.bikeId = bikeId;
    }

    public String getBikeId() {
        return bikeId;
    }

    public void setBikeId(String bikeId) {
        this.bikeId = bikeId;
    }
}

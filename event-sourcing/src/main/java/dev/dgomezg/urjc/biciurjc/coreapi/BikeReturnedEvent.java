package dev.dgomezg.urjc.biciurjc.coreapi;

public class BikeReturnedEvent {
    String bikeId;
    String location;

    public BikeReturnedEvent(String bikeId, String location) {
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

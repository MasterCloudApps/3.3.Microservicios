package dev.dgomezg.urjc.biciurjc.coreapi;

public class BikeRentedEvent {
    String bikeId;
    String userId;

    public BikeRentedEvent(String bikeId, String userId) {
        this.bikeId = bikeId;
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public String getBikeId() {
        return bikeId;
    }
}

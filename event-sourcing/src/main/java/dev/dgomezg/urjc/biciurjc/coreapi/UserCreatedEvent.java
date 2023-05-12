package dev.dgomezg.urjc.biciurjc.coreapi;

public class UserCreatedEvent {
    String userId;
    String fullName;

    public UserCreatedEvent(String userId, String fullName) {
        this.userId = userId;
        this.fullName = fullName;
    }

    public String getUserId() {
        return userId;
    }

    public String getFullName() {
        return fullName;
    }
}

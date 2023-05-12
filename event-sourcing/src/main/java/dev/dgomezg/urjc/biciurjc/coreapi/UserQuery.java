package dev.dgomezg.urjc.biciurjc.coreapi;

public class UserQuery {
    
    String userId;

    public UserQuery() {
        
    }

    public UserQuery(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}

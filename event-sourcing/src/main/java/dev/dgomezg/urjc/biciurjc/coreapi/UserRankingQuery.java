package dev.dgomezg.urjc.biciurjc.coreapi;

public class UserRankingQuery {
    String userId;

    public UserRankingQuery(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }
}

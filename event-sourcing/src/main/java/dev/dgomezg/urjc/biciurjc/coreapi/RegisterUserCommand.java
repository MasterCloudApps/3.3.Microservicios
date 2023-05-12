package dev.dgomezg.urjc.biciurjc.coreapi;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class RegisterUserCommand {

    @TargetAggregateIdentifier 
    String userId;
    String fullName;
    
    public RegisterUserCommand(String userId, String fullName) {
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

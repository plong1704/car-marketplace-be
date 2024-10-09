package com.market.carmarketservice.service.validation;

public interface ValidationService {
    public boolean validUsername(String username);

    public boolean validPassword(String password);

    public boolean validEmail(String email);
}

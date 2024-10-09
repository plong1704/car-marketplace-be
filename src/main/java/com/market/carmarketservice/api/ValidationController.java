package com.market.carmarketservice.api;

import com.market.carmarketservice.request.valid.Email;
import com.market.carmarketservice.request.valid.Password;
import com.market.carmarketservice.request.valid.Username;
import com.market.carmarketservice.service.validation.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(value = "/api/valid")
public class ValidationController {
    private final ValidationService validationService;

    @RequestMapping(value = "/username", method = RequestMethod.POST)
    public boolean validUsername(@RequestBody Username username) {
        return validationService.validUsername(username.getUsername());
    }

    @RequestMapping(value = "/password", method = RequestMethod.POST)
    public boolean validPassword(@RequestBody Password password) {
        return validationService.validPassword(password.getPassword());
    }

    @RequestMapping(value = "/email", method = RequestMethod.POST)
    public boolean validEmail(@RequestBody Email email) {
        return validationService.validEmail(email.getEmail());
    }
}

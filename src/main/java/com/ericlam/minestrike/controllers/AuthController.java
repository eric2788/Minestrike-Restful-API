package com.ericlam.minestrike.controllers;

import com.ericlam.minestrike.dao.AuthRepository;
import com.ericlam.minestrike.modals.Auth;
import com.ericlam.minestrike.utils.LoginManager;
import com.ericlam.minestrike.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("auth")
public class AuthController {

    private final AuthRepository authRepository;
    private final LoginManager loginManager;

    @Autowired
    public AuthController(AuthRepository authRepository) {
        this.authRepository = authRepository;
        loginManager = LoginManager.getInstance();
    }

    @PostMapping("check")
    public Map checkLogon(@RequestParam("valid") String uuid) {
        return Utils.returnResult(loginManager.haveSession(uuid));
    }

    @GetMapping
    public Map notGet() {
        return Utils.returnCustomError("GET method is not supported.");
    }

    @PostMapping
    public Map doLogin(@RequestBody Auth auth) {
        String username = auth.getUsername();
        String password = Utils.toHash64(auth.getPassword()); //Hash it
        Optional<Auth> authOptional = authRepository.findById(username); //get Auth info

        if (!authOptional.isPresent()) return Utils.returnResult(false); //if username non exist

        boolean match = authOptional.get().getPassword().equals(password); //check password match

        if (!match) return Utils.returnResult(false); //if password not match

        return loginManager.addSession(); // return true and send session
    }

    @DeleteMapping
    public Map logout(@RequestParam("logout") String uuid) {
        return Utils.returnResult(loginManager.deleteSession(uuid));
    }
}

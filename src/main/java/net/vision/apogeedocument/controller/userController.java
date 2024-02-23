package net.vision.apogeedocument.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.vision.apogeedocument.entities.User;
import net.vision.apogeedocument.service.AuthServiceImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("api/")
public class userController {
    private AuthenticationManager authenticationManager;
    private AuthServiceImpl authService;


    @PostMapping(path = "activation")
    public void activation(@RequestBody Map<String, String> actvaiton){
        this.authService.activation(actvaiton);
        log.info("Inscription effectuée !!!!!!");
    }


    /*
     *
     * change password
     *
     * */
    @PostMapping(path = "change-Password")
    public void changePassword(@RequestBody Map<String, String> activation){
        this.authService.changePassword(activation);
    }

    /*
     *
     * new password
     *
     * */
    @PostMapping(path = "new-password")
    public void newPassword(@RequestBody Map<String, String> activation){
        this.authService.newPassword(activation);
    }




    /*
     *
     * get all user from database
     *
     * */
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<User> getAlluser(){
        return this.authService.search();
    }

    /*
     *
     *Get user by ID
     *
     * */

    @GetMapping(path = "{id}", produces = APPLICATION_JSON_VALUE)
    public User getUserById(@PathVariable int id){
        return this.authService.getUser(id);
    }

}

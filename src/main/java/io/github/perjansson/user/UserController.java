package io.github.perjansson.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<User> findUsers() {
        return userRepository.findUsers();
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public User getUser(@PathVariable(value = "userId") final long userId) {
        return userRepository.getUser(userId);
    }

}

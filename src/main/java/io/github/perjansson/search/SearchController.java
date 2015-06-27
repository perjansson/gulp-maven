package io.github.perjansson.search;

import io.github.perjansson.user.User;
import io.github.perjansson.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/search")
public class SearchController {

    private final UserRepository userRepository;

    @Autowired
    public SearchController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public List<User> searchUsers(@RequestBody SearchInput searchInput) {
        List<User> searchResult = userRepository.findUsers(searchInput);
        System.out.println(searchInput + " gave " + searchResult);
        return searchResult;
    }

}

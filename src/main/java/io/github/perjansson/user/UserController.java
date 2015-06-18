package io.github.perjansson.user;

import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import org.apache.commons.lang3.Validate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

import static io.github.perjansson.user.User.aUser;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private static List<User> dummyUsers;

    static {
        dummyUsers = Arrays.asList(
                aUser(1, "Martin", "Dahlin", "martin.dahlin@user.se"),
                aUser(2, "Tomas", "Brolin", "tomas.brolin@user.se"),
                aUser(3, "Klas", "Ingesson", "klas.ingesson@user.se"));
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<User> getAllUsers() {
        return dummyUsers;
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public User read(@PathVariable(value = "userId") final long userId) {
        ImmutableList<User> users = FluentIterable.from(getAllUsers()).filter(new Predicate<User>() {
            @Override
            public boolean apply(User user) {
                return user.getId() == userId;
            }
        }).toList();
        Validate.isTrue(users.size() == 1, "Unable to find one user with id: " + userId);
        return users.get(0);
    }

}

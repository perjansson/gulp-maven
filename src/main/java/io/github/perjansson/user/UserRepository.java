package io.github.perjansson.user;

import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Arrays;
import java.util.List;

import static io.github.perjansson.user.User.aUser;

@Repository
public class UserRepository {

    private static List<User> dummyUsers;

    static {
        dummyUsers = Arrays.asList(
                aUser(1, "Martin", "Dahlin", "martin.dahlin@user.se"),
                aUser(2, "Tomas", "Brolin", "tomas.brolin@user.se"),
                aUser(3, "Klas", "Ingesson", "klas.ingesson@user.se"));
    }

    public List<User> findUsers() {
        return dummyUsers;
    }

    public User getUser(@PathVariable(value = "userId") final long userId) {
        ImmutableList<User> users = FluentIterable.from(findUsers()).filter(new Predicate<User>() {
            @Override
            public boolean apply(User user) {
                return user.getId() == userId;
            }
        }).toList();
        Validate.isTrue(users.size() == 1, "Unable to find one user with id: " + userId);
        return users.get(0);
    }
}

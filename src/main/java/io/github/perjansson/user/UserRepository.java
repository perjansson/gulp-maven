package io.github.perjansson.user;

import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import io.github.perjansson.search.SearchInput;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

import static io.github.perjansson.user.User.aUser;

@Repository
public class UserRepository {

    private static List<User> dummyUsers;

    static {
        dummyUsers = Arrays.asList(
                aUser(1, "Per", "Jansson", "per.jansson@user.se"),
                aUser(2, "Kanika", "Gulabani", "kanika.gulabani@user.se"),
                aUser(3, "Tomas", "Hägg", "tomas.hagg@user.se"),
                aUser(4, "Bengt", "Franzén", "bengt.franzen@user.se"),
                aUser(5, "Ruchir", "Sachdeva", "ruchir.sachdeva@user.se"),
                aUser(6, "Marcus", "Nylander", "marcus.nylander@user.se"));
    }

    public List<User> findUsers() {
        return dummyUsers;
    }

    public User getUser(final long userId) {
        ImmutableList<User> users = FluentIterable.from(findUsers()).filter(new Predicate<User>() {
            @Override
            public boolean apply(User user) {
                return user.getId() == userId;
            }
        }).toList();
        Validate.isTrue(users.size() == 1, "Unable to find one user with id: " + userId);
        return users.get(0);
    }

    public List<User> findUsers(final SearchInput searchInput) {
        return FluentIterable.from(findUsers()).filter(new Predicate<User>() {
            @Override
            public boolean apply(User user) {
                return user.getFirstName().contains(searchInput.getSearch()) ||
                        user.getLastName().contains(searchInput.getSearch()) ||
                        user.getEmail().contains(searchInput.getSearch());
            }
        }).toList();
    }
}

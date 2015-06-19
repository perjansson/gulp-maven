package io.github.perjansson.user;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class UserControllerTest {

    private UserController userController;
    private UserRepository userRepository;

    @Before
    public void setUp() {
        userRepository = mock(UserRepository.class);
        userController = new UserController(userRepository);
    }

    @Test
    public void shouldUFindUserFromRepository() {
        // given
        List<User> expectedUsers = Collections.unmodifiableList(Arrays.asList(mock(User.class), mock(User.class)));
        given(userRepository.findUsers()).willReturn(expectedUsers);
        // when
        List<User> actualUsers = userController.findUsers();
        // then
        assertThat(actualUsers).isEqualTo(expectedUsers);
    }

    @Test
    public void shouldGetUserFromRepository() {
        // given
        User expectedUser = mock(User.class);
        given(userRepository.getUser(1)).willReturn(expectedUser);
        // when
        User actualUser = userController.getUser(1);
        // then
        assertThat(actualUser).isEqualTo(expectedUser);
    }

}
package io.github.perjansson.user;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static io.github.perjansson.user.User.aUser;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class UserControllerIT {

    @InjectMocks
    UserController controller;

    @Mock
    UserRepository repository;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = standaloneSetup(controller).build();
    }

    @Test
    public void shouldFindUsers() throws Exception {
        List<User> expectedUsers = Collections.unmodifiableList(Arrays.asList(aUser(1, "John", "Doe", "john@doe.com"), aUser(1, "Papa", "Bear", "papa@bear.com")));
        given(repository.findUsers()).willReturn(expectedUsers);

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().string("[{\"id\":1,\"firstName\":\"John\",\"lastName\":\"Doe\",\"email\":\"john@doe.com\"},{\"id\":1,\"firstName\":\"Papa\",\"lastName\":\"Bear\",\"email\":\"papa@bear.com\"}]"));
    }

    @Test
    public void shouldGetUsers() throws Exception {
        User expectedUser = aUser(1, "John", "Doe", "john@doe.com");
        given(repository.getUser(1)).willReturn(expectedUser);

        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"id\":1,\"firstName\":\"John\",\"lastName\":\"Doe\",\"email\":\"john@doe.com\"}"));
    }

}

package mk.ukim.finki.wp.lab;

import mk.ukim.finki.wp.lab.model.User;
import mk.ukim.finki.wp.lab.repository.jpa.UserRepository;
import mk.ukim.finki.wp.lab.service.impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class FindingUserByIdTest {
    @Mock
    private UserRepository userRepository;

    private UserServiceImpl userService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        User user = new User("test.user");
        Long id = (long)1;
        Mockito.when(this.userRepository.findById(id)).thenReturn(Optional.of(user));

        this.userService = Mockito.spy( new UserServiceImpl(this.userRepository) );
    }

    @Test
    public void testSuccessFindById() {
        Long id = (long)1;
        User user = this.userService.findById(id);
        Mockito.verify(this.userService).findById(id);

        Assert.assertNotNull("User is null", user);
        Assert.assertEquals("User does not have username", "test.user", user.getUsername());
    }





}

package com.example.demo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootApplication
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private NameService nameService;

    @Mock
    private UserRepository userRepository;

    @Test
    public void whenUserIdIsProvided_thenRetrievedNameIsCorrect() {
        Mockito.when(nameService.getName("SomeId")).thenReturn("Mock user Name");
        String testName = userService.getUserName("SomeId");
        Assert.assertEquals("Mock user Name", testName);
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void whenUserIdProvided_verify() {
        // given
        given(userRepository.findByName("Name")).willReturn(new User(1L, "bbd", "20"));
        // when
        final User user = userRepository.findByName("Name");
        // then
        verify(userRepository, atLeastOnce()).findByName(anyString());
        assertThat(user.getId(), is(1L));
        assertThat(user.getName(), is("bbd"));
        assertThat(user.getAge(), is("20"));
    }
}
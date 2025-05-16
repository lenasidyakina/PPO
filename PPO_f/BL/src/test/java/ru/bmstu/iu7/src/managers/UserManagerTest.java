package ru.bmstu.iu7.src.managers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.bmstu.iu7.API.IUserRepository;
import ru.bmstu.iu7.API.model.User;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class UserManagerTest {

    @Mock
    IUserRepository i_user_repository;
    @Test
    public void register() throws Exception {
        UserManager user_manager = new UserManager(i_user_repository);
        user_manager.register("Lena", "Lena12345");
        Mockito.verify(i_user_repository).createUser("Lena", "Lena12345");
        Mockito.verify(i_user_repository).findUser("Lena", "Lena12345");
    }

    @Test
    public void authorize() throws Exception {
        UserManager user_manager = new UserManager(i_user_repository);
        user_manager.authorize("Lena", "Lena12345");
        Mockito.verify(i_user_repository).findUser("Lena", "Lena12345");
    }

    @Test
    public void register_2() throws Exception {
        Mockito.lenient().doReturn(new User("Lena", "Lena12345")).when(i_user_repository).findUser("Lena", "Lena12345");
        UserManager user_manager = new UserManager(i_user_repository);
        try {
            user_manager.register("Lena", "Lena12345");
        }
        catch (Exception e) {}
        Mockito.verify(i_user_repository).findUser("Lena", "Lena12345");
    }
}
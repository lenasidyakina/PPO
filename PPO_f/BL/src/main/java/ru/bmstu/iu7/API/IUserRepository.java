package ru.bmstu.iu7.API;


import ru.bmstu.iu7.API.model.IUser;

public interface IUserRepository {
    IUser createUser(String name, String password) throws Exception;
    IUser findUser(String name, String password) throws Exception;
    void delete(Long id) throws Exception;
    IUser update(IUser user) throws Exception;
}

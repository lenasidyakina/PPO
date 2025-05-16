package ru.bmstu.iu7;

import ru.bmstu.iu7.API.IUserRepository;
import ru.bmstu.iu7.API.model.User;
import ru.bmstu.iu7.impl.SpringUserRepository;

public class UserRepository implements IUserRepository {
    private SpringUserRepository m_springUserRepository;

    public UserRepository() {
        // this.m_springUserRepository = new ;
    }

    @Override
    public User createUser(String name, String password) throws Exception
    {
        m_springUserRepository.save(new User(name, password));
        return null;
    }

    @Override
    public User findUser(String name, String password) throws Exception
    {
        return null;
    }

    @Override
    public User delete(int id) throws Exception
    {
        return null;
    }

    @Override
    public User update(User user) throws Exception
    {
        return null;
    }
}

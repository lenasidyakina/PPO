package ru.bmstu.iu7;

import org.hibernate.annotations.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;
import org.springframework.stereotype.Component;
import ru.bmstu.iu7.API.IUserRepository;
import ru.bmstu.iu7.API.model.IUser;
import ru.bmstu.iu7.impl.ModelFactory;
import ru.bmstu.iu7.impl.SpringUserRepository;
import ru.bmstu.iu7.impl.model.User;

//@Component
public class UserRepository implements IUserRepository {

    //@Autowired
    private SpringUserRepository m_springUserRepository;


    public UserRepository(SpringUserRepository springUserRepository) {
       this.m_springUserRepository = springUserRepository;
    }

    @Override
    public IUser createUser(String name, String password) throws Exception
    {
        return (IUser)m_springUserRepository.save((User)ModelFactory.makeUser(name, password));
    }

    @Override
    public IUser findUser(String name, String password) throws Exception
    {
        return m_springUserRepository.findByNameAndPassword(name, password);
    }

    @Override
    public void delete(Long id) throws Exception
    {
        m_springUserRepository.deleteById(id);
    }

    @Override
    public IUser update(IUser user) throws Exception
    {
        m_springUserRepository.save((User)user);
        return null;
    }
}

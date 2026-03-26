package com.ecommerce.AuthServer.Service;


import com.ecommerce.AuthServer.Model.User;
import com.ecommerce.AuthServer.Model.UserDetail;
import com.ecommerce.AuthServer.Repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUserName(username);

        if(user ==null)
        {
            System.out.println("User Not found");
            throw new UsernameNotFoundException("User Not found");
        }
        return new UserDetail(user);

    }

    public String AddUser(User user)
    {
        userRepository.save(user);
        return "user Saved Successfully";
    }

    public List<User> GetAll()
    {
        return userRepository.findAll();
    }


}

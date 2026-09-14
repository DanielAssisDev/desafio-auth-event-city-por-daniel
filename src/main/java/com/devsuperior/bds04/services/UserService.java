package com.devsuperior.bds04.services;

import com.devsuperior.bds04.entities.Role;
import com.devsuperior.bds04.entities.User;
import com.devsuperior.bds04.projections.UserDetailsProjection;
import com.devsuperior.bds04.repositories.UserRepository;
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
        List<UserDetailsProjection> users = userRepository.searchUserByUsernameAndRole(username);
        if (users.isEmpty()) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }
        User user = new User();
        user.setEmail(users.getFirst().getUsername());
        user.setPassword(users.getFirst().getPassword());
//        for(UserDetailsProjection udp : users){
//            user.addRole(new Role(udp.getRoleId(), ));
//        }

        return null;
    }
}

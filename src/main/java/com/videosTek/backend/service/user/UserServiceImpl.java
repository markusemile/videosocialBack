package com.videosTek.backend.service.user;

import com.videosTek.backend.entity.User;
import com.videosTek.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService {

    private final UserRepository repository;

    public Optional<User> findByEmail(String email) {
        return this.repository.findByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User user = repository.findByUsername(username);
       if(user==null){
           throw new UsernameNotFoundException("user not found");
       }
       return user;
    }

}

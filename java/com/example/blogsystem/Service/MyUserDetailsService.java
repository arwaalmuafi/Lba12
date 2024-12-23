package com.example.blogsystem.Service;

import com.example.blogsystem.ApiResponse.ApiException;
import com.example.blogsystem.Model.MyUser;
import com.example.blogsystem.Repository.AuthRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final AuthRepository authRepository;

    public MyUserDetailsService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser myUser=authRepository.findMyUserByUsername(username);

        if(myUser==null){
            throw new ApiException("wrong username or password");
        }
        return myUser;
    }
}

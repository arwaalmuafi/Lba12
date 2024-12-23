package com.example.blogsystem.Service;

import com.example.blogsystem.ApiResponse.ApiException;
import com.example.blogsystem.Model.MyUser;
import com.example.blogsystem.Repository.AuthRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;

    public List<MyUser> getAllUser(){
        return authRepository.findAll();
    }
    public void register(MyUser myUser){
        myUser.setRole("USER");
        String hashPassword=new BCryptPasswordEncoder().encode(myUser.getPassword());
        myUser.setPassword(hashPassword);

        authRepository.save(myUser);
    }

    public void updateUser(Integer id, MyUser updatedUser) {
        MyUser existingUser = authRepository.findMyUserById(id);
        if(existingUser==null){
            throw new ApiException("User not found");
        }
        existingUser.setUsername(updatedUser.getUsername());
        if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
            String hashedPassword = new BCryptPasswordEncoder().encode(updatedUser.getPassword());
            existingUser.setPassword(hashedPassword);
        }
        authRepository.save(existingUser);
    }

    public void deleteUser(Integer id) {
        if (!authRepository.existsById(id)) {
            throw new RuntimeException("User not found");
        }
        authRepository.deleteById(id);
    }
}

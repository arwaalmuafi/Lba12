package com.example.blogsystem.Controller;

import com.example.blogsystem.ApiResponse.ApiResponse;
import com.example.blogsystem.Model.MyUser;
import com.example.blogsystem.Service.AuthService;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/api/user")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid MyUser myUser) {
        authService.register(myUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("user registered"));
    }

    @GetMapping("/get")
    public ResponseEntity getAllUSer() {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.getAllUser());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id, @RequestBody @Valid MyUser updatedUser) {
        authService.updateUser(id, updatedUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("User updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id) {
        authService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("User deleted successfully"));
    }
}

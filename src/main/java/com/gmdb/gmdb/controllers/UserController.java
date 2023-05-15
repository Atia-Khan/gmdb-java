package com.gmdb.gmdb.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gmdb.gmdb.models.Users;
import com.gmdb.gmdb.repositories.IUserRepository;

@RestController
@CrossOrigin("*")
@RequestMapping("/users")

public class UserController {
    
    @Autowired
    private IUserRepository repo;

    public UserController(IUserRepository repo){
        this.repo = repo;
    }

    @PostMapping("/add")
    public String addUser(@RequestBody Users user){
        this.repo.save(user);

        return "added";//"User has been aded successfully";
    }

    @GetMapping("/list")
    public List<Users> getUser(){
        return (List<Users>) this.repo.findAll();
    }

    @PostMapping("/update")
    public String updateUser(@RequestBody Users user){
        Users getUser = this.repo.findById(user.getId()).orElse(null);
        if(getUser == null){
            return "User not found";
        }
        this.repo.save(user);
        return "updated";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id){
        this.repo.deleteById(id);
        return "deleted";
    }
}

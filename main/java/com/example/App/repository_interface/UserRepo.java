package com.example.App.repository_interface;

 
import com.example.App.domain.User;

public interface UserRepo {
	
    User findByUserName(String fullname); 
    User save(User user);
	Iterable<User> getUsers(); 
	
}

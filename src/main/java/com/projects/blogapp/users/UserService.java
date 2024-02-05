package com.projects.blogapp.users;

import com.projects.blogapp.users.DTO.CreateUserRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserService(UserRepository userRepository, ModelMapper modelMapper){
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public UserEntity create_user(CreateUserRequest u){
        //use model mapper to set the values
        UserEntity userEntity = modelMapper.map(u, UserEntity.class);
        // use builder to set the values
//        var userEntity = UserEntity.builder()
//                .username(u.getUsername())
//                .email(u.getEmail())//To password has to provide
//                .build();
        return userRepository.save(userEntity);
    }
    public UserEntity getUser(Long userId){
        Optional<UserEntity> user = userRepository.findById(userId);
        return user.orElseThrow(()-> new UserNotFoundException(userId));
    }
    public UserEntity getUser(String name){
       return userRepository.findByUsername(name).orElseThrow(()-> new UserNotFoundException(name));

    }

    public UserEntity loginUser(String name, String Password){
        return userRepository.findByUsername(name).orElseThrow(()-> new UserNotFoundException(name));
        //other way of to do
//        Optional<UserEntity> user = userRepository.findByUserName(name);
//        if(user.isEmpty()){
//            throw new UserNotFoundException(name);
//        }
        // To do: match the password

//        return user;
    }
    public static class UserNotFoundException extends IllegalArgumentException{
        public UserNotFoundException(String s){
            super(s+" username is not found, retry");
        }
        public UserNotFoundException(long userId){
            super(userId+" userId is not found, retry");
        }
    }
}

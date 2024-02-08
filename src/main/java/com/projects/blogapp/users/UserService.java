package com.projects.blogapp.users;

import com.projects.blogapp.users.DTO.CreateUserRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, ModelMapper modelMapper,
                       PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public UserEntity create_user(CreateUserRequest u){
        //use model mapper to set the values
        UserEntity userEntity = modelMapper.map(u, UserEntity.class);
        userEntity.setPassword(passwordEncoder.encode(u.getPassword()));
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

    public UserEntity loginUser(String name, String password){
        var user = userRepository.findByUsername(name).orElseThrow(()-> new UserNotFoundException(name));
        var passmatch = passwordEncoder.matches(password, user.getPassword());
        if(!passmatch) throw new InvalidCreditionalException();
        return  user;
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
    public static class InvalidCreditionalException extends IllegalArgumentException{
        public InvalidCreditionalException(){
            super("Invalid username or password");
        }
    }
}

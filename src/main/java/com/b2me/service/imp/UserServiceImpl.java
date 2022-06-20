package com.b2me.service.imp;

import com.b2me.entity.User;
import com.b2me.exception.ResourceNotFoundException;
import com.b2me.payload.UserDto;
import com.b2me.repository.UserRepository;
import com.b2me.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.b2me.util.B2MEContants.B2ME001;
import static com.b2me.util.B2MEContants.B2ME002;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {

        User user = this.dtoToUser(userDto);

        User savedUser = this.userRepository.save(user);
        return this.userToDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer id) {
        User user = this.userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(B2ME001, B2ME002, id));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());

        User updatedUser = this.userRepository.save(user);
        return this.userToDto(updatedUser);
    }

    @Override
    public UserDto getUserById(Integer id) {
        User user = this.userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(B2ME001, B2ME002, id));
        return this.userToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = this.userRepository.findAll();
        return users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
    }

    @Override
    public String deleteUser(Integer id) throws UnsupportedOperationException {
        User user = this.userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(B2ME001, B2ME002, id));
        this.userRepository.delete(user);
        return B2ME001 + "with id" + B2ME002 + "deleted";
    }

    //setting data from entity to dto class for usage
    public User dtoToUser(UserDto userDto) {
        return this.modelMapper.map(userDto, User.class);
    }

    //setting data from dto class to entity for usage
    public UserDto userToDto(User user) {
        return this.modelMapper.map(user, UserDto.class);
    }
}

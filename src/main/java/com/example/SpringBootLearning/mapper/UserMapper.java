package com.example.SpringBootLearning.mapper;

import com.example.SpringBootLearning.dto.respone.UserRespone;
import org.mapstruct.Mapper;

import com.example.SpringBootLearning.entity.User;
import com.example.SpringBootLearning.dto.request.UserCreationRequest;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    public User toUser(UserCreationRequest request);
    public User updateUser(@MappingTarget User user, UserCreationRequest request);
    public UserRespone toUserRespone(User user);
}

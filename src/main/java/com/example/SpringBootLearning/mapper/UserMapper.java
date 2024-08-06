package com.example.SpringBootLearning.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.example.SpringBootLearning.dto.request.UserCreationRequest;
import com.example.SpringBootLearning.dto.respone.UserRespone;
import com.example.SpringBootLearning.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    public User toUser(UserCreationRequest request);

    public User updateUser(@MappingTarget User user, UserCreationRequest request);

    public UserRespone toUserRespone(User user);
}

package com.haui.btl.demo.Mapper;


import com.haui.btl.demo.Entity.User;
import com.haui.btl.demo.dto.request.UserCreationRequest;
import com.haui.btl.demo.dto.response.UserRespone;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    public User toUser(UserCreationRequest request);
    public User updateUser(@MappingTarget User user, UserCreationRequest request);
    public UserRespone toUserRespone(User user);
}

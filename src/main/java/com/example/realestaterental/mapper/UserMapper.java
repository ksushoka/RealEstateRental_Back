package com.example.realestaterental.mapper;

import com.example.realestaterental.dto.UserDTO;
import com.example.realestaterental.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toDto(User user);
    List<UserDTO> toDtoList(List<User> users);

}

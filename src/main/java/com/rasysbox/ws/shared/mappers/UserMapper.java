package com.rasysbox.ws.shared.mappers;

import com.rasysbox.ws.application.dtos.UserDto;
import com.rasysbox.ws.application.dtos.UserResponseDto;
import com.rasysbox.ws.domain.models.UserDomain;
import com.rasysbox.ws.infrastructure.persistence.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDomain entityToDomain(UserEntity userEntity);
    UserDto domainToDto(UserDomain userDomain);

    UserEntity domainToEntity(UserDomain userDomain);
    UserDomain dtoToDomain(UserDto userDto);

    List<UserDomain> entityListToDomainList(List<UserEntity> all);
    List<UserDto> domainListToDtoList(List<UserDomain> all);

    UserEntity dtoToEntity(UserDto userDto);

    UserResponseDto domainToResponseDto(UserDomain userDomain);
    List<UserResponseDto> domainListToResponseDtoList(List<UserDomain> all);
}

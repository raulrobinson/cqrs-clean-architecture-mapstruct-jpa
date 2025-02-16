package com.rasysbox.mapstruct.shared.mappers;

import com.rasysbox.mapstruct.application.dtos.UserDto;
import com.rasysbox.mapstruct.domain.models.UserDomain;
import com.rasysbox.mapstruct.infrastructure.persistence.entities.UserEntity;
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
}

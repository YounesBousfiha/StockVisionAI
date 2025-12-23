package com.jartiste.stockvisionai.application.mapper;

import com.jartiste.stockvisionai.domain.entity.User;
import com.jartiste.stockvisionai.presentation.dto.request.UserRequestDTO;
import com.jartiste.stockvisionai.presentation.dto.request.UserUpdateDTO;
import com.jartiste.stockvisionai.presentation.dto.response.UserResponseDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.9 (Microsoft)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserResponseDTO toResponse(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponseDTO.UserResponseDTOBuilder userResponseDTO = UserResponseDTO.builder();

        userResponseDTO.nom( user.getNom() );
        userResponseDTO.prenom( user.getPrenom() );
        userResponseDTO.email( user.getEmail() );
        userResponseDTO.role( user.getRole() );
        userResponseDTO.creationAt( user.getCreationAt() );

        return userResponseDTO.build();
    }

    @Override
    public User toEntity(UserRequestDTO request) {
        if ( request == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.nom( request.getNom() );
        user.prenom( request.getPrenom() );
        user.email( request.getEmail() );
        user.password( request.getPassword() );
        user.role( request.getRole() );

        user.isActivated( true );

        return user.build();
    }

    @Override
    public void updateUserFromDto(UserUpdateDTO dto, User entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getNom() != null ) {
            entity.setNom( dto.getNom() );
        }
        if ( dto.getPrenom() != null ) {
            entity.setPrenom( dto.getPrenom() );
        }
        if ( dto.getEmail() != null ) {
            entity.setEmail( dto.getEmail() );
        }
    }
}

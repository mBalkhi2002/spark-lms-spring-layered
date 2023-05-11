package com.spark.lms.model.dto;

import com.spark.lms.model.User;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    public UserDto(@NotNull String displayName, @NotNull String username, @NotNull String password, @NotNull String role) {
        super();
        this.displayName = displayName;
        this.username = username;
        this.password = password;
        this.role = role;
    }
    private Long id;
    private String displayName;
    private String username;
    private String password;
    private Integer active;
    private String role;
    private Date createdDate;
    private Date lastModifiedDate;

    public static UserDto fromUser(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setDisplayName(user.getDisplayName());
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());
        userDto.setActive(user.getActive());
        userDto.setRole(user.getRole());
        userDto.setCreatedDate(user.getCreatedDate());
        userDto.setLastModifiedDate(user.getLastModifiedDate());
        return userDto;
    }


}
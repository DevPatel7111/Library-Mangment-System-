package org.dev.JBDL_Lec14.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.dev.JBDL_Lec14.model.User;
import org.dev.JBDL_Lec14.model.UserStatus;

@Getter
@Setter
@AllArgsConstructor
@Builder
@ToString
public class UserRequest {
    @NotBlank(message = "user detatin fillef ")
    private String username;

    @NotBlank(message = "user detatin fillef ")
    private String phoneNo;
    @NotBlank(message = "user detatin fillef ")
    private String email;
    @NotBlank(message = "user detatin fillef ")
    private String address;

    public User toUser(){
        return User.builder().name(this.username).email(this.email).phoneNo(this.phoneNo).address(this.address).userStatus(UserStatus.ACTIVE).build();
    }



}

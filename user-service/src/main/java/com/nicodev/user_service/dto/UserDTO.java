package com.nicodev.user_service.dto;

import lombok.*;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class UserDTO {

    private Long user_id;
    private String name;
    private String lastName;
    private Integer dni;

}

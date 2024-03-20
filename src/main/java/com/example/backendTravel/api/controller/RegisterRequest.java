package com.example.backendTravel.api.controller;
import lombok.*;

@Data
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String nickname;
    private String email;
    private String password;
    private String userImage;
}

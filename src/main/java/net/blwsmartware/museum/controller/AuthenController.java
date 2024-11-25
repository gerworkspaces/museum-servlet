package net.blwsmartware.museum.controller;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import net.blwsmartware.museum.dto.request.account.*;
import net.blwsmartware.museum.dto.response.AuthenResponse;
import net.blwsmartware.museum.dto.response.MessageResponse;
import net.blwsmartware.museum.dto.response.user.UserResponse;
import net.blwsmartware.museum.dto.response.user.VerifyResponse;
import net.blwsmartware.museum.service.AuthenticationService;
import net.blwsmartware.museum.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AuthenController {

    UserService userService;
    AuthenticationService authenService;

    @PostMapping("/verify")
    public ResponseEntity<MessageResponse<VerifyResponse>>  verify(@RequestBody @Valid VerifyRequest verifyRequest) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(MessageResponse.<VerifyResponse>builder()
                        .result(authenService.verify(verifyRequest))
                        .build()
                );


    }

    @PostMapping("/register")
    public ResponseEntity<MessageResponse<UserResponse>> register(@RequestBody @Valid UserRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(MessageResponse.<UserResponse>builder()
                        .result(userService.createUser(request))
                        .build()
                );
    }

    @PostMapping("/login")
    public ResponseEntity<MessageResponse<AuthenResponse>> authen(@RequestBody AuthenRequest authen) {
                return ResponseEntity
                .status(HttpStatus.OK)
                .body(MessageResponse.<AuthenResponse>
                                builder()
                        .result(authenService.authentication(authen))
                        .build()
                );

    }

    @PostMapping("/refresh")
    public ResponseEntity<MessageResponse<AuthenResponse>> refresh(@RequestBody RefreshRequest request){
         return ResponseEntity
                .status(HttpStatus.OK)
                .body(MessageResponse.<AuthenResponse>builder()
                        .result(authenService.refreshToken(request))
                        .build()
                );
    }

    @PostMapping("/logout")
    public ResponseEntity<MessageResponse<?>> logout(@RequestBody LogoutRequest request) {
        authenService.logout(request);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(MessageResponse.builder()
                        .message("Logout success")
                        .build()
                );
    }
}

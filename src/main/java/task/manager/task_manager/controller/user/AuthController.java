package task.manager.task_manager.controller.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import task.manager.task_manager.config.CustomApiResponse;
import task.manager.task_manager.model.user.User;
import task.manager.task_manager.service.auth.AuthService;

@Controller
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<CustomApiResponse<User>> register(@RequestBody UserDto userDto) {
        CustomApiResponse<User> response = authService.register(userDto);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @PostMapping("/login")
    public ResponseEntity<CustomApiResponse<SignedDto>> login(@RequestBody SignInDto signInDto) {
        CustomApiResponse<SignedDto> response = authService.login(signInDto);
        return new ResponseEntity<>(response, response.getStatus());
    }

}

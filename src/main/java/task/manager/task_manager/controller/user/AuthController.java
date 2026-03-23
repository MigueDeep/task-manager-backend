package task.manager.task_manager.controller.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(name = "AUTH", description = "API de autenticación")
@Controller
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
@SecurityRequirements()
public class AuthController {

    private final AuthService authService;

    @Operation(
            summary = "Crear un nuevo usuario",
            description = "Crear un nuevo usuario.",
            tags = { "AUTH" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = UserDto.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PostMapping("/register")
    public ResponseEntity<CustomApiResponse<User>> register(@RequestBody UserDto userDto) {
        CustomApiResponse<User> response = authService.register(userDto);
        return new ResponseEntity<>(response, response.getStatus());
    }


    @Operation(
            summary = "Iniciar sesión",
            description = "Iniciar sesión con un usuario existente.",
            tags = { "AUTH" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = SignedDto.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) })
    })
    @PostMapping("/login")
    public ResponseEntity<CustomApiResponse<SignedDto>> login(@RequestBody SignInDto signInDto) {
        CustomApiResponse<SignedDto> response = authService.login(signInDto);
        return new ResponseEntity<>(response, response.getStatus());
    }

}

package task.manager.task_manager.service.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import task.manager.task_manager.config.CustomApiResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import task.manager.task_manager.config.CustomDtoValidator;
import task.manager.task_manager.controller.user.SignInDto;
import task.manager.task_manager.controller.user.SignedDto;
import task.manager.task_manager.controller.user.UserDto;
import task.manager.task_manager.model.user.User;
import task.manager.task_manager.model.user.UserRepository;
import task.manager.task_manager.security.jwt.JwtProvider;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService extends CustomDtoValidator<UserDto> {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtProvider provider;

    public CustomApiResponse<User> register(UserDto userDto){
        System.out.println("Registering user: " + userDto);
        String errors = getErrors(userDto);
        if (!errors.isEmpty()){
            return new CustomApiResponse<>(null, true, HttpStatus.BAD_REQUEST, errors);
        }
        User userFound = userRepository.findByEmail(userDto.getEmail()).orElse(null);
        if (userFound != null) {
            return new CustomApiResponse<>(null, true, HttpStatus.BAD_REQUEST, "El correo electrónico ya está en uso");
        }
        User user = userDto.convert();
        user.setId(UUID.randomUUID().toString());
        user.setFullName(userDto.getFullName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        userRepository.save(user);
        return new CustomApiResponse<>(user, false, HttpStatus.CREATED, "Usuario registrado con éxito");
    }

    public CustomApiResponse<SignedDto> login(SignInDto signInDto){
        try{
            User userFound = userRepository.findByEmail(signInDto.getEmail()).orElse(null);
            if (userFound == null){
                return new CustomApiResponse<>(null, true, HttpStatus.BAD_REQUEST, "El correo electrónico no está registrado");
            }
            if (!passwordEncoder.matches(signInDto.getPassword(), userFound.getPassword())){
                return new CustomApiResponse<>(null, true, HttpStatus.BAD_REQUEST, "La contraseña es incorrecta");
            }

            String token = provider.generateToken(userFound);
            SignedDto signedDto = new SignedDto(token, userFound);
            return new CustomApiResponse<>(signedDto, false, HttpStatus.OK, "Inicio de sesión exitoso");

        }catch (Exception e){
            e.printStackTrace();
            return new CustomApiResponse<>(null, true, HttpStatus.BAD_REQUEST, "Error al iniciar sesión");
        }
    }

}

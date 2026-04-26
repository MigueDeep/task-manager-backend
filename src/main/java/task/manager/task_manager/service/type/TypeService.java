package task.manager.task_manager.service.type;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import task.manager.task_manager.config.CustomApiResponse;
import task.manager.task_manager.config.CustomDtoValidator;
import task.manager.task_manager.controller.type.TypeDto;
import task.manager.task_manager.controller.type.TypeResponseDto;
import task.manager.task_manager.model.type.Type;
import task.manager.task_manager.model.type.TypeRepository;
import task.manager.task_manager.model.user.User;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TypeService extends CustomDtoValidator<TypeDto> {

    private final TypeRepository typeRepository;

    public CustomApiResponse<List<TypeResponseDto>> getAllTypes(User user) {
        List<Type> types = typeRepository.findAllByUserId(user.getId());
        if (types.isEmpty()) {
            return new CustomApiResponse<>(List.of(), false, HttpStatus.OK, "No se encontraron tipos de proyecto");
        }
        List<TypeResponseDto> typeDtos = types.stream()
                .map(type -> new TypeResponseDto(type.getId(), type.getName(), type.getColor()))
                .collect(Collectors.toList());
        return new CustomApiResponse<>(typeDtos, false, HttpStatus.OK, "Tipos de proyecto encontrados exitosamente");
    }

    public CustomApiResponse<Type> createType(TypeDto typeDto, User user){
        String errors = getErrors(typeDto);
        if (!errors.isEmpty()){
            return new CustomApiResponse<>(null, true, HttpStatus.BAD_REQUEST, errors);
        }
        Type typeFound = typeRepository.findByName(typeDto.getName()).orElse(null);
        if (typeFound != null && typeFound.getUser().getId().equals(user.getId())){
            return new CustomApiResponse<>(null, true, HttpStatus.CONFLICT, "El nombre del tipo ya existe");
        }
        Type type = new Type();
        type.setName(typeDto.getName());
        type.setColor(typeDto.getColor());
        type.setUser(user);
        typeRepository.save(type);
        return new CustomApiResponse<>(type, false, HttpStatus.CREATED, "Tipo de proyecto creado!");
    }
}

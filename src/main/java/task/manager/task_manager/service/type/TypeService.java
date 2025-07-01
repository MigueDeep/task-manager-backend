package task.manager.task_manager.service.type;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import task.manager.task_manager.config.CustomApiResponse;
import task.manager.task_manager.config.CustomDtoValidator;
import task.manager.task_manager.controller.type.TypeDto;
import task.manager.task_manager.model.type.Type;
import task.manager.task_manager.model.type.TypeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TypeService extends CustomDtoValidator<TypeDto> {

    private final TypeRepository typeRepository;

    public CustomApiResponse<List<Type>> getAllTypes() {
        List<Type> types = typeRepository.findAll();
        if (types.isEmpty()) {
            return new CustomApiResponse<>(null, true, HttpStatus.NOT_FOUND, "No se encontraron tipos de proyecto");
        }
        return new CustomApiResponse<>(types, false, HttpStatus.OK, "Tipos de proyecto encontrados exitosamente");
    }

    public CustomApiResponse<Type> createType(TypeDto typeDto){
        String errors = getErrors(typeDto);
        if (!errors.isEmpty()){
            return new CustomApiResponse<>(null, true, HttpStatus.BAD_REQUEST, errors);
        }
        Type typeFound = typeRepository.findByName(typeDto.getName()).orElse(null);
        if (typeFound != null){
            return new CustomApiResponse<>(null, true, HttpStatus.BAD_REQUEST, "El nombre del tipo ya existe");
        }
        Type type = new Type();
        type.setName(typeDto.getName());
        type.setColor(typeDto.getColor());
        typeRepository.save(type);
        return new CustomApiResponse<>(type, false, HttpStatus.CREATED, "Tipo de proyecto creado!");
    }
}

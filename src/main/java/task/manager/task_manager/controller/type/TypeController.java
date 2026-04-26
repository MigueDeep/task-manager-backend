package task.manager.task_manager.controller.type;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import task.manager.task_manager.config.CustomApiResponse;
import task.manager.task_manager.model.type.Type;
import task.manager.task_manager.model.user.User;
import task.manager.task_manager.service.type.TypeService;

import java.util.List;

@Controller
@RequestMapping("/api/v1/type")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class TypeController {

    private final TypeService typeService;

    @Operation(
            summary = "Registrar Tipo",
            description = "Crear un nuevo tipo de proyecto.",
            tags = { "PROJECT" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = TypeDto.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PostMapping("/register")
    public ResponseEntity<CustomApiResponse<Type>> register(@RequestBody TypeDto typeDto, @AuthenticationPrincipal User user){
        CustomApiResponse<Type> response = typeService.createType(typeDto, user);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @Operation(
            summary = "Obtener Todos los Tipos",
            description = "Obtener una lista de todos los tipos de proyecto.",
            tags = { "PROJECT" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = TypeDto.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/getAll")
    public ResponseEntity<CustomApiResponse<List<TypeResponseDto>>> getAll(@AuthenticationPrincipal User user){
        CustomApiResponse<List<TypeResponseDto>> response = new CustomApiResponse<>();
        try{
            response = typeService.getAllTypes(user);
        }catch (Exception e){
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setMessage(e.getMessage());
            response.setError(true);
        }
        return new ResponseEntity<>(response, response.getStatus());
    }



}

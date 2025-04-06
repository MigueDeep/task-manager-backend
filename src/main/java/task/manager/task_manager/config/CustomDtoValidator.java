package task.manager.task_manager.config;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.List;

public class CustomDtoValidator<T> {
    public String getErrors(T data){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        StringBuilder errorsSB = new StringBuilder();

        List<ConstraintViolation<T>> errorsSet = validator.validate(data).stream().toList();
        for (int i = 0; i < errorsSet.size(); i++) {
            if(i < errorsSet.size()-1){
                errorsSB.append(errorsSet.get(i).getMessage()).append(", ");
            } else {
                errorsSB.append(errorsSet.get(i).getMessage());
            }
        }
        return errorsSB.toString();
    }
}

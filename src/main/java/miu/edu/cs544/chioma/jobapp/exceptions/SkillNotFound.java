package miu.edu.cs544.chioma.jobapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason = "Skill with Id not found")
public class SkillNotFound extends RuntimeException{

    public SkillNotFound(String message) {
        System.out.println(message);
    }
}

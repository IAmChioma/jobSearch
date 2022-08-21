package miu.edu.cs544.chioma.jobapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason = "Application with Id not found")
public class ApplicationNotFound extends RuntimeException{

    public ApplicationNotFound(String message) {
        System.out.println(message);
    }
}

package miu.edu.cs544.chioma.jobapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason = "Company with Id not found")
public class CompanyNotFound extends RuntimeException{

    public CompanyNotFound(String message) {
        System.out.println(message);
    }
}

package miu.edu.cs544.chioma.jobapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason = "Interview with Id not found")
public class InterviewNotFound extends RuntimeException{

    public InterviewNotFound(String message) {
        System.out.println(message);
    }
}

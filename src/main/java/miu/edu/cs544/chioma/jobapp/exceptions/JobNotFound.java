package miu.edu.cs544.chioma.jobapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason = "${job.not.found}")
public class JobNotFound extends RuntimeException{

    public JobNotFound(String message) {
        System.out.println(message);
    }
}

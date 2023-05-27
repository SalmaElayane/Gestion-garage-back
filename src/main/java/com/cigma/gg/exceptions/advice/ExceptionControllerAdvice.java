package com.cigma.gg.exceptions.advice;

import com.cigma.gg.exceptions.SaveOrUpdateUserException;
import com.cigma.gg.exceptions.advice.dto.ErrorMsgDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ExceptionControllerAdvice {

    /**
     * @param e
     * @param wr
     * @return
     */
    @ExceptionHandler(SaveOrUpdateUserException.class)
    public ResponseEntity<ErrorMsgDto> handleSaveUserException(Exception e, WebRequest wr) {
        return new ResponseEntity<>(new ErrorMsgDto(e.getMessage()), HttpStatus.BAD_REQUEST);
    }
}

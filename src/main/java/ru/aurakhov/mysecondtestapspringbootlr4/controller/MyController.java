package ru.aurakhov.mysecondtestapspringbootlr4.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.aurakhov.mysecondtestapspringbootlr4.exception.UnsupportedCodeException;
import ru.aurakhov.mysecondtestapspringbootlr4.exception.ValidationFailedException;
import ru.aurakhov.mysecondtestapspringbootlr4.model.*;
import ru.aurakhov.mysecondtestapspringbootlr4.service.ModifyResponseService;
import ru.aurakhov.mysecondtestapspringbootlr4.service.UnsupportedCodeService;
import ru.aurakhov.mysecondtestapspringbootlr4.service.ValidationService;
import ru.aurakhov.mysecondtestapspringbootlr4.util.DateTimeUtil;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@RestController
public class MyController {

    private final ValidationService validationService;
    private final UnsupportedCodeService unsupportedCodeService;

    private final ModifyResponseService modifyResponseService;

    @Autowired
    public MyController(ValidationService validationService,
                        UnsupportedCodeService unsupportedCodeService,
                        @Qualifier("ModifyOperationUidResponseService ") ModifyResponseService modifyResponseService) {
        this.validationService = validationService;

        this.unsupportedCodeService = unsupportedCodeService;
        this.modifyResponseService = modifyResponseService;
    }







    @PostMapping(value = "/feedback")
    public ResponseEntity<Response> feedback(@Valid @RequestBody Request request,
                                             BindingResult bindingResult) throws ParseException {

        log.info("request: {}", request);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        Date date1 = sdf.parse(request.getSystemTime());
        Date date2 = sdf.parse(DateTimeUtil.getCustomFormat().format(new Date()));

        // Вычисляем разницу в миллисекундах
        long diff = date2.getTime() - date1.getTime();
        log.info("разница во времени составляет: " + diff + " мс " +
                " время request от Service1: " + request.getSystemTime() + "" +
                " время в Service2: " + DateTimeUtil.getCustomFormat().format(new Date()));


        Response response = Response.builder()
                .uid(request.getUid())
                .operationUid("")
                .systemsName("")
                .systemTime(DateTimeUtil.getCustomFormat().format(new Date()))
                .code(Codes.SUCCESS)
                .errorCode(ErrorCodes.EMPTY)
                .errorMessage(ErrorMessages.EMPTY)
                .build();
        try {
            unsupportedCodeService.isValid(request.getUid());
        } catch (UnsupportedCodeException e) {
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.UNSUPPORTED_EXCEPTION);
            response.setErrorMessage(ErrorMessages.UNSUPPORTED);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            validationService.isValid(bindingResult);
        } catch (ValidationFailedException e) {
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.VALIDATION_EXCEPTION);
            response.setErrorMessage(ErrorMessages.VALIDATION);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.UNKNOWN_EXCEPTION);
            response.setErrorMessage(ErrorMessages.UNKNOWN);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }
        modifyResponseService.modify(response);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

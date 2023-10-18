package ru.aurakhov.mysecondtestapspringbootlr4.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.aurakhov.mysecondtestapspringbootlr4.exception.UnsupportedCodeException;

@Slf4j
@Service
public class RequestUnsupportedCodeService implements UnsupportedCodeService {
    String message = "Uid не должен быть '123'";
    @Override
    public void isValid(String s) throws UnsupportedCodeException {
        if (s.equals("123")) {
            log.warn("Сообщение из RequestUnsupportedCodeService: " + message);
            throw new UnsupportedCodeException(message);


        }
    }
}

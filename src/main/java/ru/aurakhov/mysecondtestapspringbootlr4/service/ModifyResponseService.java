package ru.aurakhov.mysecondtestapspringbootlr4.service;

import org.springframework.stereotype.Service;
import ru.aurakhov.mysecondtestapspringbootlr4.model.Response;

@Service
public interface ModifyResponseService {

    Response modify(Response response);



}


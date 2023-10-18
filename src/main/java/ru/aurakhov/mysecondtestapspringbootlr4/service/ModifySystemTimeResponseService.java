package ru.aurakhov.mysecondtestapspringbootlr4.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.aurakhov.mysecondtestapspringbootlr4.model.Response;
import ru.aurakhov.mysecondtestapspringbootlr4.util.DateTimeUtil;

import java.util.Date;

@Service
@Qualifier("ModifySystemTimeResponseService")
public class ModifySystemTimeResponseService implements ModifyResponseService {
    @Override
    public Response modify(Response response) {

        response.setSystemTime(DateTimeUtil.getCustomFormat().format(new Date()));

        return response;
    }
}

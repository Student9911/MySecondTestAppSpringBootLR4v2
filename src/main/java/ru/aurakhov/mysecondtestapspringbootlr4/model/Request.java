package ru.aurakhov.mysecondtestapspringbootlr4.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Request {

    @NotBlank //указывает, что поле не может быть пустым или содержать только пробельные символы.
    @Size(max = 32) //указывает, что длина значения поля не может превышать 32 символа.
    private String uid;

    @NotBlank
    @Size(max = 32)
    private String operationUid;
    private String systemName;
    @NotBlank
    private String systemTime;
    private String source;

    @Range(min = 1, max = 100000,
            message = "Значение должно быть в диапазоне от 1 до 100000")
    //указывает, что значение поля должно находиться в заданном диапазоне (от 1 до 100000).
    private int communicationId;
    private int templateId;
    private int productCode;
    private int smsCode;

    @Override
    public String toString() {
        return "{" +
                "uid='" + uid + '\'' +
                ", operationUid='" + operationUid + '\'' +
                ", systemName='" + systemName + '\'' +
                ", systemTime='" + systemTime + '\'' +
                ", source='" + source + '\'' +
                ", communicationId='" + communicationId + '\'' +
                ", templateId='" + templateId + '\'' +
                ", productCode='" + productCode + '\'' +
                ", smsCode='" + smsCode +
                '}';
    }

}




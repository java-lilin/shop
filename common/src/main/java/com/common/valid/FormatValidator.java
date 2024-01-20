package com.common.valid;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

/**
 * @version 1.0
 * @projectName: shop
 * @author: lin
 * @description:TODO
 * @date: 2024/1/14  17:10
 */
@Component
public class FormatValidator {
    // 正则表达式模式定义
    private static final String PHONE_PATTERN = "^1[3-9]\\d{9}$";
    private static final String EMAIL_PATTERN = "^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$";
    private static final String ID_CARD_PATTERN = "^\\d{17}[0-9Xx]$";

    public boolean isPhoneNumberValid(String phoneNumber){
        return Pattern.matches(PHONE_PATTERN, phoneNumber);
    }

    public boolean isEmailAddressValid(String emailAddress){
        return Pattern.matches(EMAIL_PATTERN, emailAddress);
    }

    public boolean isIdCardNumberValid(String idCardNumber){
        return Pattern.matches(ID_CARD_PATTERN, idCardNumber);
    }
}
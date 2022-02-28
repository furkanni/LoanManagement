package com.innova.loan.utils;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * Twilio SMS Service Integration.
 */
@Log4j2
@Component
public class SMSUtil {

    @Autowired
    private Environment environment;

    /**
     *
     * @param phoneNumber is the number which get the sms
     * @param approval is the number which send the sms
     * @param creditLimit is already calculated in CreditScoreUtil and added the message
     */
    public void sendSms(String phoneNumber, Boolean approval, Double creditLimit) {
        try {
            Twilio.init(environment.getProperty("TWILIO_ACCOUNT_SID"), environment.getProperty("TWILIO_AUTH_TOKEN"));
            Message message = Message.creator(
                            new com.twilio.type.PhoneNumber(phoneNumber),
                            new com.twilio.type.PhoneNumber("+19035687302"),
                            approvalString(approval, creditLimit))
                    .create();

        }catch (Exception e){
            log.error(e.getMessage());
            log.error("Mesaj gönderilemedi");
        }

    }

    /**
     *
     * @param approval checking the approval for sending the right message (True = Accepted, False = Not Accepted)
     * @param creditLimit is added for accepted applications
     * @return the content of the message
     */
    public String approvalString(Boolean approval, Double creditLimit) {
        StringBuilder result = new StringBuilder();
        result.append("Kredi Başvurunuz ");
        if (approval) {
            result.append(creditLimit).append(" ₺ limit ile onaylanmıştır.");
        } else {
            result.append("reddedilmiştir.");
        }

        return result.toString();
    }
}


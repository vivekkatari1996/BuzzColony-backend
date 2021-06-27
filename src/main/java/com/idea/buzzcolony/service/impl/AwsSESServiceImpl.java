package com.idea.buzzcolony.service.impl;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.SendTemplatedEmailRequest;
import com.amazonaws.services.simpleemail.model.SendTemplatedEmailResult;
import com.idea.buzzcolony.enums.ses.EmailTemplate;
import org.json.JSONObject;

/**
 * @author Anand Ramesh
 * @version 1.0
 * @since 20/06/21
 */
public class AwsSESServiceImpl {

    private final static String FROM_EMAIL = "BuzzColony <contact.buzzcolony@gmail.com>";

    public static void sendSESMail(Object templdateData, String to, EmailTemplate emailTemplate) throws Exception {
        AmazonSimpleEmailService client = sesClientBuilder();
        SendTemplatedEmailRequest sendTemplatedEmailRequest = new SendTemplatedEmailRequest();
        sendTemplatedEmailRequest.setDestination(new Destination().withToAddresses(to));
        sendTemplatedEmailRequest.setSource(FROM_EMAIL);
        sendTemplatedEmailRequest.setTemplate(emailTemplate.name());
        sendTemplatedEmailRequest.setTemplateData(new JSONObject(templdateData).toString());
        SendTemplatedEmailResult sendTemplatedEmailResult = client.sendTemplatedEmail(sendTemplatedEmailRequest);
        client.shutdown();
    }

    private static AmazonSimpleEmailService sesClientBuilder() {
        return AmazonSimpleEmailServiceClientBuilder.standard()
                .withRegion(Regions.AP_SOUTH_1).build();
    }

}

package com.simu.ilearn.server.util;

import org.springframework.binding.message.MessageBuilder;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;

@Component("i18nMessageBean")
public class I18nMessageBean implements MessageSourceAware {
    private static MessageSource messageSource;

    public void setMessageSource(MessageSource msgsource) {
        messageSource = msgsource;
    }

    public String getMessage(String messageCode, String... params) {
        MessageBuilder builder = new MessageBuilder().code(messageCode);
        if (params != null) {
            for (String param : params) {
                builder = builder.arg(param);
            }
        }

        return builder.build().resolveMessage(messageSource, Locale.FRANCE).getText();
    }

    public String getMessage(String messageCode, List<String> params) {
        MessageBuilder builder = new MessageBuilder().code(messageCode);
        if (params != null) {
            for (String param : params) {
                builder = builder.arg(param);
            }
        }

        return builder.build().resolveMessage(messageSource, Locale.FRANCE).getText();
    }

    public static MessageSource getMessageSource() {
        return messageSource;
    }
}

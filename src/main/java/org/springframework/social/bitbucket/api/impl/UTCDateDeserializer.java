package org.springframework.social.bitbucket.api.impl;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

public class UTCDateDeserializer extends JsonDeserializer<Date> {

    @Override
    public Date deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT,
                    Locale.ENGLISH);
            dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            return dateFormat.parse(jp.getText());
        } catch (ParseException e) {
            throw new JsonParseException("Can't parse date : " + jp.getText(),
                    jp.getCurrentLocation());
        }
    }

    /**
     * JDK can't parse a TZ with a colon. Assume it will be +00:00.
     */
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

}
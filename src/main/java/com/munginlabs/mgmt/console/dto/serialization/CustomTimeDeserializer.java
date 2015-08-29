/*
 * Copyright 2015 Amila Silva (amilasilva88@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.munginlabs.mgmt.console.dto.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * custom JSON serializer for java.sql.Time type
 *
 */
public class CustomTimeDeserializer extends JsonDeserializer<Time> {

    private static final Logger LOGGER = Logger.getLogger(CustomTimeDeserializer.class);

    @Override
    public Time deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        Time time = null;
        try {
            LOGGER.info("converting time parameter from value " + jp.getText());
            Date date = formatter.parse("1970/01/01 " + jp.getText());
            time = new Time(date.getTime());
        } catch (ParseException e) {
            throw new TimeDeserializationException(e);
        }
        return time;
    }

}

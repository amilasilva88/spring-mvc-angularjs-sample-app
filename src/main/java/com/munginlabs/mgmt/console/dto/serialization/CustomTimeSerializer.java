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


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.sql.Time;
import java.text.SimpleDateFormat;

/**
 *
 * custom JSON deserializer for java.sql.Time type
 *
 */
public class CustomTimeSerializer extends JsonSerializer<Time> {

    @Override
    public void serialize(Time value, JsonGenerator jgen, SerializerProvider provider)
            throws IOException, JsonProcessingException {

        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        jgen.writeString(formatter.format(value));
    }

}

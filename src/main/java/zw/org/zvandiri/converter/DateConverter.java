/*
 * Copyright 2015 Judge Muzinda.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package zw.org.zvandiri.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

/**
 * @author Judge Muzinda
 */
public class DateConverter implements Converter<String, Date> {

    private final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    public Date convert(String s) {
        try {
            return format.parse(s);
        } catch (ParseException ex) {
            System.err.println("Error occured :" + ex.getMessage());
            return null;
        }
    }
}
package org.example.tools;

import org.example.annotations.HostFieldDate;

import java.lang.annotation.Annotation;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class HostParserDateWorker implements HostParserWorker{
    @Override
    public void process(String hostResponseText, HashMap<String, Object> response, Annotation annotation){
        HostFieldDate hostFieldAnnotation = (HostFieldDate) annotation;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(hostFieldAnnotation.pattern());
        response.put(hostFieldAnnotation.name(),
                LocalDate.parse(hostResponseText.substring(hostFieldAnnotation.start(), hostFieldAnnotation.end()), formatter));
    }

    @Override
    public String fieldToString(HashMap<String, Object> obj, Annotation annotation) {
        HostFieldDate hostFieldAnnotation = (HostFieldDate) annotation;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(hostFieldAnnotation.pattern());
        return ((LocalDate) obj.get(hostFieldAnnotation.name())).format(formatter).substring(0, hostFieldAnnotation.end() - hostFieldAnnotation.start());
    }
}

package org.example.tools;

import org.example.annotations.HostFieldDate;
import org.example.annotations.HostFieldString;

import java.lang.annotation.Annotation;
import java.util.HashMap;

public class HostParserStringWorker implements HostParserWorker{
    @Override
    public void process(String hostResponseText, HashMap<String, Object> response, Annotation annotation){
        HostFieldString hostFieldAnnotation = (HostFieldString) annotation;
        response.put(hostFieldAnnotation.name(), hostResponseText.substring(hostFieldAnnotation.start(), hostFieldAnnotation.end()));
    }

    @Override
    public String fieldToString(HashMap<String, Object> obj, Annotation annotation) {
        HostFieldString hostFieldAnnotation = (HostFieldString) annotation;
        return ((String) obj.get(hostFieldAnnotation.name())).substring(0, hostFieldAnnotation.end() - hostFieldAnnotation.start());
    }
}

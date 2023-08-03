package org.example.tools;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;

public interface HostParserWorker {

    void process(String hostResponseText, HashMap<java.lang.String, java.lang.Object> response, Annotation annotation) ;

    String fieldToString(HashMap<String, Object> obj, Annotation annotation);

}

package org.example.annotations;

import java.lang.annotation.Annotation;

public class AnnotationsTool {


    public int getStart(Annotation annotation){
        if (isKnowedAnnotation(annotation)){
            return 1;
        }
        return -1;
    }


    public static Boolean isKnowedAnnotation(Annotation annotation){
        return  (annotation instanceof HostFieldString) ||
                (annotation instanceof HostFieldDate);
    }


}

package org.example.tools;

import org.example.annotations.HostFieldDate;
import org.example.annotations.HostFieldString;

import java.lang.annotation.Annotation;

public class HostFieldDateDataComparable implements HostParserDataComparable{
    @Override
    public int start(Annotation annotation) {
        HostFieldDate hostField = (HostFieldDate) annotation;
        return hostField.start();
    }
}

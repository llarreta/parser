package org.example.tools;

import org.example.annotations.HostFieldString;

import java.lang.annotation.Annotation;

public class HostFieldStringDataComparable implements HostParserDataComparable{
    @Override
    public int start(Annotation annotation) {
        HostFieldString hostFieldString = (HostFieldString) annotation;
        return hostFieldString.start();
    }
}

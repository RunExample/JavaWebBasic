package com.example;

import java.lang.reflect.AnnotatedElement;

public class main {
    public static void main(String[] args) {
        var a = UseAnnotation.class;
        readAnnotation(a);
    }
    static void readAnnotation(AnnotatedElement element) {
        try {
            System.out.println("Annotation element values: \n");
            if (element.isAnnotationPresent(UserDefinedAnnotation.class)) {
                // getAnnotation returns Annotation type
                var singleAnnotation =
                        element.getAnnotation(UserDefinedAnnotation.class);
                var header = (UserDefinedAnnotation) singleAnnotation;

                System.out.println("value: " + header.value());
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}

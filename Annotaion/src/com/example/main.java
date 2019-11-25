package com.example;

import java.lang.reflect.AnnotatedElement;

public class main {
    public static void main(String[] args) {
        var a = UseAnnotation.class;
        readAnnotation(a);
    }
    static void readAnnotation(AnnotatedElement element) {
        try {
            System.out.println("Annotation element values:");
            if (element.isAnnotationPresent(AnnotationDef.class)) {
                // getAnnotation returns Annotation type
                var singleAnnotation =
                        element.getAnnotation(AnnotationDef.class);
                var header = (AnnotationDef) singleAnnotation;

                System.out.println("value: " + header.value());
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}

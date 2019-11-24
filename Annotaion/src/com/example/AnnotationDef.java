package com.example;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,
        ElementType.METHOD,
        ElementType.CONSTRUCTOR,
        ElementType.ANNOTATION_TYPE,
        ElementType.PACKAGE,
        ElementType.FIELD,
        ElementType.LOCAL_VARIABLE,
})
@Inherited
@interface UserDefinedAnnotation {
    String value() default "have a try";
}

public class AnnotationDef {

}

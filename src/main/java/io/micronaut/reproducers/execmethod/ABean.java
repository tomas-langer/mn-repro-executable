package io.micronaut.reproducers.execmethod;

import javax.inject.Singleton;
import javax.validation.constraints.Pattern;

@Singleton
public class ABean {
    @MethodAnnot
    public String upper(@Pattern(regexp = "\\w+") String message) {
        return message.toUpperCase();
    }
}

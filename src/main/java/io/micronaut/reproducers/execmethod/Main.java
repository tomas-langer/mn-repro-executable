
package io.micronaut.reproducers.execmethod;

import javax.validation.ConstraintViolationException;

import io.micronaut.context.ApplicationContext;

/**
 * The application main class.
 */
public final class Main {
    public static void main(String[] args) {
        ApplicationContext ctx = ApplicationContext.run();

        ABean bean = ctx.getBean(ABean.class);

        String hi = "hi";
        String nok = "$9s";

        testDirect(bean, hi);
        testDirect(bean, nok);
        testHandle(hi);
        testHandle(nok);
        testHandle2(hi);
        testHandle2(nok);
    }

    private static void testHandle2(String message) {
        try {
            System.out.println("Method Handle getting definition: ");
            System.out.println("\t" + message + " = " + MethodProcessor2.execute(message));
        } catch (ConstraintViolationException e) {
            System.out.println("\t" + message + " failed validation: " + e.getMessage());
        }
    }

    private static void testHandle(String message) {
        try {
            System.out.println("Method Handle: ");
            System.out.println("\t" + message + " = " + MethodProcessor.execute(message));
        } catch (ConstraintViolationException e) {
            System.out.println("\t" + message + " failed validation: " + e.getMessage());
        }
    }

    private static void testDirect(ABean bean, String message) {
        try {
            System.out.println("Direct call: ");
            System.out.println("\t" + message + " = " + bean.upper(message));
        } catch (ConstraintViolationException e) {
            System.out.println("\t" + message + " failed validation: " + e.getMessage());
        }
    }

}

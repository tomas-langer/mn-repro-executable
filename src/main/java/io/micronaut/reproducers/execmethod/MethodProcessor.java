package io.micronaut.reproducers.execmethod;

import javax.inject.Singleton;

import io.micronaut.context.ExecutionHandleLocator;
import io.micronaut.context.processor.ExecutableMethodProcessor;
import io.micronaut.inject.BeanDefinition;
import io.micronaut.inject.ExecutableMethod;
import io.micronaut.inject.MethodExecutionHandle;

@Singleton
public class MethodProcessor implements ExecutableMethodProcessor<MethodAnnot> {
    private static MethodExecutionHandle<?, Object> handle;

    private final ExecutionHandleLocator executionHandleLocator;

    public MethodProcessor(ExecutionHandleLocator executionHandleLocator) {
        this.executionHandleLocator = executionHandleLocator;
    }

    @Override
    public void process(BeanDefinition<?> beanDefinition, ExecutableMethod<?, ?> method) {
        handle = executionHandleLocator.createExecutionHandle(beanDefinition, (ExecutableMethod<Object, ?>) method);
    }

    static String execute(String param) {
        return (String) handle.invoke(param);
    }
}

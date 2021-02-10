package io.micronaut.reproducers.execmethod;

import javax.inject.Singleton;

import io.micronaut.context.BeanContext;
import io.micronaut.context.ExecutionHandleLocator;
import io.micronaut.context.processor.ExecutableMethodProcessor;
import io.micronaut.inject.BeanDefinition;
import io.micronaut.inject.ExecutableMethod;
import io.micronaut.inject.MethodExecutionHandle;

@Singleton
public class MethodProcessor2 implements ExecutableMethodProcessor<MethodAnnot> {
    private static MethodExecutionHandle<?, Object> handle;

    private final ExecutionHandleLocator executionHandleLocator;
    private final BeanContext beanContext;

    public MethodProcessor2(ExecutionHandleLocator executionHandleLocator, BeanContext beanContext) {
        this.executionHandleLocator = executionHandleLocator;
        this.beanContext = beanContext;
    }

    @Override
    public void process(BeanDefinition<?> beanDefinitionParam, ExecutableMethod<?, ?> method) {
        BeanDefinition<?> beanDefinition = beanContext.getBeanDefinition(beanDefinitionParam.getBeanType());
        handle = executionHandleLocator.createExecutionHandle(beanDefinition, (ExecutableMethod<Object, ?>) method);
    }

    static String execute(String param) {
        return (String) handle.invoke(param);
    }
}

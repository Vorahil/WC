package com.example.sp.config;

import com.example.sp.annotation.Role;
import com.example.sp.annotation.RoleRequired;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
@Component
public class Register implements BeanFactoryAware, CommandLineRunner {
    public static Map<String, Role> Role_Map = new HashMap<>();
    public ListableBeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (ListableBeanFactory) beanFactory;
    }

    @Override
    public void run(String... args) {
        Map<String, Object> beansWithRestController = beanFactory.getBeansWithAnnotation(RestController.class);
        Set<Class<?>> set = beansWithRestController.values().stream().map(Object::getClass).collect(Collectors.toSet());
        for (Class c : set) {
            for (Method m : c.getMethods()) {
                RoleRequired annotation = AnnotationUtils.findAnnotation(m, RoleRequired.class);
                if (annotation == null) {
                    continue;
                }
                Role_Map.put(m.getName(), annotation.value());
            }
        }
    }
}

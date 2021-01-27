package com.careydevelopment.contact.util;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.careydevelopment.contact.model.Contact;
import com.careydevelopment.contact.model.LineOfBusiness;

/**
 * Utility class that trims all whitespace from all String fields
 * Stolen from StackOverflow
 * Don't @ me
 */
public class SpaceUtil {

    private static final Logger LOG = LoggerFactory.getLogger(SpaceUtil.class);

    
    public static Object trimReflective(Object object) {
        if (object == null)
            return null;

        Class<? extends Object> c = object.getClass();
        try {
            for (PropertyDescriptor propertyDescriptor : Introspector
                    .getBeanInfo(c, Object.class).getPropertyDescriptors()) {
                Method method = propertyDescriptor.getReadMethod();
                if (method != null) {
                    String name = method.getName();
    
                    if (method.getReturnType().equals(String.class)) {
                        String property = (String) method.invoke(object);
                        if (property != null) {
                            try {
                                Method setter = c.getMethod("set" + name.substring(3),
                                        new Class<?>[] { String.class });
                                if (setter != null)
                                    setter.invoke(object, property.trim());
                            } catch (NoSuchMethodException ne) {
                                LOG.warn("Problem trying to set field " + name.substring(3));
                            }
                        }
                    } else {
                        //handle child objects
                        Object property = (Object) method.invoke(object);
                        if (property != null && !(object instanceof java.lang.Class)) {
                            if (!(property instanceof AnnotatedType) 
                                    && !(property instanceof Annotation)) {

                                trimReflective(property);                                
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            LOG.error("Problem trimming " + object, e);
        }

        return object;
    }
}

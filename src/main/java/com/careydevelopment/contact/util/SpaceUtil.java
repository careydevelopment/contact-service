package com.careydevelopment.contact.util;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    
                    // If the current level of Property is of type String
                    if (method.getReturnType().equals(String.class)) {
                        String property = (String) method.invoke(object);
                        if (property != null) {
                            Method setter = c.getMethod("set" + name.substring(3),
                                    new Class<?>[] { String.class });
                            if (setter != null)
                                // Setter to trim and set the trimmed String value
                                setter.invoke(object, property.trim());
                        }
                    }
    
                    // If an Object Array of Properties - added additional check to
                    // avoid getBytes returning a byte[] and process
                    if (method.getReturnType().isArray()
                            && !method.getReturnType().isPrimitive()
                            && !method.getReturnType().equals(String[].class)
                            && !method.getReturnType().equals(byte[].class)) {
                        System.out.println(method.getReturnType());
                        // Type check for primitive arrays (would fail typecasting
                        // in case of int[], char[] etc)
                        if (method.invoke(object) instanceof Object[]) {
                            Object[] objectArray = (Object[]) method.invoke(object);
                            if (objectArray != null) {
                                for (Object obj : (Object[]) objectArray) {
                                    // Recursively revisit with the current property
                                    trimReflective(obj);
                                }
                            }
                        }
                    }
                    // If a String array
                    if (method.getReturnType().equals(String[].class)) {
                        String[] propertyArray = (String[]) method.invoke(object);
                        if (propertyArray != null) {
                            Method setter = c.getMethod("set" + name.substring(3),
                                    new Class<?>[] { String[].class });
                            if (setter != null) {
                                String[] modifiedArray = new String[propertyArray.length];
                                for (int i = 0; i < propertyArray.length; i++)
                                    if (propertyArray[i] != null)
                                        modifiedArray[i] = propertyArray[i].trim();
    
                                // Explicit wrapping
                                setter.invoke(object,
                                        new Object[] { modifiedArray });
                            }
                        }
                    }
                    // Collections start
                    if (Collection.class.isAssignableFrom(method.getReturnType())) {
                        Collection collectionProperty = (Collection) method
                                .invoke(object);
                        if (collectionProperty != null) {
                            for (int index = 0; index < collectionProperty.size(); index++) {
                                if (collectionProperty.toArray()[index] instanceof String) {
                                    String element = (String) collectionProperty
                                            .toArray()[index];
    
                                    if (element != null) {
                                        // Check if List was created with
                                        // Arrays.asList (non-resizable Array)
                                        if (collectionProperty instanceof List) {
                                            ((List) collectionProperty).set(index,
                                                    element.trim());
                                        } else {
                                            collectionProperty.remove(element);
                                            collectionProperty.add(element.trim());
                                        }
                                    }
                                } else {
                                    // Recursively revisit with the current property
                                    trimReflective(collectionProperty.toArray()[index]);
                                }
                            }
                        }
                    }
                    // Separate placement for Map with special conditions to process
                    // keys and values
                    if (method.getReturnType().equals(Map.class)) {
                        Map mapProperty = (Map) method.invoke(object);
                        if (mapProperty != null) {
                            // Keys
                            for (int index = 0; index < mapProperty.keySet().size(); index++) {
                                if (mapProperty.keySet().toArray()[index] instanceof String) {
                                    String element = (String) mapProperty.keySet()
                                            .toArray()[index];
                                    if (element != null) {
                                        mapProperty.put(element.trim(),
                                                mapProperty.get(element));
                                        mapProperty.remove(element);
                                    }
                                } else {
                                    // Recursively revisit with the current property
                                    trimReflective(mapProperty.get(index));
                                }
    
                            }
                            // Values
                            for (Map.Entry entry : (Set<Map.Entry>) mapProperty
                                    .entrySet()) {
    
                                if (entry.getValue() instanceof String) {
                                    String element = (String) entry.getValue();
                                    if (element != null) {
                                        entry.setValue(element.trim());
                                    }
                                } else {
                                    // Recursively revisit with the current property
                                    trimReflective(entry.getValue());
                                }
                            }
                        }
                    } else {// Catch a custom data type as property and send through
                            // recursion
                        Object property = (Object) method.invoke(object);
                        if (property != null) {
                            trimReflective(property);
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

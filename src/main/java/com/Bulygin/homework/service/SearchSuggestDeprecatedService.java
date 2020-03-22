package com.Bulygin.homework.service;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Set;
import org.reflections.Reflections;

class SearchSuggestDeprecatedService {

  /**
   * If animal in animals is created from deprecated class, the method finds an interface or super
   * class for it . If interface or super class exists, the method finds non-deprecated
   * implementations or subclasses and print these classes as suggestions to use instead of the
   * deprecated class.
   *
   * @param objects can contain deprecated and non- deprecated objects.
   * @return deprecated animals list.
   */
  static ArrayList<Object> printAlternateForDeprecated(ArrayList<Object> objects) {
    ArrayList<Object> result = new ArrayList<Object>();
    for (Object object : objects) {
      Class myClass = object.getClass();
      Annotation deprecatedAnnotation = myClass.getAnnotation(Deprecated.class);
      if (deprecatedAnnotation != null) {
        result.add(object);
        if (!SearchSuggestDeprecatedService.getAlternatedInSuper(myClass)) {
          SearchSuggestDeprecatedService.getAlternatedInInterfaces(myClass);
        }
      }
    }
    return result;
  }

  private static boolean getAlternatedInSuper(Class myClass) {
    boolean result = false;
    Class usedSuper = myClass.getSuperclass();
    if (usedSuper != null) {
      result = printAlternated(myClass, usedSuper);
    }
    return result;
  }

  private static boolean getAlternatedInInterfaces(Class myClass) {
    boolean result = false;
    Class[] usedInterfaces = myClass.getInterfaces();
    if (usedInterfaces != null) {
      for (Class interFace : usedInterfaces) {
        result = printAlternated(myClass, interFace);
      }
    }
    return result;
  }

  private static boolean printAlternated(Class oldClass, Class alternatedClass) {
    boolean result = false;
    Reflections reflections = new Reflections("com.Bulygin.homework");
    Set subTypesOf = reflections.getSubTypesOf(alternatedClass);
    for (Class adviceClass : (Iterable<Class>) subTypesOf) {
      Annotation deprecatedAnnotation = adviceClass.getAnnotation(Deprecated.class);
      if (deprecatedAnnotation == null) {
        result = true;
        System.out.println(
            oldClass.getName() + " is Deprecated, you can use class " + adviceClass.getName()
                + " instead");
      }
    }
    return result;
  }
}
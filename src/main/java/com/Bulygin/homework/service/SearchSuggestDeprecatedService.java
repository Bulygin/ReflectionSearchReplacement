package com.Bulygin.homework.service;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import org.reflections.Reflections;

public class SearchSuggestDeprecatedService {

  public static ArrayList<Object> findDeprecated(ArrayList<Object> animals) {
    ArrayList<Object> result = new ArrayList<Object>();
    for (Object i : animals) {
      Class myClass = i.getClass();
      Annotation deprecatedAnnotation = myClass.getAnnotation(Deprecated.class);
      if (deprecatedAnnotation != null) {
        result.add(i);
        if (!SearchSuggestDeprecatedService.getAlternatedInSuper(myClass)) {
          SearchSuggestDeprecatedService.getAlternatedInInterfaces(myClass);
        }
      }
    }
    return result;
  }

  public static boolean getAlternatedInSuper(Class myClass) {
    boolean result = false;
    Class usedSuper = myClass.getSuperclass();
    if (usedSuper != null) {
      Reflections reflections = new Reflections("com.Bulygin.homework");
      Set subTypesOf = reflections.getSubTypesOf(usedSuper);
      Iterator<Class> iterator = subTypesOf.iterator();
      while (iterator.hasNext()) {
        Class adviceClass = iterator.next();
        Annotation deprecatedAnnotation = adviceClass.getAnnotation(Deprecated.class);
        if (deprecatedAnnotation == null) {
          result = true;
          System.out.println(
              myClass.getName() + "is Deprecated, you can use class " + adviceClass.getName()
                  + " instead");
        }
      }
    }
    return result;
  }

  public static boolean getAlternatedInInterfaces(Class myClass) {
    boolean result = false;
    Class[] usedInterfaces = myClass.getInterfaces();
    if (usedInterfaces != null) {
      for (Class interFace : usedInterfaces) {
        Reflections reflections = new Reflections("com.Bulygin.homework");
        Set subTypesOf = reflections.getSubTypesOf(interFace);
        Iterator<Class> iterator = subTypesOf.iterator();
        while (iterator.hasNext()) {
          Class adviceClass = iterator.next();
          Annotation deprecatedAnnotation = adviceClass.getAnnotation(Deprecated.class);
          if (deprecatedAnnotation == null) {
            result = true;
            System.out.println(
                myClass.getName() + " is Deprecated, you can use class " + adviceClass.getName()
                    + " instead");
          }
        }
      }
    }
    return result;
  }

}

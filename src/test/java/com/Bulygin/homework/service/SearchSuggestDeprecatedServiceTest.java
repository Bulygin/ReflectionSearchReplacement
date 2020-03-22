package com.Bulygin.homework.service;

import com.Bulygin.homework.entity.Cow;
import com.Bulygin.homework.entity.Crowl;
import com.Bulygin.homework.entity.Dodo;
import com.Bulygin.homework.entity.Iguadon;
import com.Bulygin.homework.entity.Lizard;
import com.Bulygin.homework.entity.Triceratops;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class SearchSuggestDeprecatedServiceTest {

  @SuppressWarnings("deprecated")
  @Test
  public void printAlternateForDeprecated_createAnimalsArray_MustBeThree() {
    ArrayList<Object> someAnimals = new ArrayList<Object>();
    someAnimals.add(new Crowl());
    someAnimals.add(new Dodo());
    someAnimals.add(new Lizard());
    someAnimals.add(new Triceratops());
    someAnimals.add(new Iguadon());
    someAnimals.add(new Cow());
    List<Object> deprecatedAnimals = SearchSuggestDeprecatedService
        .printAlternateForDeprecated(someAnimals);
    Assert.assertEquals(deprecatedAnimals.get(0).getClass(), Dodo.class);
    Assert.assertEquals(deprecatedAnimals.get(1).getClass(), Triceratops.class);
    Assert.assertEquals(deprecatedAnimals.get(2).getClass(), Iguadon.class);
  }
}
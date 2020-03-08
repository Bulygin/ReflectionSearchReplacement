package com.Bulygin.homework;

import com.Bulygin.homework.classes.Cow;
import com.Bulygin.homework.classes.Crowl;
import com.Bulygin.homework.classes.Dodo;
import com.Bulygin.homework.classes.Iguadon;
import com.Bulygin.homework.classes.Lizard;
import com.Bulygin.homework.classes.Triceratops;
import com.Bulygin.homework.service.SearchSuggestDeprecatedService;
import java.util.ArrayList;

public class Main {

  public static void main(String[] args) {
    ArrayList<Object> someAnimals = new ArrayList<Object>();
    someAnimals.add(new Crowl());
    someAnimals.add(new Dodo());
    someAnimals.add(new Lizard());
    someAnimals.add(new Triceratops());
    someAnimals.add(new Iguadon());
    someAnimals.add(new Cow());
    ArrayList<Object> result = SearchSuggestDeprecatedService.findDeprecated(someAnimals);
  }
}

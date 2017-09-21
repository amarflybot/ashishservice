package com.home.service;

import com.home.model.Person;

import java.util.Map;

/**
 * Created by amarendra on 20/09/17.
 */
public interface DataService {

    Map<Integer, Person> getData();

    Person getByID(Integer id);

    Integer save(Person object);

    void add();
}

package com.home.service;

import com.home.model.Person;
import org.fluttercode.datafactory.impl.DataFactory;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by amarendra on 20/09/17.
 */
public class PersonService implements DataService {

    private Map<Integer, Person> data;

    private DataFactory dataFactory;

    public PersonService() {
        this.data =  new ConcurrentHashMap<>();
        dataFactory = new DataFactory();
        Arrays.asList("Amar","Nagendra","Ashish")
                .forEach(name -> save(new Person(name)));
    }

    @Override
    public Map<Integer, Person> getData() {
        return data;
    }

    @Override
    public Person getByID(final Integer integer) {
        return data.get(integer);
    }

    @Override
    public Integer save(final Person object) {
        Optional<Integer> integer = data.keySet().stream().max(Comparator.naturalOrder());
        object.setId(integer.orElse(0)+1);
        data.put(object.getId(), object);
        return object.getId();
    }

    @Override
    public void add() {
        final String name = dataFactory.getName();
        save(new Person(name));
    }
}

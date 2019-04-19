package com.example.testapp.java_test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

public class CollectionTest {

    @Test
    public void IteratorTest() {
        Collection<String> collection = new HashSet<>();

        collection.add("java");

        collection.add("c#");

        collection.add("c#");

        collection.add("c++");

        Iterator iterator = collection.iterator();

        while (iterator.hasNext()) {
            String temp = (String) iterator.next();

            int hashCode = temp.hashCode();

            System.out.println(temp + " hashCode: " + hashCode);
        }
    }
}

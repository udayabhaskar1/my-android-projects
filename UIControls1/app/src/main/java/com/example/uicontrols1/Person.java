package com.example.uicontrols1;

import java.util.HashMap;

public class Person extends HashMap<String, String> {
    public Person(String name, String nickname) {
        put("name", name);
        put("nickname", nickname);
    }
}

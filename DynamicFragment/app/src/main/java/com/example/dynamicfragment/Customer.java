package com.example.dynamicfragment;

import java.util.HashMap;

class Customer extends HashMap<String, Object> {
    Customer(String name, String address) {
        this.put("name", name);
        this.put("address", address);
    }
}

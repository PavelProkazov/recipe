package com.prokazov.recipes_app;

import android.util.Log;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

class LogicSearch {
    int size;

    public boolean logicSearch_product(HashMap<String, String> hashMap, HashSet<String> hashSet) {
        size = 0;

        for (Map.Entry<String, String> entry : hashMap.entrySet()) {
            String key = entry.getKey().toLowerCase();
            String value = entry.getValue();
            String productsSets = hashSet.toString().toLowerCase();
            Iterator<String> it_set = hashSet.iterator();

            if (key.contains(it_set.next().toLowerCase())) {
                size++;

            } else if (hashSet.isEmpty()) {
                Log.d("mLog", "No DATA - empty set of product");
            } else {
                System.out.println(size);
            }
        }
        return size==hashSet.size();
    }
}


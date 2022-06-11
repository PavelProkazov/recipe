package com.prokazov.recipes_app;

import java.util.HashMap;
import java.util.Map;

class SortRecipes {
int ii = 0;
   public HashMap<String,String> sortRecipesMap(HashMap<String,String> hashMap, String str)
   {
      HashMap<String,String> sortedMap = new HashMap<>();
         for (Map.Entry<String, String> entry : hashMap.entrySet()){
            String key = entry.getKey().toLowerCase();
            String value = entry.getValue();
               if(key.contains(str.toLowerCase())){
                     sortedMap.put(key,value);
               }
            }
         return sortedMap;
         }
   }



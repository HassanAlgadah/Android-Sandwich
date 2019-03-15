package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        try {
            JSONObject sandwich = new JSONObject(json);
            Sandwich s = new Sandwich();
            JSONObject name = sandwich.getJSONObject("name");
            s.setMainName(name.getString("mainName"));
            s.setPlaceOfOrigin(sandwich.getString("placeOfOrigin"));
            s.setDescription(sandwich.getString("description"));
            JSONArray also = name.getJSONArray("alsoKnownAs");
            List<String > lialso = new ArrayList<String>(also.length());
            for (int i = 0; i < also.length(); i++) {
                lialso.add(also.getString(i));
            }
            s.setAlsoKnownAs(lialso);
            JSONArray ing = sandwich.getJSONArray("ingredients");
            List<String > in  = new ArrayList<String>(ing.length());
            for (int i = 0; i < ing.length(); i++) {
                in.add(ing.getString(i));
            }
            s.setIngredients(in);
            s.setImage(sandwich.getString("image"));
            return s;
        }catch (Exception e){
            e.printStackTrace();

        }
        return null;
    }
}

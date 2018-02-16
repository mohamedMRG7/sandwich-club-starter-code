package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        Sandwich sandwich=null;
        try {
            JSONObject object=new JSONObject(json);
            JSONObject name=object.getJSONObject("name");
            String mainName=name.getString("mainName");
            JSONArray alsoknownasarrObject=  name.getJSONArray("alsoKnownAs");
            List<String> alsoknownas=getlist(alsoknownasarrObject);
            String placeOfOrigin=object.getString("placeOfOrigin");
            String description=object.getString("description");
            String image=object.getString("image");
            JSONArray ingredientsarrObject=object.getJSONArray("ingredients");
            List<String> ingredients=getlist(ingredientsarrObject);

            sandwich=new Sandwich(mainName,alsoknownas,placeOfOrigin,description,image,ingredients);



        } catch (JSONException e) {
            e.printStackTrace();
        }

        return sandwich;
    }



    private static List<String> getlist(JSONArray jsonArray) throws JSONException {
        List<String>list=new ArrayList<>();

        if (jsonArray.length() !=0)
            for (int i=0 ;i<jsonArray.length();i++)
            {
                list.add(jsonArray.getString(i));
            }

        return list;
    }
}

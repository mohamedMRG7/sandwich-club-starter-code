package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {

        TextView alsoknownas=findViewById(R.id.also_known_tv);
        alsoknownas.setText(getlistString(sandwich.getAlsoKnownAs()));
        TextView description=findViewById(R.id.description_tv);
        description.setText(sandwich.getDescription());
        TextView placeOforigin=findViewById(R.id.origin_tv);
        placeOforigin.setText(sandwich.getPlaceOfOrigin());
        TextView ingredients=findViewById(R.id.ingredients_tv);
        ingredients.setText(getlistString(sandwich.getIngredients()));

    }

    private String getlistString(List<String> list)
    {
        StringBuilder x=new StringBuilder();
        for (int i=0;i<list.size();i++)
        {
            if (i!=list.size()-1)
            x.append(list.get(i)+"  ,  ");

            else x.append(list.get(i));
        }
        if (list.size()==0)
            x.append("ONLY ONE NAME ");

        return x.toString();
    }
}

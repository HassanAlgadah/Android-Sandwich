package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    private TextView dis;
    private TextView poo;
    private TextView ing;
    private TextView also;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        dis = (TextView) findViewById(R.id.description_tv);
        poo = (TextView) findViewById(R.id.origin_tv);
        ing = (TextView) findViewById(R.id.ingredients_tv);
        also = (TextView) findViewById(R.id.also_known_tv);
        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            System.out.println(2);
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            System.out.println(1);
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            System.out.println(34);
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

    private void populateUI(Sandwich s) {
        dis.append("  "+s.getDescription()+"\n");
        poo.append("  "+s.getPlaceOfOrigin()+"\n");
        for (int i = 0; i < s.getIngredients().size(); i++) {
            ing.append("  "+s.getIngredients().get(i)+"\n");
        }
        for (int i = 0; i < s.getAlsoKnownAs().size(); i++) {
            also.append("  "+s.getAlsoKnownAs().get(i)+"\n");
        }



    }
}

package com.tyyar.tyyarfooddelivery;

import android.support.annotation.NonNull;

import com.annimon.stream.Stream;
import com.tyyar.tyyarfooddelivery.model.Category;
import com.tyyar.tyyarfooddelivery.model.Choice;
import com.tyyar.tyyarfooddelivery.model.Item;
import com.tyyar.tyyarfooddelivery.model.Location;
import com.tyyar.tyyarfooddelivery.model.Merchant;
import com.tyyar.tyyarfooddelivery.model.MerchantView;
import com.tyyar.tyyarfooddelivery.model.Option;
import com.tyyar.tyyarfooddelivery.model.Review;

import java.util.ArrayList;
import java.util.List;

/**
 * User: YourPc
 * Date: 2/8/2017
 */

public class DataServer {
    public static List<Merchant> getMerchants() {
        Location location = Location.create("1", 13.000, 13.1512, "outside the lala");

        ArrayList<Category> categories = getCategories(getItems(getOptions()));

        ArrayList<Review> reviews = getReviews();

        return new ArrayList<Merchant>() {{
            add(Merchant.create("1", "Abou Shakra", "pass", "hu@gmail.com", "01110000011", 3, "file:///android_asset/abouShakra.png", "a very good resturant", 2, "12", "12.5", location, categories, reviews));
            add(Merchant.create("2", "Farroga ", "pass", "hu@gmail.com", "01110000011", 3, "file:///android_asset/ArabiataAlShabrawy.png", "a very good resturant", 2, "12", "12.5", location, categories, reviews));
            add(Merchant.create("3", "Papa John", "pass", "hu@gmail.com", "01110000011", 3, "file:///android_asset/BobSushi.jpg", "a very good resturant", 2, "12", "12.5", location, categories, reviews));
            add(Merchant.create("4", "Arabiata Al", "pass", "hu@gmail.com", "01110000011", 3, "file:///android_asset/Burger king .png", "a very good resturant", 2, "12", "12.5", location, categories, reviews));
            add(Merchant.create("5", "Bob Sushi", "pass", "hu@gmail.com", "01110000011", 3, "file:///android_asset/e3zi_sqp.png", "a very good resturant", 2, "12", "12.5", location, categories, reviews));
            add(Merchant.create("6", "Iskandarany ", "pass", "hu@gmail.com", "01110000011", 3, "file:///android_asset/e2wf_sqp.png", "a very good resturant", 2, "12", "12.5", location, categories, reviews));
            add(Merchant.create("7", "Burger king", "pass", "hu@gmail.com", "01110000011", 3, "file:///android_asset/e2gl_sqp.png", "a very good resturant", 2, "12", "12.5", location, categories, reviews));
            add(Merchant.create("8", "Burger king", "pass", "hu@gmail.com", "01110000011", 3, "file:///android_asset/e8uw_sqp.png", "a very good resturant", 2, "12", "12.5", location, categories, reviews));
            add(Merchant.create("9", "Burger king", "pass", "hu@gmail.com", "01110000011", 3, "file:///android_asset/e9yo_sqp.png", "a very good resturant", 2, "12", "12.5", location, categories, reviews));
        }};
    }

    public static List<MerchantView> getMerchantsView() {
        List<Merchant> merchants = getMerchants();

        return Stream.of(merchants)
                .map(m -> MerchantView.create(m._ID(), m.name(), 3, m.logoImageUrl(), "", 3, "2.22", "25"))
                .toList();
    }


    @NonNull
    private static ArrayList<Review> getReviews() {
        return new ArrayList<Review>() {{
            add(Review.create("1", "1", "1", "lalal", 4));
        }};
    }

    @NonNull
    private static ArrayList<Option> getOptions() {
        return new ArrayList<Option>() {{
            add(Option.create("1", "Size _REQ_", true, true, getChoices(1)));
            add(Option.create("3", "Additions (optional)", false, false, getChoices2(3)));
        }};
    }

    @NonNull
    private static ArrayList<Item> getItems(final ArrayList<Option> options) {
        return new ArrayList<Item>() {{
            add(Item.create("1", "Neapolitan Pizza", "a very good pizza", 5.00, "file:///android_asset/abouShakra.png", true, options));
            add(Item.create("1", "Chicago Pizza", "a very good pizza", 2.13, "file:///android_asset/abouShakra.png", true, options));
            add(Item.create("1", "Sicilian Pizza", "a very good pizza", 3.14, "file:///android_asset/abouShakra.png", true, options));
            add(Item.create("1", "New York Style Pizza", "a very good pizza", 4.15, "file:///android_asset/abouShakra.png", true, options));
            add(Item.create("1", "Greek Pizza", "a very good pizza", 5.16, "file:///android_asset/abouShakra.png", true, options));
            add(Item.create("1", "Greek Pizza", "a very good pizza", 6.17, "file:///android_asset/abouShakra.png", true, options));
            add(Item.create("1", "Greek Pizza", "a very good pizza", 7.18, "file:///android_asset/abouShakra.png", true, options));
        }};
    }

    @NonNull
    private static ArrayList<Category> getCategories(final ArrayList<Item> items) {
        return new ArrayList<Category>() {{
            add(Category.create("1", "Soups", items));
            add(Category.create("2", "Salads", items));
            add(Category.create("3", "Main Dishes", items));
            add(Category.create("4", "Grilled Chicken And Shish Tawook", items));
            add(Category.create("5", "Veal Corner", items));
            add(Category.create("6", "Mutton Corner", items));
            add(Category.create("7", "Kofta, Sausage And Liver", items));
        }};
    }

    @NonNull
    private static ArrayList<Choice> getChoices(int id) {
        return new ArrayList<Choice>() {{
            add(Choice.create(id + "_1", "Big", " considerable size", 5, true, true));
            add(Choice.create(id + "_2", "Medium", "about halfway between two extremes of size ", 10, true, true));
            add(Choice.create(id + "_3", "Small", "size that is less than normal", 15, true, true));
        }};
    }

    @NonNull
    private static ArrayList<Choice> getChoices2(int id) {
        return new ArrayList<Choice>() {{
            add(Choice.create(id + "_1", "No additions", "Without any addition", 0, true, false));
            add(Choice.create(id + "_2", "Additions", "Salads and pickles", 20, true, false));
            add(Choice.create(id + "_3", "Plus additions", " Toast ,Salads and pickles ", 50, true, false));
        }};
    }

    private static ArrayList<Item> getItems(ArrayList<Option> options, String url) {
        return new ArrayList<Item>() {{
            add(Item.create("1", "Neapolitan Pizza", "a very good pizza", 5.00, url, true, options));
            add(Item.create("1", "Chicago Pizza", "a very good pizza", 2.13, url, true, options));
            add(Item.create("1", "Sicilian Pizza", "a very good pizza", 3.14, url, true, options));
            add(Item.create("1", "New York Style Pizza", "a very good pizza", 4.15, url, true, options));
            add(Item.create("1", "Greek Pizza", "a very good pizza", 5.16, url, true, options));
            add(Item.create("1", "Greek Pizza", "a very good pizza", 6.17, url, true, options));
            add(Item.create("1", "Greek Pizza", "a very good pizza", 7.18, url, true, options));
        }};

    }


}

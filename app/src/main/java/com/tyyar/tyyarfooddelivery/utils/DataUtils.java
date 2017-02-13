package com.tyyar.tyyarfooddelivery.utils;

import com.tyyar.tyyarfooddelivery.model.Choice;
import com.tyyar.tyyarfooddelivery.model.Option;

import java.util.ArrayList;
import java.util.List;

import static com.tyyar.tyyarfooddelivery.adapters.OptionsAdapter.OrderSection;

/**
 * User: YourPc
 * Date: 2/5/2017
 */

public class DataUtils {
    private static final String TAG = DataUtils.class.getSimpleName();


    public static List<OrderSection> getOptionsSectionedList(List<Option> options) {
        return new ArrayList<OrderSection>() {{
            for (Option option : options) {
                add(new OrderSection(true, option.name(), options.size()));
                for (Choice choice : option.choices()) {
                    if (choice.available()) add(new OrderSection(choice));
                }
            }
        }};
    }


}
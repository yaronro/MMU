
package com.hit.controller;

import java.util.Observable;


import com.hit.model.Model;
import com.hit.view.View;

public class CacheUnitController extends Object implements Controller {

    private View view;
    private Model model;

    public CacheUnitController(View view, Model model) {
        this.model = model;
        this.view = view;
    }


    @Override
    public void update(Observable obs, Object arg) {
        if (obs instanceof Model) {
            view.updateUIData(arg);
        } else if (obs instanceof View) {
            model.updateModelData(arg);
        }
    }
}

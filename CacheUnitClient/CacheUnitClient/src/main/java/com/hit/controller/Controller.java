package com.hit.controller;

import java.util.Observable;
import java.util.Observer;

public interface Controller extends Observer {
    @Override
    default void update(Observable o, Object arg) {

    }
}

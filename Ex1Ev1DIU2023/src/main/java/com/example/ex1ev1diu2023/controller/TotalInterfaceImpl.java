package com.example.ex1ev1diu2023.controller;

public class TotalInterfaceImpl implements TotalInterface{
    @Override
    public float total(Integer unidades, Float precio) {
        return unidades*precio;
    }
}

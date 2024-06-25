package com.challenge.literalura_2.service;

public interface IConvierteDatos {
    <T> T convierteDatos(String json, Class<T> clase);
}

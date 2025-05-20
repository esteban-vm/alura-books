package com.aluracursos.books.services;

public interface IDataConversor {
    <T> T getData(String json, Class<T> tClass);
}

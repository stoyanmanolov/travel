package com.example.travel.mapper;

public interface Mapper<E, D> {
    public D toDto(E entity);
}

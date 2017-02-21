package com.edwise.pocs.itrestassured.repository;


import com.edwise.pocs.itrestassured.entity.Info;

import java.util.List;

public interface InfoRepository {

    Info save(Info info);

    Info findOne(Long id);

    List<Info> findAll();

    Info update(Info info);

    void delete(Long id);
}

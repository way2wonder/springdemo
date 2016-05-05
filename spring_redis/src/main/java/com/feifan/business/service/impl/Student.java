package com.feifan.business.service.impl;

import org.springframework.stereotype.Component;

import com.feifan.business.service.Person;


@Component
public class Student implements Person
{

    @Override
    public void eat()
    {
        // TODO Auto-generated method stub
         System.out.println("正在食堂吃饭");
    }

}

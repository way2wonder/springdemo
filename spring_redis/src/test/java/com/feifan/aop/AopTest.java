package com.feifan.aop;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.feifan.business.service.BookService;
import com.feifan.spring_redis.SpringTestCase;


public class AopTest  extends SpringTestCase
{
    
    @Autowired
    BookService bookService;

    @Test
    public void test()
    {
        bookService.sellBook();
    }
}

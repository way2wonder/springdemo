package com.feifan.proxy;


import org.junit.Test;

import com.feifan.business.service.BookService;
import com.feifan.business.service.impl.BookServiceImpl;


public class CglibProxyTest
{
    @Test
    public void test()
    {
        CglibProxy  cglib = new CglibProxy();
        BookService service = (BookService)cglib.getInstance(new BookServiceImpl());
        service.sellBook();
    }
}



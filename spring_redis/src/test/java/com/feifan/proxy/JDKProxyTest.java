package com.feifan.proxy;

import org.junit.Test;

import com.feifan.business.service.BookService;
import com.feifan.business.service.impl.BookServiceImpl;
public  class JDKProxyTest
{
    @Test
    public void test()
    {
        JDKProxy  proxyHandler = new JDKProxy();
        BookService proxy = (BookService)proxyHandler.bind(new BookServiceImpl());
        proxy.sellBook();
    }
}









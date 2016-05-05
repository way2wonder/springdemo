package com.feifan.aop;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.feifan.business.service.BookCarryService;
import com.feifan.business.service.BookService;
import com.feifan.business.service.Person;
import com.feifan.spring_redis.SpringTestCase;


public class AopTest  extends SpringTestCase
{
    
    @Autowired
    BookService bookService;

    @Autowired
    Person  person;
    
    
    @Test
    public void test()
    {
        bookService.sellBook();
        System.out.println("-----------引入（Introduction）------------");
        BookCarryService  carry = (BookCarryService)bookService;
        carry.carry();
        
        System.out.println("-----------another interface------------");
        person.eat();
        BookCarryService  pcarry = (BookCarryService)person;
        pcarry.carry();
    }
}

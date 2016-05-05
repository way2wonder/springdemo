package com.feifan.thread.producerConsumer.exampleoferror;

import java.util.LinkedList;

/**
 * 用Wait 和 notify 方式实现 生产消费者问题
 * @author zhy
 * 
 */
public class WaitNotifyProducerConsumer
{
    
    public static void main(String[] args)
    {
        Container<Baozi>  c = new Container<Baozi>();
        Producer  producer= new Producer();
        producer.setC(c);
        
        Consumer consumer = new Consumer();
        consumer.setC(c);
        
        
        Thread t1 = new Thread(producer);
        Thread t2 = new Thread(consumer);
        t1.start();
        t2.start();
    }
}



class Container<T>
{
    private   LinkedList<T>  list = new LinkedList<T>();
    
    public static final  int  MAX_SIZE = 20;

    public LinkedList<T> getList()
    {
        return list;
    }

    public void setList(LinkedList<T> list)
    {
        this.list = list;
    }
}


//生产者
class Producer  implements  Runnable
{
    private  Container<Baozi>  c;
    
    private  int  max_produce = 30;
    
    @Override
    public void run()
    {
        synchronized(c)
        {
            LinkedList<Baozi> list = c.getList();
            int i=0;
            while(i< max_produce)
            {
                while(list.size() < 20)
                {
                    list.add(new Baozi("第"+i+"个包子"));
                    System.out.println("生产了第"+i+"个包子");
                    i++;
                } 
                try
                {
                    c.wait();
                    Thread.sleep(200);
                    c.notifyAll();
                }
                catch (InterruptedException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
       
        System.out.println("producer start");
    }
    

    public Container<Baozi> getC()
    {
        return c;
    }

    public void setC(Container<Baozi> c)
    {
        this.c = c;
    }
}

//消费者
class  Consumer  implements  Runnable
{
    private  Container<Baozi>  c;
    
    @Override
    public void run()
    {
        synchronized(c)
        {
            LinkedList<Baozi> list = c.getList();
            while(list.size() > 0)
            {
                Baozi baozi = list.pop();
                System.out.println("消费了"+baozi);
            } 
            
            try
            {
                c.wait();
                Thread.sleep(200);
                c.notifyAll();  
            }
            catch (InterruptedException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public Container<Baozi> getC()
    {
        return c;
    }

    public void setC(Container<Baozi> c)
    {
        this.c = c;
    }
}
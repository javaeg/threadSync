package com.zy.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountDownLatchDemo
{
    public static void main(String[] args)
    {
        final CountDownLatch countDownLatch = new CountDownLatch(12);
        for(int i = 0 ; i < 11; i++)
        {
            new Thread(new Runnable()
            {
                public void run()
                {
                    try
                    {
                        System.out.println("[" + Thread.currentThread().getName()+"]开始执行任务");
                        TimeUnit.SECONDS.sleep(10000);
                        countDownLatch.countDown();
                        System.out.println("[" + Thread.currentThread().getName()+"]结束执行任务");
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }

                }
            },"线程" + i).start();
        }

        // 等待 一直阻塞
      //  countDownLatch.await();

       try
        {
            // 等12个线程执行完毕  最长等待时间为10s
            countDownLatch.await(10,TimeUnit.SECONDS);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }


        System.out.println("主线程开始接下来的处理！");

       // countDownLatch.countDown();

    }

}

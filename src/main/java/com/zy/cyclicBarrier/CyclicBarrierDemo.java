package com.zy.cyclicBarrier;

import java.sql.Time;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class CyclicBarrierDemo
{
    public static void main(String[] args)
    {
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
        for(int i = 0; i < 6; i++)
        {
            final int sleepSeconds = i;
            new Thread(new Runnable()
            {
                public void run()
                {
                    try
                    {
                        System.out.println("[" +Thread.currentThread().getName() + "]begin wait!");

                        // 等于4的时候 重置处理
                        if (4 == sleepSeconds)
                        {
                            System.out.println("[" +Thread.currentThread().getName() + "]reset!");
                            cyclicBarrier.reset();
                        }

                        else
                        {
                            TimeUnit.SECONDS.sleep(sleepSeconds);

                            // 傻傻等待
                            //cyclicBarrier.await();
                            // 有超时时间的等待
                            cyclicBarrier.await(10, TimeUnit.SECONDS);

                        }

                        System.out.println("[" +Thread.currentThread().getName() + "]end!");
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }

                }
            }, "线程" + i ).start();
        }

    }



}

package com.zy.semaphore;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 *银行排队---信号量的使用
 */
public class BankLineUp
{
    public static void main(String[] args)
    {
        // 信号量
        final Semaphore semaphore = new Semaphore(3);
        final Random random = new Random();
        for (int i = 0; i < 10; i++)
        {
            new Thread(new Runnable()
            {
                public void run()
                {
                    // 有空余窗口
                    if (semaphore.availablePermits() > 0)
                    {
                        System.out.println(
                            "[" + Thread.currentThread().getName() +
                                "] 进入银行办理业务，有空窗口，没人排队，可直接办理");
                    }
                    // 没有空余窗口，等待
                    else
                    {
                        System.out.println(
                            "[" + Thread.currentThread().getName() +
                                "] 进入银行办理业务，没有空余窗口，排队等待");
                    }

                    try
                    {
                        // 获取许可
                        semaphore.acquire();
                        System.out.println(
                            "[" + Thread.currentThread().getName() +
                                "] 开始办理业务");
                        // 模拟业务处理时间
                        TimeUnit.SECONDS.sleep(random.nextInt(10));
                        System.out.println(
                            "[" + Thread.currentThread().getName() +
                                "] 结束办理业务");
                        // 释放
                        semaphore.release();

                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }

                }
            },"顾客" + i).start();
        }
    }
}

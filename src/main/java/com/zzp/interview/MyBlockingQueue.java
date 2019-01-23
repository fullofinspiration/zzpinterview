package com.zzp.interview;


import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 这是新华三的网上面试题：用lock和condition实现一个堵塞队列，具体我也不太理解，只是手写了一遍。
 * 通过将两个条件谓词分开并放到两个等待线程集中，Condition更容易满足单次通知的需求，条件谓词包含
 * 的变量必须由Lock来保护，并且在检查条件谓词以及调用await和single时，必须持有Lock对象。
 * 如果需要高级功能，比如使用公平的队列操作或者在每个锁上对应多个等待线程集，应该优先使用Condition
 * 而不是内置条件队列.
 * @param <E>
 */
public class MyBlockingQueue<E> {

  private int size;
  Lock lock = new ReentrantLock();
  Condition notEmpty = lock.newCondition();
  Condition notFull = lock.newCondition();
  List<E> queue = new LinkedList<E>();


  public MyBlockingQueue(int size) {
    this.size = size;
  }

  public void put(E e) throws Exception {

    lock.lock();
    try {
      while (queue.size() >= size) {
        notFull.await();
      }
      queue.add(e);
      notEmpty.signal();
    } finally {
      lock.unlock();
    }

  }

  public E take() throws Exception {
    lock.lock();
    try {
      while (queue.size() == 0) {
        notEmpty.await();
      }
      notFull.signal();
      return queue.remove(0);

    } finally {
      lock.unlock();
    }
  }
}

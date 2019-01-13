package com.zzp.interview;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyBlockingQueue<E> {

  private final List<E> list;
  private final int limit;
  private final Lock lock = new ReentrantLock();
  private final Condition notEmpty = lock.newCondition();//非空
  private final Condition notFull = lock.newCondition();//没满

  public MyBlockingQueue(int limit) {
    this.limit = limit;
    this.list = new ArrayList<E>();
  }

  public void put(E e) {
    try {
      lock.lock();
      while (list.size() >= limit) {
        notFull.await();
      }
      list.add(e);
      notEmpty.notifyAll();
    } catch (Exception e2) {
      // TODO: handle exception
    } finally {
      lock.unlock();
    }
  }

  public E take() {
    try {
      lock.lock();
      while (list.size() == 0) {
        notEmpty.await();
      }
      E e = list.remove(0);
      notFull.notifyAll();
      return e;
    } catch (Exception e2) {
      return null;
    } finally {
      lock.unlock();
    }
  }
}

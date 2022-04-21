class DeadLockTask1 extends Thread {
  @Override
  public void run() {
    task1();
  }

  // 静态同步方法 锁1：DeadLockTask1.class
  synchronized static void task1() {
    System.out.println("task1 获得了锁 1");
    // 争夺对方的锁
    synchronized (DeadLockTask2.class) {
      System.out.println("task1 获得了锁 2");
    }
  }
}

class DeadLockTask2 extends Thread {
  @Override
  public void run() {
    task2();
  }

  // 静态同步方法 锁2：DeadLockTask2.class
  synchronized static void task2() {
    System.out.println("task2 获得了锁 1");
    // 争夺对方的锁
    synchronized (DeadLockTask1.class) {
      System.out.println("task2 获得了锁 2");
    }
  }
}

public class P15Deadlock {
  public static void main(String[] args) throws InterruptedException {
    DeadLockTask1 task1 = new DeadLockTask1();
    DeadLockTask2 task2 = new DeadLockTask2();
    task1.start();
    // 加入👇代码后能跑通，说明逻辑没问题
    // Thread.sleep(100);
    task2.start();
  }
}

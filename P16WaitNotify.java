class WaitTask extends Thread {
  @Override
  public void run() {
    synchronized (this) {
      System.out.println("开始等待");
      try {
        this.wait();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println("等待结束");
    }
  }
}

public class P16WaitNotify {
  public static void main(String[] args) throws InterruptedException {
    // task 是锁
    WaitTask task = new WaitTask();
    task.start();
    Thread.sleep(1000);
    // 等待时会放弃锁，所以主线程可以拿到 task 锁
    // notify() 需要当前线程拥有锁才能调用
    synchronized (task) {
      task.notify();
    }
  }
}

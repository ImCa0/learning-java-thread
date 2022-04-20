class JoinTask implements Runnable {

  // 等待的线程(前序任务)
  private Thread thread;
  // 打印的数(当前任务)
  private int num;

  JoinTask(Thread thread, int num) {
    this.thread = thread;
    this.num = num;
  }

  @Override
  public void run() {
    try {
      // 等待线程死亡
      thread.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println(num);
  }
}

public class P10Join {
  public static void main(String[] args) {
    Thread task1 = Thread.currentThread();
    Thread task2 = new Thread(new JoinTask(task1, 2));
    Thread task3 = new Thread(new JoinTask(task2, 3));
    System.out.println(1);
    task2.start();
    task3.start();
  }
}

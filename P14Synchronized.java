class TicketTask implements Runnable {
  private int tickets = 10;
  private Object lock = new Object();

  @Override
  public void run() {
    while (tickets > 0) {
      // 1.使用同步方法
      // sellTicket();

      // 2.使用同步代码块
      synchronized (lock) {
        // 同步代码块内外双重判断，防止外部条件成立内部条件不成立的情况
        if (tickets > 0) {
          try {
            Thread.sleep(200);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          System.out.println("第 " + tickets + " 张票售出");
          tickets--;
        }
      }
    }
  }

  synchronized void sellTicket() {
    if (tickets > 0) {
      try {
        Thread.sleep(200);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println("第 " + tickets + " 张票售出");
      tickets--;
    }
  }
}

public class P14Synchronized {
  public static void main(String[] args) {
    TicketTask task = new TicketTask();
    Thread window1 = new Thread(task);
    Thread window2 = new Thread(task);
    Thread window3 = new Thread(task);
    window1.start();
    window2.start();
    window3.start();
  }
}

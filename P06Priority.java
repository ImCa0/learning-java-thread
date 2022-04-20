public class P06Priority {
  public static void main(String[] args) {
    Thread thread = new Thread(new Runnable() {
      @Override
      public void run() {
        for (int i = 0; i < 10; i++) {
          System.out.println("thread: " + i);
        }
      }
    });

    // 获取优先级
    System.out.println(thread.getPriority());

    // 设置优先级 1-10，越高获取 CPU 的机会越多（不是必然）
    thread.setPriority(Thread.MAX_PRIORITY);
    thread.start();
    for (int i = 0; i < 10; i++) {
      System.out.println("main: " + i);
    }
  }
}

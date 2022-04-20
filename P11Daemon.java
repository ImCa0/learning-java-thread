public class P11Daemon {
  public static void main(String[] args) throws InterruptedException {
    Thread thread = new Thread() {
      @Override
      public void run() {
        while (true) {
          try {
            Thread.sleep(200);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          System.out.println("Daemon 线程正在运行");
        }
      }
    };
    System.out.println(thread.isDaemon());
    thread.setDaemon(true);
    System.out.println(thread.isDaemon());
    thread.start();
    // 主线程睡眠 1 s
    Thread.sleep(1000);
  }
}

public class P08Interrupt {
  public static void main(String[] args) throws InterruptedException {
    Thread thread = new Thread() {
      @Override
      public void run() {
        Thread currentThread = Thread.currentThread();
        // Thread.interrupted(): 静态方法，中断标记会被清除 (flase->true->flase)
        // currentThread.isInterrupted(): 非静态方法，中断标记不会被清除 (flase->true)
        while (!Thread.interrupted() && !currentThread.isInterrupted()) {
          System.out.println("正在运行");
        }
      }
    };
    thread.start();
    Thread.sleep(1000);
    thread.interrupt();
  }
}

public class P03CurrentThread {
  public static void main(String[] args) {

    new Thread() {
      @Override
      public void run() {
        System.out.println(Thread.currentThread());
      }
    }.start();

    // 获取当前线程
    Thread currentThread = Thread.currentThread();
    // 输出格式 Thread[main,5,main] -> Thread[线程名称，线程优先级，线程组名称]
    System.out.println(currentThread.toString());
  }
}

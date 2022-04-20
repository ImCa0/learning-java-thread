public class P13ThreadGroup {
  public static void main(String[] args) {
    ThreadGroup threadGroup = new ThreadGroup("MyThreadGroup");
    // 设置线程组最大优先级
    threadGroup.setMaxPriority(8);
    System.out.println(threadGroup);
    // 获取线程组存活的线程数
    System.out.println(threadGroup.activeCount());
    // 中断线程组中所有线程
    threadGroup.interrupt();
    Thread thread = new Thread(threadGroup, "MyThread");
    System.out.println(thread);
  }
}

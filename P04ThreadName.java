public class P04ThreadName {
  public static void main(String[] args) {
    // 获取线程名称
    Thread thread = Thread.currentThread();
    String threadName = thread.getName();
    System.out.println(threadName);

    // 设置线程名称
    thread.setName("newName");
    String newThreadName = thread.getName();
    System.out.println(newThreadName);
  }
}

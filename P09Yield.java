// 赋值任务
class ValueTask implements Runnable {
  static int value = 0;

  @Override
  public void run() {
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    value = 100;
  }
}

// 打印任务
class PrintTask implements Runnable {
  @Override
  public void run() {
    while (ValueTask.value == 0) {
      // 放弃 CPU 资源
      Thread.yield();
    }
    System.out.println(ValueTask.value);
  }
}

public class P09Yield {
  public static void main(String[] args) {
    Thread valueTask = new Thread(new ValueTask());
    Thread printTask = new Thread(new PrintTask());
    valueTask.start();
    printTask.start();
  }
}

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class MyThread extends Thread {
  @Override
  public void run() {
    System.out.println("执行继承 Thread 的线程");
  }
}

class MyRunnable implements Runnable {
  @Override
  public void run() {
    System.out.println("执行实现 Runnalbe 的线程");
  }
}

class MyCallable implements Callable<String> {
  @Override
  public String call() throws Exception {
    return "执行实现 Callable 的线程";
  }
}

public class P02CreateThread {
  public static void main(String[] args) {

    // 继承 Thread
    MyThread myThread = new MyThread();
    myThread.start();

    // 实现 Runnable
    MyRunnable myRunnable = new MyRunnable();
    Thread threadRunnable = new Thread(myRunnable);
    threadRunnable.start();

    // 实现 Callable
    MyCallable myCallable = new MyCallable();
    FutureTask<String> futureTask = new FutureTask<>(myCallable);
    Thread threadCallable = new Thread(futureTask);
    threadCallable.start();
    try {
      String str = futureTask.get();
      System.out.println(str);
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
  }
}

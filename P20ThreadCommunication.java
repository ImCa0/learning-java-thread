// 数据类
class Data {
  private String message;

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}

// 生产者类
class Producer implements Runnable {
  private final Data data;

  Producer(Data data) {
    this.data = data;
  }

  @Override
  public void run() {
    int i = 0;
    // 生产10个数据
    while (i < 10) {
      synchronized (data) {
        if (data.getMessage() == null) {
          // 生产数据
          data.setMessage("数据 " + i);
          i++;
          System.out.println("生产：" + data.getMessage());
        }
        // 唤醒消费者线程
        data.notify();
        try {
          // 使当前线程等待
          data.wait();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }
}

// 消费者类
class Consumer implements Runnable {
  private final Data data;

  Consumer(Data data) {
    this.data = data;
  }

  @Override
  public void run() {
    // 无限消费
    while (true) {
      synchronized (data) {
        if (data.getMessage() != null) {
          System.out.println("消费：" + data.getMessage());
          // 消费数据
          data.setMessage(null);
        }
        // 唤醒生产者线程
        data.notify();
        try {
          // 使当前线程等待
          data.wait();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }
}

public class P20ThreadCommunication {
  public static void main(String[] args) {
    Data data = new Data();
    Producer producer = new Producer(data);
    Consumer consumer = new Consumer(data);
    Thread producerThread = new Thread(producer);
    Thread consumerThread = new Thread(consumer);
    producerThread.start();
    consumerThread.start();
  }
}

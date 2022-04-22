import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class CData {
  private Lock lock = new ReentrantLock();
  // 定义两个 Condition，分别管理生产者和消费者，逻辑清晰了很多
  // 生产者在 producerCondition 上等待，消费者在 consumerCondition 上等待，唤醒也是一样的
  private Condition producerCondition = lock.newCondition();
  private Condition consumerCondition = lock.newCondition();
  private String message;

  public String getMessage() {
    lock.lock();
    try {
      // 如果生产者没有生产数据，消费者等待
      while (message == null) {
        consumerCondition.await();
      }
      producerCondition.signal();
      return message;
    } catch (InterruptedException e) {
      return "error";
    } finally {
      this.message = null;
      lock.unlock();
    }
  }

  public void setMessage(String message) {
    lock.lock();
    try {
      // 如果消费者没有消费，则生产者等待
      while (this.message != null) {
        producerCondition.await();
      }
      // 生产者生产数据
      this.message = message;
      System.out.println("生产：" + message);
      // 唤醒消费者
      consumerCondition.signal();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      lock.unlock();
    }
  }
}

class CProducer implements Runnable {
  private CData data;

  CProducer(CData data) {
    this.data = data;
  }

  @Override
  public void run() {
    int i = 0;
    while (true) {
      data.setMessage("数据 " + i);
      i++;
    }
  }
}

class CConsumer implements Runnable {
  private CData data;

  CConsumer(CData data) {
    this.data = data;
  }

  @Override
  public void run() {
    while (true) {
      System.out.println("消费：" + data.getMessage());
    }
  }
}

public class P25ConditionCommunication {
  public static void main(String[] args) throws InterruptedException {
    CData data = new CData();
    CProducer producer = new CProducer(data);
    CConsumer consumer = new CConsumer(data);
    Thread producerThread = new Thread(producer);
    Thread consumerThread = new Thread(consumer);
    producerThread.start();
    consumerThread.start();
  }
}

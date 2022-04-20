public class P07Sleep {
  public static void main(String[] args) {
    Thread thread = new Thread(new Runnable() {
      @Override
      public void run() {
        System.out.println("睡眠前");
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println("睡眠后");
      }
    });
    thread.start();
  }
}

public class P12IsAlive {
  public static void main(String[] args) throws InterruptedException {
    Thread thread = new Thread() {
      @Override
      public void run() {
        try {
          Thread.sleep(500);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    };
    System.out.println("启动前: " + thread.isAlive());
    thread.start();
    System.out.println("启动后: " + thread.isAlive());
    Thread.sleep(1000);
    System.out.println("启动1s后: " + thread.isAlive());
  }
}

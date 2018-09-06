package lock.database;

import com.github.lock.database.SimpleLock;
import org.junit.Test;

import java.util.concurrent.locks.Lock;

/**
 * @author : zhousong
 * Create in 2018/9/6
 */
public class TestSimpleLock {


    @Test
    public void test(){
        Thread t1 = new Thread(() -> {
            Lock lock = new SimpleLock("test");
            if (lock.tryLock()) {
                System.out.println("线程1获得锁");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                }
                lock.unlock();
                System.out.println("线程1释放锁");
            }

        });

        Thread t2 = new Thread(() -> {
            Lock lock = new SimpleLock("test");
            while (!lock.tryLock()) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }
                System.out.println("线程2无法获得锁");
            }
            System.out.println("线程2获得锁");
            lock.unlock();
            System.out.println("线程2释放锁");
        });

        t1.start();
        t2.start();

        try {
            Thread.sleep(10000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}

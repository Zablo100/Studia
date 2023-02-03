import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;


public class Main {

    public static void delay(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
    public static void zad1(List<Item> items){
        BlockingQueue<Item> queue = new ArrayBlockingQueue<Item>(100);
        BlockingQueue<Item> consumeQueue = new ArrayBlockingQueue<Item>(100);

        queue.addAll(items);

        Thread p1 = new Thread(() -> {
            try {
                for (var e: queue){
                    Item item = queue.take();
                    item.produceMe();
                    consumeQueue.put(item);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread p2 = new Thread(() -> {
            try {
                for (var e: queue){
                    Item item = queue.take();
                    item.produceMe();
                    consumeQueue.put(item);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread p3 = new Thread(() -> {
            try {
                for (var e: queue){
                    Item item = queue.take();
                    item.produceMe();
                    consumeQueue.put(item);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread p4 = new Thread(() -> {
            try {
                for (var e: queue){
                    Item item = queue.take();
                    item.produceMe();
                    consumeQueue.add(item);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        });

        Thread c1 = new Thread(() -> {
            try {
                for (var i: consumeQueue){
                    Item item = consumeQueue.take();
                    item.consumeMe();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread c2 = new Thread(() -> {
            try {
                for (var i: consumeQueue){
                    Item item = consumeQueue.take();
                    item.consumeMe();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread c3 = new Thread(() -> {
            try {
                for (var i: consumeQueue){
                    Item item = consumeQueue.take();
                    item.consumeMe();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });


        List<Thread> threads = Arrays.asList(p1, p2, p3, p4);

        for (var t :threads){
            t.start();
        }
        delay(2);

        List<Thread> threadsC = Arrays.asList( c1, c2, c3);

        for (var t :threadsC){
            t.start();
        }

    }


    public static void zad2Fixed(List<Item> items){
        ThreadPoolExecutor executorFixed = (ThreadPoolExecutor) Executors.newFixedThreadPool(4);
        BlockingQueue<Item> queue = new ArrayBlockingQueue<Item>(100);
        queue.addAll(items);

        for (int i=0;i<executorFixed.getMaximumPoolSize(); i++){
            executorFixed.submit(() -> {
                for (var x : queue){
                    try {
                        Item item = queue.take();
                        item.produceMe();
                        item.consumeMe();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }


    }

    public static void zad2Cached(List<Item> items){
        ThreadPoolExecutor executorFixed = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        BlockingQueue<Item> queue = new ArrayBlockingQueue<Item>(100);
        queue.addAll(items);

        for (int i=0;i<executorFixed.getMaximumPoolSize(); i++){
            executorFixed.submit(() -> {
                for (var x : queue){
                    try {
                        Item item = queue.take();
                        item.produceMe();
                        item.consumeMe();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
    }

    public static void zad2Stream(List<Item> items){
        items.parallelStream().forEach(item -> {
            item.produceMe();
            item.consumeMe();
        });
    }

    public static void main(String[] args) {
        List<Item> items = new ArrayList<>();

        for(int i=0;i<100;i++){
            items.add(new Item());
        }

        //zad2Fixed(items);
        zad2Cached(items);


    }
}

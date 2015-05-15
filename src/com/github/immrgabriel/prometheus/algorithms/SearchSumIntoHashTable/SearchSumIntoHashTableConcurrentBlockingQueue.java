package com.github.immrgabriel.prometheus.algorithms.SearchSumIntoHashTable;

import com.github.immrgabriel.prometheus.algorithms.StructureData.Collections.HashMap;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class SearchSumIntoHashTableConcurrentBlockingQueue {

    @Test
    public void testQuickSearchSumConcurrent() throws Exception{
        long startTime = System.currentTimeMillis();
        String pathFile = "./src/com/github/immrgabriel/prometheus/algorithms/SearchSumIntoHashTable/test_06.txt";
//        String pathFile = "./src/com/github/immrgabriel/prometheus/algorithms/SearchSumIntoHashTable/input_06.txt";
        File pFile = new File(pathFile);
        Set<String> source = new HashSet<String>(16, 3);
        try (BufferedReader reader = new BufferedReader(new FileReader(pFile))) {
            String current;
            while ((current = reader.readLine()) != null) {
                source.add(current);
            }
        }
        System.out.println("FillTime = " + (System.currentTimeMillis() - startTime)/1000.);
        startTime = System.currentTimeMillis();
        Set<Long> count = Collections.synchronizedSet(new HashSet<>());
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(100);
//        BlockingQueue<String> queue = new LinkedBlockingQueue<>();
//        BlockingQueue<String> queue = new SynchronousQueue<>();
        Thread t = new Thread(new IteratorTask(source, queue));
        t.start();
        Thread[] ts = new Thread[5];
        for (int i = 0; i < ts.length; i++) {
            ts[i] = new Thread(new SearchTask(queue, source, count, -1000, 1000));
        }
        for(Thread thread : ts) {
            thread.start();
        }
        for(Thread thread : ts) {
            thread.join();
        }
        t.join();
        System.out.println("count = " + count.size());
        System.out.println("SearchTime = " + (System.currentTimeMillis() - startTime)/1000.);
    }

}


class IteratorTask implements Runnable{
    public static String THE_END = "";
    private Set<String> source;
    private BlockingQueue<String> queue;

    IteratorTask(Set<String> source, BlockingQueue<String> queue) {
        this.source = source;
        this.queue = queue;
    }

    @Override
    public void run() {
        Iterator<String> it = source.iterator();
        try {
            while (it.hasNext()) {
                queue.put(it.next());
            }
            queue.put(THE_END);
        } catch (InterruptedException e) {/*ignored*/}
        System.out.println("IteratorTask fin");
    }
}



class SearchTask implements Runnable{
    private BlockingQueue<String> queue;
    private Set<String> source;
    private Set<Long> count;
    private int sMin;
    private int sMax;

    SearchTask(BlockingQueue<String> queue, Set<String> source, Set<Long> count, int sMin, int sMax) {
        this.queue = queue;
        this.source = source;
        this.count = count;
        this.sMin = sMin;
        this.sMax = sMax;
    }

    @Override
    public void run() {
        try {
            boolean done = false;
            while (!done) {
                String str = queue.take();
                if(str == IteratorTask.THE_END) {
                    queue.put(IteratorTask.THE_END);
                    done = true;
                } else {
                    long current = Long.parseLong(str);
                    long length = sMax - current + 1;
                    for (long i = sMin - current; i < length; i++) {
                        if (source.contains(String.valueOf(i))) {
                            count.add(i + current);
                            break;
                        }
                    }
                }
            }
        } catch (InterruptedException e) {/*ignored*/}
        System.out.println("SearchTask fin");
    }
}
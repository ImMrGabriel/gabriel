package com.github.immrgabriel.prometheus.algorithms.SearchSumIntoHashTable;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;
import java.util.concurrent.*;

public class SearchSumIntoHashTableConcurrentCallableSynchronousQueue {

    @Test
    public void testQuickSearchSumConcurrent() throws Exception{
        long startTime = System.currentTimeMillis();
        String pathFile = "./src/com/github/immrgabriel/prometheus/algorithms/SearchSumIntoHashTable/test_06.txt";
//        String pathFile = "./src/com/github/immrgabriel/prometheus/algorithms/SearchSumIntoHashTable/input_06.txt";
        File pFile = new File(pathFile);
        Set<Long> source = new HashSet<Long>(16, 3);
        try (BufferedReader reader = new BufferedReader(new FileReader(pFile))) {
            String current;
            while ((current = reader.readLine()) != null) {
                source.add(Long.parseLong(current));
            }
        }
        System.out.println("FillTime = " + (System.currentTimeMillis() - startTime)/1000.);
        startTime = System.currentTimeMillis();
        FutureTask<Integer> future = new FutureTask<Integer>(new IteratorCountTask(source));
        new Thread(future).start();
        System.out.println("count = " + future.get());
        System.out.println("SearchTime = " + (System.currentTimeMillis() - startTime)/1000.);
    }

}


class IteratorCountTask implements Callable<Integer>{
    private Set<Long> source;

    IteratorCountTask(Set<Long> source) {
        this.source = source;
    }

    @Override
    public Integer call() throws Exception {
        BlockingQueue<Long> queue = new SynchronousQueue<>();
        System.out.println("Runtime.getRuntime().availableProcessors() = " + Runtime.getRuntime().availableProcessors());
        Thread[] ts = new Thread[Runtime.getRuntime().availableProcessors()];
        List<FutureTask<Set<Long>>> list = new ArrayList<>(ts.length);
        for (int i = 0; i < ts.length; i++) {
            FutureTask<Set<Long>> task = new FutureTask<Set<Long>>(new SearchDiap(queue, source, -1000, 1000));
            list.add(task);
            ts[i] = new Thread(task);
            ts[i].start();
        }
        Iterator<Long> it = source.iterator();
        try {
            while (it.hasNext()) {
                queue.put(it.next());
            }
        } catch (InterruptedException e) {/*ignored*/}
        for(Thread thread : ts)
            thread.interrupt();
        for (int i = 1; i < list.size(); i++) {
            list.get(0).get().addAll(list.get(i).get());
        }
        return list.get(0).get().size();
    }
}



class SearchDiap implements Callable<Set<Long>>{
    private BlockingQueue<Long> queue;
    private Set<Long> source;
    private int sMin;
    private int sMax;

    SearchDiap(BlockingQueue<Long> queue, Set<Long> source, int sMin, int sMax) {
        this.queue = queue;
        this.source = source;
        this.sMin = sMin;
        this.sMax = sMax;
    }

    @Override
    public Set<Long> call() throws Exception {
        Set<Long> count = new HashSet<>();
        try {
            while (!Thread.currentThread().isInterrupted()) {
                long current = queue.take();
                long length = sMax - current + 1;
                for (long i = sMin - current; i < length; i++) {
                    if (source.contains(i)) {
                        count.add(i + current);
                        break;
                    }
                }
            }
        } catch (InterruptedException e) {/*ignored*/}
        return count;
    }
}
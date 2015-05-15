package com.github.immrgabriel.prometheus.algorithms.SearchArrayMediana;

import org.junit.Test;
import static org.junit.Assert.*;
import com.github.immrgabriel.prometheus.algorithms.StructureData.Collections.Heap;
import java.io.*;

public class SearchArrayMediana {

    public static void search(String fileName, int iter) throws Exception{
        if(iter < 1)
            return;
        File file = new File(fileName);
        if(!file.exists())
            return;
        System.out.println("file.getName() = " + file.getName());
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            int count = Integer.parseInt(reader.readLine()) + 1;
            Heap<Integer> heapLow = new Heap<Integer>(Integer.class, count / 2);
            heapLow.buildMaxHeap();
            Heap<Integer> heapHigh = new Heap<Integer>(Integer.class, count / 2);
            heapHigh.buildMinHeap();
            count = 0;
            String str;
            while ((str = reader.readLine()) != null) {
                count++;
                Integer current = Integer.parseInt(str);
                if(heapLow.size() > 0 && current.compareTo(heapLow.getMax()) < 0)
                    heapLow.add(current);
                else
                    heapHigh.add(current);
                if(heapLow.size() > heapHigh.size() && heapLow.size() - heapHigh.size() > 1) {
                    heapHigh.add(heapLow.extractMax());
                } else if (heapHigh.size() > heapLow.size() && heapHigh.size() - heapLow.size() > 1) {
                    heapLow.add(heapHigh.extractMin());
                }
                if(count == iter) {
                    System.out.println("iter = " + iter);
                    System.out.print("Medians: ");
                    if (heapLow.size() == heapHigh.size()) {
                        System.out.println(heapLow.getMax() + " " + heapHigh.getMin());
                    } else if (heapLow.size() > heapHigh.size()) {
                        System.out.println(heapLow.getMax());

                    } else {
                        System.out.println(heapHigh.getMin());
                    }
                    System.out.println("H_low: " + heapLow);
                    System.out.println("H_high: " + heapHigh);
                    System.out.println();
                }
            }
        }
    }

    public static void main(String... args) throws IOException{
        if(args.length < 1)
            return;
        File file = new File(args[0]);
        if(!file.exists())
            return;
        System.out.println("file.getName() = " + file.getName());
//        System.out.println(file.getParent() + "my_ext_out" + file.getName().substring(2));
        try (BufferedReader reader = new BufferedReader(new FileReader(file));
             BufferedWriter writerExtOut = new BufferedWriter(new FileWriter(file.getParent() + "/my_ext_out" + file.getName().substring(2)));
             BufferedWriter writerOut = new BufferedWriter(new FileWriter(file.getParent() + "/my_out" + file.getName().substring(2)));) {
            int count = Integer.parseInt(reader.readLine()) + 1;
            Heap<Integer> heapLow = new Heap<Integer>(Integer.class, count / 2);
            heapLow.buildMaxHeap();
            Heap<Integer> heapHigh = new Heap<Integer>(Integer.class, count / 2);
            heapHigh.buildMinHeap();
//            count = 0;
            String str;
            while ((str = reader.readLine()) != null) {
                Integer current = Integer.parseInt(str);
//                Integer maxHeapLow = heapLow.getMax();
                if(heapLow.size() > 0 && current.compareTo(heapLow.getMax()) < 0)
                    heapLow.add(current);
                else
                    heapHigh.add(current);
                if(heapLow.size() > heapHigh.size() && heapLow.size() - heapHigh.size() > 1) {
                    heapHigh.add(heapLow.extractMax());
                } else if (heapHigh.size() > heapLow.size() && heapHigh.size() - heapLow.size() > 1) {
                    heapLow.add(heapHigh.extractMin());
                }
//                System.out.print("Medians: ");
                writerExtOut.write("Medians: ");
                if(heapLow.size() == heapHigh.size()) {
//                    System.out.println(heapLow.getMax() + " " + heapHigh.getMin());
                    writerExtOut.write(heapLow.getMax() + " " + heapHigh.getMin());
                    writerExtOut.newLine();
                    writerOut.write(heapLow.getMax() + " " + heapHigh.getMin());
                    writerOut.newLine();
                } else if(heapLow.size() > heapHigh.size()) {
//                    System.out.println(heapLow.getMax());
                    writerExtOut.write(heapLow.getMax().toString());
                    writerExtOut.newLine();
                    writerOut.write(heapLow.getMax().toString());
                    writerOut.newLine();
                } else {
//                    System.out.println(heapHigh.getMin());
                    writerExtOut.write(heapHigh.getMin().toString());
                    writerExtOut.newLine();
                    writerOut.write(heapHigh.getMin().toString());
                    writerOut.newLine();
                }
//                System.out.println("H_low: " + heapLow);
//                System.out.println("H_high: " + heapHigh);
//                System.out.println();
                writerExtOut.write("H_low: " + heapLow);
                writerExtOut.newLine();
                writerExtOut.write("H_high: " + heapHigh);
                writerExtOut.newLine();
                writerExtOut.newLine();
            }
        }
    }

    @Test
    public void testExtOutput() throws Exception{
        String pathFile = "./src/com/github/immrgabriel/prometheus/algorithms/SearchArrayMediana/data_examples_05/";
        File pFile = new File(pathFile);
        String[] input = pFile.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.matches(".*input.*");
            }
        });
        for(String nameFile : input) {
            main(pathFile + nameFile);
            try (BufferedReader reader = new BufferedReader(new FileReader(pathFile + "ext_out" + nameFile.substring(2)));
                  BufferedReader readerMy = new BufferedReader(new FileReader(pathFile + "my_ext_out" + nameFile.substring(2)))) {
                String current;
                while ((current = reader.readLine()) != null) {
                    assertEquals(current, readerMy.readLine());
                }
            }
            try (BufferedReader reader = new BufferedReader(new FileReader(pathFile + "out" + nameFile.substring(2)));
                 BufferedReader readerMy = new BufferedReader(new FileReader(pathFile + "my_out" + nameFile.substring(2)))) {
                String current;
                while ((current = reader.readLine()) != null) {
                    assertEquals(current, readerMy.readLine());
                }
            }
        }
    }

    @Test
    public void task5() throws Exception{
//        search("./src/com/github/immrgabriel/prometheus/algorithms/SearchArrayMediana/data_examples_05/task5/input_16_10000.txt", 9876);
        search("./src/com/github/immrgabriel/prometheus/algorithms/SearchArrayMediana/data_examples_05/task5/input_16_10000.txt", 2015);
    }

}

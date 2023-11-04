import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ToRun {
    public static void main(String[] args) {
        // Delete these writeInput calls to stop generate new inputs each run
        writeInput(1000, "input/sorted_1000.txt", "input/random_1000.txt", "input/reversed_1000.txt");
        writeInput(10000, "input/sorted_10000.txt", "input/random_10000.txt", "input/reversed_10000.txt");
        writeInput(100000, "input/sorted_100000.txt", "input/random_100000.txt", "input/reversed_100000.txt");

        // Input size 1000
        System.out.println("======================= Input Kecil (1000) =======================");
        runPeekSort(1000, "sorted", "input/sorted_1000.txt", "output/peeksort/sorted_1000.txt");
        runRadixSort(1000, "sorted", "input/sorted_1000.txt", "output/radixsort/sorted_1000.txt");
        System.out.println();
        runPeekSort(1000, "random", "input/random_1000.txt", "output/peeksort/random_1000.txt");
        runRadixSort(1000, "random", "input/random_1000.txt", "output/radixsort/random_1000.txt");
        System.out.println();
        runPeekSort(1000, "reversed", "input/reversed_1000.txt", "output/peeksort/reversed_1000.txt");
        runRadixSort(1000, "reversed", "input/reversed_1000.txt", "output/radixsort/reversed_1000.txt");
        System.out.println();
        
        // Input size 10000
        System.out.println("======================= Input Sedang (10000) =======================");
        runPeekSort(10000, "sorted", "input/sorted_10000.txt", "output/peeksort/sorted_10000.txt");
        runRadixSort(10000, "sorted", "input/sorted_10000.txt", "output/radixsort/sorted_10000.txt");
        System.out.println();
        runPeekSort(10000, "random", "input/random_10000.txt", "output/peeksort/random_10000.txt");
        runRadixSort(10000, "random", "input/random_10000.txt", "output/radixsort/random_10000.txt");
        System.out.println();
        runPeekSort(10000, "reversed", "input/reversed_10000.txt", "output/peeksort/reversed_10000.txt");
        runRadixSort(10000, "reversed", "input/reversed_10000.txt", "output/radixsort/reversed_10000.txt");
        System.out.println();

        // Input size 100000
        System.out.println("======================= Input Besar (100000) =======================");
        runPeekSort(100000, "sorted", "input/sorted_100000.txt", "output/peeksort/sorted_100000.txt");
        runRadixSort(100000, "sorted", "input/sorted_100000.txt", "output/radixsort/sorted_100000.txt");
        System.out.println();
        runPeekSort(100000, "random", "input/random_100000.txt", "output/peeksort/random_100000.txt");
        runRadixSort(100000, "random", "input/random_100000.txt", "output/radixsort/random_100000.txt");
        System.out.println();
        runPeekSort(100000, "reversed", "input/reversed_100000.txt", "output/peeksort/reversed_100000.txt");
        runRadixSort(100000, "reversed", "input/reversed_100000.txt", "output/radixsort/reversed_100000.txt");
        System.out.println();
    }

    public static void runPeekSort(int size, String state, String pathInput, String pathOutput) {
        int[] input = readInput(pathInput, size);
        PeekSort peekSort = new PeekSort();

        Runtime rt = Runtime.getRuntime();
        long startTime = System.currentTimeMillis();
        long startMem = rt.totalMemory() - rt.freeMemory();

        peekSort.peeksort(input, 0, input.length-1);

        long endMem = rt.totalMemory() - rt.freeMemory();
        long endTime = System.currentTimeMillis();
        
        long duration = endTime - startTime;
        long used_mem = endMem - startMem;
        System.out.printf("PeekSort with %d %s data runs with: %d ms execution time, and %d memory\n", input.length, state, duration, used_mem);
        writeOutput(input, pathOutput);
    }

    public static void runRadixSort(int size, String state, String pathInput, String pathOutput) {
        int[] input = readInput(pathInput, size);
        RadixSort radixSort = new RadixSort();

        Runtime rt = Runtime.getRuntime();
        long startTime = System.currentTimeMillis();
        long startMem = rt.totalMemory() - rt.freeMemory();

        radixSort.radixsort(input, input.length);
        
        long endMem = rt.totalMemory() - rt.freeMemory();
        long endTime = System.currentTimeMillis();
        
        long duration = endTime - startTime;
        long used_mem = endMem - startMem;
        System.out.printf("RadixSort with %d %s data runs with: %d ms execution time, and %d memory\n", input.length, state, duration, used_mem);
        writeOutput(input, pathOutput);
    }

    public static int[] readInput(String filepath, int size) {
        int[] inputArr = new int[size];
        int i = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    int number = Integer.parseInt(line.trim());
                    inputArr[i] = number;
                } catch (NumberFormatException e) {
                    System.out.println("Skipping invalid number: " + line);
                }
                i++;
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading from the file: " + e.getMessage());
        }
        return inputArr;
    }

    public static void writeInput(int size, String filenameS, String filenameRa, String filenameRe) {
        SortedWriter sortedWriter = new SortedWriter();
        ReversedWriter reversedWriter = new ReversedWriter();
        RandomWriter randomWriter = new RandomWriter();
        
        sortedWriter.writeSortedInput(size, filenameS);
        randomWriter.writeRandomInput(size, filenameRa);
        reversedWriter.writeReversedInput(size, filenameRe);
    }

    public static void writeOutput(int[] output, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (int number : output) {
                writer.write(Integer.toString(number));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }

    }
}

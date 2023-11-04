import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReversedWriter {
    public void writeReversedInput(int size, String filename) {
        // Generate a list of numbers between the given range
        List<Integer> numbers = new ArrayList<>();
        for (int i = size; i >= 1; i--) {
            numbers.add(i);
        }

        // Write the sorted list to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (int number : numbers) {
                writer.write(Integer.toString(number));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }
}

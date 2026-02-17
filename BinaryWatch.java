import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BinaryWatch {

    // Function to count number of 1s in binary representation
    public static int countBits(int num) {
        int count = 0;
        while (num > 0) {
            count += (num & 1);
            num >>= 1;
        }
        return count;
    }

    public static List<String> readBinaryWatch(int turnedOn) {
        List<String> result = new ArrayList<>();

        for (int hour = 0; hour < 12; hour++) {
            for (int minute = 0; minute < 60; minute++) {

                if (countBits(hour) + countBits(minute) == turnedOn) {

                    String time = hour + ":";

                    if (minute < 10) {
                        time += "0";
                    }

                    time += minute;
                    result.add(time);
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of LEDs turned on: ");
        int turnedOn = sc.nextInt();

        List<String> times = readBinaryWatch(turnedOn);

        System.out.println("Possible times:");
        for (String t : times) {
            System.out.println(t);
        }

        sc.close();
    }
}

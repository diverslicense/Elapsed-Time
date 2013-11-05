import java.util.Scanner;

/**
 * class P4
 */
public class P4 {
  public static void main(String[] args) { // all methods called from main
    char choice; // Repeat loop
    String str = null; // Input string
    Scanner scan = new Scanner(System.in); // Read from keyboard
    P4Time time1 = new P4Time(); // 1st time - departure time
    P4Time time2 = new P4Time(System.currentTimeMillis()); // 2nd time - now
    P4Time time3 = new P4Time(); // Elapsed time = time2 - time1

    do {
      time1.readTime(); // instance called with method readTime()
      time3.elapsedTime(time1, time2); //instance called with method elapsedTime

      System.out.print("Elapsed time is ");
      time3.prt(); // Print time1 and time2
      System.out.print(" with departure time of ");
      time1.prt(); // Print departure time1
      System.out.print(" and current time of ");
      time2.prt();
      System.out.print("\n\nWant to compute elapsed time (y/n)? ");
      str = scan.next(); // Read and assign to
      choice = str.charAt(0); // Assign 1st character
    } while (choice != 'n' && choice != 'N'); // Repeat until n or N input
    scan.close(); // Close this scanner
    System.exit(0); // Close all connections
  }

}

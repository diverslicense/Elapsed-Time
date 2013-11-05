/** 
 * Assignment 4 - file header         PA#4            Due August 21, 2013
 * login #: cs11vau
 */
import java.util.Scanner;

/**
 * class P4Time - class header ...
 */
public class P4Time {
  final int MILLI_SEC_PER_SEC = 1000; // 1000 millisec in a second
  final int SEC_PER_MIN = 60; // 60 seconds in a minute
  final int MIN_PER_HOUR = 60; // 60 minutes in an hour
  final int HOURS_IN_DAY = 24; // 24 hours in a day
  public final int MIN_TIME = 0; // Minimum time unit value
  public final int MIN_PER_HR = 60; // 60 min/hr
  public final int GMT_TO_PDT = -7; // Convert GMT to PDT
  private int hr, min, sec; // Hour, minutes, seconds
  private long totalMin = MIN_TIME; // Total time stored in minutes

  public P4Time() {
    hr = 0;
    min = 0;
    sec = 0;
  } // No Argument constructor, init to 0

  public P4Time(int h, int m, int s) // Overloaded ctor: h,m,s
  {
    setTime(hr, min, sec );
  }

  public P4Time(long milliSec) { 
    // Total milliseconds since 1/1/1970
    long totalMilliseconds = milliSec;
    // GMT to PST
    totalMilliseconds += MIN_PER_HOUR * SEC_PER_MIN * MILLI_SEC_PER_SEC * GMT_TO_PDT; 
    // Seconds since 1/1/1970
    long totalSeconds = totalMilliseconds / MILLI_SEC_PER_SEC;
    long currentSecond = totalSeconds % SEC_PER_MIN; // Current second in min   
    long totalMinutes = totalSeconds / SEC_PER_MIN; // Total minutes
    long currentMinute = totalMinutes % MIN_PER_HOUR; //Current minute in hour
    long totalHours = totalMinutes / MIN_PER_HOUR; // Total hours
    long currentHour = totalHours % HOURS_IN_DAY; // Current hour of day
    setTime( (int)(currentHour), (int)(currentMinute), (int)(currentSecond) );
    // System.out.print(currentHour+":"+currentMinute+":"+currentSecond);
  } // Overloaded ctor: milliSec

  public boolean setTime(int h, int m, int s) {
    hr = h;
    min = m;
    sec = s;
    return true;
  } // Same as Overloaded ctor

  public void readTime() {
    boolean validTime = true; // Input is valid
    int tmpHr, tmpMin, tmpSec; // Temporaries to read input
    String input;
    String []inputTime; // String hr:min:sec
    Scanner scan = new Scanner(System.in); // Read from keyboard
    do
    { // Reset input error flag
      System.out.print("Enter departure (hh:mm:ss) >> ");
      input = scan.next();
      inputTime = input.split(":"); // Array Strings delimit :
      tmpHr = Integer.parseInt( inputTime[0] );
      tmpMin = Integer.parseInt( inputTime[1] );
      tmpSec = Integer.parseInt( inputTime[2] );
  
      if( tmpHr >= 0 && tmpHr < 24 && tmpMin >= 0 && tmpMin < 60 
          && tmpSec >= 0 && tmpSec < 60 /* Range check */ ) {
        setTime( tmpHr, tmpMin, tmpSec );
        validTime = true;
        // Call setTime(), set validTime to true
      }
      else {
        validTime = false;
        System.out.println("ERR! Enter time within range (00:00:00-23:59:59)");
        // Error message, clear validTime to false
      }
    }while( !validTime ); // Repeat until valid input
  }

    public void prt(){ 
      System.out.printf("%02d:%02d:%02d", hr, min, sec);  
    }
    
    private long getTime() {
      return hr * MIN_PER_HOUR * SEC_PER_MIN * MILLI_SEC_PER_SEC + 
          min * SEC_PER_MIN * MILLI_SEC_PER_SEC + 
          sec * MILLI_SEC_PER_SEC;
    }
    
    public void elapsedTime(P4Time t1, P4Time t2){
      long time1 = t1.getTime();
      long time2 = t2.getTime();
      long time3;
      if(time1 < time2) {
        time3 = time2 - time1;
      } else {
        time3 = 24*MIN_PER_HOUR*SEC_PER_MIN*MILLI_SEC_PER_SEC-time1;
        time3 += time2;
      }
      P4Time temp = new P4Time(time3 - MIN_PER_HOUR * SEC_PER_MIN * MILLI_SEC_PER_SEC * GMT_TO_PDT);
      hr = temp.hr;
      min = temp.min;
      sec = temp.sec;
    } // Compute t2-t1
    
}

import java.util.*;

/**
 * The Event class represents an hand with an associated person, which hand side (left / right), and the status of that hand.
 * This class provides constructors to create the starting hand (folded) and to copy existing events.
 * 
 * Side was implemented for troubleshooting, along with the toString() method
 * Status refers to the state of the hand, where:
 *   3 -> folded
 *   2 -> fist
 *   1 -> palm down
 *   0 -> hand behind back (out of game)
 */
class Event {
  public int person;
  public String side;
  public int status;

  public Event(int n) {
    this.person = n;
    this.side = "folded";
    this.status = 3;
  }
  public Event(Event e) {
    this.person = e.person;
    this.side = e.side;
    this.status = e.status;
  }
  
  public String toString() {
    return String.format("Person %d %s", this.person, this.side);
  }
}

public class coconut {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int s = sc.nextInt();
    int n = sc.nextInt();
    Deque<Event> q = new LinkedList<Event>();
    // Initialise starting state of the game
    for (int i = 1; i <= n; i += 1) {
      q.offer(new Event(i));
    }
    /**
     * While more than one hand is left in the game
     * (Works since if only one player left with two hands, result is same either way)
     */
    while (q.size() > 1) {
      // Cycle through hands until final syllable, % q.size() to prevent unnecessary cycling
      for (int i = 0; i < (s - 1) % q.size(); i += 1) {
        q.addLast(q.poll());
      }
      Event curr = q.poll();
      curr.status -= 1;
      // If folded (initial state), split into 2 fists
      if (curr.status == 2) {
        Event left = new Event(curr);
        left.side = "left";
        Event right = new Event(curr);
        right.side = "right";
        // Round starts with first half of fist, then second (Add to start of cycle)
        q.addFirst(right);
        q.addFirst(left);
      }
      // Add to back of cycle
      else if (curr.status > 0) {
        q.addLast(curr);
      }
    }
    // Retrieve the last person left
    System.out.println(q.poll().person);
    sc.close();
  }
}

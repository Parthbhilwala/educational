import java.util.ArrayList;
import java.util.List;

// Observer interface
interface Bidder {
    void update(int bid);
}

// Concrete Observer
class ConcreteBidder implements Bidder {
    private String name;

    public ConcreteBidder(String name) {
        this.name = name;
    }

    @Override
    public void update(int bid) {
        System.out.println(name + " has been notified: New highest bid is $" + bid);
    }
}

// Subject
class Auction {
    private List<Bidder> bidders = new ArrayList<>();
    private int highestBid = 0;

    public void addObserver(Bidder bidder) {
        bidders.add(bidder);
    }

    public void newBid(int bid) {
        if (bid > highestBid) {
            highestBid = bid;
            notifyAllBidders();
        }
    }

    private void notifyAllBidders() {
        for (Bidder bidder : bidders) {
            bidder.update(highestBid);
        }
    }
}

// Demo
public class ObserverPatternDemo {
    public static void main(String[] args) {
        Auction auction = new Auction();
        Bidder alice = new ConcreteBidder("Alice");
        Bidder bob = new ConcreteBidder("Bob");

        auction.addObserver(alice);
        auction.addObserver(bob);

        auction.newBid(100);
        auction.newBid(150);
    }
}

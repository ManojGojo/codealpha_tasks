import java.io.*;
import java.util.*;

public class StockTradingApp {

  
    static class Stock implements Serializable {
        private String symbol;
        private double price;

        public Stock(String symbol, double price) {
            this.symbol = symbol;
            this.price = price;
        }

        public String getSymbol() {
            return symbol;
        }

        public double getPrice() {
            return price;
        }

        public void updatePrice(double newPrice) {
            this.price = newPrice;
        }

        @Override
        public String toString() {
            return symbol + " - ₹" + String.format("%.2f", price);
        }
    }

    
    static class Transaction implements Serializable {
        private String type;
        private String symbol;
        private int quantity;
        private double price;

        public Transaction(String type, String symbol, int quantity, double price) {
            this.type = type;
            this.symbol = symbol;
            this.quantity = quantity;
            this.price = price;
        }

        @Override
        public String toString() {
            return type + " | " + symbol + " | Qty: " + quantity + " | ₹" + price;
        }
    }


    static class Portfolio implements Serializable {
        private double balance;
        private Map<String, Integer> holdings;
        private List<Transaction> transactions;

        public Portfolio(double initialBalance) {
            this.balance = initialBalance;
            holdings = new HashMap<>();
            transactions = new ArrayList<>();
        }

        public void buyStock(Stock stock, int quantity) {
            if (stock == null) {
                System.out.println("Invalid stock symbol.");
                return;
            }

            double totalCost = stock.getPrice() * quantity;

            if (totalCost > balance) {
                System.out.println("Insufficient balance.");
                return;
            }

            balance -= totalCost;
            holdings.put(stock.getSymbol(),
                    holdings.getOrDefault(stock.getSymbol(), 0) + quantity);

            transactions.add(new Transaction("BUY", stock.getSymbol(), quantity, stock.getPrice()));
            System.out.println("Stock purchased successfully.");
        }

        public void sellStock(Stock stock, int quantity) {
            if (stock == null) {
                System.out.println("Invalid stock symbol.");
                return;
            }

            int owned = holdings.getOrDefault(stock.getSymbol(), 0);

            if (quantity > owned) {
                System.out.println("Not enough shares.");
                return;
            }

            balance += stock.getPrice() * quantity;
            holdings.put(stock.getSymbol(), owned - quantity);

            transactions.add(new Transaction("SELL", stock.getSymbol(), quantity, stock.getPrice()));
            System.out.println("Stock sold successfully.");
        }

        public void displayPortfolio(Map<String, Stock> marketStocks) {
            System.out.println("\n===== PORTFOLIO =====");
            System.out.println("Balance: ₹" + String.format("%.2f", balance));

            double totalValue = balance;

            for (String symbol : holdings.keySet()) {
                int qty = holdings.get(symbol);
                double currentPrice = marketStocks.get(symbol).getPrice();
                double value = qty * currentPrice;
                totalValue += value;

                System.out.println(symbol + " | Qty: " + qty +
                        " | Current Value: ₹" + String.format("%.2f", value));
            }

            System.out.println("Total Portfolio Value: ₹" + String.format("%.2f", totalValue));
        }

        public void displayTransactions() {
            System.out.println("\n===== TRANSACTION HISTORY =====");
            for (Transaction t : transactions) {
                System.out.println(t);
            }
        }

        public void save(String filename) throws IOException {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename));
            oos.writeObject(this);
            oos.close();
        }

        public static Portfolio load(String filename)
                throws IOException, ClassNotFoundException {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename));
            Portfolio p = (Portfolio) ois.readObject();
            ois.close();
            return p;
        }
    }

    
    static class Market {
        private Map<String, Stock> stocks;

        public Market() {
            stocks = new HashMap<>();
            stocks.put("TCS", new Stock("TCS", 3500));
            stocks.put("INFY", new Stock("INFY", 1500));
            stocks.put("RELIANCE", new Stock("RELIANCE", 2500));
        }

        public void displayMarket() {
            System.out.println("\n===== MARKET DATA =====");
            for (Stock s : stocks.values()) {
                System.out.println(s);
            }
        }

        public Stock getStock(String symbol) {
            return stocks.get(symbol.toUpperCase());
        }

        public Map<String, Stock> getAllStocks() {
            return stocks;
        }

        public void simulatePriceChange() {
            Random random = new Random();
            for (Stock s : stocks.values()) {
                double change = -100 + random.nextDouble() * 200;
                s.updatePrice(Math.max(100, s.getPrice() + change));
            }
        }
    }

    
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Market market = new Market();
        Portfolio portfolio = new Portfolio(100000);

        while (true) {
            market.simulatePriceChange();

            System.out.println("\n===== STOCK TRADING SYSTEM =====");
            System.out.println("1. View Market");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. View Transactions");
            System.out.println("6. Save Portfolio");
            System.out.println("7. Exit");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    market.displayMarket();
                    break;

                case 2:
                    System.out.print("Enter Stock Symbol: ");
                    String buySymbol = sc.next();
                    System.out.print("Enter Quantity: ");
                    int buyQty = sc.nextInt();
                    portfolio.buyStock(market.getStock(buySymbol), buyQty);
                    break;

                case 3:
                    System.out.print("Enter Stock Symbol: ");
                    String sellSymbol = sc.next();
                    System.out.print("Enter Quantity: ");
                    int sellQty = sc.nextInt();
                    portfolio.sellStock(market.getStock(sellSymbol), sellQty);
                    break;

                case 4:
                    portfolio.displayPortfolio(market.getAllStocks());
                    break;

                case 5:
                    portfolio.displayTransactions();
                    break;

                case 6:
                    try {
                        portfolio.save("portfolio.dat");
                        System.out.println("Portfolio saved successfully.");
                    } catch (Exception e) {
                        System.out.println("Error saving file.");
                    }
                    break;

                case 7:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
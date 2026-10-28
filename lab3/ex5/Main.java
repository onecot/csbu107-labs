import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.IOException;

class Customer {
  private String customerId;
  private String fullName;
  private String address;
  private String phone;

  public Customer(String customerId, String fullName, String address, String phone) {
    this.customerId = customerId;
    this.fullName = fullName;
    this.address = address;
    this.phone = phone;
  }

  public String getCustomerId() { return customerId; }

  public String getFullName() { return fullName; }

  public String getAddress() { return address; }

  public String getPhone() { return phone; }

  public String getInfo() {
    return customerId + " - " + fullName + " - " + address + " - " + phone;
  }
}

class Product {
  private String productId;
  private String type;
  private int volume;
  private int pricePerMl;

  public Product(String productId, String type, int volume, int pricePerMl) {
    this.productId = productId;
    this.type = type;
    this.volume = Math.max(0, volume);
    this.pricePerMl = pricePerMl;
  }

  public String getProductId() { return productId; }
  public String getType() { return type; }
  public int getVolume() { return volume; }
  public int getPricePerMl() { return pricePerMl; }

  public int getPrice() {
    return volume * pricePerMl;
  }
}

abstract class CosmeticPackage {
  private final String packageName;
  private List<Product> products;

  public CosmeticPackage(String packageName) {
    this.packageName = packageName;
    products = new ArrayList<>();
  }

  public int getPrice() {
    int price = 0;
    for(int i = 0; i < products.size(); ++i) {
      price += products.get(i).getPrice();
    }
    return price;
  }

  public boolean addProduct(Product p) { products.add(p); return true; } 

  public String getPackageName() { return this.packageName; }

  public String describeProducts() {
    String result = "";
    for (int i = 0; i < products.size(); ++i) {
      Product p = products.get(i);
      result += "  " + p.getType() + " - " + p.getVolume() + " ml - " + p.getPrice() + " VND\n";
    }
    return result;
  }
}

class RomanticPackage extends CosmeticPackage {
  public RomanticPackage(int roseVolume) {
    super("Romantic");
    addProduct(new Product("P1", "Rose Perfume", roseVolume, 20_000));
  }

  @Override
  public boolean addProduct(Product product) {
    String t = product.getType();
    boolean allowed = t.equals("Rose Perfume")
                   || t.contains("Shampoo")
                   || t.contains("Shower Gel");
    if(!allowed) return false;
    return super.addProduct(product);
  }
}

class FreshAirPackage extends CosmeticPackage {
  public FreshAirPackage(int standard, int volume) {
    super("Fresh-Air");
    if(standard == 1)
      addProduct(new Product("P4", "Oily Hair Shampoo Standard 1", volume, 30_000));
    else
      addProduct(new Product("P5", "Oily Hair Shampoo Standard 2", volume, 40_000));
  }

  @Override
  public boolean addProduct(Product product) {
    String t = product.getType();
    boolean allowed = t.contains("Perfume")
                   || t.contains("Oily Hair Shampoo")
                   || t.contains("Shower Gel");
    if(!allowed) return false;
    return super.addProduct(product);
  }
}

class Order {
  private String orderId;
  private Customer customer;
  private String invoiceDate;
  private CosmeticPackage cosmeticPackage;

  public Order(String orderId, Customer customer, String invoiceDate,
               CosmeticPackage cosmeticPackage) {
    this.orderId = orderId;
    this.customer = customer;
    this.invoiceDate = invoiceDate;
    this.cosmeticPackage = cosmeticPackage;
  }

  public int getPrice() { return cosmeticPackage.getPrice(); }

  public String describe() {
    String result = "";
    result += "Order: " + orderId + " | Date: " + invoiceDate + "\n";
    result += "Customer: " + customer.getInfo() + "\n";
    result += "Package: " + cosmeticPackage.getPackageName() + "\n";
    result += cosmeticPackage.describeProducts();
    result += "Total: " + getPrice() + " VND\n";
    return result;
  }
}

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    List<Order> orders = new ArrayList<>();

    System.out.print("Enter number of orders: ");
    int n = Integer.parseInt(sc.nextLine());

    for (int i = 0; i < n; ++i) {
      System.out.println("\nOrder #" + (i + 1));

      System.out.print("Order ID: ");
      String orderId = sc.nextLine();

      System.out.print("Customer ID: ");
      String customerId = sc.nextLine();

      System.out.print("Full name: ");
      String fullName = sc.nextLine();

      System.out.print("Address: ");
      String address = sc.nextLine();

      System.out.print("Phone: ");
      String phone = sc.nextLine();

      System.out.print("Invoice date: ");
      String invoiceDate = sc.nextLine();

      Customer customer = new Customer(customerId, fullName, address, phone);

      System.out.print("Package type (1-Romantic, 2-Fresh-Air): ");
      int packageType = Integer.parseInt(sc.nextLine());

      CosmeticPackage cosmeticPackage;

      if (packageType == 1) {
        System.out.print("Rose Perfume volume (ml): ");
        int roseVolume = Integer.parseInt(sc.nextLine());
        cosmeticPackage = new RomanticPackage(roseVolume);
      } else if (packageType == 2) {
        int standard;
        while (true) {
          System.out.print("Oily Hair Shampoo standard (1 or 2): ");
          standard = Integer.parseInt(sc.nextLine());
          if(standard < 1 || 2 < standard) {
            System.out.println("Standard must be 1 or 2.");
            continue;
          }
          break;
        }

        System.out.print("Oily Hair Shampoo volume (ml): ");
        int volume = Integer.parseInt(sc.nextLine());

        cosmeticPackage = new FreshAirPackage(standard, volume);
      } else {
        System.out.println("Invalid package type.");
        --i;
        continue;
      }

      // Optional products
      while (true) {
        System.out.println("\n--- Optional products ---");
        System.out.println("1. Rose Perfume (20000 VND/ml)");
        System.out.println("2. Chamomile Perfume (15000 VND/ml)");
        System.out.println("3. Dry Hair Shampoo (20000 VND/ml)");
        System.out.println("4. Oily Hair Shampoo Standard 1 (30000 VND/ml)");
        System.out.println("5. Oily Hair Shampoo Standard 2 (40000 VND/ml)");
        System.out.println("6. Dry Skin Shower Gel (20000 VND/ml)");
        System.out.println("7. Oily Skin Shower Gel (40000 VND/ml)");
        System.out.println("0. Stop");
        System.out.print("Choose: ");

        int productType = Integer.parseInt(sc.nextLine());

        if (productType == 0) break;

        System.out.print("Volume (ml): ");
        int volume = Integer.parseInt(sc.nextLine());
        
        Product product;
        switch (productType) {
          case 1:
            product = new Product("P1", "Rose Perfume", volume, 20_000);
            break;
          case 2:
            product = new Product("P2", "Chamomile Perfume", volume, 15_000);
            break;
          case 3:
            product = new Product("P3", "Dry Hair Shampoo", volume, 20_000);
            break;
          case 4:
            product = new Product("P4", "Oily Hair Shampoo Standard 1", volume, 30_000);
            break;
          case 5:
            product = new Product("P5", "Oily Hair Shampoo Standard 2", volume, 40_000);
            break;
          case 6:
            product = new Product("P6", "Dry Skin Shower Gel", volume, 20_000);
            break;
          case 7:
            product = new Product("P7", "Oily Skin Shower Gel", volume, 40_000);
            break;
          default:
            System.out.println("Invalid product type.");
            continue;
        }

        if(!cosmeticPackage.addProduct(product)) {
          System.out.println(product.getType() + " is not allowed in the " + cosmeticPackage.getPackageName() + " package.");
        }
      }

      orders.add(new Order(orderId, customer, invoiceDate, cosmeticPackage));
    }

    System.out.println("\n===== ORDER LIST =====");
    for (Order o : orders) {
      System.out.print(o.describe());
      System.out.println();
    }

    // Save to order_list.txt
    PrintWriter writer = null;
    try {
      writer = new PrintWriter("order_list.txt");
      for (Order o : orders) {
        writer.print(o.describe());
        writer.println();
      }

      System.out.println("Saved to order_list.txt");
    } catch (IOException e) {
      System.out.println("Cannot write order_list.txt: " + e.getMessage());
    } finally {
      if(writer != null) writer.close();
    }

    sc.close();
  }
}

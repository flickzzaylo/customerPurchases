import java.util.*;

public class Main {
    protected static String EXCEPTION = "YOU WROTE THE WRONG LINE";
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String input, name, product;
        String[] splitInput;
        int count;
        List<Customer> customers = new ArrayList<>();
        while(!(input = scan.nextLine()).isEmpty()){
            try{
                splitInput = input.split(" ");
                if(splitInput.length != 3){
                    throw new Exception(EXCEPTION);
                }
                name = splitInput[0];
                product = splitInput[1];
                count = Integer.parseInt(splitInput[2]);
                Customer customerToFind = new Customer(name);
                if(customers.contains(customerToFind)){
                    var customer = customers.get(customers.indexOf(customerToFind));
                    customer.addPurchase(product,count);
                }else{
                    Customer customer = new Customer(name);
                    customer.addPurchase(product,count);
                    customers.add(customer);
                }
            }
            catch (Exception e){
                System.out.println(EXCEPTION);
            }
        }
        Collections.sort(customers);
        for(var x : customers){
            System.out.println(x.name + ":");
            for (String productName : x.purchases.keySet()) {
                System.out.println(productName + " " + x.purchases.get(productName));
            }
        }
    }
}

class Customer implements Comparable<Customer>{
    String name;
    Map<String, Integer> purchases;

    public Customer(String name){
        this.purchases = new TreeMap<>();
        this.name = name;
    }

    public void addPurchase(String product, int count){
        if(purchases.containsKey(product)){
            purchases.put(product, purchases.get(product)+count);
        }else{
            purchases.put(product,count);
        }
    }
    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        Customer customer = (Customer) o;
        return name.equals(customer.name);
    }

    @Override
    public int compareTo(Customer other) {
        return this.name.compareTo(other.name);
    }
}
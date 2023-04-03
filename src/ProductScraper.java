import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ProductScraper {
    
    public static void main(String[] args) throws IOException {
        
        String url = "https://www.staples.com/Computer-office-desks/cat_CL210795/663ea?icid=BTS:2020:STUDYSPACE:DESKS";
        
        // Connect to the website and get the HTML
        Document doc = Jsoup.connect(url).get();
        
        // Get the product details from the HTML
        Elements productElements = doc.select("div.product");
        List<Product> products = new ArrayList<>();
        
        for (Element productElement : productElements) {
            Product product = new Product();
            
            product.setName(productElement.select("div.product-title").text());
            product.setPrice(productElement.select("div.product-price").text());
            product.setItemNumber(productElement.select("div.product-sku").text());
            product.setModelNumber(productElement.select("div.product-model").text());
            product.setCategory(productElement.select("div.product-category").text());
            product.setDescription(productElement.select("div.product-description").text());
            
            products.add(product);
        }
        
        // Export the product details to a CSV file
        FileWriter writer = new FileWriter("products.csv");
        
        // Write the header row
        writer.write("Product Name,Product Price,Item Number,Model Number,Product Category,Product Description\n");
        
        // Write the product details
        for (int i = 0; i < Math.min(products.size(), 10); i++) {
            Product product = products.get(i);
            writer.write(product.getName() + "," + product.getPrice() + "," + product.getItemNumber() + "," + product.getModelNumber() + "," + product.getCategory() + "," + product.getDescription() + "\n");
        }
        
        writer.close();
        
        System.out.println("Product details exported to products.csv");
    }
    
}

class Product {
    
    private String name;
    private String price;
    private String itemNumber;
    private String modelNumber;
    private String category;
    private String description;
    
    // Getters and setters
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getPrice() {
        return price;
    }
    
    public void setPrice(String price) {
        this.price = price;
    }
    
    public String getItemNumber() {
        return itemNumber;
    }
    
    public void setItemNumber(String itemNumber) {
        this.itemNumber = itemNumber;
    }
    
    public String getModelNumber() {
        return modelNumber;
    }
    
    public void setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber;
    }
    
    public String getCategory() {
        return category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
}
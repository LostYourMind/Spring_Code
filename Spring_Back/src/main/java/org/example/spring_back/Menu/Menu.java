package org.example.spring_back.Menu;
import java.util.List;

public class Menu {
    private Info info;
    private List<Category> data;

    // getters and setters for info
    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    // getters and setters for data
    public List<Category> getData() {
        return data;
    }

    public void setData(List<Category> data) {
        this.data = data;
    }

    public static class Info {
        private String name;
        private String address;
        private String type;

        // getters and setters for name
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        // getters and setters for address
        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        // getters and setters for type
        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    public static class Category {
        private CategoryName category;
        private List<Product> product;

        public CategoryName getCategory() {return category;}
        public void setCategory(CategoryName category) {this.category = category;}

        public List<Product> getProduct() { return product; }
        public void setProduct(List<Product> product) { this.product = product; }

        public static class CategoryName {
            private String categoryName;

            public String getCategoryName() { return categoryName; }
            public void setCategoryName(String categoryName) {this.categoryName = categoryName;}
        }
    }

    public static class Product {
        private String name;
        private String price;
        private String image; // 이미지 데이터가 Base64 문자열로 전송되는 것을 가정합니다.

        // getters and setters for name
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        // getters and setters for price
        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        // getters and setters for image
        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }
}

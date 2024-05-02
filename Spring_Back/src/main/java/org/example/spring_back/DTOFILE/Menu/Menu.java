/*
package org.example.spring_back.DTOFILE.Menu;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class Menu {
    private Info info;

    @JsonProperty("data")
    private List<Category> categories;

    @JsonProperty("kioskId")
    private Integer KioskID;

    //region 키오스크 ID

    public Integer getKioskID() {
        return KioskID;
    }

    public void setKioskID(Integer kioskID) {
        KioskID = kioskID;
    }

    //endregion

    // Getters and Setters
    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    // Info Inner Class
    public static class Info {
        private String name;
        private String address;
        private String type;

        // Getters and Setters for Info
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    // Category Inner Class
    public static class Category {
        // 'category' JSON 객체 내부의 'categoryName' 필드에 해당하는 키에 매핑됩니다.
        @JsonProperty("categoryName")
        private String categoryName;

        // 'product' JSON 배열에 매핑되는 필드로 변경합니다.
        @JsonProperty("product")
        private List<Product> products;

        // Getters and Setters for Category
        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

        public List<Product> getProducts() {
            return products;
        }

        public void setProducts(List<Product> products) {
            this.products = products;
        }
    }

    // Product Inner Class
    public static class Product {
        private String name;
        private Integer price;

        // JSON 'image' 키의 값을 Base64 문자열로 받는 필드입니다.
        @JsonProperty("image")
        private String imageBase64;

        // Getters and Setters for Product
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getPrice() {
            return price;
        }

        public void setPrice(Integer price) {
            this.price = price;
        }

        public String getImageBase64() {
            return imageBase64;
        }

        public void setImageBase64(String imageBase64) {
            this.imageBase64 = imageBase64;
        }
    }
}

*/

package org.example.spring_back.DTOFILE.Menu;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class Menu {
    private Info info;

    @JsonProperty("data")
    private List<Category> categories;

    @JsonProperty("kioskId")
    private String KioskID;

    //region 키오스크 ID

    public String getKioskID() {
        return KioskID;
    }

    public void setKioskID(String kioskID) {
        KioskID = kioskID;
    }

    //endregion

    // Getters and Setters
    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    // Info Inner Class
    public static class Info {
        private String name;
        private String address;
        private String type;

        // Getters and Setters for Info
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    public static class Category {
        // 'category' JSON 객체를 매핑하는 새로운 내부 클래스
        @JsonProperty("category")
        private CategoryDetails categoryDetails;

        @JsonProperty("product")
        private List<Product> products;

        // Getters and Setters for Category
        public CategoryDetails getCategoryDetails() {
            return categoryDetails;
        }

        public void setCategoryDetails(CategoryDetails categoryDetails) {
            this.categoryDetails = categoryDetails;
        }

        public List<Product> getProducts() {
            return products;
        }

        public void setProducts(List<Product> products) {
            this.products = products;
        }

        // CategoryDetails Inner Class
        public static class CategoryDetails {
            @JsonProperty("categoryName")
            private String categoryName;

            // Getters and Setters for CategoryDetails
            public String getCategoryName() {
                return categoryName;
            }

            public void setCategoryName(String categoryName) {
                this.categoryName = categoryName;
            }
        }
    }

    // Product Inner Class
    public static class Product {
        private String name;
        private Integer price;

        // JSON 'image' 키의 값을 Base64 문자열로 받는 필드입니다.
        @JsonProperty("image")
        private String imageBase64;

        // Getters and Setters for Product
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getPrice() {
            return price;
        }

        public void setPrice(Integer price) {
            this.price = price;
        }

        public String getImageBase64() {
            return imageBase64;
        }

        public void setImageBase64(String imageBase64) {
            this.imageBase64 = imageBase64;
        }
    }
}


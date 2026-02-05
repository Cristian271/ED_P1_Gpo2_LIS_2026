package data.estructures.singlylinkedlist;

public class Product {
    protected Product next;
    protected String nameProduct;
    protected float weight;

    public Product(String nameProduct, float weight){
        this.nameProduct = nameProduct;
        this.weight = weight;
        next = null;
    }

    public Product(String nameProduct, float weight, Product next){
        this(nameProduct, weight);
        this.next = next;
    }

    public Product getNext() {
        return next;
    }

    public void setNext(Product next) {
        this.next = next;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }
}

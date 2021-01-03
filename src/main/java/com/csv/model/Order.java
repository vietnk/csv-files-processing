package com.csv.model;

/**
 * Order
 */
public class Order {

    private String orderId;
    private String item;
    private String quantity;


    /**
     * get order id
     * @return
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * set order id
     * @param orderId
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    /**
     * get Item
     * @return
     */
    public String getItem() {
        return item;
    }

    /**
     * set item
     * @param item
     */
    public void setItem(String item) {
        this.item = item;
    }

    /**
     * get Quantity
     * @return
     */
    public String getQuantity() {
        return quantity;
    }

    /**
     * set quantity
     * @param quantity
     */
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    @Override
    /**
     * generate string to csv
     */
    public String toString() {
        return orderId + "," + item + "," + quantity;
    }
}

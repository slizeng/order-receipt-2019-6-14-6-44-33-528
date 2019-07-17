package org.katas.refactoring;

import java.util.List;

import static java.lang.String.format;
import static java.util.stream.Collectors.joining;

/**
 * OrderReceipt prints the details of order including customer name, address, description, quantity,
 * price and amount. It also calculates the sales tax @ 10% and prints as part
 * of order. It computes the total order amount (amount of individual lineItems +
 * total sales tax) and prints it.
 */
public class OrderReceipt {
    private Order order;

    public OrderReceipt(Order order) {
        this.order = order;
    }

    public String printReceipt() {
        StringBuilder receipt = new StringBuilder();

        // print headers
        receipt.append("======Printing Orders======\n");

        receipt.append(order.getCustomerName());
        receipt.append(order.getCustomerAddress());

        // prints lineItems
        double totalSalesTax = 0d;
        double total = 0d;

        receipt.append(formatLineItems(order.getLineItems()));

        for (LineItem lineItem : order.getLineItems()) {
            // calculate sales tax @ rate of 10%
            double salesTax = lineItem.getTotalAmount() * 0.10;
            totalSalesTax += salesTax;

            // calculate total amount of lineItem = price * quantity + 10 % sales tax
            total += lineItem.getTotalAmount() + salesTax;
        }

        // prints the state tax
        receipt.append("Sales Tax").append('\t').append(totalSalesTax);

        // print total amount
        receipt.append("Total Amount").append('\t').append(total);
        return receipt.toString();
    }

    private String formatLineItems(List<LineItem> lineItems) {
        return lineItems.stream()
                .map(this::formatSingleLineItem)
                .collect(joining(""));
    }

    private String formatSingleLineItem(LineItem lineItem) {
        return format("%s\t%s\t%s\t%s\n",
                lineItem.getDescription(),
                lineItem.getPrice(),
                lineItem.getQuantity(),
                lineItem.getTotalAmount());
    }
}
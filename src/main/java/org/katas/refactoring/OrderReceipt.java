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
    private static final String HEADER = "======Printing Orders======\n";
    private static final String TAX_PREFIX = "Sales Tax\t";
    private static final String TOTAL_AMOUNT_PREFIX = "Total Amount\t";
    private Order order;

    public OrderReceipt(Order order) {
        this.order = order;
    }

    public String printReceipt() {
        StringBuilder receipt = new StringBuilder();

        receipt.append(HEADER);
        assembleBasicInfo(receipt);
        assembleLineItems(receipt);
        assembleTotalAndTax(receipt);

        return receipt.toString();
    }

    private void assembleTotalAndTax(StringBuilder receipt) {
        double totalAmount = calculateTotalAmount(order.getLineItems());
        double totalSalesTax = totalAmount * 0.10;
        double totalAmountWithTax = totalAmount + totalSalesTax;

        receipt.append(TAX_PREFIX).append(totalSalesTax);
        receipt.append(TOTAL_AMOUNT_PREFIX).append(totalAmountWithTax);
    }

    private void assembleLineItems(StringBuilder receipt) {
        receipt.append(formatLineItems(order.getLineItems()));
    }

    private void assembleBasicInfo(StringBuilder receipt) {
        receipt.append(order.getCustomerName());
        receipt.append(order.getCustomerAddress());
    }

    private double calculateTotalAmount(List<LineItem> lineItems) {
        return lineItems.stream()
                .mapToDouble(LineItem::getTotalAmount)
                .sum();
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
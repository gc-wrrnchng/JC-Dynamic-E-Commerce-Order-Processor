package org.example;

import java.util.Scanner;

public class InteractiveOrderProcessor {
    public static void main (String args[]) {
        Scanner sc = new Scanner(System.in);
        double unitPrice;
        int quantity;
        boolean isMember;
        String[] validCustomerTiers = {"Regular", "Silver", "Gold"};
        String customerTier = "";
        String[] validShippingZones = {"ZoneA", "ZoneB", "ZoneC", "Unknown"};
        String shippingZone;
        String discountCode;
        double subTotal;
        String discountMessage = "";
        String promoCodeMessage = "";
        boolean hasFreeShipping = false;
        double surcharge;
        String surchargeMessage = "";
        double shippingCost;
        double finalOrderTotal;

        // INTERACTIVE ORDER PROCESSOR
        System.out.println("\nWelcome to the Interactive Order Processor!");
        System.out.println("\n--- Enter Order Details ---");
        System.out.print("Enter unit price: ");
        unitPrice = sc.nextDouble();
        sc.nextLine();
        System.out.print("Enter quantity: ");
        quantity = sc.nextInt();
        sc.nextLine();
        System.out.print("Is customer a member? (true/false): ");
        isMember = sc.nextBoolean();
        sc.nextLine();

        // Logic for membership status
        if (isMember) {
            System.out.println("Customer is a member. Applying member discount.");
            System.out.print("Enter customer tier (Regular/Silver/Gold): ");
            customerTier = sc.nextLine();
            if (customerTier == null || customerTier.isEmpty() || !java.util.Arrays.asList(validCustomerTiers).contains(customerTier)) {
                customerTier = "Regular"; // Default to Regular if invalid input
            }
            System.out.print("Enter shipping zone (ZoneA, ZoneB, ZoneC, Unknown): ");
            shippingZone = sc.nextLine();
            if (shippingZone == null || shippingZone.isEmpty() || !java.util.Arrays.asList(validShippingZones).contains(shippingZone)) {
                shippingZone = "Unknown"; // Default to Unknown if invalid input
            }
            System.out.print("Enter discount code (SAVE10, FREESHIP, or \"\" for none): ");
            discountCode = sc.nextLine();
        } else {
            System.out.println("Customer is not a member. No member discount applied.");
            System.out.print("Enter shipping zone (ZoneA, ZoneB, ZoneC, Unknown): ");
            shippingZone = sc.nextLine();
            if (shippingZone == null || shippingZone.isEmpty() || !java.util.Arrays.asList(validShippingZones).contains(shippingZone)) {
                shippingZone = "Unknown"; // Default to Unknown if invalid input
            }
            System.out.print("Enter discount code (SAVE10, FREESHIP, or \"\" for none): ");
            discountCode = sc.nextLine();
        }

        // Display summary of order details
        System.out.println("\n--- Order Details ---");
        System.out.println("Unit Price: $" + unitPrice);
        System.out.println("Quantity: " + quantity);
        System.out.println("Is Member: " + isMember);
        if (isMember) {
            System.out.println("Customer Tier: " + customerTier);
        }
        System.out.println("Shipping Zone: " + shippingZone);
        System.out.println("Discount Code: " + discountCode);

        // Calculations
        System.out.println("\n--- Calculation Steps ---");
        subTotal = unitPrice * quantity;
        System.out.printf("Initial Subtotal: $%.2f%n", subTotal);

        // Membership tier discount logic
        if (isMember) {
            if (customerTier.equalsIgnoreCase("Gold")) {
                discountMessage = "Gold - 15%";
                subTotal *= 0.85; // 15% discount for Gold members
            } else if (customerTier.equalsIgnoreCase("Silver")) {
                discountMessage = "Silver - 10%";
                subTotal *= 0.90; // 10% discount for Silver members
            } else {
                discountMessage = "Regular - No Discount";
                subTotal *= 1; // Regular or unknown members get no discount
            }
        } else {
            discountMessage = "Unknown Member - No Discount";
            subTotal *= 1; // Non-members get no discount
        }
        System.out.printf("After Tier Discount (%s): $%.2f%n", discountMessage, subTotal);

        // Quantity discount logic
        if (quantity >= 5) {
            subTotal *= 0.95; // Additional 5% discount for quantity >= 5
        } else {
            subTotal *= 1; // No quantity discount
        }
        System.out.print("After Quantity Discount (5% for >= 5 items): ");
        System.out.printf("$%.2f%n", subTotal);

        // Promo code discount logic
        if (discountCode.equals("SAVE10")) {
            if (subTotal > 75) {
                promoCodeMessage = "SAVE10 for > $75";
                subTotal -= 10; // $10 off for SAVE10 if subtotal > 75
            } else {
                System.out.println("SAVE10 not applied: Subtotal must be greater than $75.");
            }
        } else if (discountCode.equalsIgnoreCase("FREESHIP")) {
            hasFreeShipping = true;
            promoCodeMessage = "FREESHIP for free shipping";
        } else {
            promoCodeMessage = "No Promo Code Applied";
        }
        System.out.printf("After Promotional Code (%s): $%.2f%n", promoCodeMessage, subTotal);

        // Surcharge logic
        surcharge = (subTotal < 25) ? 3.00 : 0.00;
        surchargeMessage = (surcharge > 0) ? "Surcharge applied" : "No surcharge";
        subTotal += surcharge;
        System.out.printf("After Small Order Surchage (if applicable): $%.2f (%s)%n", subTotal, surchargeMessage);

        // Shipping logic
        if (!hasFreeShipping) {
            switch (shippingZone) {
                case "ZoneA": {
                    shippingCost = 5.00;
                } break;
                case "ZoneB": {
                    shippingCost = 12.50;
                } break;
                case "ZoneC": {
                    shippingCost = 20.00;
                } break;
                default: {
                    shippingCost = 25.00;
                }
            }
        } else {
            shippingCost = 0.00; // Free shipping
        }
        System.out.printf("%nShipping Cost: $%.2f (%s)%n", shippingCost, shippingZone);

        // Final total calculation
        finalOrderTotal = subTotal + shippingCost;
        System.out.printf("\nFinal Order Total: $%.2f%n", finalOrderTotal);

        // STRING EQUALITY DEMO
        System.out.println("\n--- String Equality Demo ---");
        System.out.print("Enter first string for comparison: ");
        String firstString = sc.nextLine();
        System.out.print("Enter second string for comparison: ");
        String secondString = sc.nextLine();

        System.out.println("\nString 1: \"" + firstString + "\"");
        System.out.println("String 2: \"" + secondString + "\"");

        String contentMessage1 = "";
        String contentMessage2 = "";
        boolean dotEqualsScenario = firstString.equals(secondString);
        boolean dotEqualsIgnoreCaseScenario = firstString.equalsIgnoreCase(secondString);

        if (dotEqualsScenario) {
            contentMessage1 = "Content is identical, case-sensitive";
        } else {
            contentMessage1 = "Content is different due to case";
        }

        if (dotEqualsIgnoreCaseScenario) {
            contentMessage2 += "Content is identical, ignoring case";
        } else {
            contentMessage2 += "Content is different, even ignoring case";
        }

        System.out.println("\nString 1 == String 2: "
                + (firstString == secondString)
                + " (Compares references, which are different for user input strings)");
        System.out.println("String 1 .equals() String 2: "
                + dotEqualsScenario
                + " (" + contentMessage1 + ")");
        System.out.println("String 1 .equalsIgnoreCase() String 2: "
                + dotEqualsIgnoreCaseScenario
                 + " (" + contentMessage2 + ")");
    }
}

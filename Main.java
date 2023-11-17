import positionallist.*;

/**
 * Main class for the PositionalList program.
 */
public class Main {
    /**
     * Main method to run the PositionalList program.
     *
     * @param args The command line arguments (not used).
     */
    public static void main(String[] args) {
        PositionalList<String> list = new LinkedListPositionalList<>();

        while (true) {
            System.out.println("Choose an operation to perform:");
            System.out.println("1. Add element at the beginning");
            System.out.println("2. Add element at the end");
            System.out.println("3. Add element after a position");
            System.out.println("4. Access first element");
            System.out.println("5. Access last element");
            System.out.println("6. Access element before a position");
            System.out.println("7. Access element after a position");
            System.out.println("8. Set element at a position");
            System.out.println("9. Remove element at a position");
            System.out.println("10. Print List");
            System.out.println("11. Display List Size");
            System.out.println("12. Exit");

            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    System.out.println("Enter the element to add at the beginning:");
                    String element = getUserInput();
                    list.addFirst(element);
                    break;

                case 2:
                    System.out.println("Enter the element to add at the end:");
                    element = getUserInput();
                    list.addLast(element);
                    break;

                case 3:
                    if (list.isEmpty()) {
                        System.out.println("List is empty. Cannot add after a position.");
                        break;
                    }
                    System.out.println("Enter the element to add:");
                    element = getUserInput();
                    System.out.println("Enter the position after which to add:");
                    Position<String> position = getPositionFromUser(list);
                    if (position == null) {
                        break; // Invalid position input
                    }
                    list.addAfter(position, element);
                    break;

                case 4:
                    if (list.isEmpty()) {
                        System.out.println("List is empty. Cannot access first element.");
                    } else {
                        System.out.println("First element: " + list.first().getElement());
                    }
                    break;

                case 5:
                    if (list.isEmpty()) {
                        System.out.println("List is empty. Cannot access last element.");
                    } else {
                        System.out.println("Last element: " + list.last().getElement());
                    }
                    break;

                case 6:
                    if (list.isEmpty()) {
                        System.out.println("List is empty. Cannot access element before a position.");
                        break;
                    }
                    System.out.println("Enter the position to find the element before:");
                    position = getPositionFromUser(list);
                    if (position == null) {
                        break; // Invalid position input
                    }
                    System.out.println("Element before the given position: " + list.before(position).getElement());
                    break;

                case 7:
                    if (list.isEmpty()) {
                        System.out.println("List is empty. Cannot access element after a position.");
                        break;
                    }
                    System.out.println("Enter the position to find the element after:");
                    position = getPositionFromUser(list);
                    if (position == null) {
                        break; // Invalid position input
                    }
                    System.out.println("Element after the given position: " + list.after(position).getElement());
                    break;

                case 8:
                    if (list.isEmpty()) {
                        System.out.println("List is empty. Cannot set element at a position.");
                        break;
                    }
                    System.out.println("Enter the new element:");
                    element = getUserInput();
                    System.out.println("Enter the position to set the element:");
                    position = getPositionFromUser(list);
                    if (position == null) {
                        break; // Invalid position input
                    }
                    System.out.println("Old element at the position: " + list.set(position, element));
                    break;

                case 9:
                    if (list.isEmpty()) {
                        System.out.println("List is empty. Cannot remove element at a position.");
                        break;
                    }
                    System.out.println("Enter the position to remove the element:");
                    position = getPositionFromUser(list);
                    if (position == null) {
                        break; // Invalid position input
                    }
                    System.out.println("Removed element: " + list.remove(position));
                    break;

                case 10:
                    printList(list);
                    break;

                case 11:
                    System.out.println("Size of the list: " + list.size());
                    break;

                case 12:
                    System.out.println("Exiting the program.");
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private static int getUserChoice() {
        try {
            return Integer.parseInt(getUserInput());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid input. Please enter a valid number.");
        }
    }

    private static String getUserInput() {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        return scanner.nextLine();
    }

    private static Position<String> getPositionFromUser(PositionalList<String> list) {
        System.out.println("Enter the index of the position (0 to " + (list.size() - 1) + "):");
        int index = getUserChoice();
        if (index < 0) {
            throw new IllegalArgumentException("Invalid position index.");
        }

        Position<String> position = list.first();
        for (int i = 0; i < index; i++) {
            position = list.after(position);
        }

        return position;
    }

    private static void printList(PositionalList<String> list) {
        System.out.print("Positional List: [");
        boolean first = true;
        for (String element : list) {
            if (!first) {
                System.out.print(", ");
            }
            System.out.print(element);
            first = false;
        }
        System.out.println("]");
    }

}

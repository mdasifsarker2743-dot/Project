
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
public class EventVerseApp {

    // ================= DATA =================

    static ArrayList<User> users = new ArrayList<>();
    static ArrayList<Event> events = new ArrayList<>();
    static ArrayList<Venue> venues = new ArrayList<>();
    static ArrayList<FoodPackage> foodPackages = new ArrayList<>();
    static ArrayList<ServiceItem> services = new ArrayList<>();

    static User loggedInUser;

    // ================= GUI =================

    static JFrame frame;
    static JPanel contentPanel;

    static final Color NAVY = new Color(27, 39, 69);
    static final Color PURPLE = new Color(105, 82, 210);
    static final Color BG = new Color(245, 247, 252);

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            loadDefaultData();
            createWindow();
            showLogin();

        });
    }

    // ================= WINDOW =================

    static void createWindow() {

        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }

        frame = new JFrame(
                "EventVerse - Event Organizing System"
        );

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1100, 700);
        frame.setMinimumSize(new Dimension(900, 600));
        frame.setLocationRelativeTo(null);

        contentPanel = new JPanel();
        contentPanel.setBackground(BG);

        frame.setContentPane(contentPanel);
        frame.setVisible(true);
    }

    // ================= DEFAULT DATA =================

    static void loadDefaultData() {

        users.add(new Admin(
                "Administrator",
                "admin@eventverse.com",
                "1234"
        ));

        // ================= VENUES =================

        venues.add(new Venue(
                "City Convention Center",
                "Dhaka",
                500,
                30000
        ));

        venues.add(new Venue(
                "Grand Community Hall",
                "Dhaka",
                300,
                18000
        ));

        venues.add(new Venue(
                "University Auditorium",
                "Dhaka",
                1000,
                50000
        ));

        venues.add(new Venue(
                "Garden Resort",
                "Gazipur",
                700,
                65000
        ));

        venues.add(new Venue(
                "Royal Banquet Hall",
                "Khulna",
                200,
                15000
        ));

        // ================= FOOD PACKAGES =================

        foodPackages.add(new FoodPackage(
                "Basic Package",
                300,
                "Rice, Chicken, Vegetables, Soft Drink"
        ));

        foodPackages.add(new FoodPackage(
                "Standard Package",
                500,
                "Kacchi, Chicken, Salad, Dessert, Soft Drink"
        ));

        foodPackages.add(new FoodPackage(
                "Premium Package",
                800,
                "Premium Menu, Multiple Items, Dessert and Drinks"
        ));

        // ================= EVENT SERVICES =================

        services.add(new ServiceItem(
                "Basic Decoration",
                10000,
                "Simple event decoration"
        ));

        services.add(new ServiceItem(
                "Standard Decoration",
                25000,
                "Professional decoration"
        ));

        services.add(new ServiceItem(
                "Premium Decoration",
                50000,
                "Premium themed decoration"
        ));

        services.add(new ServiceItem(
                "Photography",
                15000,
                "Professional photography"
        ));

        services.add(new ServiceItem(
                "Videography",
                20000,
                "Full video coverage"
        ));

        services.add(new ServiceItem(
                "Sound System",
                10000,
                "Speakers and microphones"
        ));

        services.add(new ServiceItem(
                "Stage Setup",
                20000,
                "Stage and backdrop"
        ));

        services.add(new ServiceItem(
                "Special Lighting",
                15000,
                "Professional lighting"
        ));
    }

    // ================= LOGIN =================

    static void showLogin() {

        contentPanel.removeAll();
        contentPanel.setLayout(new GridBagLayout());

        JPanel loginPanel = new JPanel();

        loginPanel.setPreferredSize(
                new Dimension(430, 420)
        );

        loginPanel.setBackground(Color.WHITE);

        loginPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(220, 220, 220)
                        ),
                        new EmptyBorder(30, 40, 30, 40)
                )
        );

        loginPanel.setLayout(
                new BoxLayout(
                        loginPanel,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel title = new JLabel("EVENTVERSE");

        title.setFont(
                new Font("Arial", Font.BOLD, 30)
        );

        title.setForeground(PURPLE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitle = new JLabel(
                "Complete Event Organizing System"
        );

        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField emailField = new JTextField();

        JPasswordField passwordField =
                new JPasswordField();

        emailField.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, 40)
        );

        passwordField.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, 40)
        );

        JButton loginButton = new JButton("LOGIN");

        JButton registerButton =
                new JButton("CREATE ACCOUNT");

        styleButton(loginButton);
        styleButton(registerButton);

        loginButton.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        registerButton.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        loginPanel.add(title);

        loginPanel.add(
                Box.createVerticalStrut(8)
        );

        loginPanel.add(subtitle);

        loginPanel.add(
                Box.createVerticalStrut(30)
        );

        loginPanel.add(new JLabel("Email"));

        loginPanel.add(emailField);

        loginPanel.add(
                Box.createVerticalStrut(15)
        );

        loginPanel.add(new JLabel("Password"));

        loginPanel.add(passwordField);

        loginPanel.add(
                Box.createVerticalStrut(25)
        );

        loginPanel.add(loginButton);

        loginPanel.add(
                Box.createVerticalStrut(10)
        );

        loginPanel.add(registerButton);

        // LOGIN ACTION

        loginButton.addActionListener(e -> {

            String email =
                    emailField.getText().trim();

            String password =
                    new String(
                            passwordField.getPassword()
                    );

            for (User user : users) {

                if (user.email.equals(email)
                        && user.password.equals(password)) {

                    loggedInUser = user;

                    showDashboard();

                    return;
                }
            }

            JOptionPane.showMessageDialog(
                    frame,
                    "Invalid Email or Password!"
            );
        });

        // REGISTER ACTION

        registerButton.addActionListener(
                e -> showRegister()
        );

        contentPanel.add(loginPanel);

        refresh();
    }

    // ================= REGISTER =================

    static void showRegister() {

        JTextField nameField =
                new JTextField();

        JTextField emailField =
                new JTextField();

        JPasswordField passwordField =
                new JPasswordField();

        Object[] fields = {

                "Full Name:", nameField,

                "Email:", emailField,

                "Password:", passwordField
        };

        int result =
                JOptionPane.showConfirmDialog(
                        frame,
                        fields,
                        "Create Account",
                        JOptionPane.OK_CANCEL_OPTION
                );

        if (result == JOptionPane.OK_OPTION) {

            String name =
                    nameField.getText().trim();

            String email =
                    emailField.getText().trim();

            String password =
                    new String(
                            passwordField.getPassword()
                    );

            if (name.isEmpty()
                    || email.isEmpty()
                    || password.isEmpty()) {

                JOptionPane.showMessageDialog(
                        frame,
                        "Please fill all fields!"
                );

                return;
            }

            // Check duplicate email

            for (User user : users) {

                if (user.email.equalsIgnoreCase(email)) {

                    JOptionPane.showMessageDialog(
                            frame,
                            "This email is already registered!"
                    );

                    return;
                }
            }

            users.add(
                    new Customer(
                            name,
                            email,
                            password
                    )
            );

            JOptionPane.showMessageDialog(
                    frame,
                    "Account Created Successfully!\nNow login with your account."
            );
        }
    }

    // ================= DASHBOARD =================

    static void showDashboard() {

        contentPanel.removeAll();

        contentPanel.setLayout(
                new BorderLayout()
        );

        // ================= SIDEBAR =================

        JPanel sidebar = new JPanel();

        sidebar.setBackground(NAVY);

        sidebar.setPreferredSize(
                new Dimension(240, 0)
        );

        sidebar.setBorder(
                new EmptyBorder(
                        25,
                        15,
                        25,
                        15
                )
        );

        sidebar.setLayout(
                new BoxLayout(
                        sidebar,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel logo = new JLabel("EVENTVERSE");

        logo.setForeground(Color.WHITE);

        logo.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        24
                )
        );

        logo.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        sidebar.add(logo);

        sidebar.add(
                Box.createVerticalStrut(35)
        );

        JButton dashboardButton =
                menuButton("Dashboard");

        JButton createEventButton =
                menuButton("Create Event");

        JButton myEventsButton =
                menuButton("My Events");

        JButton venueButton =
                menuButton("Venues");

        JButton foodButton =
                menuButton("Food Packages");

        JButton servicesButton =
                menuButton("Event Services");

        JButton logoutButton =
                menuButton("Logout");

        sidebar.add(dashboardButton);

        sidebar.add(
                Box.createVerticalStrut(10)
        );

        sidebar.add(createEventButton);

        sidebar.add(
                Box.createVerticalStrut(10)
        );

        sidebar.add(myEventsButton);

        sidebar.add(
                Box.createVerticalStrut(10)
        );

        sidebar.add(venueButton);

        sidebar.add(
                Box.createVerticalStrut(10)
        );

        sidebar.add(foodButton);

        sidebar.add(
                Box.createVerticalStrut(10)
        );

        sidebar.add(servicesButton);

        sidebar.add(Box.createVerticalGlue());

        sidebar.add(logoutButton);

        // ================= BUTTON ACTIONS =================

        dashboardButton.addActionListener(
                e -> showHomePage()
        );

        createEventButton.addActionListener(
                e -> createEvent()
        );

        myEventsButton.addActionListener(
                e -> showMyEvents()
        );

        venueButton.addActionListener(
                e -> showVenues()
        );

        foodButton.addActionListener(
                e -> showFoodPackages()
        );

        servicesButton.addActionListener(
                e -> showServices()
        );

        logoutButton.addActionListener(e -> {

            loggedInUser = null;

            showLogin();
        });

        contentPanel.add(
                sidebar,
                BorderLayout.WEST
        );

        showHomePage();

        refresh();
    }

    // ================= HOME PAGE =================

    static void showHomePage() {

        // Remove previous CENTER component

        BorderLayout layout =
                (BorderLayout) contentPanel.getLayout();

        Component center =
                layout.getLayoutComponent(
                        BorderLayout.CENTER
                );

        if (center != null) {

            contentPanel.remove(center);
        }

        JPanel main = new JPanel();

        main.setBackground(BG);

        main.setBorder(
                new EmptyBorder(
                        40,
                        50,
                        40,
                        50
                )
        );

        main.setLayout(
                new BoxLayout(
                        main,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel welcome = new JLabel(
                "Welcome, "
                        + loggedInUser.name
                        + "!"
        );

        welcome.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        30
                )
        );

        welcome.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        JLabel description = new JLabel(
                "Plan and organize your complete event from one place."
        );

        description.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        16
                )
        );

        description.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        // ================= CARD =================

        JPanel card = new JPanel();

        card.setBackground(Color.WHITE);

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(225, 225, 225)
                        ),
                        new EmptyBorder(
                                30,
                                35,
                                30,
                                35
                        )
                )
        );

        card.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        400
                )
        );

        card.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        card.setLayout(
                new BoxLayout(
                        card,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel cardTitle = new JLabel(
                "PLAN YOUR EVENT"
        );

        cardTitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        24
                )
        );

        cardTitle.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        JTextArea info = new JTextArea();

        info.setText(
                "1. Create your event\n\n"
                        + "2. Enter number of guests\n\n"
                        + "3. Select a suitable venue\n\n"
                        + "4. Choose food package\n\n"
                        + "5. Select event services\n\n"
                        + "6. Automatic cost calculation\n\n"
                        + "7. Ticket and payment confirmation"
        );

        info.setEditable(false);

        info.setBackground(Color.WHITE);

        info.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        17
                )
        );

        info.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        card.add(cardTitle);

        card.add(
                Box.createVerticalStrut(20)
        );

        card.add(info);

        JButton startButton =
                new JButton("CREATE EVENT NOW");

        styleButton(startButton);

        startButton.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        startButton.addActionListener(
                e -> createEvent()
        );

        card.add(
                Box.createVerticalStrut(20)
        );

        card.add(startButton);

        main.add(welcome);

        main.add(
                Box.createVerticalStrut(10)
        );

        main.add(description);

        main.add(
                Box.createVerticalStrut(30)
        );

        main.add(card);

        contentPanel.add(
                main,
                BorderLayout.CENTER
        );

        refresh();
    }

    // ================= CREATE EVENT =================

    static void createEvent() {

        JTextField eventNameField =
                new JTextField();

        JComboBox<String> eventTypeBox =
                new JComboBox<>(
                        new String[]{

                                "Wedding",
                                "Birthday",
                                "Seminar",
                                "Concert",
                                "University Program",
                                "Corporate Event",
                                "Sports Event",
                                "Cultural Program",
                                "Other"
                        }
                );

        JTextField dateField =
                new JTextField();

        JTextField guestField =
                new JTextField();

        JTextField scheduleField =
                new JTextField();

        Object[] fields = {

                "Event Name:", eventNameField,

                "Event Type:", eventTypeBox,

                "Event Date:", dateField,

                "Number of Guests:", guestField,

                "Event Schedule:", scheduleField
        };

        int result =
                JOptionPane.showConfirmDialog(
                        frame,
                        fields,
                        "Create New Event",
                        JOptionPane.OK_CANCEL_OPTION
                );

        if (result != JOptionPane.OK_OPTION) {
            return;
        }

        String eventName =
                eventNameField.getText().trim();

        String eventType =
                (String) eventTypeBox.getSelectedItem();

        String date =
                dateField.getText().trim();

        String schedule =
                scheduleField.getText().trim();

        int guests;

        try {

            guests =
                    Integer.parseInt(
                            guestField.getText().trim()
                    );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    frame,
                    "Please enter a valid guest number!"
            );

            return;
        }

        if (eventName.isEmpty()
                || date.isEmpty()
                || guests <= 0) {

            JOptionPane.showMessageDialog(
                    frame,
                    "Please enter valid information!"
            );

            return;
        }

        // ================= VENUE SELECTION =================

        ArrayList<Venue> suitableVenues =
                new ArrayList<>();

        for (Venue venue : venues) {

            if (venue.capacity >= guests) {

                suitableVenues.add(venue);
            }
        }

        if (suitableVenues.isEmpty()) {

            JOptionPane.showMessageDialog(
                    frame,
                    "No suitable venue found for "
                            + guests
                            + " guests!"
            );

            return;
        }

        Venue selectedVenue =
                (Venue) JOptionPane.showInputDialog(
                        frame,
                        "Select a suitable venue:",
                        "Venue Selection",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        suitableVenues.toArray(),
                        suitableVenues.get(0)
                );

        if (selectedVenue == null) {
            return;
        }

        // ================= FOOD SELECTION =================

        FoodPackage selectedFood =
                (FoodPackage) JOptionPane.showInputDialog(
                        frame,
                        "Select Food Package:",
                        "Food Selection",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        foodPackages.toArray(),
                        foodPackages.get(0)
                );

        if (selectedFood == null) {
            return;
        }

        // ================= SERVICE SELECTION =================

        JList<ServiceItem> serviceList =
                new JList<>(
                        services.toArray(
                                new ServiceItem[0]
                        )
                );

        serviceList.setSelectionMode(
                ListSelectionModel.MULTIPLE_INTERVAL_SELECTION
        );

        JScrollPane serviceScroll =
                new JScrollPane(serviceList);

        serviceScroll.setPreferredSize(
                new Dimension(450, 250)
        );

        int serviceResult =
                JOptionPane.showConfirmDialog(
                        frame,
                        serviceScroll,
                        "Select Event Services",
                        JOptionPane.OK_CANCEL_OPTION
                );

        if (serviceResult != JOptionPane.OK_OPTION) {
            return;
        }

        ArrayList<ServiceItem> selectedServices =
                new ArrayList<>(
                        serviceList.getSelectedValuesList()
                );

        // ================= TICKET SYSTEM =================

        int ticketOption =
                JOptionPane.showConfirmDialog(
                        frame,
                        "Is this a ticketed event?",
                        "Ticket System",
                        JOptionPane.YES_NO_OPTION
                );

        boolean ticketed =
                ticketOption == JOptionPane.YES_OPTION;

        double ticketPrice = 0;

        int ticketQuantity = 0;

        if (ticketed) {

            JTextField priceField =
                    new JTextField();

            JTextField quantityField =
                    new JTextField();

            Object[] ticketFields = {

                    "Ticket Price:", priceField,

                    "Ticket Quantity:", quantityField
            };

            int ticketResult =
                    JOptionPane.showConfirmDialog(
                            frame,
                            ticketFields,
                            "Ticket Details",
                            JOptionPane.OK_CANCEL_OPTION
                    );

            if (ticketResult != JOptionPane.OK_OPTION) {
                return;
            }

            try {

                ticketPrice =
                        Double.parseDouble(
                                priceField.getText().trim()
                        );

                ticketQuantity =
                        Integer.parseInt(
                                quantityField.getText().trim()
                        );

                if (ticketPrice < 0
                        || ticketQuantity < 0) {

                    throw new Exception();
                }

            } catch (Exception e) {

                JOptionPane.showMessageDialog(
                        frame,
                        "Invalid Ticket Information!"
                );

                return;
            }
        }

        // ================= CREATE EVENT =================

        Event event = new Event(
                eventName,
                eventType,
                date,
                guests,
                schedule,
                selectedVenue,
                selectedFood,
                selectedServices,
                ticketed,
                ticketPrice,
                ticketQuantity,
                loggedInUser
        );

        // ================= PAYMENT =================

        String[] paymentMethods = {

                "bKash",
                "Nagad",
                "Card",
                "Cash"
        };

        String paymentMethod =
                (String) JOptionPane.showInputDialog(
                        frame,
                        "Select Payment Method:",
                        "Payment",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        paymentMethods,
                        paymentMethods[0]
                );

        if (paymentMethod == null) {
            return;
        }

        event.paymentMethod =
                paymentMethod;

        events.add(event);

        JOptionPane.showMessageDialog(
                frame,
                event.getSummary(),
                "Event Successfully Created!",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    // ================= MY EVENTS =================

    static void showMyEvents() {

        removeCenterPanel();

        JPanel main =
                new JPanel(
                        new BorderLayout(
                                10,
                                15
                        )
                );

        main.setBackground(BG);

        main.setBorder(
                new EmptyBorder(
                        30,
                        40,
                        30,
                        40
                )
        );

        JLabel title =
                new JLabel("My Events");

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        28
                )
        );

        main.add(
                title,
                BorderLayout.NORTH
        );

        DefaultListModel<Event> model =
                new DefaultListModel<>();

        for (Event event : events) {

            if (event.organizer == loggedInUser
                    || loggedInUser instanceof Admin) {

                model.addElement(event);
            }
        }

        JList<Event> eventList =
                new JList<>(model);

        eventList.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        16
                )
        );

        main.add(
                new JScrollPane(eventList),
                BorderLayout.CENTER
        );

        JPanel bottomPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER
                        )
                );

        bottomPanel.setBackground(BG);

        JButton detailsButton =
                new JButton("VIEW EVENT DETAILS");

        styleButton(detailsButton);

        bottomPanel.add(detailsButton);

        detailsButton.addActionListener(e -> {

            Event selected =
                    eventList.getSelectedValue();

            if (selected == null) {

                JOptionPane.showMessageDialog(
                        frame,
                        "Please select an event!"
                );

                return;
            }

            showText(
                    "Event Details",
                    selected.getSummary()
            );
        });

        main.add(
                bottomPanel,
                BorderLayout.SOUTH
        );

        contentPanel.add(
                main,
                BorderLayout.CENTER
        );

        refresh();
    }

    // ================= VENUES =================

    static void showVenues() {

        StringBuilder text =
                new StringBuilder();

        for (Venue venue : venues) {

            text.append(
                    venue.details()
            );

            text.append(
                    "\n\n--------------------------\n\n"
            );
        }

        showText(
                "Available Venues",
                text.toString()
        );
    }

    // ================= FOOD =================

    static void showFoodPackages() {

        StringBuilder text =
                new StringBuilder();

        for (FoodPackage food : foodPackages) {

            text.append(
                    food.details()
            );

            text.append(
                    "\n\n--------------------------\n\n"
            );
        }

        showText(
                "Food Packages",
                text.toString()
        );
    }

    // ================= SERVICES =================

    static void showServices() {

        StringBuilder text =
                new StringBuilder();

        for (ServiceItem service : services) {

            text.append(
                    service.details()
            );

            text.append(
                    "\n\n--------------------------\n\n"
            );
        }

        showText(
                "Event Services",
                text.toString()
        );
    }

    // ================= TEXT WINDOW =================

    static void showText(
            String title,
            String text
    ) {

        JTextArea area =
                new JTextArea(text);

        area.setEditable(false);

        area.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        15
                )
        );

        area.setLineWrap(true);

        area.setWrapStyleWord(true);

        area.setCaretPosition(0);

        JScrollPane scroll =
                new JScrollPane(area);

        scroll.setPreferredSize(
                new Dimension(650, 500)
        );

        JOptionPane.showMessageDialog(
                frame,
                scroll,
                title,
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    // ================= REMOVE CENTER =================

    static void removeCenterPanel() {

        BorderLayout layout =
                (BorderLayout) contentPanel.getLayout();

        Component center =
                layout.getLayoutComponent(
                        BorderLayout.CENTER
                );

        if (center != null) {

            contentPanel.remove(center);
        }
    }

    // ================= MENU BUTTON STYLE =================

    static JButton menuButton(String text) {

        JButton button =
                new JButton(text);

        button.setMaximumSize(
                new Dimension(210, 48)
        );

        button.setPreferredSize(
                new Dimension(210, 48)
        );

        button.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        button.setBackground(PURPLE);

        button.setForeground(Color.WHITE);

        button.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        15
                )
        );

        button.setFocusPainted(false);

        button.setBorderPainted(false);

        button.setOpaque(true);

        button.setContentAreaFilled(true);

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        return button;
    }

    // ================= NORMAL BUTTON STYLE =================

    static void styleButton(JButton button) {

        button.setBackground(PURPLE);

        button.setForeground(Color.WHITE);

        button.setFocusPainted(false);

        button.setBorderPainted(false);

        button.setOpaque(true);

        button.setContentAreaFilled(true);

        button.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        button.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        45
                )
        );

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );
    }

    // ================= REFRESH =================

    static void refresh() {

        contentPanel.revalidate();

        contentPanel.repaint();
    }
}


// =====================================================
// USER CLASS
// =====================================================

class User {

    String name;
    String email;
    String password;

    User(
            String name,
            String email,
            String password
    ) {

        this.name = name;
        this.email = email;
        this.password = password;
    }
}


// =====================================================
// CUSTOMER CLASS
// =====================================================

class Customer extends User {

    Customer(
            String name,
            String email,
            String password
    ) {

        super(
                name,
                email,
                password
        );
    }
}


// =====================================================
// ADMIN CLASS
// =====================================================

class Admin extends User {

    Admin(
            String name,
            String email,
            String password
    ) {

        super(
                name,
                email,
                password
        );
    }
}


// =====================================================
// VENUE CLASS
// =====================================================

class Venue {

    String name;
    String location;
    int capacity;
    double cost;

    Venue(
            String name,
            String location,
            int capacity,
            double cost
    ) {

        this.name = name;
        this.location = location;
        this.capacity = capacity;
        this.cost = cost;
    }

    String details() {

        return "Venue: " + name
                + "\nLocation: " + location
                + "\nCapacity: " + capacity
                + " persons"
                + "\nCost: ৳"
                + String.format(
                "%,.2f",
                cost
        );
    }

    @Override
    public String toString() {

        return name
                + " | "
                + location
                + " | Capacity: "
                + capacity
                + " | ৳"
                + String.format(
                "%,.0f",
                cost
        );
    }
}


// =====================================================
// FOOD PACKAGE CLASS
// =====================================================

class FoodPackage {

    String name;
    double perPersonCost;
    String menu;

    FoodPackage(
            String name,
            double perPersonCost,
            String menu
    ) {

        this.name = name;
        this.perPersonCost = perPersonCost;
        this.menu = menu;
    }

    String details() {

        return "Food Package: " + name
                + "\nPrice Per Person: ৳"
                + String.format(
                "%,.2f",
                perPersonCost
        )
                + "\nMenu: "
                + menu;
    }

    @Override
    public String toString() {

        return name
                + " | ৳"
                + String.format(
                "%,.0f",
                perPersonCost
        )
                + " per person";
    }
}


// =====================================================
// SERVICE ITEM CLASS
// =====================================================

class ServiceItem {

    String name;
    double cost;
    String description;

    ServiceItem(
            String name,
            double cost,
            String description
    ) {

        this.name = name;
        this.cost = cost;
        this.description = description;
    }

    String details() {

        return name
                + "\nCost: ৳"
                + String.format(
                "%,.2f",
                cost
        )
                + "\nDescription: "
                + description;
    }

    @Override
    public String toString() {

        return name
                + " | ৳"
                + String.format(
                "%,.0f",
                cost
        );
    }
}


// =====================================================
// EVENT CLASS
// =====================================================

class Event {

    String name;
    String type;
    String date;
    int guests;
    String schedule;

    Venue venue;

    FoodPackage foodPackage;

    ArrayList<ServiceItem> selectedServices;

    boolean ticketed;

    double ticketPrice;

    int ticketQuantity;

    String paymentMethod;

    User organizer;


    Event(
            String name,
            String type,
            String date,
            int guests,
            String schedule,
            Venue venue,
            FoodPackage foodPackage,
            ArrayList<ServiceItem> selectedServices,
            boolean ticketed,
            double ticketPrice,
            int ticketQuantity,
            User organizer
    ) {

        this.name = name;
        this.type = type;
        this.date = date;
        this.guests = guests;
        this.schedule = schedule;

        this.venue = venue;

        this.foodPackage = foodPackage;

        this.selectedServices =
                selectedServices;

        this.ticketed = ticketed;

        this.ticketPrice = ticketPrice;

        this.ticketQuantity =
                ticketQuantity;

        this.organizer = organizer;
    }


    // ================= FOOD COST =================

    double getFoodCost() {

        return foodPackage.perPersonCost
                * guests;
    }


    // ================= SERVICE COST =================

    double getServiceCost() {

        double total = 0;

        for (ServiceItem service :
                selectedServices) {

            total += service.cost;
        }

        return total;
    }


    // ================= TOTAL COST =================

    double getTotalCost() {

        return venue.cost
                + getFoodCost()
                + getServiceCost();
    }


    // ================= TICKET REVENUE =================

    double getTicketRevenue() {

        return ticketPrice
                * ticketQuantity;
    }


    // ================= SUMMARY =================

    String getSummary() {

        StringBuilder text =
                new StringBuilder();

        text.append(
                "========== EVENTVERSE ==========\n\n"
        );

        // EVENT INFO

        text.append(
                "EVENT INFORMATION\n"
        );

        text.append(
                "Event Name: "
        );

        text.append(name);

        text.append("\n");

        text.append(
                "Event Type: "
        );

        text.append(type);

        text.append("\n");

        text.append(
                "Date: "
        );

        text.append(date);

        text.append("\n");

        text.append(
                "Guests: "
        );

        text.append(guests);

        text.append("\n");

        text.append(
                "Schedule: "
        );

        text.append(schedule);

        text.append(
                "\n\n"
        );


        // VENUE

        text.append(
                "VENUE\n"
        );

        text.append(
                "Name: "
        );

        text.append(
                venue.name
        );

        text.append("\n");

        text.append(
                "Location: "
        );

        text.append(
                venue.location
        );

        text.append("\n");

        text.append(
                "Venue Cost: ৳"
        );

        text.append(
                String.format(
                        "%,.2f",
                        venue.cost
                )
        );

        text.append(
                "\n\n"
        );


        // FOOD

        text.append(
                "FOOD PACKAGE\n"
        );

        text.append(
                foodPackage.name
        );

        text.append("\n");

        text.append(
                "Menu: "
        );

        text.append(
                foodPackage.menu
        );

        text.append("\n");

        text.append(
                "Per Person: ৳"
        );

        text.append(
                String.format(
                        "%,.2f",
                        foodPackage.perPersonCost
                )
        );

        text.append("\n");

        text.append(
                "Total Food Cost: ৳"
        );

        text.append(
                String.format(
                        "%,.2f",
                        getFoodCost()
                )
        );

        text.append(
                "\n\n"
        );


        // SERVICES

        text.append(
                "SELECTED SERVICES\n"
        );

        if (selectedServices.isEmpty()) {

            text.append(
                    "No extra services selected\n"
            );

        } else {

            for (ServiceItem service :
                    selectedServices) {

                text.append("- ");

                text.append(
                        service.name
                );

                text.append(
                        ": ৳"
                );

                text.append(
                        String.format(
                                "%,.2f",
                                service.cost
                        )
                );

                text.append(
                        "\n"
                );
            }
        }

        text.append("\n");

        text.append(
                "TOTAL SERVICE COST: ৳"
        );

        text.append(
                String.format(
                        "%,.2f",
                        getServiceCost()
                )
        );

        text.append(
                "\n\n"
        );


        // TICKET

        if (ticketed) {

            text.append(
                    "TICKET SYSTEM\n"
            );

            text.append(
                    "Ticket Price: ৳"
            );

            text.append(
                    String.format(
                            "%,.2f",
                            ticketPrice
                    )
            );

            text.append("\n");

            text.append(
                    "Ticket Quantity: "
            );

            text.append(
                    ticketQuantity
            );

            text.append("\n");

            text.append(
                    "Expected Ticket Revenue: ৳"
            );

            text.append(
                    String.format(
                            "%,.2f",
                            getTicketRevenue()
                    )
            );

            text.append(
                    "\n\n"
            );
        }


        // PAYMENT

        text.append(
                "PAYMENT METHOD: "
        );

        text.append(
                paymentMethod
        );

        text.append(
                "\n\n"
        );


        // TOTAL

        text.append(
                "==============================\n"
        );

        text.append(
                "TOTAL EVENT COST: ৳"
        );

        text.append(
                String.format(
                        "%,.2f",
                        getTotalCost()
                )
        );

        text.append(
                "\n=============================="
        );

        return text.toString();
    }


    @Override
    public String toString() {

        return name
                + " | "
                + type
                + " | "
                + date
                + " | "
                + guests
                + " Guests";
    }
}

}

import java.util.Scanner;

public class HospitalBooking {

    static Scanner sc = new Scanner(System.in);

    static String[] patients = new String[10];
    static String[] doctors = {
        "Dr. Ananya - Cardiologist",
        "Dr. Rahul - Dermatologist",
        "Dr. Priya - Pediatrician",
        "Dr. Arjun - Orthopedic"
    };

    static String[] slots = {
        "10:00 AM",
        "11:00 AM",
        "12:00 PM",
        "2:00 PM"
    };

    static boolean[] booked = new boolean[slots.length];
    static int patientCount = 0;

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n===== HOSPITAL APPOINTMENT SYSTEM =====");
            System.out.println("1. Add Patient");
            System.out.println("2. View Doctors");
            System.out.println("3. View Available Slots");
            System.out.println("4. Book Appointment");
            System.out.println("5. View Patients");
            System.out.println("6. Cancel Appointment");
            System.out.println("7. Exit");

            choice = readInt("Enter your choice: ");

            switch (choice) {

                case 1:
                    addPatient();
                    break;

                case 2:
                    viewDoctors();
                    break;

                case 3:
                    viewSlots();
                    break;

                case 4:
                    bookAppointment();
                    break;

                case 5:
                    viewPatients();
                    break;

                case 6:
                    cancelAppointment();
                    break;

                case 7:
                    System.out.println("Thank you!");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 7);
    }

    static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            if (sc.hasNextInt()) {
                return sc.nextInt();
            }
            sc.next();
            System.out.println("Please enter a valid number!");
        }
    }

    static void addPatient() {

        if (patientCount >= patients.length) {
            System.out.println("Patient limit reached!");
            return;
        }

        sc.nextLine();

        System.out.print("Enter patient name: ");
        String name = sc.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("Name cannot be empty!");
            return;
        }

        patients[patientCount] = name;
        patientCount++;

        System.out.println("Patient added successfully!");
    }

    static void viewDoctors() {

        System.out.println("\n--- DOCTORS ---");

        for (int i = 0; i < doctors.length; i++) {
            System.out.println((i + 1) + ". " + doctors[i]);
        }
    }

    static void viewSlots() {

        System.out.println("\n--- AVAILABLE SLOTS ---");

        boolean anyAvailable = false;

        for (int i = 0; i < slots.length; i++) {

            if (!booked[i]) {
                System.out.println((i + 1) + ". " + slots[i]);
                anyAvailable = true;
            }
        }

        if (!anyAvailable) {
            System.out.println("No slots available!");
        }
    }

    static void bookAppointment() {

        if (patientCount == 0) {
            System.out.println("Please add a patient first!");
            return;
        }

        viewPatients();

        int patient = readInt("Select patient number: ");

        if (patient < 1 || patient > patientCount) {
            System.out.println("Invalid patient number!");
            return;
        }

        viewSlots();

        int slot = readInt("Select slot number: ");

        if (slot < 1 || slot > slots.length) {
            System.out.println("Invalid slot number!");
        } else if (booked[slot - 1]) {
            System.out.println("Slot is not available!");
        } else {
            booked[slot - 1] = true;

            System.out.println("Appointment booked successfully!");
            System.out.println("Patient: " + patients[patient - 1]);
            System.out.println("Time: " + slots[slot - 1]);
        }
    }

    static void viewPatients() {

        System.out.println("\n--- PATIENTS ---");

        if (patientCount == 0) {
            System.out.println("No patients added yet!");
            return;
        }

        for (int i = 0; i < patientCount; i++) {
            System.out.println((i + 1) + ". " + patients[i]);
        }
    }

    static void cancelAppointment() {

        viewSlots();

        int slot = readInt("Enter slot number to cancel: ");

        if (slot < 1 || slot > slots.length) {
            System.out.println("Invalid slot number!");
        } else if (!booked[slot - 1]) {
            System.out.println("No appointment found!");
        } else {
            booked[slot - 1] = false;
            System.out.println("Appointment cancelled!");
        }
    }
}

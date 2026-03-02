import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class Student {
    private String name;
    private ArrayList<Double> grades;

    public Student(String name) {
        this.name = name;
        this.grades = new ArrayList<>();
    }

    public void addGrade(double grade) {
        grades.add(grade);
    }

    public String getName() {
        return name;
    }

    public ArrayList<Double> getGrades() {
        return grades;
    }

    public double getAverage() {
        if (grades.isEmpty()) return 0.0;

        double sum = 0;
        for (double grade : grades) {
            sum += grade;
        }
        return sum / grades.size();
    }

    public double getHighest() {
        if (grades.isEmpty()) return 0.0;

        double highest = grades.get(0);
        for (double grade : grades) {
            if (grade > highest) {
                highest = grade;
            }
        }
        return highest;
    }

    public double getLowest() {
        if (grades.isEmpty()) return 0.0;

        double lowest = grades.get(0);
        for (double grade : grades) {
            if (grade < lowest) {
                lowest = grade;
            }
        }
        return lowest;
    }
}

public class StudentGradeGUI extends JFrame {

    private JTextField nameField;
    private JTextField gradesField;
    private JTextArea reportArea;

    private ArrayList<Student> students;

    public StudentGradeGUI() {
        students = new ArrayList<>();

        setTitle("Student Grade Management System");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        // Top Panel (Input Section)
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Enter Student Details"));

        inputPanel.add(new JLabel("Student Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Grades (comma separated):"));
        gradesField = new JTextField();
        inputPanel.add(gradesField);

        JButton addButton = new JButton("Add Student");
        JButton reportButton = new JButton("Generate Report");

        inputPanel.add(addButton);
        inputPanel.add(reportButton);

        add(inputPanel, BorderLayout.NORTH);

        // Center Panel (Report Section)
        reportArea = new JTextArea();
        reportArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(reportArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Summary Report"));

        add(scrollPane, BorderLayout.CENTER);

        // Button Actions
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });

        reportButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                generateReport();
            }
        });
    }

    private void addStudent() {
        String name = nameField.getText().trim();
        String gradesInput = gradesField.getText().trim();

        if (name.isEmpty() || gradesInput.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter all fields.");
            return;
        }

        Student student = new Student(name);

        try {
            String[] gradesArray = gradesInput.split(",");
            for (String g : gradesArray) {
                double grade = Double.parseDouble(g.trim());
                student.addGrade(grade);
            }

            students.add(student);

            JOptionPane.showMessageDialog(this, "Student added successfully.");

            nameField.setText("");
            gradesField.setText("");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid grade format. Enter numbers separated by commas.");
        }
    }

    private void generateReport() {
        reportArea.setText("");

        if (students.isEmpty()) {
            reportArea.append("No student data available.\n");
            return;
        }

        reportArea.append("===== STUDENT SUMMARY REPORT =====\n\n");

        for (Student student : students) {
            reportArea.append("Student Name: " + student.getName() + "\n");
            reportArea.append("Grades: " + student.getGrades() + "\n");
            reportArea.append(String.format("Average: %.2f\n", student.getAverage()));
            reportArea.append(String.format("Highest: %.2f\n", student.getHighest()));
            reportArea.append(String.format("Lowest: %.2f\n", student.getLowest()));
            reportArea.append("--------------------------------------------------\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new StudentGradeGUI().setVisible(true);
            }
        });
    }
}
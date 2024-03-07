
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.JButton.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
 import java.awt.Toolkit;
import java.util.Timer;
import java.util.TimerTask;

public class HealthReminder extends JFrame {

    private double totalCalories = 0.0;
    private Date previousCycleDate;
    private JTextField dateTextField;
    private JTextArea textArea;
    private List<SleepEntry> sleepEntries = new ArrayList<>();
    private List<JCheckBox> todoCheckboxes = new ArrayList<>();
    private List<MoodEntry> moodEntries = new ArrayList<>();
    private List<WaterIntakeEntry> waterIntakeEntries = new ArrayList<>();
    private List<WeightEntry> weightEntries = new ArrayList<>();
 private JScrollPane scrollPane;
    public HealthReminder() {
        setTitle(" Samsung Health App");
       setBackground(Color.BLACK);
        setSize(1600, 1600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
         JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 1, -1, -1));

        JButton calorieButton = createStyledButton("Calorie Intake", Color.BLACK, Color.GREEN);
        JButton menstrualButton = createStyledButton("Menstrual Cycle", Color.BLACK, Color.GREEN);
        JButton todoButton = createStyledButton("To-Do List", Color.BLACK, Color.GREEN);
        JButton bmiButton = createStyledButton("BMI Calculator", Color.BLACK, Color.GREEN);
        JButton sleepTrackerButton = createStyledButton("Sleep Tracker", Color.BLACK, Color.GREEN);
        JButton moodTrackerButton = createStyledButton("Mood Tracker", Color.BLACK, Color.GREEN);
        JButton waterReminderButton = createStyledButton("Water Reminder", Color.BLACK, Color.GREEN);
        JButton weightTrackerButton = createStyledButton("Weight Tracker", Color.BLACK, Color.GREEN);
        weightTrackerButton.addActionListener(e -> openWeightTracker());
     
        

        calorieButton.addActionListener(e -> openCalorieIntakeWindow());
        menstrualButton.addActionListener(e -> openMenstrualApp());
        todoButton.addActionListener(e -> openTODOApp());
        bmiButton.addActionListener(e -> openBMICalculator());
        sleepTrackerButton.addActionListener(e -> openSleepTracker());
        moodTrackerButton.addActionListener(e -> openMoodTracker());
        waterReminderButton.addActionListener(e -> openWaterIntakeReminder());

            panel.add(calorieButton);
            panel.add(menstrualButton);
            panel.add(todoButton);
            panel.add(bmiButton);
            panel.add(sleepTrackerButton);
            panel.add(moodTrackerButton);
            panel.add(waterReminderButton);
            panel.add(weightTrackerButton);
            
          JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        add(scrollPane);
        setLocationRelativeTo(null);
        setVisible(true);

    }

   private JButton createStyledButton(String text, Color backgroundColor, Color textColor) {
    JButton button = new JButton(text);
    button.setBackground(backgroundColor);
    button.setForeground(textColor);
    button.setFont(new Font("Arial", Font.BOLD, 17)); 
    button.setBounds(100, 10, 5, 0);
    button.setFocusPainted(false);
    return button;
}
private void openCalorieIntakeWindow() {
    JFrame calorieFrame = new JFrame("Calorie Intake");
    calorieFrame.setSize(400, 200);

    JLabel label = new JLabel("Enter your calorie intake:");
    JTextField textField = new JTextField();
    JButton saveButton = new JButton("Save");

    JLabel dateLabel = new JLabel("Date:");
    JTextField dateField = new JTextField();
    dateField.setEditable(false);
    dateField.setText(getCurrentDate());

    JLabel timeLabel = new JLabel("Time:");
    JTextField timeField = new JTextField();
    timeField.setEditable(false);
    timeField.setText(getCurrentTime());

    label.setBounds(20, 20, 200, 20);
    textField.setBounds(20, 50, 200, 30);
    saveButton.setBounds(20, 90, 80, 30);

    dateLabel.setBounds(240, 20, 60, 20);
    dateField.setBounds(300, 20, 80, 20);

    timeLabel.setBounds(240, 50, 60, 20);
    timeField.setBounds(300, 50, 80, 20);

    saveButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double calorieIntake = Double.parseDouble(textField.getText());
                totalCalories += calorieIntake;

                // Get current date and time
                String currentDate = getCurrentDate();
                String currentTime = getCurrentTime();

                // Show message with saved calories, current date, and time
                JOptionPane.showMessageDialog(calorieFrame, "Calories saved: " + calorieIntake
                        + "\nTotal Calories: " + totalCalories
                        + "\nDate: " + currentDate
                        + "\nTime: " + currentTime);

                // Update date and time fields with current values
                dateField.setText(currentDate);
                timeField.setText(currentTime);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(calorieFrame, "Please enter a valid number for calories.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    });

    calorieFrame.setLayout(null);
    calorieFrame.add(label);
    calorieFrame.add(textField);
    calorieFrame.add(saveButton);
    calorieFrame.add(dateLabel);
    calorieFrame.add(dateField);
    calorieFrame.add(timeLabel);
    calorieFrame.add(timeField);

    calorieFrame.setLocationRelativeTo(this);
    calorieFrame.setVisible(true);
}



private String getCurrentDate() {
    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    Date currentDate = new Date();
    return dateFormat.format(currentDate);
}


private String getCurrentTime() {
    SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
    Date currentTime = new Date();
    return timeFormat.format(currentTime);
}


    private void openMenstrualApp() {
        JFrame menstrualFrame = new JFrame("Menstrual Cycle");
        menstrualFrame.setSize(400, 300);

        JLabel label = new JLabel("Enter the date of your last menstrual cycle:");

        dateTextField = new JTextField();
        JButton saveButton = new JButton("Save");
        JButton predictButton = new JButton("Predict Next Cycle Date");
        textArea = new JTextArea();

        setLayout(null);
        label.setBounds(20, 20, 300, 20);
        dateTextField.setBounds(20, 50, 150, 30);
        saveButton.setBounds(20, 90, 120, 30);
        predictButton.setBounds(20, 130, 180, 30);
        textArea.setBounds(20, 170, 300, 80);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                    previousCycleDate = dateFormat.parse(dateTextField.getText());

                    textArea.setText("Previous Cycle Date: " + dateFormat.format(previousCycleDate));

                    JOptionPane.showMessageDialog(menstrualFrame, "Previous cycle date saved:\n"
                            + dateFormat.format(previousCycleDate));
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(menstrualFrame, "Invalid date format. Please use MM/dd/yyyy.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        predictButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (previousCycleDate != null) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(previousCycleDate);
                    calendar.add(Calendar.DAY_OF_MONTH, 28); // Assuming a standard 28-day cycle

                    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                    String predictedDate = dateFormat.format(calendar.getTime());

                    textArea.append("\nPredicted Next Cycle Date: " + predictedDate);
                } else {
                    JOptionPane.showMessageDialog(menstrualFrame, "Please save the previous cycle date first.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        menstrualFrame.setLayout(null);
        menstrualFrame.add(label);
        menstrualFrame.add(dateTextField);
        menstrualFrame.add(saveButton);
        menstrualFrame.add(predictButton);
        menstrualFrame.add(textArea);

        menstrualFrame.setLocationRelativeTo(this);
        menstrualFrame.setVisible(true);
    }

    public static void main(String[] args) {
        new HealthReminder();
    }

    private void openTODOApp() {
        JFrame todoFrame = new JFrame("To-Do List");
        todoFrame.setSize(400, 300);

        JTextArea todoTextArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(todoTextArea);
        JButton addButton = new JButton("Add Todo");

        setLayout(null);
        scrollPane.setBounds(20, 20, 340, 200);
        addButton.setBounds(20, 230, 120, 30);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String todo = JOptionPane.showInputDialog(todoFrame, "Enter your todo:");
                if (todo != null && !todo.isEmpty()) {
                    addTodoItem(todo, todoTextArea);
                }
            }
        });

        todoFrame.setLayout(null);
        todoFrame.add(scrollPane);
        todoFrame.add(addButton);

        todoFrame.setLocationRelativeTo(this);
        todoFrame.setVisible(true);
    }

    private void addTodoItem(String todo, JTextArea todoTextArea) {
        JCheckBox checkBox = new JCheckBox(todo);
        todoCheckboxes.add(checkBox);

        checkBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkBox.isSelected()) {
                    
                    todoTextArea.append("Done: " + checkBox.getText() + "\n");
                } else {
                    
                    todoTextArea.append("Not Done: " + checkBox.getText() + "\n");
                }
            }
        });

        setLayout(null);
        checkBox.setBounds(20, 20 + todoCheckboxes.size() * 30, 300, 20);
        todoTextArea.append(checkBox.getText() + "\n");

        add(checkBox);
        revalidate();
        repaint();
    }

    private void openBMICalculator() {
        JFrame bmiFrame = new JFrame("BMI Calculator");
        bmiFrame.setSize(300, 200);

        JLabel heightLabel = new JLabel("Enter height (m):");
        JTextField heightTextField = new JTextField();

        JLabel weightLabel = new JLabel("Enter weight (kg):");
        JTextField weightTextField = new JTextField();

        JButton calculateButton = new JButton("Calculate");
        JLabel resultLabel = new JLabel();

        setLayout(null);
        heightLabel.setBounds(20, 20, 120, 20);
        heightTextField.setBounds(150, 20, 120, 20);
        weightLabel.setBounds(20, 50, 120, 20);
        weightTextField.setBounds(150, 50, 120, 20);
        calculateButton.setBounds(20, 80, 120, 30);
        resultLabel.setBounds(20, 120, 250, 20);

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double height = Double.parseDouble(heightTextField.getText());
                    double weight = Double.parseDouble(weightTextField.getText());

                    double bmi = calculateBMI(height, weight);
                    resultLabel.setText("BMI: " + new DecimalFormat("#.##").format(bmi) + " - " + getBMICategory(bmi));
                } catch (NumberFormatException ex) {
                    resultLabel.setText("Invalid input. Please enter valid numbers.");
                }
            }
        });

        bmiFrame.setLayout(null);
        bmiFrame.add(heightLabel);
        bmiFrame.add(heightTextField);
        bmiFrame.add(weightLabel);
        bmiFrame.add(weightTextField);
        bmiFrame.add(calculateButton);
        bmiFrame.add(resultLabel);

        bmiFrame.setLocationRelativeTo(this);
        bmiFrame.setVisible(true);
    }

    private double calculateBMI(double height, double weight) {
        return weight / (height * height);
    }

    private String getBMICategory(double bmi) {
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi < 24.9) {
            return "Normal Weight";
        } else if (bmi < 29.9) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }

    private void openSleepTracker() {
        JFrame sleepTrackerFrame = new JFrame("Sleep Tracker");
        sleepTrackerFrame.setSize(400, 300);

        JLabel dateLabel = new JLabel("Date:");
        JTextField dateTextField = new JTextField();
        JLabel durationLabel = new JLabel("Duration (hours):");
        JTextField durationTextField = new JTextField();
        JButton logButton = new JButton("Log Sleep");
        JTextArea sleepLogTextArea = new JTextArea();

        setLayout(null);
        dateLabel.setBounds(20, 20, 80, 20);
        dateTextField.setBounds(120, 20, 120, 20);
        durationLabel.setBounds(20, 50, 120, 20);
        durationTextField.setBounds(120, 50, 120, 20);
        logButton.setBounds(20, 80, 120, 30);
        sleepLogTextArea.setBounds(20, 120, 340, 120);

        logButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                    Date date = dateFormat.parse(dateTextField.getText());
                    double duration = Double.parseDouble(durationTextField.getText());

                    SleepEntry entry = new SleepEntry(date, duration);
                    sleepEntries.add(entry);

                    updateSleepLog(sleepLogTextArea);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(sleepTrackerFrame, "Invalid input. Please enter valid values.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        sleepTrackerFrame.setLayout(null);
        sleepTrackerFrame.add(dateLabel);
        sleepTrackerFrame.add(dateTextField);
        sleepTrackerFrame.add(durationLabel);
        sleepTrackerFrame.add(durationTextField);
        sleepTrackerFrame.add(logButton);
        sleepTrackerFrame.add(sleepLogTextArea);

        sleepTrackerFrame.setLocationRelativeTo(this);
        sleepTrackerFrame.setVisible(true);
    }

    private void updateSleepLog(JTextArea sleepLogTextArea) {
        sleepLogTextArea.setText("Sleep Log:\n");

        for (SleepEntry entry : sleepEntries) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            String formattedDate = dateFormat.format(entry.getDate());

            sleepLogTextArea.append(formattedDate + ": " + entry.getDuration() + " hours\n");
        }
    }

    private static class SleepEntry {

        private Date date;
        private double duration;

        public SleepEntry(Date date, double duration) {
            this.date = date;
            this.duration = duration;
        }

        public Date getDate() {
            return date;
        }

        public double getDuration() {
            return duration;
        }
    }

    private void openMoodTracker() {
        JFrame moodTrackerFrame = new JFrame("Mood Tracker");
        moodTrackerFrame.setSize(400, 300);

        JLabel dateLabel = new JLabel("Date:");
        JTextField dateTextField = new JTextField();
        JLabel moodLabel = new JLabel("Mood:");
        JComboBox<String> moodComboBox = new JComboBox<>(new String[]{"Happy", "Sad", "Neutral", "Excited", "Stressed"});
        JButton logButton = new JButton("Log Mood");
        JTextArea moodLogTextArea = new JTextArea();

        setLayout(null);
        dateLabel.setBounds(20, 20, 80, 20);
        dateTextField.setBounds(120, 20, 120, 20);
        moodLabel.setBounds(20, 50, 80, 20);
        moodComboBox.setBounds(120, 50, 120, 20);
        logButton.setBounds(20, 80, 120, 30);
        moodLogTextArea.setBounds(20, 120, 340, 120);

        logButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                    Date date = dateFormat.parse(dateTextField.getText());
                    String selectedMood = (String) moodComboBox.getSelectedItem();

                    MoodEntry entry = new MoodEntry(date, selectedMood);
                    moodEntries.add(entry);

                    updateMoodLog(moodLogTextArea);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(moodTrackerFrame, "Invalid input. Please enter valid values.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        moodTrackerFrame.setLayout(null);
        moodTrackerFrame.add(dateLabel);
        moodTrackerFrame.add(dateTextField);
        moodTrackerFrame.add(moodLabel);
        moodTrackerFrame.add(moodComboBox);
        moodTrackerFrame.add(logButton);
        moodTrackerFrame.add(moodLogTextArea);

        moodTrackerFrame.setLocationRelativeTo(this);
        moodTrackerFrame.setVisible(true);
    }

    private void updateMoodLog(JTextArea moodLogTextArea) {
        moodLogTextArea.setText("Mood Log:\n");

        for (MoodEntry entry : moodEntries) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            String formattedDate = dateFormat.format(entry.getDate());

            moodLogTextArea.append(formattedDate + ": " + entry.getMood() + "\n");
        }
    }

    private static class MoodEntry {

        private Date date;
        private String mood;

        public MoodEntry(Date date, String mood) {
            this.date = date;
            this.mood = mood;
        }

        public Date getDate() {
            return date;
        }

        public String getMood() {
            return mood;
        }
    }

    private void openWaterIntakeReminder() {
        JFrame waterReminderFrame = new JFrame("Water Intake Reminder");
        waterReminderFrame.setSize(400, 300);

        JLabel intervalLabel = new JLabel("Reminder Interval (minutes):");
        JTextField intervalTextField = new JTextField();
        JButton startButton = new JButton("Start Reminder");
        JButton stopButton = new JButton("Stop Reminder");
        JTextArea waterIntakeLogTextArea = new JTextArea();

        setLayout(null);
        intervalLabel.setBounds(20, 20, 180, 20);
        intervalTextField.setBounds(200, 20, 120, 20);
        startButton.setBounds(20, 50, 150, 30);
        stopButton.setBounds(200, 50, 150, 30);
        waterIntakeLogTextArea.setBounds(20, 90, 340, 160);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int interval;
                try {
                    interval = Integer.parseInt(intervalTextField.getText());
                    startWaterIntakeReminder(interval, waterIntakeLogTextArea);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(waterReminderFrame, "Invalid interval. Please enter a valid number.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopWaterIntakeReminder();
            }
        });

        waterReminderFrame.setLayout(null);
        waterReminderFrame.add(intervalLabel);
        waterReminderFrame.add(intervalTextField);
        waterReminderFrame.add(startButton);
        waterReminderFrame.add(stopButton);
        waterReminderFrame.add(waterIntakeLogTextArea);

        waterReminderFrame.setLocationRelativeTo(this);
        waterReminderFrame.setVisible(true);
    }

   

private void startWaterIntakeReminder(int interval, JTextArea waterIntakeLogTextArea) {
    Timer timer = new Timer();
    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

    TimerTask reminderTask = new TimerTask() {
        @Override
        public void run() {
            Date reminderTime = new Date();
            WaterIntakeEntry entry = new WaterIntakeEntry(reminderTime);
            waterIntakeEntries.add(entry);

            waterIntakeLogTextArea.append("Reminder at " + dateFormat.format(reminderTime) + "\n");

            Toolkit.getDefaultToolkit().getScreenSize();
        }
    };

}

    private void stopWaterIntakeReminder()
    {
        
    }

    private static class WaterIntakeEntry {

        private Date reminderTime;

        public WaterIntakeEntry(Date reminderTime) {
            this.reminderTime = reminderTime;
        }

        public Date getReminderTime() {
            return reminderTime;
        }
    }

    private void openWeightTracker() {
        JFrame weightTrackerFrame = new JFrame("Weight Tracker");
        weightTrackerFrame.setSize(400, 300);

        JLabel dateLabel = new JLabel("Date:");
        JTextField dateTextField = new JTextField();
        JLabel weightLabel = new JLabel("Weight (kg):");
        JTextField weightTextField = new JTextField();
        JButton logButton = new JButton("Log Weight");
        JTextArea weightLogTextArea = new JTextArea();

        dateLabel.setBounds(20, 20, 80, 20);
        dateTextField.setBounds(120, 20, 120, 20);
        weightLabel.setBounds(20, 50, 80, 20);
        weightTextField.setBounds(120, 50, 120, 20);
        logButton.setBounds(20, 80, 120, 30);
        weightLogTextArea.setBounds(20, 120, 340, 120);

        logButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                    Date date = dateFormat.parse(dateTextField.getText());
                    double weight = Double.parseDouble(weightTextField.getText());

                    WeightEntry entry = new WeightEntry(date, weight);
                    weightEntries.add(entry);

                    updateWeightLog(weightLogTextArea);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(weightTrackerFrame, "Invalid input. Please enter valid values.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        weightTrackerFrame.setLayout(null);
        weightTrackerFrame.add(dateLabel);
        weightTrackerFrame.add(dateTextField);
        weightTrackerFrame.add(weightLabel);
        weightTrackerFrame.add(weightTextField);
        weightTrackerFrame.add(logButton);
        weightTrackerFrame.add(weightLogTextArea);

        weightTrackerFrame.setLocationRelativeTo(this);
        weightTrackerFrame.setVisible(true);
    }

    private void updateWeightLog(JTextArea weightLogTextArea) {
        weightLogTextArea.setText("Weight Log:\n");

        for (WeightEntry entry : weightEntries) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            String formattedDate = dateFormat.format(entry.getDate());

            weightLogTextArea.append(formattedDate + ": " + entry.getWeight() + " kg\n");
        }
    }

    private static class WeightEntry {

        private Date date;
        private double weight;

        public WeightEntry(Date date, double weight) {
            this.date = date;
            this.weight = weight;
        }

        public Date getDate() {
            return date;
        }

        public double getWeight() {
            return weight;
        }
    }

}

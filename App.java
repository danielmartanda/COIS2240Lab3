import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class App extends JFrame implements ActionListener {
    //Declaring a JTextArea
    private JTextArea textArea;

    //App Class Constructor.
    public App() {
        //JFrame settings
        //Setting Title
        setTitle("Daniel's CSV Loader Application");
        //Setting Dimensions of Window
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        //App().setBackground(Color.GRAY);

        //1-Create a a new button named loadButton. The text in the Button should say LoadCSV
        JButton loadButton = new JButton("LoadCSV");

        //2-After that initialize a new TextArea (we already declared a textArea on Line 11), this is the area where we will display our CSV.
        textArea = new JTextArea();

        //3-Set the new textArea to be uneditable (e.g., .setEditable(false))
        textArea.setEditable(false);

        //4-Create a JScrollPane within the text area, so that we can scroll up and down.
        JScrollPane scrollPane = new JScrollPane(textArea);

        //5-Add an action listener to the button that calls the load CSV function (below)
        loadButton.addActionListener(this);
        //Additional code included outside of this scope

        //6-Play around with coloring and styling to make your application look more professional
        loadButton.setFont(new Font("Roboto", Font.BOLD, 25));
        loadButton.setBackground(Color.GRAY);
        textArea.setBackground(Color.LIGHT_GRAY);

        add(loadButton, BorderLayout.NORTH);        
        add(scrollPane, BorderLayout.CENTER);
    }

    //5 continued
    @Override
    public void actionPerformed(ActionEvent e) {
        loadCsv("/Users/danielm/Downloads/Lab3CSVFile.csv");
    }

    private void loadCsv(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            StringBuilder content = new StringBuilder();
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
            textArea.setText(content.toString());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Failed to load the CSV file.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new App().setVisible(true);
            }
        });
    }
}

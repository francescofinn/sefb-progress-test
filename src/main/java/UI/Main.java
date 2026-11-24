package UI;
import assessments;
import assessments.AssessmentFeed;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String args[]) {
        SwingUtilities.invokeLater(() -> {
            // Instantiate JFrame
            JFrame frame = new JFrame("AI-Aware Software Engineering Assessment System");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(900, 500);
            frame.setLocationRelativeTo(null);

            int rowNum = AssessmentFeed.getCIDs().size();

            // Instantiate grid
            JPanel grid = new JPanel(new GridLayout(rowNum,1, 10, 10));

        });
    }
}

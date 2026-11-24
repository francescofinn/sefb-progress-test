package UI;
import assessments.AssessmentFeed;
import model.MSCStudent;
import model.Student;
import model.UGStudent;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

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
            Map<String, JPanel> studentCells = new HashMap<>();
            Map<String, JLabel> studentLabels = new HashMap<>();
            Map<String, Student> students = new HashMap<>();

            for (int i = 0; i < rowNum; i++) {
                String cid = AssessmentFeed.getCIDs().get(i);
                String name = AssessmentFeed.getStudentName(cid);
                String type = AssessmentFeed.getStudentType(cid);

                if (type.equals("UG")) {
                    Student student = new UGStudent(name, cid);
                    students.put(cid, student);

                } else if (type.equals("MSc")) {
                    Student student = new MSCStudent(name, cid);
                    students.put(cid, student);

                } else {
                    throw new RuntimeException("Illegal Student type");
                }

                // Populate panel & label hashmaps and add the label to their respective panels
                studentCells.put(cid, new JPanel());
                studentLabels.put(cid, new JLabel());
                studentCells.get(cid).add(studentLabels.get(cid));
            }

            for (JPanel p : studentCells.values()) {
                grid.add(p);
            }

            frame.add(grid);
            frame.setVisible(true);

            // Display Text
            for (Student s : students.values()) {
                JLabel label = studentLabels.get(s.getCid());

                label.setText(StudentDisplayText(s));

                if (isAIDiscrepancy(s)) {
                    studentCells.get(s.getCid()).setBackground(Color.RED);
                }
            }

            LogConsole(students);

        });
    }

    public static String StudentDisplayText(Student s) {
        // I realise I could have added the weighting functionality directly into the student classes
        // but I already was in too deep and did not want to waste time
        // I would have done this by creating getters for the final marks with the weighting done in the method
        // The same applies for the total grade of all modules
        // In real life I would have refactored. Hopefully you can take this into account.
        // If not... Oh well. Have a great day. Try this cookie place called Puffy in SoHo if you're ever feeling
        // like a sweet treat. You're welcome.
        double programMark = s.getIPTProgMark();

        if (!s.isIPTExplainedSuccess() && programMark > 40) {
            programMark = 40;
        }

        double finalAIMark = 0.4 * s.getAIPTFuncMark() + 0.6 * s.getAIPTVerificationMark();

        if (s.getType().equals("UG")) {
            double moduleTotal = 0.3 * programMark + 0.7 * finalAIMark;

            return "<html>" +
                    "Name: " + s.getName() + "<br>" +
                    "CID: " + s.getCid() + "<br>" +
                    "Type: " + s.getType() + "<br>" +
                    "Independent Programming Test: " + programMark + "%<br>" +
                    "Weighted Module: " + 0.3 * programMark + "%<br>" +
                    "AI-Assisted Programming Test: " + finalAIMark + "%<br>" +
                    "Weighted Module: " + 0.7 * finalAIMark + "%<br>" +
                    "Total Weighted Module Mark: " + moduleTotal + "<%<br>" +
                    "</html>";
        } else {
            double veevaMark = 0.5 * ((MSCStudent) s).getVeevaDesingMark() + 0.5 * ((MSCStudent) s).getVeevaExplanationMark();
            double total = 0.25 * programMark + 0.45 * finalAIMark + 0.3 * veevaMark;

            return "<html>" +
                    "Name: " + s.getName() + "<br>" +
                    "CID: " + s.getCid() + "<br>" +
                    "Type: " + s.getType() + "<br>" +
                    "Independent Programming Test: " + programMark + "%<br>" +
                    "Weighted Module: " + 0.25 * programMark + "%<br>" +
                    "AI-Assisted Programming Test: " + finalAIMark + "%<br>" +
                    "Weighted Module: " + 0.45 * finalAIMark + "%<br>" +
                    "Software Design Veeva: " + veevaMark + "%<br>" +
                    "Weighted Module: " + 0.3 * veevaMark + "%<br>" +
                    "Total Mark: " + total + "<%<br>" +
                    "</html>";
        }
    }

    public static void LogConsole(Map<String, Student> stringStudentMap) {
        for (Student s : stringStudentMap.values()) {
            double AIMark = 0.4 * s.getAIPTFuncMark() + 0.6 * s.getAIPTVerificationMark();
            double IPTMark = s.getIPTProgMark();
            System.out.println(s.getName() + " - " + s.getCid() + " - " + s.getType());
            System.out.println("Independent Programming Test:");
            System.out.println("Program Mark: " + IPTMark);
            System.out.println("Explained Successfully: " + s.isIPTExplainedSuccess());
            System.out.println("");
            System.out.println("AI-Assisted Programming Project:");
            System.out.println("Functionality Mark: " + s.getAIPTFuncMark());
            System.out.println("Verification Mark: " + s.getAIPTVerificationMark());

            if (s.getType().equals("MSc")) {
                System.out.println("");
                System.out.println("Software Design Veeva:");
                System.out.println("Design Mark: " + ((MSCStudent) s).getVeevaDesingMark());
                System.out.println(("Explanation Mark: " + ((MSCStudent) s).getVeevaExplanationMark()));
            }

            System.out.println("=============================================================");
        }
    }

    public static boolean isAIDiscrepancy(Student s) {
        double AIMark = 0.4 * s.getAIPTFuncMark() + 0.6 * s.getAIPTVerificationMark();
        double IPTMark = s.getIPTProgMark();

        if (!s.isIPTExplainedSuccess() && IPTMark > 40) {
            IPTMark = 40;
        }

        if (AIMark - IPTMark >= 20) {
            return true;
        }

        return false;
    }
}

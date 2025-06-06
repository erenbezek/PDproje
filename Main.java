import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Java Syntax Highlighter");
            CodeEditor editor = new CodeEditor(frame);

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.add(new JScrollPane(editor));
            frame.setVisible(true);
        });
    }
}

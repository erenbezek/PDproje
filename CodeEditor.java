import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.util.List;

public class CodeEditor extends JTextPane {
    private final Lexer lexer = new Lexer();
    private final StyledDocument doc;

    private final Style keywordStyle;
    private final Style numberStyle;
    private final Style identifierStyle;
    private final Style operatorStyle;
    private final Style commentStyle;
    private final Style separatorStyle;
    private final Style errorStyle;

    public CodeEditor(JFrame frame) {
        doc = getStyledDocument();

        keywordStyle = doc.addStyle("Keyword", null);
        StyleConstants.setForeground(keywordStyle, Color.BLUE);
        StyleConstants.setBold(keywordStyle, true);

        numberStyle = doc.addStyle("Number", null);
        StyleConstants.setForeground(numberStyle, new Color(138, 43, 226));

        identifierStyle = doc.addStyle("Identifier", null);
        StyleConstants.setForeground(identifierStyle, Color.BLACK);

        operatorStyle = doc.addStyle("Operator", null);
        StyleConstants.setForeground(operatorStyle, Color.RED);

        commentStyle = doc.addStyle("Comment", null);
        StyleConstants.setForeground(commentStyle, new Color(0, 128, 0));

        separatorStyle = doc.addStyle("Separator", null);
        StyleConstants.setForeground(separatorStyle, Color.DARK_GRAY);

        errorStyle = doc.addStyle("Error", null);
        StyleConstants.setForeground(errorStyle, Color.RED);
        StyleConstants.setBold(errorStyle, true);

        getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) { highlight(frame); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { highlight(frame); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) {}
        });

        setFont(new Font("Consolas", Font.PLAIN, 16));
    }

    private void highlight(JFrame frame) {
        SwingUtilities.invokeLater(() -> {
            String text = getText();
            List<Token> tokens = lexer.analyze(text);

            doc.setCharacterAttributes(0, text.length(), identifierStyle, true);

            int offset = 0;
            for (Token token : tokens) {
                int start = text.indexOf(token.value, offset);
                if (start < 0) continue;
                Style style = switch (token.type) {
                    case KEYWORD -> keywordStyle;
                    case NUMBER -> numberStyle;
                    case IDENTIFIER -> identifierStyle;
                    case OPERATOR -> operatorStyle;
                    case COMMENT -> commentStyle;
                    case SEPARATOR -> separatorStyle;
                    case UNKNOWN -> errorStyle;
                };
                doc.setCharacterAttributes(start, token.value.length(), style, true);
                offset = start + token.value.length();
            }

            Parser parser = new Parser(tokens);
            boolean valid = parser.parse();
            frame.setTitle(valid ? "✅ Kod Geçerli" : "❌ Hata Var");
        });
    }
}

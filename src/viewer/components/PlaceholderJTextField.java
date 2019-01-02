package viewer.components;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.Document;

public class PlaceholderJTextField extends JTextField {

	private String placeholder;    

    public PlaceholderJTextField() {
    }

    public PlaceholderJTextField(final Document pDoc, final String pText, final int pColumns) {
        super(pDoc, pText, pColumns);
    }

    public PlaceholderJTextField(final int pColumns) {
        super(pColumns);
    }

    public PlaceholderJTextField(final String pText) {
        super(pText);
    }

    public PlaceholderJTextField(final String pText, final int pColumns) {
        super(pText, pColumns);
    }

    public String getPlaceholder() {
        return placeholder;
    }

    @Override
    protected void paintComponent(final Graphics pG) {
        super.paintComponent(pG);

        if (placeholder == null || placeholder.length() == 0 || getText().length() > 0) {
            return;
        }

        final Graphics2D g = (Graphics2D) pG;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(getDisabledTextColor());
        g.drawString(placeholder, getInsets().left, pG.getFontMetrics().getMaxAscent() + getInsets().top);
    } // paintComponent

    public void setPlaceholder(final String s) {
        placeholder = s;
    } // setPlaceholder
    
    /* USEAGE SET
		PlaceholderJTextField idText = new PlaceholderJTextField("");
        idText.setColumns(20);
        idText.setPlaceholder("All your base are belong to us!");
        Font f = idText.getFont();
        idText.setFont(new Font(f.getName(), f.getStyle(), 30));
        JOptionPane.showMessageDialog(null, idText);
     */
}
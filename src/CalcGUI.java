/*
https://www3.ntu.edu.sg/home/ehchua/programming/java/j4a_gui.html
 */
import java.awt.*;
import java.awt.event.*;

public class CalcGUI extends Frame {
    private Button[] btnNumbers;
    private Button btnEq, btnAdd, btnSub, btnMult, btnDiv, btnClear;
    private TextField textDisp;
    float tempA = 0;
    String action;

    //Handles all GUI
    public CalcGUI () {

        Panel panelDisplay = new Panel(new FlowLayout());
        textDisp = new TextField("", 20);
        panelDisplay.add(textDisp);
        textDisp.setEditable(false);

        Panel panelButtons = new Panel(new GridLayout(4, 3));
        btnNumbers = new Button[10];
        for (int i = 1; i <= 9; i ++) {

            btnNumbers[i] = new Button(String.valueOf(i));
            panelButtons.add(btnNumbers[i]);
            if (i == 3) {
                btnAdd = new Button("+"); panelButtons.add(btnAdd);
            } else if (i == 6) {
                btnSub = new Button("-"); panelButtons.add(btnSub);
            } else if (i == 9) {
                btnMult = new Button("*"); panelButtons.add(btnMult);
            }
        }

        btnClear = new Button("C");
        panelButtons.add(btnClear);

        btnNumbers[0] = new Button("0");
        panelButtons.add(btnNumbers[0]);

        btnEq = new Button("=");
        panelButtons.add(btnEq);

        btnDiv = new Button("/");
        panelButtons.add(btnDiv);

        // Allocate an instance of the "named" inner class BtnListener.
        BtnListener listener = new BtnListener();

        for (int i = 0; i <= 9; i++) {
            btnNumbers[i].addActionListener(listener);
        }
        btnDiv.addActionListener(listener); btnMult.addActionListener(listener); btnSub.addActionListener(listener);
        btnAdd.addActionListener(listener); btnClear.addActionListener(listener); btnEq.addActionListener(listener);

        closeWindow();

        setLayout(new BorderLayout());
        add(panelDisplay, BorderLayout.NORTH);
        add(panelButtons, BorderLayout.CENTER);

        setTitle("Calculator");
        setSize(200, 300);
        setVisible(true);
    }

    //Performs appropiate action when any Button is clicked
    private class BtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt) {
            String btnLabel = evt.getActionCommand();
            // switch used for efficiency
            switch (btnLabel) {
                case "0":
                case "1":
                case "2":
                case "3":
                case "4":
                case "5":
                case "6":
                case "7":
                case "8":
                case "9":
                    String tempText = textDisp.getText().toString();
                    textDisp.setText(tempText + btnLabel);
                    break;
                case "+":
                case "-":
                case "*":
                case "/":
                    //saves the first number on the screen and action to be performed
                    if(tempA == 0) {
                        tempA = Float.parseFloat(textDisp.getText());
                        action = btnLabel; textDisp.setText("");
                        break;
                    } else {
                        //functionality included to allow continuous calculations without having to press "="
                        float ans = getAns(tempA, Integer.parseInt(textDisp.getText()), action);
                        tempA = ans; action = btnLabel; textDisp.setText("");
                        break;
                    }
                case "=":
                    float ans = getAns(tempA, Integer.parseInt(textDisp.getText()), action);
                    textDisp.setText(String.valueOf(ans));
                    break;
                //clears everything stored and everything on the screen
                case "C":
                    tempA = 0; textDisp.setText(""); break;
            }
        }
    }

    private float getAns(float a, int b, String operation) {
        switch (operation) {
            case "+":
                return addition(a, b);
            case "-":
                return subtraction(a, b);
            case "*":
                return multiplication(a, b);
            case "/":
                return division(a, b);
        }
        return 0;
    }
    private float addition(float a, float b) {
        textDisp.setText(String.valueOf(a + b));
        return a + b;
    }

    private float subtraction(float a, int b) {
        textDisp.setText(String.valueOf(a - b));
        return a - b;
    }

    private float multiplication(float a, int b) {
        textDisp.setText(String.valueOf(a * b));
        return a * b;
    }

    private float division(float a , int b) {
        textDisp.setText(String.valueOf(a / b));
        return a / b;
    }

    public static void main(String[] args) {
        new CalcGUI();
    }

    private void closeWindow() {
        addWindowListener(new WindowListener() {
            @Override
            public void windowClosing(WindowEvent evt) {
                System.exit(0);  // terminate the program
            }
            // Need to provide an empty body for compilation
            @Override public void windowOpened(WindowEvent evt) { }
            @Override public void windowClosed(WindowEvent evt) { }
            @Override public void windowIconified(WindowEvent evt) { }
            @Override public void windowDeiconified(WindowEvent evt) { }
            @Override public void windowActivated(WindowEvent evt) { }
            @Override public void windowDeactivated(WindowEvent evt) { }
        });
    }

}

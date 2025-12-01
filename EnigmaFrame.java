import javax.swing.*;
import java.awt.*;
import java.awt.event.*;




public class EnigmaFrame extends JFrame {


    private JTextArea inp;
    private JTextArea outp;
    private JComboBox<String> inR;
    private JComboBox<String> midR;
    private JComboBox<String> outR;
    private JTextField initialP;
    JButton encrypt = new JButton("Encrypt");
    JButton decrypt = new JButton("Decrypt");

    private final String[] numList = {"1", "2", "3", "4", "5"};

    public EnigmaFrame() {


        super();
        setTitle("Enigma GUI");


        JPanel upperPanel = new JPanel(new FlowLayout());

        inR  = new JComboBox<String>(numList);
        midR = new JComboBox<String>(numList);
        outR  = new JComboBox<String>(numList);
        initialP = new JTextField(5); //reasonable column space for Initial Position box


        upperPanel.add(new JLabel("Inner")); //using FlowLayout, added labels and buttons in order so that labels appear next to their buttons
        upperPanel.add(inR);
        upperPanel.add(new JLabel("Middle"));
        upperPanel.add(midR);
        upperPanel.add(new JLabel("Out"));
        upperPanel.add(outR);
        upperPanel.add(new JLabel("Initial Positions"));
        upperPanel.add(initialP);
        upperPanel.add(encrypt);
        upperPanel.add(decrypt);


        inp = new JTextArea(5, 25); //Gave column space to be a wide amount
        outp = new JTextArea(5, 25);
        outp.setEditable(false); //sets result to be non-editable

        //panel for text
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BorderLayout());

        //individual panels within the text panel
        JPanel inPanel = new JPanel();
        inPanel.setLayout(new BorderLayout());
        inPanel.add(new JLabel("Input"), BorderLayout.WEST);
        inPanel.add(inp, BorderLayout.CENTER);
        JPanel outPanel = new JPanel();
        outPanel.setLayout(new BorderLayout());
        outPanel.add(new JLabel("Output"), BorderLayout.WEST);
        outPanel.add(outp, BorderLayout.CENTER);
        //a panel created to add space between the input and the output JTextAreas
        JPanel spacePanel = new JPanel();
        spacePanel.setPreferredSize(new Dimension(0, 20));

        //input and space area
        JPanel top = new JPanel(new BorderLayout());
        top.add(inPanel, BorderLayout.NORTH);
        top.add(spacePanel, BorderLayout.SOUTH);
        //output panel
        JPanel bottom = new JPanel(new BorderLayout());
        bottom.add(outPanel, BorderLayout.NORTH);

        //combines the input/output section panels together
        textPanel.add(top, BorderLayout.NORTH);
        textPanel.add(bottom, BorderLayout.SOUTH);

        //adds all the panels to the jframe
        this.setLayout(new BorderLayout());
        this.add(upperPanel, BorderLayout.NORTH);
        this.add(textPanel, BorderLayout.SOUTH);



        //encrypt and decrypt buttons change action depending on which one is selected
        encrypt.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) {
                EnigmaRunningMethod(true);

            }
        }

    );

        decrypt.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) {
                EnigmaRunningMethod(false);

            }
        }

    );

        this.pack();
        this.setLocation(100, 100);


    }

    private void EnigmaRunningMethod(boolean doEncrypt) {

    String in  = (String) inR.getSelectedItem(); //found "getSelectedItem()" command online 
    String mid = (String) midR.getSelectedItem();
    String out  = (String) outR.getSelectedItem();


    String TextRetriever = initialP.getText();
    String msg = inp.getText();

    // build args array for Comms.run
    String[] args = new String[6];


    args[0] = in;
    args[1] = mid;
    args[2] = out;
    args[3] = TextRetriever;

    if (doEncrypt == true) {
        args[4] = "encrypt";
    } 
    else {
        args[4] = "decrypt";
    }



    args[5] = msg;
    String result = Comms.run(args); //found .run command online
    outp.setText(result);


}


}

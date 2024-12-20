package MasterMind;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Random;
import javax.swing.*;
class Game extends JPanel {
    // bblue 0, 153, 204        purple 96, 58, 106        green 0,92,75      pink  239,129,202
    // blue , purple , pink , baby_blue , green
    JFrame frame = new JFrame();
    CButton buttons[][] = new CButton[9][4];
    CButton pins[][] = new CButton[9][4];
    CButton blue,bblue,purple,pink,green;
    JButton undo , guess,reset;
    CButton []Code = new CButton[4];
    JLabel win_or_losep = new JLabel();
    JLabel masterMind = new JLabel("MasterMind, Guess Colors"),invalid = new JLabel("enter valid colors!");
    boolean taked_t[] = {false,false,false,false},taked_g[] ={false,false,false,false};
    char arr[] = {'W','W','W','W'};
    char Code_in_char[] = {'W','W','W','W'};
    char colors[] = {'P','G','B','U','L'};
    int res[] = {0,0};
    int turn = 0;
    public Game(){
        setup_frame_panel();
        setButtons();
        setPins();
        chose_buttons();
        setactions();
        generate_random_guess();
        SetGuess();
        System.out.println(Arrays.toString(Code_in_char));
    }
    char random_color(){
        int x = new Random().nextInt(0,5);
        return colors[x];
    }
    void generate_random_guess(){
        for(int i =0;i<4;i++){
            Code_in_char[i] = random_color();
            if(Code_in_char[i] == 'B'){
                Code[i].setForeground(Color.BLUE.brighter());
            }else if(Code_in_char[i] == 'P'){
                Code[i].setForeground(new Color(239,129,202));
            }else if(Code_in_char[i] == 'U'){
                Code[i].setForeground(new Color(96, 58, 106 ));
            }else if(Code_in_char[i] == 'L'){
                Code[i].setForeground(new Color(0, 153, 204 ));
            }else if(Code_in_char[i] == 'G'){
                Code[i].setForeground(new Color(0,92,75));
            }
        }
    }
    public void setup_frame_panel(){
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(550,720);
        frame.add(this);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        this.setBackground(Color.black);
        this.setLayout(null);
        frame.setTitle("MasterMind");
        add(masterMind);
        masterMind.setBounds(100,40,1000,35);
        masterMind.setForeground(Color.green);
        masterMind.setFont(new Font(Font.SERIF,Font.BOLD,25));
        add(invalid);
        invalid.setBounds(12,580,100,30);
        invalid.setForeground(Color.YELLOW);
        invalid.setFont(new Font(Font.SERIF,Font.ITALIC + Font.BOLD,10));
        invalid.setVisible(false);
        add(win_or_losep);
    }
    void setPins(){
        for(int i = 0;i<9;i++){
            for(int j = 0;j<4;j++){
                pins[i][j] = new CButton();
                pins[i][j].setForeground(Color.white);
                this.add(pins[i][j]);

            }
        }
        int temp = 0;
        for(int i = 0;i<9;i++){
            pins[i][0].setBounds(435,600 + temp,20,20);
            pins[i][1].setBounds(465,600 + temp,20,20);
            pins[i][2].setBounds(435,630 + temp,20,20);
            pins[i][3].setBounds(465,630 + temp,20,20);
            temp -=60;
        }

    }
    void setButtons(){
        for(int i = 0;i<9;i++){
            for(int j = 0;j<4;j++){
                buttons[i][j] = new CButton();
                buttons[i][j].setForeground(Color.white);
                this.add(buttons[i][j]);

            }
        }
        int y = 600;
        for(int i = 0;i<9;i++){
            int x = 120;
            for(int j = 0;j<4;j++){
                buttons[i][j].setBounds(x,y,50,50);
                x+=75;
            }
            y-=60;
        }
        for(int i = 0;i<4;i++){
            Code[i] = new CButton();
            Code[i].setForeground(Color.WHITE);
            add(Code[i]);
            Code[i].setBounds(120+i*75,40,50,50);
        }
    }
    void chose_buttons(){
        pink = new CButton();
        bblue = new CButton();
        green = new CButton();
        blue = new CButton();
        purple = new CButton();
        add(pink);
        add(blue);
        add(purple);
        add(green);
        add(bblue);
        pink.setupButton(new Color(239,129,202),new Color(239,160,220),new Color(239,100,202));
        pink.setBounds(20,130,50,50);
        blue.setupButton(Color.blue.brighter(),new Color(51, 51, 255),Color.BLUE.darker());
        blue.setBounds(20,190,50,50);
        green.setupButton(new Color(20,102,95),new Color(0,120,75),new Color(0,50,75));
        green.setBounds(20,250,50,50);
        purple.setupButton(new Color(106, 8, 106),new Color(96, 70, 106),new Color(96, 30, 106));
        purple.setBounds(20,310,50,50);
        bblue.setupButton(new Color(0, 193, 204 ),new Color(20, 163, 204 ),new Color(0, 103, 204 ));
        bblue.setBounds(20,370,50,50);
        undo = new JButton("Undo");
        guess = new JButton("Guess");
        reset = new JButton("reset");
        add(undo);
        add(reset);
        add(guess);
        undo.setBounds(10,450,80,30);
        undo.setFont(new Font(Font.SERIF,Font.PLAIN,12));
        undo.setBackground(Color.red);
        undo.setForeground(Color.BLACK);
        reset.setBounds(10,490,80,30);
        reset.setFont(new Font(Font.SERIF,Font.PLAIN,12));
        reset.setBackground(Color.red);
        reset.setForeground(Color.BLACK);
        guess.setBounds(10,530,80,50);
        guess.setFont(new Font(Font.SERIF,Font.PLAIN,12));
        guess.setBackground(Color.red);
        guess.setForeground(Color.BLACK);

    }
    void setactions(){
        actions ac = new actions();
        blue.addActionListener(ac);
        green.addActionListener(ac);
        purple.addActionListener(ac);
        pink.addActionListener(ac);
        bblue.addActionListener(ac);
        undo.addActionListener(ac);
        reset.addActionListener(ac);
        guess.addActionListener(ac);
    }
    void SetGuess(){
        for(int i = 0;i<4;i++){
            Code[i].setVisible(false);
        }
    }
    void showGuess(){
        for(int i = 0;i<4;i++){
            Code[i].setVisible(true);
        }
        masterMind.setVisible(false);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.white);
        g.drawLine(420,116,420,650);
        int temp = 60;
        for(int i = 0;i<9;i++){
            g.drawLine(100,116+(temp*i),500,116+(temp*i));
        }
        g.drawLine(0,116,500,116);
        g.drawLine(100,116,100,650);
        g.drawLine(500,116,500,650);
    }
    public static void main(String...arg){
        new Game();
    }
    private class actions implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == pink){
                add_Color('P');
                invalid.setVisible(false);
            }
            if(e.getSource() == blue){
                add_Color('B');
                invalid.setVisible(false);
            }
            if(e.getSource() == green){
                add_Color('G');
                invalid.setVisible(false);
            }
            if(e.getSource() == purple){
                add_Color('U');
                invalid.setVisible(false);
            }
            if(e.getSource() == bblue){
                add_Color('L');
                invalid.setVisible(false);
            }
            if(e.getSource() == undo){
                invalid.setVisible(false);
                Undo();
            }
            if(e.getSource() == reset){
                invalid.setVisible(false);
                Reset();
            }
            if(e.getSource() == guess){
                invalid.setVisible(false);
                if(cont()){
                    if(Check()){
                        win_or_losep.setText("Winner Winner Chicken Dinner");
                        win_or_losep.setBounds(120,0,1000,30);
                        win_or_losep.setFont(new Font(Font.SERIF,Font.BOLD,25));
                        win_or_losep.setForeground(Color.YELLOW);
                        showGuess();
                    }else{
                        show_res();
                        resetTurn();
                    }
                    if(turn == 9){
                        win_or_losep.setText("Loooser , Try again :) ");
                        win_or_losep.setBounds(150,0,1000,30);
                        win_or_losep.setFont(new Font(Font.SERIF,Font.BOLD,25));
                        win_or_losep.setForeground(Color.RED);
                        showGuess();
                    }
                }


            }
        }
    }
    void add_Color(char color){
        for(int i = 0;i<4;i++){
            // puruple u   bblue l
            if(arr[i] == 'W' ){
                arr[i] = color;
                if(color == 'B'){
                    buttons[turn][i].setForeground(Color.BLUE.brighter());
                }else if(color == 'P'){
                    buttons[turn][i].setForeground(new Color(239,129,202));
                }else if(color == 'U'){
                    buttons[turn][i].setForeground(new Color(96, 58, 106 ));
                }else if(color == 'L'){
                    buttons[turn][i].setForeground(new Color(0, 153, 204 ));
                }else if(color == 'G'){
                    buttons[turn][i].setForeground(new Color(0,92,75));
                }
                break;
            }
        }

    }
    void show_res(){
        for(int i = 0;i<res[0];i++){
            pins[turn-1][i].setForeground(Color.GREEN);
        }
        for(int i = res[0];i<res[0]+res[1];i++){
            pins[turn-1][i].setForeground(Color.BLUE);
        }
    }
    void resetTurn(){
        res[0] = 0;
        res[1] = 0;
        for(int i =0;i<4;i++){
            arr[i] = 'W';
            taked_g[i] = false;
            taked_t[i] = false;
        }


    }
    void Undo(){
        for(int i =3;i>=0;i--){
            if(arr[i] != 'W'){
                buttons[turn][i].setForeground(Color.WHITE);
                arr[i] = 'W';
                break;
            }
        }
    }
    void Reset(){
        for(int i = 0;i<4;i++){
            arr[i] = 'W';
            buttons[turn][i].setForeground(Color.white);
        }
    }
    boolean cont(){
        boolean play = true;
        for(int i = 0;i<4;i++){
            if(arr[i] == 'W'){
                play = false;
                break;
            }
        }
        if(play){
            return true;
        }else{
            invalid.setVisible(true);
            return false;
        }
    }
    boolean Check(){
        for(int i =0;i<4;i++){
            if(arr[i] == Code_in_char[i]){
                res[0]++;
                taked_g[i] = true;
                taked_t[i] = true;
            }
        }
        for(int i = 0;i<4;i++){
            for(int j = 0;j<4;j++){
                if((!taked_t[i])){
                    if((!taked_g[j])){
                        if(arr[i] == Code_in_char[j]){
                            res[1]++;
                            taked_t[i] = true;
                            taked_g[j] = true;
                        }
                    }
                }
            }
        }
        if(res[0] == 4){
            return true;
        }
        else{
            turn++;
            return false;
        }

    }
}
class CButton extends JButton{
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.fillOval(0, 0, getWidth(), getHeight());
        setContentAreaFilled(false);
        setOpaque(false);
        setBorderPainted(false);
        setFocusPainted(false);
    }
    void setupButton(Color main,Color enter,Color Clicked){
        this.setForeground(main);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                setForeground(Clicked);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                setForeground(enter);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                setForeground(enter);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                setForeground(main);
            }
        });
    }
}
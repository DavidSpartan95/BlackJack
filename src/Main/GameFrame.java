
package Main;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//region
public class GameFrame extends JFrame implements ActionListener {
       final private JPanel[] dealerHand = new JPanel[21];
       final private JPanel[] playerHand = new JPanel[21];
       private JPanel displayBetAndCash;
       private JPanel logInScreen;
       private JPanel infoScreen;
       final private JLabel[] card = new JLabel[53];
       private JTextField usernameInputBox;
       private JTextField moneyInputBox;
       final private JPanel backgroundColor = new JPanel();
       private JButton newGameButton;
       private JButton quitGameButton;
       private JButton hitButton;
       private JButton stayButton;
       private JButton betButton;
       private JButton singInButton;
       private JButton twoDollarButton;
       private JButton tenDollarButton;
       private JButton fiftyDollarButton;
       private JButton oneHundredDollarButton;
       private JButton twoHundredFiftyDollarButton;
       private JButton doubleDown;
       private ImageIcon[] icon = new ImageIcon[53];
       private int dealersHandNum = 2;
       private int dealerScore = 0;
       private int playerHandNum = 2;
       private int playerScore = 0;
       private int dollar;
       private int currentBet = 0;
       private int topCard = 1;
       private int playerAceNum = 0;
       private int dealerAceNum = 0;
       private boolean signInFail = false;
       private boolean dealerBust = false;
       private boolean playerBust = false;
       private boolean doubleDownIsPressed = false;
       private String username;
       private String infoScreenText;
       final private Deck deck = new Deck();
       final private CalculateScore calc = new CalculateScore();
//  endregion
     GameFrame() {
            signInScreen();
            backgroundColor.setBackground(new Color(21,71,51));
            backgroundColor.setSize(800,600);
            backgroundColor.setLocation(0,0);

            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setLayout(null);
            this.setSize(800,600);
            this.setResizable(false);
            this.setVisible(true);
            this.add(backgroundColor);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
            if(e.getSource()== singInButton){
                username = usernameInputBox.getText();
                String dollarTemp; //Convert this to int dollar later
                boolean correctInput = false;
                dollarTemp = moneyInputBox.getText();
                try {
                    dollar = Integer.parseInt(dollarTemp);
                    System.out.println("$" + dollar);
                    correctInput = true;
                }catch(NumberFormatException exception){
                    System.out.println("Did not put in valid number");
                }
                if(dollar <=1000 && dollar >= 2 && username.length()<=16 && correctInput){
                    System.out.println("Signed in");
                    this.remove(backgroundColor);
                    this.remove(singInButton);
                    this.remove(logInScreen);
                    infoScreenText = "Welcome to the table " + username + "! click New Game to start";
                    displayInfoScreen();
                    displayBetAndCash();

                    buttons();
                    this.add(backgroundColor);
                    SwingUtilities.updateComponentTreeUI(this);

                }else{
                    signInFail = true;
                    this.remove(backgroundColor);
                    this.remove(singInButton);
                    this.remove(logInScreen);
                    signInScreen();
                    this.add(backgroundColor);
                    SwingUtilities.updateComponentTreeUI(this);
                }

            }
            if(e.getSource()== quitGameButton){
                this.dispose();
                }
            if(e.getSource()== newGameButton){
                if(playerScore>0){
                    topCard = 1;
                    playerBust = false;
                    dealerBust = false;
                    playerScore = 0;
                    dealerScore = 0;
                    dealerAceNum = 0;
                    playerAceNum = 0;

                    for(int i=1; i<=playerHandNum; i++){
                        this.remove(playerHand[i]);
                    }

                    for(int i=1; i<=dealersHandNum; i++){
                        this.remove(dealerHand[i]);
                    }
                    dealersHandNum = 2;
                    playerHandNum = 2;
                }
                Deck.clearDeck();
                deck.createCards();
                putCardsInDeck();
                this.remove(backgroundColor);
                this.remove(infoScreen);
                infoScreenText = "Bet at least $2 but no more than $500 and click Bet&Play";
                displayInfoScreen();
                //resetButton.setEnabled(true);
                newGameButton.setEnabled(false);
                twoDollarButton.setEnabled(true);
                tenDollarButton.setEnabled(true);
                fiftyDollarButton.setEnabled(true);
                oneHundredDollarButton.setEnabled(true);
                twoHundredFiftyDollarButton.setEnabled(true);
                this.add(backgroundColor);
                SwingUtilities.updateComponentTreeUI(this);
            }
            if(e.getSource()==twoDollarButton||e.getSource()==tenDollarButton||e.getSource()==fiftyDollarButton
            ||e.getSource()==oneHundredDollarButton||e.getSource()==twoHundredFiftyDollarButton){
                this.remove(backgroundColor);
                this.remove(displayBetAndCash);
                if(e.getSource()==twoDollarButton && dollar>= 2){
                    dollar -= 2;
                    currentBet += 2;
                }
                if(e.getSource()==tenDollarButton && dollar >= 10){
                    dollar -= 10;
                    currentBet += 10;
                }
                if(e.getSource()==fiftyDollarButton && dollar >= 50){
                    dollar -= 50;
                    currentBet += 50;
                }
                if(e.getSource()==oneHundredDollarButton && dollar >= 100){
                    dollar -= 100;
                    currentBet += 100;
                }
                if(e.getSource()==twoHundredFiftyDollarButton && dollar >= 250){
                    dollar -= 250;
                    currentBet += 250;
                }
                if(currentBet>=2){betButton.setEnabled(true);}
                displayBetAndCash();
                this.add(backgroundColor);
                SwingUtilities.updateComponentTreeUI(this);
            }
            if (e.getSource()==betButton){
                betButton.setEnabled(false);
                stayButton.setEnabled(true);
                doubleDown.setEnabled(true);
                this.remove(backgroundColor);
                this.remove(infoScreen);
                betButton.setEnabled(false);
                hitButton.setEnabled(true);
                twoDollarButton.setEnabled(false);
                tenDollarButton.setEnabled(false);
                fiftyDollarButton.setEnabled(false);
                oneHundredDollarButton.setEnabled(false);
                twoHundredFiftyDollarButton.setEnabled(false);
                drawDealerHand();
                drawPlayerHand();

                if(playerScore==21){
                    infoScreenText = "21! you win 1.5x your bet, click New Game to play again";
                    hitButton.setEnabled(false);
                    doubleDown.setEnabled(false);
                    stayButton.setEnabled(false);
                    newGameButton.setEnabled(true);
                    displayInfoScreen();
                    dollar += (currentBet*1.5);//NEED TO REMOVE THE ACE
                    currentBet = 0;
                    this.remove(displayBetAndCash);
                    displayBetAndCash();
                }
                this.add(backgroundColor);
                SwingUtilities.updateComponentTreeUI(this);
            }
            if(e.getSource()==doubleDown){
                if(dollar>=currentBet){
                    doubleDownIsPressed = true;
                    doubleDown.setEnabled(false);
                    stayButton.setEnabled(false);
                    dollar -= currentBet;
                    currentBet = currentBet*2;
                    this.remove(displayBetAndCash);
                    this.remove(backgroundColor);
                    displayBetAndCash();
                    this.add(backgroundColor);
                    SwingUtilities.updateComponentTreeUI(this);
                }
            }
            if(e.getSource()==hitButton){
                doubleDown.setEnabled(false);
                if(doubleDownIsPressed){
                     doubleDownIsPressed = false;
                     hitButton.setEnabled(false);
                     stayButton.setEnabled(true);
                }
                this.remove(backgroundColor);
                playerDrawCard();

                if(playerBust){
                    infoScreenText = "Over 21! BUST! you lose: $"+ currentBet +"  click New Game to play again";
                    newGameButton.setEnabled(true);
                    if(dollar<2){
                        infoScreenText = "Over 21! BUST! You currently have less than $2... Game Over :c";
                        newGameButton.setEnabled(false);
                    }
                    displayInfoScreen();
                    currentBet = 0;
                    this.remove(displayBetAndCash);
                    displayBetAndCash();
                    hitButton.setEnabled(false);
                    stayButton.setEnabled(false);
                }
                this.add(backgroundColor);
                SwingUtilities.updateComponentTreeUI(this);
            }
            if (e.getSource()==stayButton){
                hitButton.setEnabled(false);
                stayButton.setEnabled(false);
                newGameButton.setEnabled(true);
                this.remove(backgroundColor);
                dealerHand[1].remove(card[0]);
                dealerHand[1].add(card[1]);
                while(dealerScore<17){
                    dealerDrawCard();
                }
                if (dealerBust){
                    dollar += (currentBet*2);
                    currentBet = 0;
                    infoScreenText = "Dealer Bust! went over 21 you WIN! ";
                }
                else if (dealerScore == playerScore){
                    infoScreenText = "You and the dealer have same result, you receive your stake back";
                    dollar += currentBet;
                    currentBet = 0;
                }
                else if (dealerScore>=playerScore){
                    currentBet = 0;
                    infoScreenText = "Dealer hand: " + dealerScore + " Your hand: " + playerScore + " You lose...";
                }
                else {
                    dollar += (currentBet*2);
                    currentBet = 0;
                    infoScreenText = "Dealer hand: " + dealerScore + " Your hand: " + playerScore + " You Win!";
                }
                if(dollar<2){
                    infoScreenText = "You have less than $2 and can not play anymore :( Game is over";
                    newGameButton.setEnabled(false);
                }
                this.remove(displayBetAndCash);
                displayBetAndCash();
                displayInfoScreen();
                doubleDown.setEnabled(false);
                this.add(backgroundColor);
                SwingUtilities.updateComponentTreeUI(this);

            }
    }
    public void displayBetAndCash() {
         displayBetAndCash = new JPanel();
         JLabel cashLeft = new JLabel("Money Left: $" + dollar);
         JLabel bet = new JLabel("Your bet: $" + currentBet);
         cashLeft.setBounds(10,10,140,25);
         bet.setBounds(10,40,140,25);
         displayBetAndCash.add(cashLeft);
         displayBetAndCash.add(bet);
         displayBetAndCash.setBounds(633,0,150,60);
         this.add(displayBetAndCash);

    }
    public void drawDealerHand(){
           for (int i = 1; i <= 2; i++) {
                  dealerHand[i] = new JPanel();
                  dealerHand[i].setBackground(Color.red);
                  dealerHand[i].setBounds((i-1)*70+100, 0, 70, 98);
                  dealerHand[i].setLayout(new BorderLayout());
                  if (i==1){
                      dealerHand[i].add(card[0]);//This is backside
                  }else{
                      dealerHand[i].add(card[topCard]);
                  }
                  dealerScore += calc.addPoints(Deck.getCard(topCard-1));
                  if(calc.addPoints(Deck.getCard(topCard-1)) == 11){
                      dealerAceNum ++;
                  }
                  if(dealerAceNum==2){
                      dealerScore-=10;
                      dealerAceNum--;
                  }
                  this.add(dealerHand[i]);
                  topCard++;
           }
        System.out.println("Dealer score " + dealerScore);
    }
    public void dealerDrawCard(){
         dealersHandNum ++;
         dealerHand[dealersHandNum] = new JPanel();
         dealerHand[dealersHandNum].setBackground(Color.red);
         dealerHand[dealersHandNum].setBounds((dealersHandNum-1)*70+100,0,70,98);
         dealerHand[dealersHandNum].setLayout(new BorderLayout());
         dealerHand[dealersHandNum].add(card[topCard]);
         dealerScore += calc.addPoints(Deck.getCard(topCard-1));
        if(calc.addPoints(Deck.getCard(topCard-1)) == 11){
            dealerAceNum++;
        }
        this.add(dealerHand[dealersHandNum]);
        topCard++;
        if(dealerScore>21){
            if(dealerAceNum>0){
                dealerAceNum--;
                dealerScore-=10;
            }else {
                dealerBust = true;
                System.out.println("dealer Bust!");
            }
        }

    }
    public void drawPlayerHand(){
           for (int i = 1; i <= 2; i++) {
                  playerHand[i] = new JPanel();
                  playerHand[i].setBackground(Color.red);
                  playerHand[i].setBounds((i-1)*70+100, 450, 70, 98);
                  playerHand[i].setLayout(new BorderLayout());
                  playerHand[i].add(card[topCard]);
                  playerScore += calc.addPoints(Deck.getCard(topCard-1));
                  if(calc.addPoints(Deck.getCard(topCard-1)) == 11){
                   playerAceNum++;
                  }
                  this.add(playerHand[i]);
                  topCard++;
                  if(playerAceNum==2){
                   playerScore-=10;
                   playerAceNum--;
                  }
           }
        System.out.println("Player score " + playerScore);
    }
    private void playerDrawCard() {
         playerHandNum ++;
         playerHand[playerHandNum] = new JPanel();
         playerHand[playerHandNum].setBackground(Color.red);
         playerHand[playerHandNum].setBounds((playerHandNum-1)*70+100, 450, 70, 98);
         playerHand[playerHandNum].setLayout(new BorderLayout());
         playerHand[playerHandNum].add(card[topCard]);
         playerScore += calc.addPoints(Deck.getCard(topCard-1));
        if(calc.addPoints(Deck.getCard(topCard-1)) == 11){
            playerAceNum++;
        }
         this.add(playerHand[playerHandNum]);
         topCard++;
        if(playerScore>21){
            if(playerAceNum>0){
                playerAceNum--;
                playerScore-=10;
            }else {
                playerBust = true;
                System.out.println("player Bust!");
            }
        }
         System.out.println("Player score " + playerScore);
    }
    public void putCardsInDeck(){
            for (int i=1; i<=52; i++){
                   icon[i] = new ImageIcon(getClass().getClassLoader().getResource(Deck.getCard(i-1)));
                   card[i]= new JLabel();
                   card[i].setIcon(icon[i]);
           }
            icon[0] = new ImageIcon(getClass().getClassLoader().getResource("card_back.png"));
            card[0] = new JLabel();
            card[0].setIcon(icon[0]);
    }
    public void buttons(){
         //Start Button
        newGameButton = new JButton();
        newGameButton.setText("New Game");
        newGameButton.setSize(100,50);
        newGameButton.setLocation(0,50);
        newGameButton.addActionListener(this);
        this.add(newGameButton);
        //Reset Button
        quitGameButton = new JButton();
        quitGameButton.setText("Quit Game");
        quitGameButton.setSize(100,50);
        quitGameButton.setLocation(0,0);
        quitGameButton.addActionListener(this);
        quitGameButton.setEnabled(true);
        this.add(quitGameButton);
        //HIT Me Button
        hitButton = new JButton();
        hitButton.setText("HIT!");
        hitButton.setSize(100,50);
        hitButton.setLocation(0,100);
        hitButton.addActionListener(this);
        hitButton.setEnabled(false);
        this.add(hitButton);
        //Stay Button
        stayButton = new JButton();
        stayButton.setText("STAY!");
        stayButton.setSize(100,50);
        stayButton.setLocation(0,150);
        stayButton.addActionListener(this);
        stayButton.setEnabled(false);
        this.add(stayButton);
        //Bet/Play
        betButton = new JButton();
        betButton.setText("Bet&Play");
        betButton.setSize(100,50);
        betButton.setLocation(0,200);
        betButton.addActionListener(this);
        betButton.setEnabled(false);
        this.add(betButton);
        //All the betting options 2,10,50,100,500
        twoDollarButton = new JButton("$2");
        twoDollarButton.setSize(100,50);
        twoDollarButton.setLocation(0,250);
        twoDollarButton.addActionListener(this);
        twoDollarButton.setEnabled(false);
        this.add(twoDollarButton);

        tenDollarButton = new JButton("$10");
        tenDollarButton.setSize(100,50);
        tenDollarButton.setLocation(0,300);
        tenDollarButton.addActionListener(this);
        tenDollarButton.setEnabled(false);
        this.add(tenDollarButton);

        fiftyDollarButton = new JButton("$50");
        fiftyDollarButton.setSize(100,50);
        fiftyDollarButton.setLocation(0,350);
        fiftyDollarButton.addActionListener(this);
        fiftyDollarButton.setEnabled(false);
        this.add(fiftyDollarButton);

        oneHundredDollarButton = new JButton("$100");
        oneHundredDollarButton.setSize(100,50);
        oneHundredDollarButton.setLocation(0,400);
        oneHundredDollarButton.addActionListener(this);
        oneHundredDollarButton.setEnabled(false);
        this.add(oneHundredDollarButton);

        twoHundredFiftyDollarButton = new JButton("$250");
        twoHundredFiftyDollarButton.setSize(100,50);
        twoHundredFiftyDollarButton.setLocation(0,450);
        twoHundredFiftyDollarButton.addActionListener(this);
        twoHundredFiftyDollarButton.setEnabled(false);
        this.add(twoHundredFiftyDollarButton);
        //Double Down button
        doubleDown = new JButton("Double Down!");
        doubleDown.setFont(new Font("Arial",Font.PLAIN,10));
        doubleDown.setSize(100,50);
        doubleDown.setLocation(0,500);
        doubleDown.addActionListener(this);
        doubleDown.setEnabled(false);
        this.add(doubleDown);


    }
    public void displayInfoScreen(){
        infoScreen = new JPanel();
        infoScreen.setLayout(null);
        JLabel infoText = new JLabel(infoScreenText);
        infoText.setBounds(10,75,390,50);
        infoScreen.setBounds(200,200,400,200);
        infoScreen.add(infoText);
        this.add(infoScreen);

    }
    public void signInScreen(){
         logInScreen = new JPanel();
         JLabel usernameText = new JLabel("UserName:");
         JLabel moneyText = new JLabel("Insert amount $:");
         JLabel infoText = new JLabel("(Max 16 symbol name & Max $1000 Min $2)");
         usernameInputBox = new JTextField(20);
         moneyInputBox = new JTextField(20);
         logInScreen.setLayout(null);
         usernameInputBox.setBounds(150, 100,100,25);
         usernameText.setBounds(13,100,75,25);
         moneyText.setBounds(13,150,130,25);
         infoText.setBounds(75, 200,250,25);
         moneyInputBox.setBounds(150,150,100,25);
         logInScreen.setBounds(200,150,400,300);

         if(signInFail){
            JLabel errorMessage = new JLabel("Invalid input*");
            errorMessage.setForeground(Color.RED);
            errorMessage.setBounds(150,170,100,25);
            logInScreen.add(errorMessage);
         }

         singInButton = new JButton();
         singInButton.setText("Sign in");
         singInButton.setSize(100,50);
         singInButton.setLocation(350,400);
         singInButton.addActionListener(this);
         this.add(singInButton);
         logInScreen.add(usernameText);
         logInScreen.add(moneyText);
         logInScreen.add(infoText);
         logInScreen.add(moneyInputBox);
         logInScreen.add(usernameInputBox);
         this.add(logInScreen);
    }

}

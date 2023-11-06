import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TicTacToeFrame extends JFrame {

    TicTacToeTile[][] board = new TicTacToeTile[3][3];

    int moveCnt = 0;

    String currMove = "X";



    JPanel mainPnl;
    JPanel topPnl;
    JPanel middlePnl;
    JPanel boardPnl;
    JPanel bottomPnl;

    JLabel topLbl;
    JLabel moveLbl = new JLabel("Please select a valid move");
    JLabel playAgain;

    JTextArea textTa;
    JScrollPane scroller;


    JButton quitBtn;


    public TicTacToeFrame() {
        JFrame frame = new JFrame();
        mainPnl = new JPanel();

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;


        Image img = kit.getImage("src/img.png");

        mainPnl.setLayout(new BorderLayout());
        createTopPanel();
        mainPnl.add(topPnl, BorderLayout.NORTH);
        createBoardPanel();
        mainPnl.add(boardPnl, BorderLayout.CENTER);
        createBottomPanel();
        mainPnl.add(bottomPnl, BorderLayout.SOUTH);

        UIManager.put("OptionPane.okButtonText", "Play Again!");




        setSize(screenWidth / 2, screenHeight / 2);
        setLocation(screenWidth / 4, screenHeight / 4);
        setTitle("Tic Tac Toe Game");
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        add(mainPnl);

    }

    public void createTopPanel()
    {

        topPnl = new JPanel();
        topLbl = new JLabel("Tic Tac Toe");


        topLbl.setFont(new Font("Verdana", Font.PLAIN, 36));


        topPnl.add(topLbl);


    }

    public void createMiddlePanel()
    {

    }

    public void createBottomPanel()
    {
        bottomPnl = new JPanel();
        quitBtn = new JButton("Quit");
        quitBtn.addActionListener((ActionEvent ae) -> System.exit(0));

        bottomPnl.add(quitBtn);
    }

    public void createBoardPanel()
    {
        boardPnl = new JPanel();
        boardPnl.setLayout(new GridLayout(3, 3));


        for( int row = 0; row < 3; row++)
            for(int col= 0; col < 3; col++)
            {
                board[row][col] = new TicTacToeTile(row, col);
                TicTacToeTile tile = board[row][col];
                tile.setText(" ");
                board[row][col].addActionListener((ActionEvent ae) -> playGame(tile, currMove));
            }



        boardPnl.add(board[0][0]);
        boardPnl.add(board[0][1]);
        boardPnl.add(board[0][2]);
        boardPnl.add(board[1][0]);
        boardPnl.add(board[1][1]);
        boardPnl.add(board[1][2]);
        boardPnl.add(board[2][0]);
        boardPnl.add(board[2][1]);
        boardPnl.add(board[2][2]);
    }

    public void playGame(TicTacToeTile tile, String player)
    {

        int tileRow = tile.getRow();
        int tileCol = tile.getCol();

        TicTacToeTile newTile = TicTacToe.setTile(board, tile, player);

        if (newTile == null)
        {
            System.out.println("Null");
            return;
        }



        this.moveCnt++;
        System.out.println(moveCnt);
        board[tileRow][tileCol] = newTile;


        if (moveCnt >= 5)
        {
            boolean win = TicTacToe.checkWin(player, board);

            if (win)
            {
                JOptionPane.showMessageDialog(mainPnl,
                        String.format("%s wins!", player),
                        "Tic Tac Toe",
                        JOptionPane.INFORMATION_MESSAGE);
                clearBoard();
            }

        }
        if (moveCnt >= 7)
                {

                    boolean tie = TicTacToe.isTie(board);

                    if (tie)
                    {
                        JOptionPane.showMessageDialog(mainPnl,
                                "It's a tie!",
                                "Tic Tac Toe",
                                JOptionPane.INFORMATION_MESSAGE);
                        clearBoard();
                    }


                }

        if (currMove.equals("X")) {
            currMove = "O";
        } else currMove = "X";


    }

    private void clearBoard() {

        playAgain = new JLabel("Click a button to play again");
        for( int row = 0; row < 3; row++)
            for(int col= 0; col < 3; col++)
            {
                board[row][col].setText(" ");
            }

        moveCnt = 0;


    }









}

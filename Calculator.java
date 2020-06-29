package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.ArrayList;

public class Calculator extends Application {

    TextField textField;
    double agregate=0;
    @Override
    public void start(Stage primaryStage) throws Exception{

        //gridpane
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setHgap(5);
        grid.setVgap(6);


        //Buttons
        //Numbers
        Button zero = new Button("0");
        GridPane.setConstraints(zero,0,5);
        zero.getStyleClass().add("zero-btn");
        zero.setOnAction(event -> handleInput(zero.getText()));
        GridPane.setColumnSpan(zero,2);

        Button one = new Button("1");
        GridPane.setConstraints(one,0,4);
        one.getStyleClass().add("number-btn");
        one.setOnAction(event -> handleInput(one.getText()));

        Button two = new Button("2");
        GridPane.setConstraints(two,1,4);
        two.getStyleClass().add("number-btn");
        two.setOnAction(event -> handleInput(two.getText()));

        Button three = new Button("3");
        GridPane.setConstraints(three,2,4);
        three.getStyleClass().add("number-btn");
        three.setOnAction(event -> handleInput(three.getText()));

        Button four = new Button("4");
        GridPane.setConstraints(four,0,3);
        four.getStyleClass().add("number-btn");
        four.setOnAction(event -> handleInput(four.getText()));

        Button five = new Button("5");
        GridPane.setConstraints(five,1,3);
        five.getStyleClass().add("number-btn");
        five.setOnAction(event -> handleInput(five.getText()));

        Button six = new Button("6");
        GridPane.setConstraints(six,2,3);
        six.getStyleClass().add("number-btn");
        six.setOnAction(event -> handleInput(six.getText()));

        Button seven = new Button("7");
        GridPane.setConstraints(seven,0,2);
        seven.getStyleClass().add("number-btn");
        seven.setOnAction(event -> handleInput(seven.getText()));

        Button eight = new Button("8");
        GridPane.setConstraints(eight,1,2);
        eight.getStyleClass().add("number-btn");
        eight.setOnAction(event -> handleInput(eight.getText()));

        Button nine = new Button("9");
        GridPane.setConstraints(nine,2,2);
        nine.getStyleClass().add("number-btn");
        nine.setOnAction(event -> handleInput(nine.getText()));

        //Operands
        Button plus = new Button("+");
        GridPane.setConstraints(plus,3,4);
        plus.getStyleClass().add("operand-btn");
        plus.setOnAction(event -> handleInput(plus.getText()));

        Button minus = new Button("-");
        GridPane.setConstraints(minus,3,3);
        minus.getStyleClass().add("operand-btn");
        minus.setOnAction(event -> handleInput(minus.getText()));

        Button multiply = new Button("x");
        GridPane.setConstraints(multiply,3,2);
        multiply.getStyleClass().add("operand-btn");
        multiply.setOnAction(event -> handleInput("*"));

        Button divide = new Button("/");
        GridPane.setConstraints(divide,3,1);
        divide.getStyleClass().add("operand-btn");
        divide.setOnAction(event -> handleInput(divide.getText()));

        Button percentage = new Button("%");
        GridPane.setConstraints(percentage,2,1);
        percentage.getStyleClass().add("blue-btn");
        percentage.setOnAction(event -> handleInput(percentage.getText()));

        Button invert = new Button("+/-");
        GridPane.setConstraints(invert,1,1);
        invert.getStyleClass().add("blue-btn");
        invert.setOnAction(event -> textField.setText(String.valueOf(-1*agregate)));


        Button ac = new Button("C");
        GridPane.setConstraints(ac,0,1);
        ac.setOnAction(event -> textField.setText(""));
        ac.getStyleClass().add("blue-btn");



        Button dot = new Button(".");
        GridPane.setConstraints(dot,2,5);
        dot.setOnAction(event -> handleInput(dot.getText()));
//        dot.getStyleClass().add("operand-btn");

        Button equal = new Button("=");
        GridPane.setConstraints(equal,3,5);
        equal.setOnAction(event -> calculate(textField.getText()));
        equal.getStyleClass().add("operand-btn");
        //Text Field
        textField = new TextField();
        textField.setPromptText("0");
        GridPane.setConstraints(textField,0,0);
        GridPane.setColumnSpan(textField,4);
        textField.getStyleClass().add("text-field");
        textField.setAlignment(Pos.CENTER_RIGHT);
        textField.editableProperty().set(false);


        //add everything in the grid
        grid.getChildren().addAll(
                textField,
                invert,
                zero,
                one,
                two,
                three,
                four,
                five,
                six,
                seven,
                eight,
                nine,
                ac,
                percentage,
                divide,
                multiply,
                minus,
                plus,
                dot,
                equal);


        Scene scene = new Scene(grid,400,400);
        scene.getStylesheets().add("sample/Style.css");
        primaryStage.setTitle("Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleInput(String x) {
        textField.setText(textField.getText()+x);
    }

//    private void calculate(){
//

//    }

    public static void main(String[] args) {
        launch(args);
    }
     boolean isDigit(char check) {
        if (Character.isDigit(check)) {
            return true;
        }
        return false;
    }
    private void calculate(String a) {



        try {
            System.out.println(a);
            String operators[]=a.split("[0-9]+");
            String operands[]=a.split("[/%*+-]");
            agregate = Integer.parseInt(operands[0]);
            for(int i=1;i<operands.length;i++){
                if(operators[i].equals("+"))
                    agregate += Double.parseDouble(operands[i]);
                else if(operators[i].equals("*"))
                    agregate *= Double.parseDouble(operands[i]);
                else if(operators[i].equals("%"))
                    agregate =agregate* Double.parseDouble(operands[i])/100;
                else if(operators[i].equals("/"))
                    agregate /= Double.parseDouble(operands[i]);
                else
                    agregate -= Double.parseDouble(operands[i]);
            }
            System.out.println(agregate);
            textField.setText(String.valueOf(agregate));
            //System.out.println(result);

        }
        catch (Exception e){
            textField.setText("SYNTAX ERROR");
            System.out.println(e);
        }


    }
}

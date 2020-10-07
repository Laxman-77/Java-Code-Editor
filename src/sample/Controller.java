package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import java.io.*;
import java.util.ArrayList;

public class Controller {


    String currentPath="", currentTitle="", currentType="";
    String bgColor = "#2b2b2b";
    String FColor = "#ffffff";

    @FXML
    TextArea compileText, editTax;

    @FXML
    void newFile(ActionEvent E) throws IOException {
        if(!currentPath.equals("")){
            Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Do you want to save current file");
            if(alert.showAndWait().get().getText().equals("OK")){
                saveFile(new ActionEvent());
            }
        }
        FileChooser fc = new FileChooser();
        File newfile = fc.showSaveDialog(null);
        currentPath = newfile.getPath();
        System.out.println(currentPath);
        System.out.println(newfile.getName());
        currentTitle = newfile.getName().substring(0, newfile.getName().lastIndexOf('.'));
        currentType = newfile.getName().substring(newfile.getName().lastIndexOf('.') + 1);
        if (currentType.equals("java")) {
            Java newJavaFile = new Java();
            newJavaFile.setTitle(currentTitle);
            newJavaFile.setInitialText();
            editTax.setText(newJavaFile.initialText);
            Main.getStage().setTitle("JAVA- " + currentTitle + ".java");
        } else if (currentType.equals("c")) {
            C CFile = new C();
            CFile.setInitialText();
            editTax.setText(CFile.initialText);
            Main.getStage().setTitle("C - " + currentTitle + ".c");

        } else if (currentType.equals("cpp")) {
            Cpp cppFile = new Cpp();
            cppFile.setInitialText();
            editTax.setText(cppFile.initialText);

            Main.getStage().setTitle("C++ - " + currentTitle + ".cpp");

        } else {
            Main.getStage().setTitle(currentTitle +"." + currentType);
            editTax.setText("");
        }
        editTax.setFont(Font.font("Sans Serif",14));
    }

    @FXML
    void saveFile(ActionEvent E) throws IOException {
        if (!currentPath.equals("") || !currentPath.equals(null)) {
            FileWriter fw = new FileWriter(currentPath);
            fw.write(editTax.getText());
            fw.close();
            
            Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(currentTitle+"."+currentType+" saved successfully");
            alert.showAndWait();
        }
    }

    @FXML
    void openFile(ActionEvent e) throws IOException {

        FileChooser fc = new FileChooser();
        File newfile = fc.showOpenDialog(null);
        System.out.println(currentPath);
        System.out.println(newfile.getName());
        currentPath=newfile.getPath();
        currentTitle = newfile.getName().substring(0, newfile.getName().lastIndexOf('.'));
        currentType = newfile.getName().substring(newfile.getName().lastIndexOf('.') + 1);
        if (currentType.equals("java")) {

            Main.getStage().setTitle("JAVA- " + currentTitle + ".java");

        } else if (currentType.equals("c")) {


            Main.getStage().setTitle("C - " + currentTitle + ".c");

        } else if (currentType.equals("cpp")) {

            Main.getStage().setTitle("C++ - " + currentTitle + ".cpp");

        } else {

            Main.getStage().setTitle(currentTitle + "." + currentType);
        }
        String x = " ";
        String line;
        FileReader fileReader = new FileReader(newfile.getPath());
        BufferedReader bufferreader = new BufferedReader(fileReader);

        while ((line = bufferreader.readLine()) != null) {
            x += line + "\n";
        }
        editTax.setText(x);
        editTax.setFont(Font.font("Sans Serif",14));
    }

    @FXML
    void compileJava(ActionEvent E) {
        try {
            JavaCompiler javaCom = new JavaCompiler();
            javaCom.setFileName(currentPath);
            System.out.println(currentPath);
            compileText.setText("");
            compileText.setText("Compiled Successfully" + "\n\n");
            String m = javaCom.compile();
            compileText.setText(m);

        } catch (Exception ex) {
        }
    }

    @FXML
    void Runjava(ActionEvent E) {
        try {
            JavaRun a = new JavaRun();
            a.setFileName(currentPath);
            String x = "Excecuting Program:\n";
            x += a.run();
            x += "\nProgram Finished With Exit Code 0";
            compileText.setText(x);
        } catch (Exception eq) {
            System.out.println("aaa");
        }
    }
    @FXML
    void compileCpp(ActionEvent E) {
        try {

            CppCompiler cComp = new CppCompiler();
            cComp.setFileName(currentPath);
            compileText.setText("compiling \n");

            String  m = cComp.compile();
            compileText.setText(m);
            compileText.setText("Compiled Successfully\n");
        } catch (Exception ex) {
        }
    }

    @FXML
    void RunCpp(ActionEvent E) {
        try {
            CppRun a = new CppRun();
            a.setFileName(currentPath);
            String x= "Excecuting Program:\n";
            x += a.run();
            x+="\nProgram Finished With Exit Code 0";
            compileText.setText(x);
        } catch (Exception eq) {
            System.out.println("aaa");
        }
    }

    @FXML
    void compileC(ActionEvent E) {
        try {

            CCompiler javaCom = new CCompiler();
            javaCom.setFileName(currentPath);
            compileText.setText("");
            compileText.setText("Compiling" + "\n\n");
            String  m = javaCom.compile();

            compileText.setText(m);
            compileText.setText("Compiled Successfully" + "\n\n");

        } catch (Exception ex) {
        }
    }

    @FXML
    void RunC(ActionEvent E) {
        try {
            CRun a = new CRun();
            a.setFileName(currentPath);
            String x= "Excecuting Program:\n";
            x += a.run();
            x+="\nProgram Finished With Exit Code 0";
            compileText.setText(x);
        } catch (Exception eq) {
            System.out.println("aaa");
        }
    }

    @FXML
    void changeFontColor(ActionEvent E) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Change Font Color");
        alert.setHeaderText("Select Color From Color Picker");
        GridPane grid = new GridPane();
        Scene scene = new Scene(new HBox(20), 400, 100);
        HBox box = (HBox) scene.getRoot();
        box.setPadding(new Insets(5, 5, 5, 5));

        final ColorPicker colorPicker = new ColorPicker();

        colorPicker.setValue(Color.valueOf(FColor));

        final Text text = new Text("This is your new color!!");
        text.setFont(Font.font("Verdana", 20));
        text.setFill(colorPicker.getValue());

        colorPicker.setOnAction(new EventHandler() {
            public void handle(Event t) {
                text.setFill(colorPicker.getValue());
            }
        });

        box.getChildren().addAll(colorPicker, text);
        grid.getChildren().add(box);
        alert.getDialogPane().setContent(box);
        if (alert.showAndWait().get().getText().equals("OK")) {
            FColor = colorPicker.getValue().toString();
            FColor = "#" + FColor.substring(2, FColor.length() - 2);
            System.out.println(colorPicker.getValue());
            System.out.println(FColor);
            editTax.setStyle("-fx-text-fill:" + FColor + "; " + "-fx-control-inner-background:" + bgColor + ";");
        }
    }

        @FXML
        void changeBgColor(ActionEvent E){

            Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
            //alert.s
            alert.setTitle("Change Background Color");
            alert.setHeaderText("Select Color From Color Picker");
            GridPane grid=new GridPane();
            Scene scene = new Scene(new HBox(20), 400, 100);
            HBox box = (HBox) scene.getRoot();
            box.setPadding(new Insets(5, 5, 5, 5));

            final ColorPicker colorPicker = new ColorPicker();

            colorPicker.setValue(Color.valueOf(bgColor));

            final Text text = new Text("This is your new color!!");
            text.setFont(Font.font ("Verdana", 20));
            text.setFill(colorPicker.getValue());

            colorPicker.setOnAction(new EventHandler() {
                public void handle(Event t) {

                    text.setFill(colorPicker.getValue());

                }
            });

            box.getChildren().addAll(colorPicker,text);
            grid.getChildren().add(box);
            alert.getDialogPane().setContent(box);
            if(alert.showAndWait().get().getText().equals("OK")){
                bgColor = colorPicker.getValue().toString();
                bgColor = "#"+bgColor.substring(2,bgColor.length()-2);
                editTax.setStyle("-fx-text-fill:"+FColor+"; " +"-fx-control-inner-background:"+bgColor+";"); ;
            }
    }
@FXML
    void ChangeFont(ActionEvent E){

        Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
        //alert.s
        alert.setTitle("Change Font Family and Font Size");
        alert.setHeaderText("Choose Font and its size");
        alert.setResizable(true);

        GridPane grid=new GridPane();
        Scene scene = new Scene(new HBox(80), 400, 300);
        HBox box = (HBox) scene.getRoot();
        box.setPadding(new Insets(5, 5, 5, 5));
        ObservableList<String> fonts = FXCollections.observableArrayList(Font.getFamilies());
        ArrayList<Integer> m =new ArrayList<Integer>();
        for(int i=5;i<=30;i++){
            m.add(i);
        }
        ObservableList<Integer> sizes = FXCollections.observableArrayList(m);
        // Select font
        ChoiceBox<String> selectFont;
        selectFont = new ChoiceBox<>();
        selectFont.setValue("SansSerif");
        selectFont.setLayoutX(600);
        selectFont.setLayoutY(200);
        selectFont.setItems(fonts);
        ChoiceBox<Integer> selectFontsize;
        selectFontsize = new ChoiceBox<Integer>();
        selectFontsize.setValue(5);
        selectFontsize.setLayoutX(600);
        selectFontsize.setLayoutY(400);
        selectFontsize.setItems(sizes);
        Text text = new Text("New Font");

        selectFont.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> text.setFont(Font.font(newValue, FontWeight.MEDIUM,24)));
        selectFontsize.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> text.setFont(Font.font(selectFont.getValue(),FontWeight.MEDIUM,newValue)));

        text.setFont(editTax.getFont());
        box.getChildren().addAll(selectFont,selectFontsize, text);
        grid.getChildren().add(box);
        alert.getDialogPane().setContent(box);
        if(alert.showAndWait().get().getText().equals("OK")){
        editTax.setFont(Font.font(selectFont.getValue(),selectFontsize.getValue()));
        }
    }
    int startindex=0;
    String toFind="";
    @FXML
    void Find(ActionEvent e){

        Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
        //alert.s
        alert.setTitle("Find");
        alert.setHeaderText("Enter Text and Find it");
        GridPane grid=new GridPane();
        Scene scene = new Scene(new HBox(20), 400, 100);
        HBox box = (HBox) scene.getRoot();
        box.setPadding(new Insets(5, 5, 5, 5));
        TextField f = new TextField("Find What?");
        Button find = new  Button("Find");
        Button findNext = new Button("Find Next");
        int counter=0;

        Label x = new Label("No String searched");

        startindex=0;
        find.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event2)
            {
                toFind= f.getText();
                startindex=   editTax.getText().indexOf(toFind);
                 editTax.selectRange(startindex,toFind.length());
                 //startindex =startindex+ toFind.length();
                 editTax.requestFocus();
                 alert.close();
            }
        });

        box.getChildren().addAll(f,find);
        alert.getDialogPane().setContent(box);
        alert.show();
    }

    @FXML
    void findnext(ActionEvent e) {
        if(startindex==-1)
        return;

        editTax.deselect();
        System.out.println(toFind);
        startindex = editTax.getText().indexOf(toFind, startindex + toFind.length());
        try {
            if (startindex == -1) throw new Exception();
                System.out.println(startindex);
            editTax.selectRange(startindex, startindex + toFind.length());
            editTax.requestFocus();

        }
        catch(Exception ee){

        }
    }
    @FXML
    void Replace(ActionEvent e){

        Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Replace");
        alert.setHeaderText("Enter Text and Replace it");
        GridPane grid=new GridPane();
        Scene scene = new Scene(new HBox(20), 400, 100);
        HBox box = (HBox) scene.getRoot();
        box.setPadding(new Insets(5, 5, 5, 5));
        TextField f = new TextField("Text");
        TextField ff = new TextField("Replace with");
        Button r = new Button("Replace All");

        r.setOnAction(new EventHandler<ActionEvent>(){
        @Override
        public void handle(ActionEvent event2)
        {
          editTax.setText(editTax.getText().replaceAll(f.getText(),ff.getText()));
        }
        });

        final Text text = new Text("This is your new color!!");
        text.setFont(Font.font ("Verdana", 20));
        box.getChildren().addAll(f,ff,r);
        alert.getDialogPane().setContent(box);
        alert.showAndWait();
    }

    @FXML
    void undo(ActionEvent E){
        editTax.undo();
    }

    @FXML
    void redo(ActionEvent E){
        editTax.redo();
    }
    @FXML
    void cut(ActionEvent E){
        String m = editTax.getSelectedText();
        editTax.replaceSelection("");

        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent content = new ClipboardContent();
        content.putString(m);

        clipboard.setContent(content);
    }
    @FXML
    void copy(ActionEvent E){
        String m = editTax.getSelectedText();

        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent content = new ClipboardContent();
        content.putString(m);

        clipboard.setContent(content);
    }
    @FXML
    void paste(ActionEvent E) {
        Clipboard systemClipboard = Clipboard.getSystemClipboard();
        if(systemClipboard.hasContent(DataFormat.PLAIN_TEXT)){
            editTax.setText(editTax.getText().substring(0,editTax.getAnchor())+systemClipboard.getContent(DataFormat.PLAIN_TEXT).toString()+editTax.getText().substring(editTax.getAnchor()));
        }
     }

     @FXML
    void aboutUs(ActionEvent E){
        Alert about=new Alert(Alert.AlertType.INFORMATION);
        about.setTitle("About Us");
        about.setHeaderText("IIT2018040 Manthan Surkar\nIIT2018051 Avishek\nIIT2018066 Laxman Goliya");
        about.showAndWait();
         System.out.println("jjj");
     }

}

abstract class FileType {

    protected String type;
    protected String extension;
    protected String initialText;
    protected String title;

    protected String getInitalText() {
        return this.initialText;
    }

    protected String getType() {
        return this.type;
    }

    protected String getExtension() {
        return this.extension;
    }

    abstract protected void setInitialText();

    protected void setTitle(String title) {
        this.title = title;
    }

}

class Java extends FileType {
    Java() {

        this.extension = ".java";
        this.type = "Application";
    }

    protected void setInitialText() {

        String s1 = "import java.util.*;";
        s1 += "\nclass " + this.title;
        s1 += "\n{\n\tpublic static void main(String args[])";
        s1 += "\n\t{\n\n\n\t}\n}";
        this.initialText = s1;
    }
}
class C extends FileType {
    C() {

        this.extension = ".c";
        this.type = "C";
    }

    protected void setInitialText() {

        String s1 = "#include<stdio.h>\n";
        s1+="\nint main()\n{\n\t\n\n}\n";
        this.initialText = s1;
    }
}


class Cpp extends FileType {
    Cpp() {

        this.extension = ".cpp";
        this.type = "CPP";
    }

    protected void setInitialText() {

        String s1 = "#include<bits/stdc++.h>\nusing namespace std;\n";
        s1+="\nint main()\n{\n\t\n\n}\n";
        this.initialText = s1;
    }
}
abstract class Compiler {

    String name;
    protected void setFileName(String name){
       this.name = name;
    }

    abstract protected String compile() throws InterruptedException, IOException;

}
class JavaCompiler extends Compiler{
    protected String compile() throws InterruptedException, IOException {
        Runtime rt = Runtime.getRuntime();
        Process p = rt.exec("javac  " + name);
        p.waitFor();
        int i = p.exitValue();
        InputStream ip = p.getErrorStream();
        int a = ip.available();
        String m;
        if (a == 0)
            m= "Good !!. There is no error in your Program\n";
        else {
            int c;
            String ts = "\n";
            while ((c = ip.read()) != -1) {
                ts = ts + (char) c;
            }
            m=ts;
        }
        return m;
    }

}

class CCompiler extends Compiler{

    protected String compile() throws InterruptedException, IOException {
        Runtime rt = Runtime.getRuntime();
        Process p = rt.exec("gcc -o c " + name);
        System.out.println("gcc -o c " + name);

        p.waitFor();
        int i = p.exitValue();
        InputStream ip = p.getErrorStream();
        int a = ip.available();
        String m;
        if (a == 0)
            m= "Good !!. There is no error in your Program\n";
        else {
            int c;
            String ts = "\n";
            while ((c = ip.read()) != -1) {
                ts = ts + (char) c;
            }
            m=ts;
        }
        return m;
    }

}

class CppCompiler extends Compiler{

    protected String compile() throws InterruptedException, IOException {
        Runtime rt = Runtime.getRuntime();
        Process p = rt.exec("g++ -o cpp " + name);
        System.out.println("g++ -o cpp " + name);
        p.waitFor();
        int i = p.exitValue();
        InputStream ip = p.getErrorStream();
        int a = ip.available();
        String m;
        if (a == 0)
            m= "Good !!. There is no error in your Program\n";
        else {
            int c;
            String ts = "\n";
            while ((c = ip.read()) != -1) {
                ts = ts + (char) c;
            }
            m=ts;
        }
        return m;
    }

}

abstract class Runner {

    String name;
    protected void setFileName(String name){
        this.name=name;
    }

    abstract protected String run() throws InterruptedException, IOException;

}


class JavaRun extends Runner{

    protected String run() throws InterruptedException, IOException {
        String ts = "" ;
        try {
            Runtime t = Runtime.getRuntime();
            Process p = t.exec("java  " + name);
            p.waitFor();
            int i = p.exitValue();
            InputStream ip = p.getInputStream();
            int a = ip.available();
            //   ta2.setText("Output of " + r + ".java  ...... \n\n");
            ts ="\n";
            if (a != 0) {
                int c;
                while ((c = ip.read()) != -1) {
                    ts = ts + (char) c;
                }
                //         ta2.setText(ts);
            }
            //           ta2.setText("Program Successfully Executed");
        } catch (Exception eq) {
        }
        return ts;
//        r}
    }

}

class CppRun extends Runner{

    protected String run() throws InterruptedException, IOException {
        String ts = "" ;
        try {

            Runtime t = Runtime.getRuntime();
            Process p = t.exec("./cpp");
            p.waitFor();
            int i = p.exitValue();
            InputStream ip = p.getInputStream();
            int a = ip.available();
            //   ta2.setText("Output of " + r + ".java  ...... \n\n");
            ts ="\n";
            if (a != 0) {
                int c;
                while ((c = ip.read()) != -1) {
                    ts = ts + (char) c;
                }
                //         ta2.setText(ts);
            }
            //           ta2.setText("Program Successfully Executed");
        } catch (Exception eq) {
        }
        return ts;
//        r}
    }

}

class CRun extends Runner{

    protected String run() throws InterruptedException, IOException {
        String ts = "" ;
        try {

            Runtime t = Runtime.getRuntime();
            Process p = t.exec("./c");
            p.waitFor();
            int i = p.exitValue();
            InputStream ip = p.getInputStream();
            int a = ip.available();
            //   ta2.setText("Output of " + r + ".java  ...... \n\n");
            ts ="\n";
            if (a != 0) {
                int c;
                while ((c = ip.read()) != -1) {
                    ts = ts + (char) c;
                }
                //         ta2.setText(ts);
            }
            //           ta2.setText("Program Successfully Executed");
        } catch (Exception eq) {
        }
        return ts;
//        r}
    }



}

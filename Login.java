import network.ConnectURI;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class Login {
 private JButton button1;
 private JButton button2;
 private JTextField textField1;
 private JTextField textField2;
 private JTextField textField3;
 private JTextField textField4;

 public class login extends JFrame {
  private JButton submitButton;
  private JTextField textMessage;
  private JTextField textStatus;
  private JTextField textComment;
  private JButton closeButton;
  private JPanel Display;
  private JFrame login;

  public <ResponModel> login() {
   login = new JFrame("TM1_22090014_SATRIAWIBOWO");
   login.setDefaultCloseOperation(EXIT_ON_CLOSE);
   login.setPreferredSize(new Dimension(500, 1000));
   login.setResizable(true);
   login.add(Display);
   login.pack();
   login.setLocationRelativeTo(null);


   submitButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
     if (e.getSource()== submitButton){
      try {
       URL url = new URL("http://harber.mimoapps.xyz/api/getaccess.php");
       HttpURLConnection connection =(HttpURLConnection) url.openConnection();
       connection.setRequestMethod("GET");

       BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
       String inputLine;
       StringBuffer response = new StringBuffer();
       while ((inputLine = in.readLine())!=null){
        response.append(inputLine);
       }
       in.close();

       JSONArray jsonArray = new JSONArray(response.toString());

       ArrayList<ResponModel>parsedList = new ArrayList<>();
       for (int i = 0;i<jsonArray.length();i++){
        ResponModel resModel = new ResponModel();
        JSONObject myJSONObject = jsonArray.getJSONObject(i);
        resModel.setMessage(myJSONObject.getString("message"));
        resModel.setStatus(myJSONObject.getString("status"));
        resModel.setComment(myJSONObject.getString("comment"));
        parsedList.add(resModel);

       }
       for (int index = 0 ; index < parsedList.size();index++){
        textMessage.setText(parsedList.get(index).getmessage());
        textStatus.setText(parsedList.get(index).getstatus());
        textComment.setText(parsedList.get(index).getcomment());
       }
      } catch (IOException ex) {
       throw new RuntimeException(ex);
      } catch (ProtocolException ex) {
       throw new RuntimeException(ex);
      } catch (MalformedURLException ex) {
       throw new RuntimeException(ex);
      } catch (IOException ex) {
       throw new RuntimeException(ex);
      }
     }
    }
   });

   closeButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
     ConnectURI koneksisaya = new ConnectURI();
     URL myAddress = koneksisaya.buildURL("http://harber.mimoapps.xyz/api/getaccess.php");
     String response = null;
     try {
      response = koneksisaya.getResponseFromHttpUrl(myAddress);
     } catch (IOException ex) {
      throw new RuntimeException(ex);
     }
     System.out.println("");
     System.out.println(response);
     System.out.println("");
     System.out.println("");
     System.out.println("*  terimakasih  *");
     System.exit(0);
    }
   });
   login.setVisible(true);
  }
  public static void main(String[] args) {
   new Login();
  }


  }
 }

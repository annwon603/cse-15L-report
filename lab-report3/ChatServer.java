import java.io.IOException;
import java.net.URI;
import java.util.*;
class Handler implements URLHandler{
    // ArrayList<String> text = new ArrayList<String>();  //Should store user and message thus size is 2
    ArrayList<String> message = new ArrayList<String>();
    ArrayList<String> user = new ArrayList<String>();
    public String handleRequest(URI url){
        if(url.getPath().equals("/")){  //detects path
            if(user.size() == 0 && message.size() == 0){
                return "Nothing";
            }else{

                StringBuilder result = new StringBuilder();
                
                for(int i = 0; i< message.size()-1; i++){
                    result.append(user.get(i)).append(" : ").append(message.get(i)).append("\n");
                }
                return result.toString();
            }
        }else{
            if(url.getPath().contains("/add-message")){
                String query = url.getQuery(); //string contains the query after the ?
                String[] parameters = query.split("&"); //splits the message and user
                for (String words : parameters) { //iterate through the two elements in parameters[]
                    String[] userInput = words.split("=");
                    if (userInput[0].equals("s")) { //words inputed after s
                        message.add(userInput[1]);
                    } else {
                        if (userInput[0].equals("user")){ //words inputed after user
                            user.add(userInput[1]);
                        }
                    }
                }
                return "User and Message added";
            }
            return "404 Not Found:";
        }
    }

}

class ChatServer {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
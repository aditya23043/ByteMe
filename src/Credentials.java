import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Credentials {

    private String filename = "/home/adi/repo/ByteMe/src/database/pass.db";
    private ArrayList<UserPass> cred_list = new ArrayList<>();

    Credentials() {
        cred_list = this.read();
    }

    public void sample_data() {
        this.write("adi", "something");
        this.write("someone", "something");
    }

    public ArrayList<UserPass> read() {

        FileInputStream file_in;
        ObjectInputStream in;

        try {
            file_in = new FileInputStream(filename);
            in = new ObjectInputStream(file_in);
            cred_list = (ArrayList<UserPass>) in.readObject();
        } 
        catch(EOFException e){
        }
        catch (IOException | ClassNotFoundException e) {
            Util.throw_error("IO Exception!");
            e.printStackTrace();
            System.exit(-1);
        }

        return cred_list;
    }

    public void write(String username, String password) {

        cred_list.add(new UserPass(username, password));

        FileOutputStream file_out;
        ObjectOutputStream out;

        try {
            file_out = new FileOutputStream(filename);
            out = new ObjectOutputStream(file_out);
            out.writeObject(cred_list);
        } 
        catch (IOException e) {
            Util.throw_error("IO Exception!");
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public boolean authenticate(String username, String password){
        for (UserPass user_pass : cred_list) {
            if (user_pass.username.equals(username) && user_pass.password.equals(password)) {
                return true;
            }
            else if (cred_list.getLast().equals(user_pass)) {
                return false;
            }
        }
        return false;
    }

    protected ArrayList<UserPass> get_list() {
        return this.cred_list;
    }

}

class UserPass implements Serializable {
    String username;
    String password;

    UserPass(String _username, String _password) {
        this.username = _username;
        this.password = _password;
    }
}

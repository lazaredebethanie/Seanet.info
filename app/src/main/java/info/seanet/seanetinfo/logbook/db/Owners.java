package info.seanet.seanetinfo.logbook.db;

/**
 * Created by andre on 22/11/15.
 */
public class Owners {

    private int owner_id;
    private String name;
    private String address;
    private String email;
    private int savedFlag;
    private String saved;

    public Owners(){}

    public Owners(int owner_id,String name,String address, String email, int savedFlag, String saved){
        this.owner_id=owner_id;
        this.name=name;
        this.address=address;
        this.email=email;
        this.savedFlag=savedFlag;
        this.saved=saved;
    }

    public int getId() {
        return owner_id;
    }

    public void setId(int owner_id) {
        this.owner_id=owner_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSavedFlag() {
        return savedFlag;
    }

    public void setSavedFlag(int savedFlag) {
        this.savedFlag = savedFlag;
    }

    public String getSaved() {
        return saved;
    }

    public void setSaved(String saved) {
        this.saved = saved;
    }

    public String toString(){
        return owner_id+";"+name+";"+address+";"+email+";"+savedFlag+";"+saved;
    }



}

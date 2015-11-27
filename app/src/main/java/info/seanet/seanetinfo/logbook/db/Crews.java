package info.seanet.seanetinfo.logbook.db;

/**
 * Created by andre on 27/11/15.
 */
public class Crews {
    private int crew_id;
    private String name;
    private String list;
    private int savedFlag;
    private String saved;

    public Crews(){}

    public Crews(int crew_id,String name,String list, int savedFlag, String saved){
        this.crew_id=crew_id;
        this.name=name;
        this.list=list;
        this.savedFlag=savedFlag;
        this.saved=saved;
    }

    public int getId() {
        return crew_id;
    }

    public void setId(int crew_id) {
        this.crew_id=crew_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getList() {
        return list;
    }

    public void setList(String list) {
        this.list = list;
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
        return crew_id+";"+name+";"+list+";"+savedFlag+";"+saved;
    }




}

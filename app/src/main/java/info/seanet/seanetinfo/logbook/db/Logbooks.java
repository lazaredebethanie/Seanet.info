package info.seanet.seanetinfo.logbook.db;

/**
 * Created by andre on 23/11/15.
 */
public class Logbooks {
    private int logbook_id;
    private String nameLog;
    private String boat;
    private String phone;
    private String reg_nr;
    private String fra_nr;
    private String ins_pol;
    private String ins_cie;
    private String cap_email;
    private String harbour;
    private String callsign;
    private String length;
    private String beam;
    private String draught;
    private String tonnage;
    private int owner_id;
    private String created;
    private String closed;
    private int savedFlag;
    private String saved;

    public Logbooks(){}

    public Logbooks(int logbook_id,String nameLog, String boat, String phone, String reg_nr, String fra_nr,
                    String ins_pol, String ins_cie, String cap_email, String harbour,
                    String callsign, String length, String beam, String draught, String tonnage,
                    int owner_id, String created, String closed, int savedFlag, String saved){
        this.logbook_id=logbook_id;
        this.nameLog=nameLog;
        this.boat=boat;
        this.phone=phone;
        this.reg_nr=reg_nr;
        this.fra_nr=fra_nr;
        this.ins_pol=ins_pol;
        this.ins_cie=ins_cie;
        this.cap_email=cap_email;
        this.harbour=harbour;
        this.callsign=callsign;
        this.length=length;
        this.beam=beam;
        this.draught=draught;
        this.tonnage=tonnage;
        this.owner_id=owner_id;
        this.created=created;
        this.closed=closed;
        this.savedFlag=savedFlag;
        this.saved=saved;
    }

    public int getId() {
        return logbook_id;
    }

    public void setId(int logbook_id) {
        this.logbook_id=logbook_id;
    }

    public String getNameLog() {
        return nameLog;
    }

    public void setNameLog(String nameLog) {
        this.nameLog = nameLog;
    }

    public String getBoat() {
        return boat;
    }

    public void setBoat(String boat) {
        this.boat = boat;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRegNR() {
        return reg_nr;
    }

    public void setRegNR(String reg_nr) {
        this.reg_nr = reg_nr;
    }

    public String getFraNR() {
        return fra_nr;
    }

    public void setFraNR(String fra_nr) {
        this.fra_nr = fra_nr;
    }

    public String getInsPol() {
        return ins_pol;
    }

    public void setInsPol(String ins_pol) {
        this.ins_pol = ins_pol;
    }

    public String getInsCie() {
    return ins_cie;
}

    public void setInsCie(String ins_cie) {
        this.ins_cie = ins_cie;
    }

    public String getCapEmail() {
        return cap_email;
    }

    public void setCapEmail(String cap_email) {
        this.cap_email = cap_email;
    }

    public String getHarbour() {
        return harbour;
    }

    public void setHarbour(String harbour) {
        this.harbour = harbour;
    }

    public String getRadioCall() {
        return callsign;
    }

    public void setRadioCall(String callsign) {
        this.callsign = callsign;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getBeam() {
        return beam;
    }

    public void setBeam(String beam) {
        this.beam = beam;
    }

    public String getDraught() {
        return draught;
    }

    public void setDraught(String draught) {
        this.draught = draught;
    }

    public String getTonnage() {
        return tonnage;
    }

    public void setTonnage(String tonnage) {
        this.tonnage = tonnage;
    }

    public int getOwnerId() {
        return owner_id;
    }

    public void setOwnerId(int owner_id) {
        this.owner_id = owner_id;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getClosed() {
        return closed;
    }

    public void setClosed(String closed) {
        this.closed = closed;
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
        return owner_id+";"+nameLog+";"+boat+";"+phone+";"+reg_nr+";"+fra_nr+";"+ins_pol+";"+
                ins_pol+";"+ins_cie+";"+cap_email+";"+harbour+";"+callsign+";"+length+";"+beam+";"+
                draught+";"+tonnage+";"+owner_id+";"+created+";"+closed+";"+savedFlag+";"+saved;
    }




}

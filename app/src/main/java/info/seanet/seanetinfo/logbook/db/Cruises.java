package info.seanet.seanetinfo.logbook.db;

/**
 * Created by andre on 23/11/15.
 */
public class Cruises {
    private int cruises_id;
    private String nameCruise;
    private String startDate;
    private String endDate;
    private String from;
    private String to;
    private String fromHarbour;
    private String toHarbour;
    private String captain;
    private int crew_id;
    private String expectedWeather;
    private String waterLevels;
    private String fuel;
    private String atStop;
    private int logbook_id;
    private int savedFlag;
    private String saved;

    public Cruises(){}

    public Cruises(int cruises_id, String nameCruise, String startDate, String endDate, String from,
                   String to, String fromHarbour, String toHarbour, String captain, int crew_id,
                   String expectedWeather, String waterLevels, String fuel, String atStop,
                   int logbook_id, int savedFlag, String saved){
        this.cruises_id=cruises_id;
        this.nameCruise=nameCruise;
        this.startDate=startDate;
        this.endDate=endDate;
        this.from=from;
        this.to=to;
        this.fromHarbour=fromHarbour;
        this.toHarbour=toHarbour;
        this.captain=captain;
        this.crew_id=crew_id;
        this.expectedWeather=expectedWeather;
        this.waterLevels=waterLevels;
        this.fuel=fuel;
        this.atStop=atStop;
        this.logbook_id=logbook_id;
        this.savedFlag=savedFlag;
        this.saved=saved;
    }

    public int getId() {
        return cruises_id;
    }

    public void setId(int cruises_id) {
        this.cruises_id=cruises_id;
    }

    public String getNameCruise() {
        return nameCruise;
    }

    public void setNameCruise(String nameCruise) {
        this.nameCruise = nameCruise;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFromHarbour() {
        return fromHarbour;
    }

    public void setFromHarbour(String fromHarbour) {
        this.fromHarbour = fromHarbour;
    }

    public String getToHarbour() {
    return toHarbour;
}

    public void setToHarbour(String toHarbour) {
        this.toHarbour = toHarbour;
    }

    public String getCaptain() {
        return captain;
    }

    public void setCaptain(String captain) {
        this.captain = captain;
    }

    public int getCrew_id() {
        return crew_id;
    }

    public void setCrew_id(int crew_id) {
        this.crew_id = crew_id;
    }

    public String getExpectedWeather() {
        return expectedWeather;
    }

    public void setExpectedWeather(String expectedWeather) {
        this.expectedWeather = expectedWeather;
    }

    public String getWaterLevels() {
        return waterLevels;
    }

    public void setWaterLevels(String waterLevels) {
        this.waterLevels = waterLevels;
    }
    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public String getAtStop() {
        return atStop;
    }

    public void setAtStop(String atStop) {
        this.atStop = atStop;
    }

    public int getLogbook_id() {
        return logbook_id;
    }

    public void setLogbook_id(int logbook_id) {
        this.logbook_id = logbook_id;
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
        return cruises_id+";"+nameCruise+";"+startDate+";"+endDate+";"+from+";"+to+";"+fromHarbour+";"+
                toHarbour+";"+captain+";"+crew_id+";"+expectedWeather+";"+waterLevels+";"+fuel+";"+atStop+";"+
                logbook_id+";"+savedFlag+";"+saved;
    }




}

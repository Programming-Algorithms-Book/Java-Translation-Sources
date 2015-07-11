public class TranslationType {
    private String firstString;
    private String secondString;
    
    public TranslationType(String firstString, String secondString) {
        this.setFirstString(firstString);
        this.setSecondString(secondString);
    }

    public String getFirstString() {
        return this.firstString;
    }

    public void setFirstString(String firstString) {
        this.firstString = firstString;
    }

    public String getSecondString() {
        return this.secondString;
    }

    public void setSecondString(String secondString) {
        this.secondString = secondString;
    }
}

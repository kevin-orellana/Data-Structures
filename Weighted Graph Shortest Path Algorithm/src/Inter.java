public class Inter{
    String name;
    String reverseName;
    String firstCrossStreet;
    String secondCrossStreet;

    public Inter(String firstCrossStreet, String secondCrossStreet){
        this.firstCrossStreet = firstCrossStreet;
        this.secondCrossStreet = secondCrossStreet;
        this.name = firstCrossStreet+secondCrossStreet;
        this.reverseName = secondCrossStreet+firstCrossStreet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstCrossStreet() {
        return firstCrossStreet;
    }

    public void setFirstCrossStreet(String firstCrossStreet) {
        this.firstCrossStreet = firstCrossStreet;
    }


    public String getSecondCrossStreet() {
        return secondCrossStreet;
    }

    public void setSecondCrossStreet(String secondCrossStreet) {
        this.secondCrossStreet = secondCrossStreet;
    }


    public boolean intersects(String street){
        if ((street == this.firstCrossStreet) || (this.secondCrossStreet == street)){
            return true;
        }
        return false;
    }


    public boolean sameAs(Inter other){
        return ((this.name == other.name) || (this.reverseName == other.name));
    }


        @Override
    public String toString(){
        return (firstCrossStreet + secondCrossStreet);
    }

}

public class Intersection{

    String name;

    String crossStreet;

    public Intersection(String streetName){
        this.name = streetName;
    }

    public void crossStreet(String street){
        this.crossStreet = street;
    }

    public boolean intersects(String street){
        if (street == this.crossStreet){
            return true;
        }
        return false;
    }

    @Override
    public String toString(){
        return (name + "," + crossStreet);
    }

}

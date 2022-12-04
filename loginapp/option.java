package loginapp;


public enum option {
    Admin, Korisnik;

    private option () {}
    public String value() {
        return name();
    }
    public static option fromvalue(String v){
        return valueOf(v);
    }

}

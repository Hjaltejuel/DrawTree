public enum States {
    MAX("Max"),
    MIN("Min"),
    RANDOM("Chance");

    private String name;

    States(String name){
        this.name = name;
    }


    public String getName() {
        return name;
    }
}

package domain;

public class Customer {
    public String getId() {

        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    private String id;
    private String name;
    private String email;

    public Customer(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}

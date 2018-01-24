package reddec.alexei.phonebookreddec.model;

public class ModelPhonebook {

    private Integer id;
    private String name;
    private String surname;
    private String phone1;
    private String phone2;
    private String phone3;

    public ModelPhonebook(Integer id, String name, String surname, String phone1, String phone2, String phone3) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.phone3 = phone3;
    }

    public Integer getId() {
        return id;
    }

    public String getPhone1() {
        return phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public String getPhone3() {
        return phone3;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

}
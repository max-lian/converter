package yakovlev.m.converter.model;

public class Student {
    private String latinFirstName;
    private String latinLastName;
    private String uaFirstName;
    private String uaLastName;
    private String email;
    private String password;

    public Student() { }

    private Student(Builder builder) {
        this.latinFirstName = builder.latinFirstName;
        this.latinLastName = builder.latinLastName;
        this.uaFirstName = builder.uaFirstName;
        this.uaLastName = builder.uaLastName;
        this.email = builder.email;
        this.password = builder.password;
    }

    public static Builder builder() {
        return new Builder();
    }


    public String getLatinFirstName() {
        return latinFirstName;
    }

    private void setLatinFirstName(String latinFirstName) {
        this.latinFirstName = latinFirstName;
    }

    public String getLatinLastName() {
        return latinLastName;
    }

    public void setLatinLastName(String latinLastName) {
        this.latinLastName = latinLastName;
    }

    public String getUaFirstName() {
        return uaFirstName;
    }

    public void setUaFirstName(String uaFirstName) {
        this.uaFirstName = uaFirstName;
    }

    public String getUaLastName() {
        return uaLastName;
    }

    public void setUaLastName(String uaLastName) {
        this.uaLastName = uaLastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Student{" +
                "latinFirstName='" + latinFirstName + '\'' +
                ", latinLastName='" + latinLastName + '\'' +
                ", uaFirstName='" + uaFirstName + '\'' +
                ", uaLastName='" + uaLastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }


    public static class Builder {
        private String latinFirstName;
        private String latinLastName;
        private String uaFirstName;
        private String uaLastName;
        private String email;
        private String password;

        private Builder() {
        }

        public Builder setLatinFirstName(String latinFirstName) {
            this.latinFirstName = latinFirstName;
            return this;
        }

        public Builder setLatinLastName(String latinLastName) {
            this.latinLastName = latinLastName;
            return this;
        }

        public Builder setUaFirstName(String uaFirstName) {
            this.uaFirstName = uaFirstName;
            return this;
        }

        public Builder setUaLastName(String uaLastName) {
            this.uaLastName = uaLastName;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder of(Student student) {
            this.latinFirstName = student.latinFirstName;
            this.latinLastName = student.latinLastName;
            this.uaFirstName = student.uaFirstName;
            this.uaLastName = student.uaLastName;
            this.email = student.email;
            this.password = student.password;
            return this;
        }

        public Student build() {
            return new Student(this);
        }
    }
}

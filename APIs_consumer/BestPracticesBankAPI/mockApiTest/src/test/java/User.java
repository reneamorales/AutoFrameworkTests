import java.util.Map;

public class User {

        private String name;
        private String password;
        private String email;
        private int id;
        private Map<String, Object> account;
    public User(){

    }
        public User(String name, String password, String email, int id, Map<String, Object> account) {
            this.name = name;
            this.password = password;
            this.email = email;
            this.id = id;
            this.account = account;
        }

        // Getters and Setters for all attributes
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Map<String, Object> getAccount() {
            return account;
        }

        public void setAccount(Map<String, Object> account) {
            this.account = account;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", password='" + password + '\'' +
                    ", email='" + email + '\'' +
                    ", id=" + id +
                    ", account=" + account +
                    '}';
        }
    }


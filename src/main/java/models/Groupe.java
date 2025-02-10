package models;

import javax.persistence.*;
import java.util.Set;
    @Entity
    public class Groupe {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String name;

        @OneToMany(mappedBy = "groupe")
        private Set<Canal> canals;

        @OneToMany(mappedBy = "groupe")
        private Set<User> usersSet;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Set<Canal> getCanals() {
            return canals;
        }

        public void setCanals(Set<Canal> canals) {
            this.canals = canals;
        }

        public Set<User> getUsersSet() {
            return usersSet;
        }

        public void setUsersSet(Set<User> usersSet) {
            this.usersSet = usersSet;
        }
    }

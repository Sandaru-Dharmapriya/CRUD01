/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.util.ArrayList;
import java.util.List;
import model.Person;

public class PersonDAO {
    private static List<Person> people = new ArrayList<>();
    private static int nextId = 1; 

    
    static {
        people.add(new Person(nextId++, "K.G.M.Athukorala", "0774822299", "Piliyandala,Colombo"));
    }

    
    public List<Person> getAllPeople() {
        return people;
    }

    public Person getPersonById(int id) {
        for (Person person : people) {
            if (person.getId() == id) {
                return person;
            }
        }
        return null;
    }

    public void addPerson(Person person) {
        person.setId(nextId++); 
        people.add(person);
    }

    public void updatePerson(Person updatedPerson) {
        for (int i = 0; i < people.size(); i++) {
            if (people.get(i).getId() == updatedPerson.getId()) {
                people.set(i, updatedPerson);
                return;
            }
        }
        
    }

    public void deletePerson(int id) {
        people.removeIf(person -> person.getId() == id);
    }

    public int getNextPersonID() {
        return nextId; 
    }
}


package controller;

import models.Client;
import services.ClientService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

@ManagedBean(name = "clientController", eager = true)
@RequestScoped
public class ClientController implements Serializable{
    private static final long serialVersionUID = 1L;
    private ClientService clientService;
    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }

    private int id;
    private String surname;
    private String name;
    private String patronymic;
    private Date birthday;
    private int accNumber;
    private double balance;
    private boolean canEdit = false;

    private String message = "Add client";

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getAccNumber() {
        return accNumber;
    }

    public void setAccNumber(int accNumber) {
        this.accNumber = accNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public boolean isCanEdit() {
        return canEdit;
    }

    public void setCanEdit(boolean canEdit) {
        this.canEdit = canEdit;
    }

    public ArrayList<Client> getAllClients(){
        ArrayList<Client> clients = (ArrayList) clientService.getAllClients();
        return clients;

    }
    public String deleteClient(int id) {
        clientService.deleteClient(id);
        return "";
    }
    public String addClick(){
        id = 0;
        surname = null;
        name = null;
        patronymic = null;
        birthday = null;
        accNumber = 0;
        balance = 0;
        canEdit = false;
        message = "Add client";
        return "";
    }
    public String editClick(int id){
        message = "Edit Client";
        canEdit = true;
        Client client = clientService.getClientById(id);
        this.id = id;
        surname = client.getSurname();
        name = client.getName();
        patronymic = client.getPatronymic();
        birthday = client.getBirthday();
        accNumber = client.getAccNumber();
        balance = client.getBalance();
        return "";
    }
    public String saveClient() {
        Client client = new Client(id, surname, name, patronymic, birthday, accNumber, balance);
        System.out.println(client);
        if (canEdit){
            editClient(client);
        }
        else {
            addClient(client);
        }
        id = 0;
        surname = null;
        name = null;
        patronymic = null;
        birthday = null;
        accNumber = 0;
        balance = 0;
        canEdit = false;
        message = "Add client";
        return "";
    }
    private void addClient(Client client){
        clientService.addClient(client);
    }
    private void editClient(Client client){
        clientService.updateClient(client);
    }
}

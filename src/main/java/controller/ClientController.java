package controller;

import models.Client;
import org.primefaces.context.RequestContext;
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

    private Client client = new Client();
    private boolean canEdit = false;
    private String message = "Add client";

    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        message = message;
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
    public String deleteClient(Client client) {
        clientService.deleteClient(client.getId());
        return "";
    }
    public String cancelClick(){
        client = new Client();
        canEdit = false;
        message = "Add client";
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('dlg').hide();");
        return "";
    }
    public String editClick(Client oldClient){
        client = oldClient;
        message = "Edit Client";
        canEdit = true;
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('dlg').show();");
        return "";
    }
    public String saveClient() {
        if (canEdit){
            editClient(client);
        }
        else {
            addClient(client);
        }
        client = new Client();
        message = "Add client";
        canEdit = false;
        return "";
    }
    private void addClient(Client client){
        clientService.addClient(client);
    }
    private void editClient(Client client){
        clientService.updateClient(client);
    }
}

package controller;

import services.ClientService;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("idValidator")
public class IdValidator implements Validator {
    private ClientService clientService;
    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        String id = o.toString();
        if (clientService.clientExists(Integer.parseInt(id))) {
            FacesMessage msg = new FacesMessage("ID validation failed", "ID already exists");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }
}

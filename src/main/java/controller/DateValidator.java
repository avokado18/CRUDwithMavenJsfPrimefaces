package controller;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.Date;

@FacesValidator("dateValidator")
public class DateValidator implements Validator {
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        Date date = (Date) o;
        Date minDate = new Date(0, 0, 1);
        Date maxDate = new Date();
        if ((date.before(minDate)) || (date.after(maxDate))) {
            FacesMessage msg = new FacesMessage("Incorrect Date!! Please, input date between "+minDate+" and "+maxDate+" in format dd.mm.yyyy");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }
}

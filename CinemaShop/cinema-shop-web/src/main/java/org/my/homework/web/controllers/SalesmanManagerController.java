package org.my.homework.web.controllers;

import org.my.homework.app.dao.AuthDAO;
import org.my.homework.app.dao.CommonDAO;
import org.my.homework.app.entities.IUser;
import org.my.homework.app.entities.Showing;
import org.my.homework.app.entities.User;
import org.my.homework.app.entities.UserNamedQuery;
import org.primefaces.context.RequestContext;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.util.List;

/**
 * Created by Alexander on 01-May-16.
 */
@ManagedBean(name = "salesmanManagerController")
public class SalesmanManagerController extends BaseTableController{

    private List<IUser> salesmans;

    @EJB
    private CommonDAO commonDAO;
    @EJB
    private AuthDAO authDAO;

    public List<IUser> getSalesmans() {
        return salesmans;
    }

    @PostConstruct
    private void init(){
        FacesContext context = FacesContext.getCurrentInstance();
        User user = (User)context.getExternalContext().getSessionMap().get("user");
        if(user != null) {
            salesmans = authDAO.getSalesmans(user.getId());
        }
    }

    public void setSalesmans(List<IUser> salesmans) {
        this.salesmans = salesmans;
    }

    public void onDeleteRow() {
        if(getSelectedEntity() != null) {
            commonDAO.delete(getSelectedEntity());
            salesmans.remove(getSelectedEntity());
        }
    }

    public void onSaveRow(User entity){
        boolean canSave = false;
        FacesMessage message = null;

        salesmans.add(entity);
        commonDAO.saveOrUpdate(entity);

        canSave = true;
        RequestContext context = RequestContext.getCurrentInstance();
        message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Saved successfully", "");
        FacesContext.getCurrentInstance().addMessage(null, message);
        context.addCallbackParam("saveResult", canSave);
    }
}

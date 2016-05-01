package org.my.homework.web.controllers;

import org.my.homework.app.entities.CommonEntity;
import org.my.homework.app.entities.Movie;
import org.primefaces.event.SelectEvent;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 * Created by Alexander on 30-Apr-16.
 */
public class  BaseTableController <T extends CommonEntity>{

    private T selectedEntity = null;

    public T getSelectedEntity() {
        return selectedEntity;
    }

    public void setSelectedEntity(T selectedEntity) {
        this.selectedEntity = selectedEntity;
    }

    public void onRowSelect(SelectEvent event) {
        selectedEntity = (T)event.getObject();
        FacesMessage msg = new FacesMessage("Selected", String.valueOf(selectedEntity.getId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}

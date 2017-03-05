/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.notifications;

import com.diageo.admincontrollerweb.beans.ParameterBeanLocal;
import com.diageo.admincontrollerweb.beans.UserBeanLocal;
import com.diageo.admincontrollerweb.entities.DwUsers;
import com.diageo.admincontrollerweb.enums.ParameterKeyEnum;
import com.diageo.admincontrollerweb.enums.ProfileEnum;
import com.diageo.admincontrollerweb.enums.StatusSystemMDM;
import com.diageo.admincontrollerweb.exceptions.ControllerWebException;
import static com.diageo.diageomdmweb.bean.DiageoRootBean.capturarValor;
import com.diageo.diageomdmweb.mail.EMail;
import com.diageo.diageomdmweb.mail.templates.VelocityTemplate;
import com.diageo.diageonegocio.beans.ChainUserBeanLocal;
import com.diageo.diageonegocio.beans.OutletsUserBeanLocal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Stateless;

/**
 *
 * @author EDUARDO
 */
@Stateless
public class NotificationTimerBean {

    @EJB
    private ParameterBeanLocal parameterBeanLocal;
    @EJB
    private ChainUserBeanLocal chainUserBeanLocal;
    @EJB
    private OutletsUserBeanLocal outletsUserBeanLocal;
    @EJB
    private UserBeanLocal userBeanLocal;

    @Schedule(dayOfWeek = "Mon", month = "*", hour = "4", year = "*", minute = "1", second = "0")
    //@Schedule(dayOfWeek = "*", month = "*", hour = "*", year = "*", minute = "12", second = "30")
    public void myTimer() {
        try {
            String pathMail = parameterBeanLocal.findByKey(ParameterKeyEnum.PATH_MAIL_TEMPLATE.name()).get(0).getParameterValue();
            List<DwUsers> list = userBeanLocal.findAll();
            for (DwUsers user : list) {
                if (user.getStateUser().equals("1")) {
                    String name = user.getNameUser().toUpperCase() + " " + user.getLastName().toUpperCase();
                    String rol = user.getDistri1() != null ? user.getDistri1().toUpperCase() : "";
                    if (user.getProfileId().getProfileId().equals(ProfileEnum.COMMERCIAL_MANAGER.getId())) {
                        long quantity = outletsUserBeanLocal.notificationPendingOutlet(user.getUserId(), StatusSystemMDM.PENDING_APPROVAL.name());
                        if (quantity > 0) {
                            sendMail(name, rol, quantity, pathMail, user.getEmailUser());
                        } else {
                            sendMail(name, rol, pathMail, user.getEmailUser());
                        }
                    } else if (user.getProfileId().getProfileId().equals(ProfileEnum.TMC_DISTRIBUIDORES.getId())) {
                        long quantity = outletsUserBeanLocal.notificationPendingOutlet(user.getUserId(), StatusSystemMDM.PENDING_TMC.name());
                        if (quantity > 0) {
                            sendMail(name, rol, quantity, pathMail, user.getEmailUser());
                        } else {
                            sendMail(name, rol, pathMail, user.getEmailUser());
                        }
                    } else if (user.getProfileId().getProfileId().equals(ProfileEnum.TMC_CADENAS.getId()) || user.getProfileId().getProfileId().equals(ProfileEnum.KAM_CADENAS.getId())) {
                        long quantity = chainUserBeanLocal.notificationPendingChain(user.getUserId(), StatusSystemMDM.PENDING_KAM_TMC_CHAINS.name());
                        if (quantity > 0) {
                            sendMail(name, rol, quantity, pathMail, user.getEmailUser());
                        } else {
                            sendMail(name, rol, pathMail, user.getEmailUser());
                        }
                    } else if (user.getProfileId().getProfileId().equals(ProfileEnum.KAM.getId())) {
                        long quantity = chainUserBeanLocal.notificationPendingChain(user.getUserId(), StatusSystemMDM.PENDING_APPROVAL.name());
                        if (quantity > 0) {
                            sendMail(name, rol, quantity, pathMail, user.getEmailUser());
                        } else {
                            sendMail(name, rol, pathMail, user.getEmailUser());
                        }
                    } else if (user.getProfileId().getProfileId().equals(ProfileEnum.ADMINISTRATOR.getId())
                            || user.getProfileId().getProfileId().equals(ProfileEnum.DATA_STEWARD.getId())) {
                        List<String> statusChain = new ArrayList<>();
                        statusChain.add(StatusSystemMDM.PENDING_APPROVAL.name());
                        statusChain.add(StatusSystemMDM.PENDING_KAM_TMC_CHAINS.name());
                        long quantityChains = chainUserBeanLocal.notificationPendingChainIn(statusChain);
                        List<String> statusOutlets = new ArrayList<>();
                        statusOutlets.add(StatusSystemMDM.PENDING_APPROVAL.name());
                        statusOutlets.add(StatusSystemMDM.PENDING_TMC.name());
                        long quantityOutlets = outletsUserBeanLocal.notificationPendingOutlet(statusOutlets);
                        long totally = quantityOutlets + quantityChains;
                        System.out.println(totally);
                        if (totally > 0) {
                            sendMail(name, rol, totally, pathMail, user.getEmailUser());
                        }
                    }
                }
            }
        } catch (ControllerWebException ex) {
            Logger.getLogger(NotificationTimerBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void sendMail(String name, String rol, long quantity, String pathMail, String eMail) {
        EMail mail = new EMail();
        String msg = VelocityTemplate.notificationQuantity(name, rol, quantity, pathMail);
        mail.send(new String[]{eMail}, "Outlet Database", msg);
    }

    private void sendMail(String name, String rol, String pathMail, String eMail) {
        EMail mail = new EMail();
        String msg = VelocityTemplate.notificationQuantityZero(name, rol, pathMail);
        mail.send(new String[]{eMail}, "Outlet Database", msg);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.bean.massiveload;

import com.diageo.diageomdmweb.bean.DiageoRootBean;
import com.diageo.diageonegocio.beans.OutletBeanLocal;
import com.diageo.diageonegocio.entidades.Db3party;
import com.diageo.diageonegocio.entidades.DbOcs;
import com.diageo.diageonegocio.entidades.DbOutlets;
import com.diageo.diageonegocio.entidades.DbOwners;
import com.diageo.diageonegocio.entidades.DbPotentials;
import com.diageo.diageonegocio.entidades.DbSubSegments;
import com.diageo.diageonegocio.entidades.DbTowns;
import com.diageo.diageonegocio.exceptions.DiageoBusinessException;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author EDUARDO
 */
@Named
//@ViewScoped
@RequestScoped
public class MassiveLoadBean extends DiageoRootBean {

    private static final int LENGTH_RECORD = 16;
    @EJB
    private OutletBeanLocal outletBeanLocal;
    private String log;

    /**
     * Creates a new instance of MassiveLoadBean
     */
    public MassiveLoadBean() {
        setLog("");
    }

    public void load(FileUploadEvent file) {
        UploadedFile uf = file.getFile();
        String str = new String(uf.getContents(), StandardCharsets.UTF_8);
        massiveLoad(str);
    }

    public void massiveLoad(String text) {
        String[] arr = text.split("\n");
        for (int i = 0; i < arr.length; i++) {
            String string = arr[i];
            String[] rows = string.split("\\|");
            if (rows != null && rows.length != LENGTH_RECORD) {
                if (getLog().isEmpty()) {
                    setLog("No se puedo cargar el archivo, posibles causas en las líneas ");
                }
                setLog(getLog() + (i + 1) + ", ");
            } else {
                for (int index = 0; index < arr.length; index++) {
                    try {
                        String str = arr[index];
                        String[] rows_ = str.split("\\|");
                        DbOutlets outlet = new DbOutlets();
                        outlet.setBusinessName(rows_[0]);
                        outlet.setOutletName(rows_[1]);
                        outlet.setNit(rows_[2]);
                        outlet.setVerificationNumber(rows_[3]);
                        DbSubSegments subSegment = new DbSubSegments(Integer.parseInt(rows_[4]));
                        outlet.setSubSegmentId(subSegment);
                        DbPotentials potential = new DbPotentials(Integer.parseInt(rows_[5]));
                        outlet.setPotentialId(potential);
                        if (!rows_[6].isEmpty()) {
                            DbTowns town = new DbTowns(Integer.parseInt(rows_[6]));
                            outlet.setTownId(town);
                        }
                        outlet.setAddress(rows_[7]);
                        outlet.setEmail(rows_[8]);
                        outlet.setNumberPdv(rows_[9]);
                        outlet.setKiernanId(rows_[10]);
                        if (!rows_[11].isEmpty()) {
                            DbOwners owner = new DbOwners(Integer.parseInt(rows_[11]));
                            outlet.setOwnerId(owner);
                        }
                        if (!rows_[12].isEmpty()) {
                            DbOcs ocs_1 = new DbOcs(Integer.parseInt(rows_[12]));
                            outlet.setOcsPrimary(ocs_1);
                        }
                        if (!rows_[13].isEmpty()) {
                            DbOcs ocs_2 = new DbOcs(Integer.parseInt(rows_[13]));
                            outlet.setOcsSecondary(ocs_2);
                        }
                        outlet.setWebsite(rows_[14]);
                        if (!rows_[15].isEmpty()) {
                            
                        }
                    } catch (Exception e) {
                        if (getLog().isEmpty()) {
                            setLog("No se puedo cargar el archivo, posibles causas en las líneas ");
                        }
                        setLog(getLog() + index + ", ");
                    }
                }
            }
        }
        if (getLog().isEmpty()) {
            List<DbOutlets> outletList = new ArrayList<>();
            for (int i = 0; i < arr.length; i++) {
                String string = arr[i];
                String[] rows = string.split("\\|");
                DbOutlets outlet = new DbOutlets();
                outlet.setBusinessName(rows[0]);
                outlet.setOutletName(rows[1]);
                outlet.setNit(rows[2]);
                outlet.setVerificationNumber(rows[3]);
                DbSubSegments subSegment = new DbSubSegments(Integer.parseInt(rows[4]));
                outlet.setSubSegmentId(subSegment);
                DbPotentials potential = new DbPotentials(Integer.parseInt(rows[5]));
                outlet.setPotentialId(potential);
                if (!rows[6].isEmpty()) {
                    DbTowns town = new DbTowns(Integer.parseInt(rows[6]));
                    outlet.setTownId(town);
                }
                outlet.setAddress(rows[7]);
                outlet.setEmail(rows[8]);
                outlet.setNumberPdv(rows[9]);
                outlet.setKiernanId(rows[10]);
                if (!rows[11].isEmpty()) {
                    DbOwners owner = new DbOwners(Integer.parseInt(rows[11]));
                    outlet.setOwnerId(owner);
                }
                if (!rows[12].isEmpty()) {
                    DbOcs ocs_1 = new DbOcs(Integer.parseInt(rows[12]));
                    outlet.setOcsPrimary(ocs_1);
                }
                if (!rows[13].isEmpty()) {
                    DbOcs ocs_2 = new DbOcs(Integer.parseInt(rows[13]));
                    outlet.setOcsSecondary(ocs_2);
                }
                outlet.setWebsite(rows[14]);
                if (!rows[15].isEmpty()) {
                   
                }
                outletList.add(outlet);
            }
            for (DbOutlets out : outletList) {
                try {
                    outletBeanLocal.createOutlet(out);
                } catch (DiageoBusinessException ex) {
                    Logger.getLogger(MassiveLoadBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            RequestContext.getCurrentInstance().execute("PF('dlgLog').show();");
            //downloadFile(log);
        }
    }

    public void downloadFile(String log) {
        try {
            Path pathTemp = Files.createTempFile("log_", ".txt");
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.setContentType("APPLICATION/OCTET-STREAM");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + "log" + "\"");
            try (FileOutputStream fos = new FileOutputStream(pathTemp.toFile());
                    FileInputStream fis = new FileInputStream(pathTemp.toFile());) {
                fos.write(log.getBytes());
                int length;
                while ((length = fis.read()) != -1) {
                    response.getOutputStream().write(log.getBytes(), 0, length);
                }
            } catch (IOException ex) {
                Logger.getLogger(MassiveLoadBean.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (IOException ex) {
            Logger.getLogger(MassiveLoadBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * @return the log
     */
    public String getLog() {
        return log;
    }

    /**
     * @param log the log to set
     */
    public void setLog(String log) {
        this.log = log;
    }

}

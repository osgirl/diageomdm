/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.athenaout.timer;

import com.diageo.athenaout.bean.FileT040;
import com.diageo.athenaout.bean.FileT042;
import com.diageo.athenaout.bean.FileT1110;
import com.diageo.athenaout.bean.FileT41;
import com.diageo.athenaout.dto.T040Dto;
import com.diageo.athenaout.dto.T041Dto;
import com.diageo.athenaout.dto.T042PartyAddrDto;
import com.diageo.athenaout.dto.T1110Dto;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.Schedule;
import javax.ejb.Timer;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author EDUARDO
 */
@Stateless
public class BuildFilesTimer implements IBuildFIlesTimer {

    private static final Logger LOG = Logger.getLogger(BuildFilesTimer.class.getName());
    @EJB
    private FileT040 fileT040;
    @EJB
    private FileT41 fileT41;
    @EJB
    private FileT042 buildFileT042;
    @EJB
    private FileT1110 fileT1110;

    @Schedule(dayOfMonth = "*", dayOfWeek = "*", hour = "*", minute = "*", month = "*", year = "*", second = "30")
    @Override
    public void execute(Timer t) {
        File directory = new File("C:\\ejemplos\\" + Calendar.getInstance().getTimeInMillis());
        directory.mkdir();

        try (FileWriter osXinT042 = new FileWriter(directory.getAbsolutePath() + "\\5.XIN_T042PARTYADDR.csv");
                FileWriter osXinT041 = new FileWriter(directory.getAbsolutePath() + "\\6.XIN_T041PARTYDIV.csv");
                FileWriter osXinT040 = new FileWriter(directory.getAbsolutePath() + "\\7.XIN_T040PARTY.csv");
                FileWriter osXinTa1110 = new FileWriter(directory.getAbsolutePath() + "\\8.XIN_TA1110PDV_PDC.csv");) {
            List<T1110Dto> listT1110 = fileT1110.findOutlets();
            List<T041Dto> listT041Dto = fileT41.findOutlets();
            List<T040Dto> listT040 = fileT040.findOutlets();
            List<T042PartyAddrDto> list = buildFileT042.findOutlets();
            for (T042PartyAddrDto record : list) {
//                byte[] bytes = record.toString().getBytes(StandardCharsets.UTF_8.name());
//                FileUtils.writeByteArrayToFile(new File(directory.getAbsolutePath() + "\\5.XIN_T042PARTYADDR.csv"), bytes);
                //org.apache.commons.io.FileUtils.writeStringToFile(new File(directory.getAbsolutePath() + "\\5.XIN_T042PARTYADDR.csv"), record.toString(), StandardCharsets.UTF_8.name());
                osXinT042.write(record.toString());
            }
            for (T1110Dto listT11101 : listT1110) {
                //org.apache.commons.io.FileUtils.writeStringToFile(new File(directory.getAbsolutePath() + "\\8.XIN_TA1110PDV_PDC.csv"), listT11101.toString(), StandardCharsets.UTF_8.name());
                osXinTa1110.write(listT11101.toString());
            }
            for (T041Dto dto041 : listT041Dto) {
                //org.apache.commons.io.FileUtils.writeStringToFile(new File(directory.getAbsolutePath() + "\\6.XIN_T041PARTYDIV.csv"), dto041.toString(), StandardCharsets.UTF_8.name());
                osXinT041.write(dto041.toString());
            }
            for (T040Dto dtoT040 : listT040) {
                //org.apache.commons.io.FileUtils.writeStringToFile(new File(directory.getAbsolutePath() + "\\7.XIN_T040PARTY.csv"), dtoT040.toString(), StandardCharsets.UTF_8.name());
                osXinT040.write(dtoT040.toString());
            }
            LOG.log(Level.INFO, "File create successful!");
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
    }

}

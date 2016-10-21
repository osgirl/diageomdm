/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.admincontrollerweb.enums;

/**
 *
 * @author EDUARDO
 */
public enum StatusSystemMDM {

    PENDING_TMC, PENDING_KAM, PENDING_COMMERCIAL_MANAGER, PENDING_TMC_POTENTIAL, APPROVED, REJECT;

    public static StatusSystemMDM statusEngine(StatusSystemMDM statusIn, Integer idProfile) {
        ProfileEnum profile = ProfileEnum.valueOf(idProfile);
        if (ProfileEnum.TMC.equals(profile)) {
            return PENDING_COMMERCIAL_MANAGER;
        } else if (ProfileEnum.KAM.equals(profile)) {
            return PENDING_COMMERCIAL_MANAGER;
        } else if (ProfileEnum.CATDEV.equals(profile)) {
            return PENDING_TMC_POTENTIAL;
        } else if (ProfileEnum.COMMERCIAL_MANAGER.equals(profile)) {
            return statusIn;
        } else if (ProfileEnum.NAM.equals(profile)) {
            return statusIn;
        }
        return statusIn;
    }
}
